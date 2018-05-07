package com.channelapi.web.interceptor;

import com.channelapi.web.constant.Constant;
import com.channelapi.web.entity.User;
import com.channelapi.web.util.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求的URL
        String url = httpServletRequest.getRequestURI();

        //登录界面不拦截
        if(url.contains("loginPage")|| url.contains("loginCheck")){
            return true;
        }

        //获取Session
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute(Constant.USER_KEY);
        if(user != null){
            UserContext.setUserSession(user);
            return true;
        }

        httpServletRequest.getRequestDispatcher("/loginPage").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
