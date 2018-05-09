package com.channelapi.web.service.impl;

import com.channelapi.web.dao.ReportDataDayDao;
import com.channelapi.web.entity.AppInfo;
import com.channelapi.web.entity.ReportDataDay;
import com.channelapi.web.entity.User;
import com.channelapi.web.service.ReportDataDayService;
import com.channelapi.web.util.UserContext;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ReportDataDayServiceImpl implements ReportDataDayService {

    private static Logger logger = Logger.getLogger(ReportDataDayServiceImpl.class);

    private static Gson gson = new Gson();


    @Autowired
    private ReportDataDayDao reportDataDayDao;



    @Override
    public List<ReportDataDay> queryByAdverterCode(String adverterCode) {
        return reportDataDayDao.queryByAdverter(adverterCode);
    }

    @Override
    public List<ReportDataDay> queryAll() {
        return reportDataDayDao.queryAll();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void updateReportDataDay(List<ReportDataDay> reportDataDays){
        logger.info("updateReportDataDay...");
        //删除本地数据
        SimpleDateFormat sdf_Y_M_d = new SimpleDateFormat("yyyy-MM-dd");
        String bizDate = sdf_Y_M_d.format(reportDataDays.get(0).getBizDate());
        int i = reportDataDayDao.deleteByDate(bizDate);
        logger.info("delete local report data count: " + i);
        //更新数据
        int j = reportDataDayDao.insertReportDataDayBatch(reportDataDays);
        logger.info("insert local report data count: " + j);
    }

    //渠道商只能看到自己的应用 管理员可以看到所有应用
    @Override
    public List<AppInfo> queryAppInfo() {
        logger.info("queryAppInfo...");
        User user = UserContext.getUserSession();
        logger.info("current user is :" + gson.toJson(user));
        String adverterCode = "";
        if (user.getRoleId() == 2){
            adverterCode = user.getUsername();
        }
        return reportDataDayDao.queryAppInfo(adverterCode);
    }

    @Override
    public List<ReportDataDay> queryByFilter(String adverterCode, String startDate, String endDate, String appCode) {
        return reportDataDayDao.queryByFilter(adverterCode, startDate, endDate, appCode);
    }
}
