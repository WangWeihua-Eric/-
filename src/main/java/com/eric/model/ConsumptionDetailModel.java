package com.eric.model;

import java.util.Date;

public class ConsumptionDetailModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String userId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '用户id',
    private String storeId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private String cardId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '卡id',
    private String projectName;//` varchar(20) NOT NULL DEFAULT '' COMMENT '项目id',
    private Integer day;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '限制',
    private Integer process;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '进度',
    private Double price;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '实收价格',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private String technicianId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '技师id',
    private Double onceFee;//` double(10,2) NOT NULL COMMENT '技师手工费',
    private String reason;//` varchar(20) NOT NULL DEFAULT '' COMMENT '备注',
    private String orderId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '订单id',
    private String remarks;//` varchar(20) NOT NULL DEFAULT '' COMMENT '说明',
    private Integer evaluation;//` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '星级',
    private String evaluationRemarks;//` varchar(20) NOT NULL DEFAULT '' COMMENT '星级说明',

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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public Double getOnceFee() {
        return onceFee;
    }

    public void setOnceFee(Double onceFee) {
        this.onceFee = onceFee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluationRemarks() {
        return evaluationRemarks;
    }

    public void setEvaluationRemarks(String evaluationRemarks) {
        this.evaluationRemarks = evaluationRemarks;
    }
}
