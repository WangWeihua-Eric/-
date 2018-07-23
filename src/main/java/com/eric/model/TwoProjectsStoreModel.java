package com.eric.model;

import java.util.Date;

public class TwoProjectsStoreModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String oneProjectId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '一级项目id',
    private String projectName;//` varchar(64) NOT NULL DEFAULT '' COMMENT '库存名',
    private String storeId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private Double price;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '入库价格',
    private Integer count;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '剩余',
    private Date endTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '过期时间',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private String number;//` varchar(20) NOT NULL DEFAULT '' COMMENT '批号',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
