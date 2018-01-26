package com.eric.pojo;

import java.util.Date;

public class TechnicianPojo {
    private String name;
    private String phone;
    private String level;
    private String grad;
    private Double fee;
    private Double totalFee;
    private Double orderFee;
    private Double totalOrderFee;
    private Date inTime;

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Double getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Double orderFee) {
        this.orderFee = orderFee;
    }

    public Double getTotalOrderFee() {
        return totalOrderFee;
    }

    public void setTotalOrderFee(Double totalOrderFee) {
        this.totalOrderFee = totalOrderFee;
    }
}
