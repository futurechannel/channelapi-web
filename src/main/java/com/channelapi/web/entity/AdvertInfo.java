package com.channelapi.web.entity;


public class AdvertInfo {

    private String adverterCode;
    private String appCode;
    private String adverterName;
    private int balanceRatio;
    private String comeFrom;

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

    public String getAdverterName() {
        return adverterName;
    }

    public void setAdverterName(String adverterName) {
        this.adverterName = adverterName;
    }

    public int getBalanceRatio() {
        return balanceRatio;
    }

    public void setBalanceRatio(int balanceRatio) {
        this.balanceRatio = balanceRatio;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }
}
