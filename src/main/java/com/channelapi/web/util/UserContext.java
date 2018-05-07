package com.channelapi.web.util;

import com.channelapi.web.constant.Constant;
import com.channelapi.web.entity.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;

public class UserContext implements Serializable {

    public static User getUserSession() {
        return (User) RequestContextHolder.getRequestAttributes().getAttribute(Constant.USER_KEY, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 将用户登录对象绑定到当前线程
     *
     *
     */
    public static void setUserSession(User user) {
        RequestContextHolder.getRequestAttributes().setAttribute(Constant.USER_KEY, user, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 将用户登录对象从当前线程销毁
     */
    public static void removeUserSession() {
        RequestContextHolder.getRequestAttributes().removeAttribute(Constant.USER_KEY,RequestAttributes.SCOPE_REQUEST);
    }
}
