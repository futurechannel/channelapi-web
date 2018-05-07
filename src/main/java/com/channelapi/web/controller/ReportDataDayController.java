package com.channelapi.web.controller;

import com.channelapi.web.entity.AppInfo;
import com.channelapi.web.entity.ReportDataDay;
import com.channelapi.web.entity.User;
import com.channelapi.web.service.ChannelApiRemoteService;
import com.channelapi.web.service.ReportDataDayService;
import com.channelapi.web.service.impl.ReportDataDayServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ReportDataDayController {

    private static Logger logger = Logger.getLogger(ReportDataDayController.class);

    @Autowired
    private ReportDataDayService reportDataDayService;

    @Autowired
    private ChannelApiRemoteService channelApiRemoteService;

    @RequestMapping(value = "/queryByFilter")
    public ModelAndView queryByFilter(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("currentUser");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String appCode = request.getParameter("appCode");

        //从昨天开始查询
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        String bizDate = sdf.format(calendar.getTime());
        startDate =  "lastDay".equals(request.getParameter("from"))?bizDate: startDate;

        String adverterCode = "";
        if (user.getRoleId() == 2){
            adverterCode = user.getUsername();
        }
        Map<String, Object> result = new HashMap<>();
        List<ReportDataDay> reportDataDays = reportDataDayService.queryByFilter(adverterCode, startDate, endDate, appCode);
        result.put("reportDataDays", reportDataDays);
        result.put("startDate", startDate);
        result.put("endDate", endDate);
        result.put("appCode", appCode);
        ModelAndView modelAndView =  new ModelAndView("reportDataDay");
        modelAndView.addObject("result", result);
        return modelAndView;
    }


    @RequestMapping(value = "/pullRemoteReportDataDay", method = RequestMethod.GET)
    public Map<String, Object> pullReportDataDay(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<>();
        String bizDate = request.getParameter("bizDate");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (bizDate == null){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                bizDate = sdf.format(calendar.getTime());
            }else {
                bizDate = sdf.format(sdf.parse(bizDate));
            }
            List<ReportDataDay> reportDataDays  =  channelApiRemoteService.pullReportDataDays(bizDate);
            reportDataDayService.updateReportDataDay(reportDataDays);

            result.put("msg", "success");
        } catch (Exception e) {
            result.put("error", e.getMessage());
            logger.error("pullRemoteReportDataDay", e);
        }
        return result;
    }


    @RequestMapping(value = "/queryAppCode")
    public List<AppInfo> queryAppCode(HttpServletRequest request, HttpServletResponse response){
        List<AppInfo> appInfos = reportDataDayService.queryAppInfo();
        return appInfos;
    }

}
