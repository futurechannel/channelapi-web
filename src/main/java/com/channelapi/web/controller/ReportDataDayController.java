package com.channelapi.web.controller;

import com.channelapi.web.entity.AppInfo;
import com.channelapi.web.entity.ReportDataDay;
import com.channelapi.web.entity.User;
import com.channelapi.web.service.ChannelApiRemoteService;
import com.channelapi.web.service.ReportDataDayService;
import com.channelapi.web.service.impl.ReportDataDayServiceImpl;
import com.channelapi.web.util.FileUtil;
import com.channelapi.web.util.UserContext;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ReportDataDayController {

    private static Logger logger = Logger.getLogger(ReportDataDayController.class);

    private static Gson gson = new Gson();

    @Autowired
    private ReportDataDayService reportDataDayService;

    @Autowired
    private ChannelApiRemoteService channelApiRemoteService;

    @RequestMapping(value = "/queryByFilter")
    public ModelAndView queryByFilter(HttpServletRequest request, HttpServletResponse response){
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

        Map<String, Object> result = new HashMap<>();
        List<ReportDataDay> reportDataDays  = getReportDataDays(startDate, endDate, appCode);
        result.put("reportDataDays", reportDataDays);
        result.put("startDate", startDate);
        result.put("endDate", endDate);
        result.put("appCode", appCode);
        ModelAndView modelAndView =  new ModelAndView("reportDataDay");
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    private List<ReportDataDay> getReportDataDays(String startDate, String endDate, String appCode){
        User user = UserContext.getUserSession();
        String adverterCode = "";
        if (user.getRoleId() == 2){
            adverterCode = user.getUsername();
        }
        return reportDataDayService.queryByFilter(adverterCode, startDate, endDate, appCode);
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


    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String appCode = request.getParameter("appCode");

        List<ReportDataDay> reportDataDays  = getReportDataDays(startDate, endDate, appCode);
        logger.info("Export Excel Data: " + gson.toJson(reportDataDays));
        try {
            //使用poi下载文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建sheet
            HSSFSheet sheet1 = workbook.createSheet("渠道统计信息");
            //创建row信息
            HSSFRow row = sheet1.createRow(0);
            //创建单元格头标
            row.createCell(0).setCellValue("日期");
            row.createCell(1).setCellValue("渠道号");
            row.createCell(2).setCellValue("应用号");
            row.createCell(3).setCellValue("点击量");
            row.createCell(4).setCellValue("激活量");
            row.createCell(5).setCellValue("回调量");

            //获取数据
            if (reportDataDays != null && reportDataDays.size() > 0) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                for (ReportDataDay reportDataDay : reportDataDays) {
                    int lastRowNum = sheet1.getLastRowNum();
                    HSSFRow lastRow = sheet1.createRow(lastRowNum + 1);
                    lastRow.createCell(0).setCellValue(sdf.format(reportDataDay.getBizDate()));
                    lastRow.createCell(1).setCellValue(reportDataDay.getAdverterCode());
                    lastRow.createCell(2).setCellValue(reportDataDay.getAppCode());
                    lastRow.createCell(3).setCellValue(reportDataDay.getClickCnt());
                    lastRow.createCell(4).setCellValue(reportDataDay.getActiveCnt());
                    lastRow.createCell(5).setCellValue(reportDataDay.getCallbackCnt());
                }
            }
            //设置文件名
            String filename = "渠道数据统计表.xls";
            //设置文件输出头
            response.setHeader("Content-Disposition", "attachment;filename="+ FileUtil.encodeDownloadFileName(filename, request.getHeader("user-agent")));
            //设置文件类型servletAction.getMine
            ServletContext servletContext = request.getServletContext();
            response.setContentType(servletContext.getMimeType(filename));
            //下载输出流
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            logger.error("exportExcel error", e);
        }
    }
}
