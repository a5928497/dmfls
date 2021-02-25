package com.yukoon.dmfls.interceptors;

import com.yukoon.dmfls.Entities.User;
import com.yukoon.dmfls.Services.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        Subject subject = SecurityUtils.getSubject();
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        System.out.println("isRemembered:"+subject.isRemembered());
        if (!subject.isAuthenticated() && subject.isRemembered()){
            Object principal = subject.getPrincipal();
            System.out.println(principal);
            if (null != principal) {
                User user = userService.autoLogin(principal.toString());
                UsernamePasswordToken upToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
                upToken.setRememberMe(true);
                try {
                    //执行登录
                    subject.login(upToken);
                    flag = true;
                }catch (AuthenticationException ae){
                    System.out.println("没有权限请先登陆");
                    httpServletRequest.setAttribute("msg","没有权限请先登陆");
                    httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest,httpServletResponse);
                    return false;
                }
            }
        }
        else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
