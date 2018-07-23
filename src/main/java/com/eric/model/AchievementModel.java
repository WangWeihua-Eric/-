package com.eric.model;

import java.util.Date;

public class AchievementModel {
    private Integer id;
    private String cardId;// 卡id
    private Integer cardType; //卡类型
    private String projectName;//项目名
    private String projectId;//项目id
    private Double price;//实收金额
    private String userId;//客户id
    private String userName;//客户名
    private String userPhone;//客户手机
    private String reason;//备注
    private String techniciansId;//技师id
    private String type;//类型
    private String storeId;//门店id
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String techniciansName;//技师名字
    private String remarks;//说明
    private String evaluationRemarks;//星级说明
    private Integer evaluation;//星级

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTechniciansId() {
        return techniciansId;
    }

    public void setTechniciansId(String techniciansId) {
        this.techniciansId = techniciansId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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

    public String getTechniciansName() {
        return techniciansName;
    }

    public void setTechniciansName(String techniciansName) {
        this.techniciansName = techniciansName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEvaluationRemarks() {
        return evaluationRemarks;
    }

    public void setEvaluationRemarks(String evaluationRemarks) {
        this.evaluationRemarks = evaluationRemarks;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }
}
