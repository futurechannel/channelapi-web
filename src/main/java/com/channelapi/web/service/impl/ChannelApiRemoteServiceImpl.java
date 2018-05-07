package com.channelapi.web.service.impl;

import com.channelapi.web.constant.Constant;
import com.channelapi.web.dao.ChannelapiRemoteDao;
import com.channelapi.web.entity.ReportDataDay;
import com.channelapi.web.service.ChannelApiRemoteService;
import com.channelapi.web.util.DataSourceContext;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ChannelApiRemoteServiceImpl implements ChannelApiRemoteService {

    private static Logger logger = Logger.getLogger(ChannelApiRemoteServiceImpl.class);

    private static Gson gson = new Gson();

    @Autowired
    private ChannelapiRemoteDao channelapiRemoteDao;

    @Override
    public List<ReportDataDay> pullReportDataDays(String bizDate) throws Exception {
        logger.info("pullReportDataDays..." + bizDate);

        SimpleDateFormat sdf_Y_M_d = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf_YMd = new SimpleDateFormat("yyyyMMdd");
        String bizDate_Y_M_d = sdf_Y_M_d.format(sdf_Y_M_d.parse(bizDate));

        //后一天
        Calendar calendar = Calendar.getInstance();
        Date bizDateParse = sdf_Y_M_d.parse(bizDate);
        calendar.setTime(bizDateParse);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String nextDate_Y_M_d = sdf_Y_M_d.format(calendar.getTime());

        //修改格式
        String bizDateYMd = sdf_YMd.format(sdf_Y_M_d.parse(bizDate)) ;

        //切换为远程数据源
        DataSourceContext.setDataSourceType(Constant.DATA_SOURCE_SERVICE);
        logger.info("change datasource to service: " + DataSourceContext.getDataSourceType());
        //查询远程服务数据
        List<ReportDataDay> reportDataDays = channelapiRemoteDao.queryReportDataDays(bizDate_Y_M_d, nextDate_Y_M_d, bizDateYMd);
        logger.info("service report data query result: " + gson.toJson(reportDataDays));
        DataSourceContext.clearDataSourceType();

        return reportDataDays;
    }
}
