package com.channelapi.web.service;

import com.channelapi.web.entity.AppInfo;
import com.channelapi.web.entity.ReportDataDay;

import java.text.ParseException;
import java.util.List;

public interface ReportDataDayService {

    List<ReportDataDay> queryByAdverterCode(String adverterCode);
    List<ReportDataDay> queryAll();
    void updateReportDataDay(List<ReportDataDay> reportDataDays);
    List<AppInfo> queryAppInfo();
    List<ReportDataDay> queryByFilter(String adverterCode, String startDate, String endDate, String appCode);
}
