package com.channelapi.web.job;

import com.channelapi.web.annotation.DynamicDataSourceAnnotation;
import com.channelapi.web.constant.Constant;
import com.channelapi.web.dao.ChannelapiRemoteDao;
import com.channelapi.web.entity.AdvertInfo;
import com.channelapi.web.util.EmailUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
@DynamicDataSourceAnnotation
public class WarningJob {

    private static Logger logger = Logger.getLogger(WarningJob.class);
    private static final String from = "15240025221@163.com";

    @Autowired
    private ChannelapiRemoteDao channelapiRemoteDao;

    @Scheduled(cron= "0 0/1 * * * ?")
    @DynamicDataSourceAnnotation(dataSource = Constant.DATA_SOURCE_SERVICE)
    public void callbackRateWarningJob(){
        logger.info("=======callbackRateWarningJob start==========");
        try {
            Date curDate = new Date();
            String tableDate = DateFormatUtils.format(curDate, "yyyyMMdd");
            Date last30MinDate = DateUtils.addMinutes(curDate, -30);
            List<AdvertInfo> advertInfos = channelapiRemoteDao.getCallbackRateWarnings(tableDate, new Timestamp(last30MinDate.getTime()), new Timestamp(curDate.getTime()));
            logger.info("getCallbackRateWarnings result : " + advertInfos);
            if (advertInfos != null && advertInfos.size() > 0) {
                //发送预警邮件
                StringBuilder content = new StringBuilder();
                for (AdvertInfo advertInfo : advertInfos) {
                    content.append("渠道:").append(advertInfo.getAdverterCode()).append(",");
                    content.append("应用:").append(advertInfo.getAppCode()).append(",");
                    content.append("回调率阈值:").append(advertInfo.getCallBackRateLimit()).append(",");
                    content.append("回调率:").append(advertInfo.getCallBackRate());
                    String toEmails = advertInfo.getWarningEmail();
                    EmailUtil.sendEmail(from, toEmails, "回调率过高预警", content.toString());
                }
            }
            logger.info("=======callbackRateWarningJob end==========");
        } catch (Exception e) {
            logger.error("callbackRateWarningJob exception : ", e);
        }
    }
}
