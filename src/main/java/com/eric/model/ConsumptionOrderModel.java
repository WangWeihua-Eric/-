package com.eric.model;

import java.util.Date;

public class ConsumptionOrderModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String userId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '用户id',
    private String storeId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private String cardId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '卡id',
    private String projectName;//` varchar(20) NOT NULL DEFAULT '' COMMENT '项目名',
    private Integer status;//` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
    private Integer type;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '1时间卡2疗程卡',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private Integer evaluation;//` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '星级',
    private String remarks;//` varchar(20) NOT NULL DEFAULT '' COMMENT '说明',

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
