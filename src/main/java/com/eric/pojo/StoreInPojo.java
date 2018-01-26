package com.eric.pojo;

import java.util.Date;

public class StoreInPojo {
    private String oneName;//	一级类名
    private String twoName;//	二级类名
    private Integer count;//		数量
    private Date endTime;//	过期时间
    private Double fee;//		价格

    public String getOneName() {
        return oneName;
    }

    public void setOneName(String oneName) {
        this.oneName = oneName;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
