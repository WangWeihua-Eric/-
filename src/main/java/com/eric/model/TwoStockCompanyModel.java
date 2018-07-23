package com.eric.model;

import java.util.Date;

public class TwoStockCompanyModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String oneId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '一级库存id',
    private String projectName;//` varchar(64) NOT NULL DEFAULT '' COMMENT '库存名',
    private String companyId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '公司id',
    private Double price;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '入库价格',
    private Double outPrice;//` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '出库价格',
    private Integer count;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '剩余',
    private Date endTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '过期时间',
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
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
}
