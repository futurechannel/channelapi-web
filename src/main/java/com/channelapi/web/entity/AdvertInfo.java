package com.channelapi.web.entity;


import java.math.BigDecimal;

public class AdvertInfo {

    private String adverterCode;
    private String appCode;
    private String adverterName;
    private int balanceRatio;
    private String comeFrom;
    private BigDecimal callBackRateUpperLimit;
    private BigDecimal callBackRateLowerLimit;
    private BigDecimal callBackRate;
    private String warningEmail;

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

    public BigDecimal getCallBackRate() {
        return callBackRate;
    }

    public void setCallBackRate(BigDecimal callBackRate) {
        this.callBackRate = callBackRate;
    }

    public String getWarningEmail() {
        return warningEmail;
    }

    public void setWarningEmail(String warningEmail) {
        this.warningEmail = warningEmail;
    }

    public BigDecimal getCallBackRateUpperLimit() {
        return callBackRateUpperLimit;
    }

    public void setCallBackRateUpperLimit(BigDecimal callBackRateUpperLimit) {
        this.callBackRateUpperLimit = callBackRateUpperLimit;
    }

    public BigDecimal getCallBackRateLowerLimit() {
        return callBackRateLowerLimit;
    }

    public void setCallBackRateLowerLimit(BigDecimal callBackRateLowerLimit) {
        this.callBackRateLowerLimit = callBackRateLowerLimit;
    }

    @Override
    public String toString() {
        return "AdvertInfo{" +
                "adverterCode='" + adverterCode + '\'' +
                ", appCode='" + appCode + '\'' +
                ", adverterName='" + adverterName + '\'' +
                ", balanceRatio=" + balanceRatio +
                ", comeFrom='" + comeFrom + '\'' +
                ", callBackRateUpperLimit=" + callBackRateUpperLimit +
                ", callBackRateLowerLimit=" + callBackRateLowerLimit +
                ", callBackRate=" + callBackRate +
                ", warningEmail='" + warningEmail + '\'' +
                '}';
    }
}
