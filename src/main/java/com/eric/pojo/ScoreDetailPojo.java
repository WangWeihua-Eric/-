package com.eric.pojo;

import java.util.Date;

public class ScoreDetailPojo {
    private String projectName;
    private Double scoreChange;
    private Date time;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getScoreChange() {
        return scoreChange;
    }

    public void setScoreChange(Double scoreChange) {
        this.scoreChange = scoreChange;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
