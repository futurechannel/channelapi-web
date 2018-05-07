package com.channelapi.web.dao;

import com.channelapi.web.entity.ReportDataDay;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ChannelapiRemoteDao {
    List<ReportDataDay> queryReportDataDays(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("tableDate") String tableDate);
}
