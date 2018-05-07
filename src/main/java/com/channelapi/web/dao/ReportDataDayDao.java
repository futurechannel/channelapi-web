package com.channelapi.web.dao;

import com.channelapi.web.entity.AppInfo;
import com.channelapi.web.entity.ReportDataDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportDataDayDao {
    List<ReportDataDay> queryByAdverter(String adverterCode);
    List<ReportDataDay> queryAll();
    int insertReportDataDayBatch(List<ReportDataDay> reportDataDays);
    int deleteByDate(String bizDate);
    List<AppInfo> queryAppInfo(@Param("adverterCode") String adverterCode);
    List<ReportDataDay> queryByFilter(@Param("adverterCode") String adverterCode, @Param("startDate") String startDate,@Param("endDate") String endDate,@Param("appCode") String appCode);
}
