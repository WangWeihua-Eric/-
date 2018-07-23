package com.eric.model;

import java.util.Date;

public class UsersModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String userId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '用户id',
    private String storeId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '门店id',
    private String phone;//` varchar(20) NOT NULL DEFAULT '' COMMENT '用户手机号',
    private String userName;//` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '用户姓名',
    private String sex;//` varchar(4) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '性别',
    private Date birthdayTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '用户生日',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private String address;//` varchar(20) NOT NULL DEFAULT '' COMMENT '地址',

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthdayTime() {
        return birthdayTime;
    }

    public void setBirthdayTime(Date birthdayTime) {
        this.birthdayTime = birthdayTime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
