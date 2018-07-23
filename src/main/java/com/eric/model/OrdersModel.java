package com.eric.model;

import java.util.Date;

public class OrdersModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String userId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '用户id',
    private String storeId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private String renewProject;//` varchar(20) NOT NULL DEFAULT '' COMMENT '项目',
    private String giveProject;//` varchar(20) NOT NULL DEFAULT '' COMMENT '赠送项目',
    private Integer courseOfTreatment;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '次数',
    private Integer day;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '天数',
    private Double price;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '钱',
    private Double point;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '积分',
    private Integer type;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '类型',
    private String technicianId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '技师id',
    private String reason;//` varchar(20) NOT NULL DEFAULT '' COMMENT '备注',
    private Integer status;//` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private Double shopPoint;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '商城积分',
    private String giveCardId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '赠送卡id',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getRenewProject() {
        return renewProject;
    }

    public void setRenewProject(String renewProject) {
        this.renewProject = renewProject;
    }

    public String getGiveProject() {
        return giveProject;
    }

    public void setGiveProject(String giveProject) {
        this.giveProject = giveProject;
    }

    public Integer getCourseOfTreatment() {
        return courseOfTreatment;
    }

    public void setCourseOfTreatment(Integer courseOfTreatment) {
        this.courseOfTreatment = courseOfTreatment;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getShopPoint() {
        return shopPoint;
    }

    public void setShopPoint(Double shopPoint) {
        this.shopPoint = shopPoint;
    }

    public String getGiveCardId() {
        return giveCardId;
    }

    public void setGiveCardId(String giveCardId) {
        this.giveCardId = giveCardId;
    }
}
