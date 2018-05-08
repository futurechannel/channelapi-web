package com.channelapi.web.service.impl;

import com.channelapi.web.entity.ReportDataDay;
import com.channelapi.web.service.ReportDataDayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gq on 2018/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring-*.xml")
public class ReportDataDayServiceImplTest {
    @Resource
    private ReportDataDayService reportDataDayService;

    @Test
    @Deprecated
    public void updateReportDataDay() throws Exception {
        List<ReportDataDay> list=new ArrayList<>();
        ReportDataDay dataDay=new ReportDataDay();
        dataDay.setAppCode("mangguo");
        dataDay.setAdverterCode("pp_001");
        dataDay.setBizDate(new Date());
        dataDay.setActiveCnt(1);
        dataDay.setCallbackCnt(1);
        dataDay.setId(1);
        dataDay.setClickCnt(1);
        list.add(dataDay);
        reportDataDayService.updateReportDataDay(list);
    }

}