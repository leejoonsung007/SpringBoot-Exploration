package com.springboot.school.bestchoice.form;

import com.google.common.base.Objects;
import com.springboot.school.bestchoice.model.User;
import com.springboot.school.bestchoice.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;

public class RegistrationForm {

    public static ResultMsg validate (User user){

        if(StringUtils.isBlank(user.getEmail())){
            return ResultMsg.errorMsg("Email is not valid");
        }
        if(StringUtils.isBlank(user.getUsername())) {
            return ResultMsg.errorMsg("Name is not valid");
        }

        if(StringUtils.isBlank(user.getConfirmPassword()) || StringUtils.isBlank(user.getPassword())
                || !user.getPassword().equals(user.getConfirmPassword())) {
            return ResultMsg.errorMsg("password is not valid");
        }

        if(user.getPassword().length() < 6){
            return ResultMsg.errorMsg("the length of password is less than 6");
        }
        return ResultMsg.successMsg("");
    }

    public static ResultMsg validateResetPassword(String key, String password, String confirmPassword){
        if(StringUtils.isBlank(key) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)){
            return ResultMsg.errorMsg("parameters are invalid");
        }
        if(!Objects.equal(password, confirmPassword)){
            return ResultMsg.errorMsg("password and confirmed password must be the same");
        }
        return ResultMsg.successMsg("");
    }

}
