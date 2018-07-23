package com.eric.model;

import java.util.Date;

public class TechniciansModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String userId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '技师id',
    private String storeId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private String phone;//` varchar(20) NOT NULL DEFAULT '' COMMENT '技师手机号',
    private String name;//` varchar(20) NOT NULL DEFAULT '' COMMENT '技师姓名',
    private String grade;//` varchar(20) NOT NULL DEFAULT '' COMMENT '技师等级',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private String education;//` varchar(20) NOT NULL DEFAULT '' COMMENT '学历',
    private Date inTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '入职时间',
    private Integer status;//` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0是没删除1是删除',

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
