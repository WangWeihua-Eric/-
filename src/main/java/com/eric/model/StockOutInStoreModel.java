package com.eric.model;

import java.util.Date;

public class StockOutInStoreModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String oneId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '一级库存id',
    private String oneName;//` varchar(20) NOT NULL DEFAULT '' COMMENT '一级分类名',
    private String twoId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '二级库存id',
    private String twoName;//` varchar(20) NOT NULL DEFAULT '' COMMENT '二级分类名',
    private String operation;//` varchar(20) NOT NULL DEFAULT '' COMMENT '操作',
    private String storeId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private Double price;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
    private Integer count;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数量',
    private String reason;//` varchar(20) NOT NULL DEFAULT '' COMMENT '备注',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOneId() {
        return oneId;
    }

    public void setOneId(String oneId) {
        this.oneId = oneId;
    }

    public String getOneName() {
        return oneName;
    }

    public void setOneName(String oneName) {
        this.oneName = oneName;
    }

    public String getTwoId() {
        return twoId;
    }

    public void setTwoId(String twoId) {
        this.twoId = twoId;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
}
