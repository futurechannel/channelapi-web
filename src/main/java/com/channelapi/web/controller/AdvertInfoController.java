package com.channelapi.web.controller;

import com.channelapi.web.service.ChannelApiRemoteService;
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
import java.util.HashMap;
import java.util.Map;


@RestController
public class AdvertInfoController {
    public static final Logger logger = Logger.getLogger(AdvertInfoController.class);

    @Autowired
    private ChannelApiRemoteService channelApiRemoteService;

    @RequestMapping(value = "/advertInfo")
    public ModelAndView advertInfo(HttpServletRequest request, HttpServletResponse response){
        if (UserContext.getUserSession().getRoleId() != 1){
            return new ModelAndView("error").addObject("errorMsg", "无权限访问");
        }
        ModelAndView modelAndView = new ModelAndView("advertInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/queryAdvertInfo")
    public Map<String, Object> queryAdvertInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        if (UserContext.getUserSession().getRoleId() != 1){
            map.put("rows", null);
            map.put("msg", "无访问权限!");
            return map;
        }
        map.put("rows", channelApiRemoteService.queryAdvertInfo());
        return map;
    }

    @RequestMapping(value = "/insertAdvertInfo", method = RequestMethod.POST)
    public Map<String, Object> insertAdvertInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        if (UserContext.getUserSession().getRoleId() != 1){
            map.put("msg", "无权限访问!");
            return map;
        }

        String adverterCode = request.getParameter("adverterCode");
        String appCode = request.getParameter("appCode");
        String adverterName = request.getParameter("adverterName");
        String balanceRatio = request.getParameter("balanceRatio");
        String comeFrom = request.getParameter("comeFrom");

        if (StringUtils.isEmpty(adverterCode.trim()) || StringUtils.isEmpty(appCode.trim()) || StringUtils.isEmpty(balanceRatio.trim())){
            map.put("msg", "参数不能为空！");
            return map;
        }

        channelApiRemoteService.insertAdvertInfo(adverterCode, appCode, adverterName, Integer.parseInt(balanceRatio), comeFrom);
        map.put("msg", "添加成功");
        map.put("code", 1);
        return map;
    }


    @RequestMapping(value = "/deleteAdvertInfo", method = RequestMethod.POST)
    public Map<String, Object> deleteAdvertInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        if (UserContext.getUserSession().getRoleId() != 1){
            map.put("msg", "无权限访问!");
            return map;
        }

        String adverterCode = request.getParameter("adverterCode");
        String appCode = request.getParameter("appCode");

        int i = channelApiRemoteService.deleteAdvertInfo(adverterCode, appCode);
        map.put("msg", "删除成功");
        map.put("code", 1);
        return map;
    }


    @RequestMapping(value = "/updateAdvertInfo", method = RequestMethod.POST)
    public Map<String, Object> updateAdvertInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        if (UserContext.getUserSession().getRoleId() != 1){
            map.put("msg", "无权限访问!");
            return map;
        }

        String adverterCodeOld = request.getParameter("adverterCodeOld");
        String appCodeOld = request.getParameter("appCodeOld");
        String adverterCode = request.getParameter("adverterCode");
        String appCode = request.getParameter("appCode");
        String adverterName = request.getParameter("adverterName");
        String balanceRatio = request.getParameter("balanceRatio");
        String comeFrom = request.getParameter("comeFrom");

        if (StringUtils.isEmpty(adverterCode.trim()) || StringUtils.isEmpty(appCode.trim()) || StringUtils.isEmpty(balanceRatio.trim())){
            map.put("msg", "参数不能为空！");
            return map;
        }

        int i = channelApiRemoteService.updateAdvertInfo(adverterCodeOld, appCodeOld, adverterCode, appCode, adverterName, Integer.parseInt(balanceRatio), comeFrom);
        map.put("msg", "更新成功");
        map.put("code", 1);
        return map;
    }
}
