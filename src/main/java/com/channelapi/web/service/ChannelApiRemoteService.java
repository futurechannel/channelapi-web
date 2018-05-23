package com.channelapi.web.service;

import com.channelapi.web.entity.AdvertInfo;
import com.channelapi.web.entity.IdfaInfo;
import com.channelapi.web.entity.ReportDataDay;

import java.util.List;

public interface ChannelApiRemoteService {
    List<ReportDataDay> pullReportDataDays(String bizDate) throws Exception;

    List<AdvertInfo> queryAdvertInfo();

    void insertAdvertInfo(String adverterCode, String appCode, String adverterName, int balanceRatio, String comeFrom);

    int deleteAdvertInfo(String adverterCode, String appCode);

    int updateAdvertInfo(String adverterCodeOld, String appCodeOld, String adverterCode, String appCode, String adverterName, int balanceRatio, String comeFrom);

    IdfaInfo queryIdfaInfo(String idfa, String app_code, String bizDate) throws Exception;
}
