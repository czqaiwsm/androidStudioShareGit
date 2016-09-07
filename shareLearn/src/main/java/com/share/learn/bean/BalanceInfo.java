package com.share.learn.bean;

import java.io.Serializable;

/**
 * @author czq
 * @desc 请用一句话描述它
 * @date 16/6/7
 */
public class BalanceInfo implements Serializable{

    private String balance;//": 1199,
    private String alipay;//" : "",
    private String realName;//": ""
    private String   wxpay  = "";
    private String   wxName = "";

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWxpay() {
        return wxpay;
    }

    public void setWxpay(String wxpay) {
        this.wxpay = wxpay;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }
}
