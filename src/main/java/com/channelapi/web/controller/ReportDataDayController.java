package com.channelapi.web.controller;

import com.channelapi.web.entity.AppInfo;
import com.channelapi.web.entity.ReportDataDay;
import com.channelapi.web.entity.User;
import com.channelapi.web.service.ChannelApiRemoteService;
import com.channelapi.web.service.ReportDataDayService;
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

    @RequestMapping(value = "/reportDataDay")
    public ModelAndView advertInfo(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("reportDataDay");
        modelAndView.addObject("roleId", UserContext.getUserSession().getRoleId());
        return modelAndView;
    }

    @RequestMapping(value = "/queryByFilter")
    public Map<String, Object> queryByFilter(HttpServletRequest request, HttpServletResponse response){
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String appCode = request.getParameter("appCode");

        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> reportDataDays  = getReportDataDays(startDate, endDate, appCode);
        result.put("rows", reportDataDays);
        return result;
    }

    private List<Map<String, Object>> getReportDataDays(String startDate, String endDate, String appCode){
        User user = UserContext.getUserSession();
        String adverterCode = "";
        List<Map<String, Object>> results = new ArrayList<>();
        if (user.getRoleId() == 2){
            adverterCode = user.getUsername();
        }
        //获取所有字段数据
        List<ReportDataDay> reportDataDays = reportDataDayService.queryByFilter(adverterCode, startDate, endDate, appCode);
        for (ReportDataDay reportDataDay : reportDataDays){
            Map<String, Object> map = new HashMap<>();
            map.put("bizDate", reportDataDay.getBizDate());
            map.put("adverterCode", reportDataDay.getAdverterCode());
            map.put("appCode", reportDataDay.getAppCode());
            map.put("clickCnt", reportDataDay.getClickCnt());
            map.put("callbackCnt", reportDataDay.getCallbackCnt());
            //管理员才能看到所有字段 对渠道商过滤部分字段
            if (user.getRoleId() == 1){
                map.put("activeCnt", reportDataDay.getActiveCnt());
            }
            results.add(map);
        }
        return results;
    }


    @RequestMapping(value = "/pull", method = RequestMethod.GET)
    public Map<String, Object> pullReportDataDay(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<>();
        if (UserContext.getUserSession().getRoleId() != 1){
            result.put("msg", "无访问权限!");
            return result;
        }
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
            result.put("size", reportDataDays == null?0:reportDataDays.size());
            result.put("data", gson.toJson(reportDataDays));
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
        User user = UserContext.getUserSession();

        List<Map<String, Object>> reportDataDays  = getReportDataDays(startDate, endDate, appCode);
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
            if (user.getRoleId() == 1){
                row.createCell(4).setCellValue("激活量");
                row.createCell(5).setCellValue("回调量");
            }else if (user.getRoleId() == 2){
                row.createCell(4).setCellValue("回调量");
            }

            //获取数据
            if (reportDataDays != null && reportDataDays.size() > 0) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                for (Map reportDataDay : reportDataDays) {
                    int lastRowNum = sheet1.getLastRowNum();
                    HSSFRow lastRow = sheet1.createRow(lastRowNum + 1);
                    lastRow.createCell(0).setCellValue(sdf.format(reportDataDay.get("bizDate")));
                    lastRow.createCell(1).setCellValue(String.valueOf(reportDataDay.get("adverterCode")));
                    lastRow.createCell(2).setCellValue(String.valueOf(reportDataDay.get("appCode")));
                    lastRow.createCell(3).setCellValue(String.valueOf(reportDataDay.get("clickCnt")));
                    if (user.getRoleId() == 1){
                        lastRow.createCell(4).setCellValue(String.valueOf(reportDataDay.get("activeCnt")));
                        lastRow.createCell(5).setCellValue(String.valueOf(reportDataDay.get("callbackCnt")));
                    }else if (user.getRoleId() == 2){
                        lastRow.createCell(4).setCellValue(String.valueOf(reportDataDay.get("callbackCnt")));
                    }
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
