package com.channelapi.web.dao;

import com.channelapi.web.entity.AdvertInfo;
import com.channelapi.web.entity.IdfaInfo;
import com.channelapi.web.entity.ReportDataDay;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ChannelapiRemoteDao {
    List<ReportDataDay> queryReportDataDays(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("tableDate") String tableDate);
    List<AdvertInfo> queryAdvertInfo();
    void insertAdvertInfo(@Param("adverterCode") String adverterCode, @Param("appCode") String appCode, @Param("adverterName") String adverterName, @Param("balanceRatio") int balanceRatio, @Param("comeFrom") String comeFrom);
    int deleteAdvertInfo(@Param("adverterCode") String adverterCode, @Param("appCode") String appCode);
    int updateAdvertInfo(@Param("adverterCodeOld") String adverterCodeOld
            , @Param("appCodeOld") String appCodeOld
            , @Param("adverterCode") String adverterCode
            , @Param("appCode") String appCode
            , @Param("adverterName") String adverterName
            , @Param("balanceRatio") int balanceRatio
            , @Param("comeFrom") String comeFrom);

    IdfaInfo queryIdfaInfo(@Param("idfa") String idfa, @Param("appCode") String appCode, @Param("tableDate") String tableDate);
}
