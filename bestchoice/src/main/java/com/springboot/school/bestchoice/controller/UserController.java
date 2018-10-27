package com.springboot.school.bestchoice.controller;

import com.springboot.school.bestchoice.constant.CommonConstant;
import com.springboot.school.bestchoice.form.RegistrationForm;
import com.springboot.school.bestchoice.model.User;
import com.springboot.school.bestchoice.result.ResultMsg;
import com.springboot.school.bestchoice.service.UserService;
import com.springboot.school.bestchoice.utils.HashUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // -----------------------------------Register-----------------------------------
    @RequestMapping("accounts/register")
    public String register(User user, ModelMap modelMap){
        // get the registration page
        if(user == null || user.getUsername() == null){
            return "user/accounts/register";
        }

        //user verification
        ResultMsg resultMsg = RegistrationForm.validate(user);
        if (resultMsg.isSuccess() && userService.addUser(user)){
            modelMap.put("email", user.getEmail());
            modelMap.put("username", user.getUsername());
            return "user/accounts/registerSubmit";
        }else{
            return "redirect:/accounts/register?" + resultMsg.asUrlParams();
        }
    }

    @RequestMapping("accounts/verify")
    public String verify(String key){
        boolean result = userService.enable(key);
        if(result){
            return "redirect:/index?" + ResultMsg.successMsg("Activate successfully").asUrlParams();
        }else{
            return "redirect:/accounts/register?" + ResultMsg.errorMsg("Activation fail").asUrlParams();
        }
    }

    // -----------------------------------Login-----------------------------------
    @RequestMapping("accounts/login")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String target = request.getParameter("target");
        if(username == null || password == null){
            request.setAttribute("target", target);
            return "/user/accounts/login";
        }

        User user = userService.check(username, password);
        if(user == null){
            return "redirect:/accounts/login?" + "target=" + target +
                    "&username=" + username + "&" + ResultMsg.errorMsg("invalid username and password").asUrlParams();
        }else{
            HttpSession session = request.getSession(true);
            session.setAttribute(CommonConstant.USER_ATTRIBUTE, user);
            return StringUtils.isNoneBlank(target)? "redirect:" + target : "redirect:/index";
        }
    }

    @RequestMapping("accounts/logout")
    public String logout(javax.servlet.http.HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "redirect:/index";

    }

    //---------------------------profile page------------------------------
    @GetMapping("accounts/profile")
    public String profile(){
        return "/user/accounts/profile";
    }

    @RequestMapping("accounts/edit_profile")
    public String editProfile(HttpServletRequest request, User updateUser, ModelMap model){
        if(updateUser.getEmail() == null){
            return "/user/accounts/edit_profile";
        }
        userService.updateUser(updateUser, updateUser.getEmail());
        //put update user to session
        User query = new User();
        query.setEmail(updateUser.getEmail());
        List<User> users = userService.getUserByQuery(query);
        request.getSession(true).setAttribute(CommonConstant.USER_ATTRIBUTE, users.get(0));
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("update successfully").asUrlParams();

    }

    @RequestMapping("accounts/changePassword")
    public String changePassword(String email, String password, String newPassword, String confirmPassword, ModelMap modelMap){
        User user = userService.check(email, password);
        if(user == null || !confirmPassword.equals(newPassword)){
            return "redirect:/accounts/profile?" + ResultMsg.errorMsg("password is not correct").asUrlParams();
        }
        User updateUser = new User();
        updateUser.setPassword(HashUtils.encrypt(newPassword));
        userService.updateUser(updateUser, updateUser.getEmail());
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("change password successfully").asUrlParams();
    }

    //forget password
    @RequestMapping("accounts/remember")
    public String remember(String username, ModelMap modelmap){
        if(StringUtils.isBlank(username)){
            return "redirect:/accounts:?" + ResultMsg.errorMsg("Email cannot be empty");
        }
        userService.resetNotify(username);
        modelmap.put("email", username);
        return "/user/accounts/remember";
    }

    @RequestMapping("accounts/reset")
    public String reset(String key, ModelMap modelMap){
        String email = userService.getResetEmail(key);
        if(StringUtils.isBlank(email)){
            return "redirect:/accounts/login?" + ResultMsg.errorMsg("The link has been expired").asUrlParams();
        }
        modelMap.put("email", email);
        modelMap.put("success_key", key);
        return "/user/accounts/reset";
    }

//    @RequestMapping("accounts/resetSubmit")
//    public String resetSubmit(HttpServletRequest request, User user)

}
