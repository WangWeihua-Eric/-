package com.eric.model;

import java.util.Date;

public class CompanyModel {
    private Integer id;//` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    private String userName;//` varchar(20) NOT NULL DEFAULT '' COMMENT '公司名字',
    private String userId;//` varchar(20) NOT NULL DEFAULT '' COMMENT '公司账号',
    private String address;//` varchar(20) NOT NULL DEFAULT '' COMMENT '公司地址',
    private String phone;//` varchar(20) NOT NULL DEFAULT '' COMMENT '公司电话',
    private String userPassword;//` varchar(20) NOT NULL DEFAULT '' COMMENT '密码',
    private Date createTime;//` timestamp NOT NULL DEFAULT '1980-01-01 00:00:00' COMMENT '创建时间',
    private Date updateTime;//` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private Integer userLevel;//` int(2) unsigned NOT NULL DEFAULT '2' COMMENT '公司账号等级',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}
