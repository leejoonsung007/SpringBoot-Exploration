package com.springboot.school.bestchoice.interceptor;

import com.springboot.school.bestchoice.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Component
public class AuthActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user =  UserContext.getUser();
        if(user == null){
            String msg = URLEncoder.encode("please login first", "utf-8");
            String target = URLEncoder.encode(request.getRequestURI().toString(), "utf-8");
            if("GET".equalsIgnoreCase(request.getMethod())){
                response.sendRedirect("/accounts/login?errorMsg=" + msg + "&target=" + target);
                return false;
            }else{
                response.sendRedirect("/accounts/login?errorMsg=" + msg);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
