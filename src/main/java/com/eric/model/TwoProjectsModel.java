package com.eric.model;

import java.util.Date;

public class TwoProjectsModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String projectId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '项目id',
    private String oneProjectId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '一级项目id',
    private String projectName;//` varchar(64) NOT NULL DEFAULT '' COMMENT '项目名',
    private String userId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private Double price;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
    private Double oncePrice;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '单次手工费',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private Integer status;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0是没删除1是删除',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOneProjectId() {
        return oneProjectId;
    }

    public void setOneProjectId(String oneProjectId) {
        this.oneProjectId = oneProjectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOncePrice() {
        return oncePrice;
    }

    public void setOncePrice(Double oncePrice) {
        this.oncePrice = oncePrice;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
