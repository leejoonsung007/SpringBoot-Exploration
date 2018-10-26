package com.springboot.school.bestchoice.controller;

import com.springboot.school.bestchoice.constant.CommonConstant;
import com.springboot.school.bestchoice.form.RegistrationForm;
import com.springboot.school.bestchoice.model.User;
import com.springboot.school.bestchoice.result.ResultMsg;
import com.springboot.school.bestchoice.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
            session.setAttribute(CommonConstant.PLAIN_USER_ATTRIBUTE, user);
            return StringUtils.isNoneBlank(target)? "redirect:" + target : "redirect:/index";
        }
    }

    @RequestMapping("accounts/logout")
    public String logout(javax.servlet.http.HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "redirect:/index";

    }
}
