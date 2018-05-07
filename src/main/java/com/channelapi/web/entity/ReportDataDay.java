package com.channelapi.web.entity;

import java.util.Date;

/**
 * Created by gq on 2018/4/11.
 */
public class ReportDataDay {


    private int id;
    private Date bizDate;
    private String adverterCode;
    private String appCode;
    private int clickCnt;
    private int callbackCnt;
    private int activeCnt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getAdverterCode() {
        return adverterCode;
    }

    public void setAdverterCode(String adverterCode) {
        this.adverterCode = adverterCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public int getClickCnt() {
        return clickCnt;
    }

    public void setClickCnt(int clickCnt) {
        this.clickCnt = clickCnt;
    }

    public int getCallbackCnt() {
        return callbackCnt;
    }

    public void setCallbackCnt(int callbackCnt) {
        this.callbackCnt = callbackCnt;
    }

    public int getActiveCnt() {
        return activeCnt;
    }

    public void setActiveCnt(int activeCnt) {
        this.activeCnt = activeCnt;
    }
}
