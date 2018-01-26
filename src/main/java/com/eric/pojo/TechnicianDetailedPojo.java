package com.eric.pojo;

import java.util.Date;

public class TechnicianDetailedPojo {
    private String userName;
    private String projectName;
    private Double fee;
    private Date date;
    private String type;
    private String userId;
    private String projectId;
    private Integer typeTmep;

    public Integer getTypeTmep() {
        return typeTmep;
    }

    public void setTypeTmep(Integer typeTmep) {
        this.typeTmep = typeTmep;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
