package com.channelapi.web.service;

import com.channelapi.web.entity.ReportDataDay;

import java.util.List;

public interface ChannelApiRemoteService {
    List<ReportDataDay> pullReportDataDays(String bizDate) throws Exception;
}
