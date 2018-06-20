package com.channelapi.web.controller;

import com.channelapi.web.entity.IdfaInfo;
import com.channelapi.web.service.ChannelApiRemoteService;
import com.channelapi.web.util.UserContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IdfaInfoController {

    public static final Logger logger = Logger.getLogger(AdvertInfoController.class);

    @Autowired
    private ChannelApiRemoteService channelApiRemoteService;

    @RequestMapping(value = "/idfaInfo")
    public ModelAndView advertInfo(HttpServletRequest request, HttpServletResponse response){
        if (UserContext.getUserSession().getRoleId() != 1){
            return new ModelAndView("error").addObject("errorMsg", "无权限访问");
        }
        ModelAndView modelAndView = new ModelAndView("idfaInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/queryIdfaInfo")
    public Map<String, Object> queryIdfaInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        if (UserContext.getUserSession().getRoleId() != 1){
            map.put("msg", "无访问权限!");
            return map;
        }

        try {
            String idfa = request.getParameter("idfa");
            String appCode = request.getParameter("appCode");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String bizDate = sdf.format(new Date());
            IdfaInfo data = channelApiRemoteService.queryIdfaInfo(idfa, appCode, bizDate);
            if (data == null){
                map.put("msg", "没有此idfa信息");
                return map;
            }
            map.put("data", data);
            map.put("code", 1);
        } catch (Exception e) {
            logger.error("idfa信息查询错误", e);
        }
        return map;
    }
}
