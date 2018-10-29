package com.springboot.blog.BlogSystem.controller;

import com.springboot.blog.BlogSystem.domain.Blog;
import com.springboot.blog.BlogSystem.domain.User;
import com.springboot.blog.BlogSystem.service.BlogService;
import com.springboot.blog.BlogSystem.service.UserService;
import com.springboot.blog.BlogSystem.util.ConstraintViolationExceptionHandler;
import com.springboot.blog.BlogSystem.wo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import java.util.List;

@Controller
@RequestMapping("/u")
public class UserSpaceController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Value("${file.server.url}")
    private String fileServerUrl;

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username, Model model){
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "redirect:/u/" + username + "/blogs";
    }

    // get personal profile
    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username, Model model){
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("fileServerUrl", fileServerUrl);
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    // set personal profile
    @PostMapping("{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String savaProfile(@PathVariable("username") String username, User user){
        User originalUser = userService.getUserById(user.getId()).get();
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        // check whether the password has been changed
        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if(!isMatch){
            originalUser.setEncodePassword(user.getPassword());
        }
        userService.saveOrUpateUser(originalUser);
        return "redirect:/u/" + username + "/profile";
    }

    // get user avatar
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model){
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }


    //save user avatar
    @PostMapping("/{username}/avatar")
    @PreAuthorize("authentication.equals(#username)")
    public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, @RequestBody User user){
        String avatarUrl = user.getAvatar();
        User originalUser = userService.getUserById(user.getId()).get();
        originalUser.setAvatar(avatarUrl);
        userService.saveOrUpateUser(originalUser);

        return ResponseEntity.ok().body(new Response(true, "successful", avatarUrl));
    }

    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
                                   @RequestParam(value = "catalog", required = false) Long categoryId,
                                   @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                   @RequestParam(value = "async", required = false) boolean async,
                                   @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, Model model) {

        User user = (User)userDetailsService.loadUserByUsername(username);

        Page<Blog> page = null;

        if (categoryId != null && categoryId > 0){

        }else if(order.equals("hot")){
            Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize");
            Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
            page = blogService.listBlogsByTitleVoteAndSort(user, keyword, pageable);
        } else if(order.equals("new")){
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = blogService.listBlogsByTitleVote(user, keyword, pageable);
        }

        List<Blog> list = page.getContent();

        model.addAttribute("user", user);
        model.addAttribute("order", order);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("blogList", list);
        return (async == true ? "/userspace/u :: #mainContainerRepleace" : "/userspace/u");
    }

    @GetMapping("/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("username") String username, @PathVariable("id") Long id, Model model){
        User principal = null;
        Optional<Blog> blog = blogService.getBlogById(id);

        //increase the number of reading
        blogService.readingIncrease(id);

        // check whether the user is the owener
        boolean isBlogOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder
                .getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && username.equals(principal.getUsername())) {
                isBlogOwner = true;
            }
        }

        model.addAttribute("isBlogOwner", isBlogOwner);
        model.addAttribute("blogModel", blog.get());

        return "/userspace/blog";

    }

    @GetMapping("/{username}/blogs/edit")
    public ModelAndView editBlog(@PathVariable("username") String username, Model model){
        model.addAttribute("blog", new Blog(null, null, null));
        model.addAttribute("fileServerUrl", fileServerUrl);
        return new ModelAndView("/userspace/blogedit", "blogModel", model);
    }

    @PostMapping("/{username}/blogs/edit")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveBlog(@PathVariable("username") String username,
                                             @RequestBody Blog blog){

        try{
            //check whether it has been edited or created
            if(blog.getId() != null){
                Blog originalBlog = blogService.getBlogById(blog.getId()).get();
                originalBlog.setTitle(blog.getTitle());
                originalBlog.setContent(blog.getContent());
                originalBlog.setSummary(blog.getSummary());
            }else{
                User user = (User) userDetailsService.loadUserByUsername(username);
                blog.setUser(user);
                blogService.saveBlog(blog);
            }
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/u/" + username + "/blogs/" + blog.getId();
        return ResponseEntity.ok().body(new Response(true, "successful", redirectUrl));
    }

    @GetMapping("/{username}/blogs/edit/{id}")
    public ModelAndView editBlog(@PathVariable("username") String username, @PathVariable("id") Long id, Model model){
        model.addAttribute("blog", blogService);
        model.addAttribute("fileSerUrl", fileServerUrl);
        return new ModelAndView("/userspace/blogedit", "blogModel", model);
    }



    @DeleteMapping("/{username}/blogs/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> deleteBlog(@PathVariable("username") String username, @PathVariable("id") Long id){

        try{
            blogService.removeBlog(id);
        } catch(Exception e){
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }

        String redirectUrl = "/u/" + username + "/blogs";
        return ResponseEntity.ok().body(new Response(true, "successful", redirectUrl));
    }






}
