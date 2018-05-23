package com.channelapi.web.controller;

import com.channelapi.web.constant.Constant;
import com.channelapi.web.entity.User;
import com.channelapi.web.service.UserManageService;
import com.channelapi.web.util.UserContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class UserManageController {

    private static Logger logger = Logger.getLogger(UserManageController.class);

    @Autowired
    private UserManageService userManageService;


    @RequestMapping(value = "/indexPage", method = RequestMethod.GET)
    public ModelAndView indexPage(HttpServletRequest request, HttpServletResponse response) {
        User user = UserContext.getUserSession();
        List<Map<String, Object>> menus =  userManageService.queryMenuByRole(user.getRoleId());
        return new ModelAndView("indexPage");
    }

    @RequestMapping(value = "/menus")
    public List<Map<String, Object>> getMenus(HttpServletRequest request, HttpServletResponse response) {
        User user = UserContext.getUserSession();
        List<Map<String, Object>> menus =  userManageService.queryMenuByRole(user.getRoleId());
        return menus;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("loginPage");
    }

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public ModelAndView loginCheck(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                logger.info("error: username or password is empty");
                return new  ModelAndView("error").addObject("errorMsg", "用户名或密码为空");
            }

            User user = userManageService.checkUser(username, password);

            if (user == null) {
                logger.info("error: username verify fail");
                return new  ModelAndView("error").addObject("errorMsg", "用户名或密码错误");
            }

            HttpSession session = request.getSession();
            session.setAttribute(Constant.USER_KEY, user);

        }catch (Exception e){
            logger.error("loginCheck error", e);
            return new  ModelAndView("error").addObject("errorMsg", "登录验证异常");
        }
        return new  ModelAndView("redirect:indexPage");
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.USER_KEY);
        return new ModelAndView("loginPage");
    }
}
