package com.channelapi.web.job;

import com.channelapi.web.entity.ReportDataDay;
import com.channelapi.web.service.ChannelApiRemoteService;
import com.channelapi.web.service.ReportDataDayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DataPorterJob {

    private static Logger logger = Logger.getLogger(DataPorterJob.class);

    @Autowired
    private ReportDataDayService reportDataDayService;

    @Autowired
    private ChannelApiRemoteService channelApiRemoteService;

    /**
     * 定时任务，每天凌晨1点同步前一天数据
     */
    @Scheduled(cron= "0 0 1 * * ?")
    public void reportDataDayJob(){
        logger.info("=======reportDataDayJob start==========");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        try {
            String bizDate = sdf.format(calendar.getTime());
            List<ReportDataDay> reportDataDays =  channelApiRemoteService.pullReportDataDays(bizDate);
            reportDataDayService.updateReportDataDay(reportDataDays);
        } catch (Exception e) {
            logger.error("Scheduled reportDataDayJob error", e);
        }
    }
}
