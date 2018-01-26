package com.eric.pojo;

import java.util.List;

public class UserInfoPojo {
    private UserPojo userInfo;
    private List<CardPojo> timeCardList;
    private List<CardPojo> frequencyCardList;
    private List<ConsumptionDetailPojo> consumptionDetailList;
    private List<ScoreDetailPojo> scoreDetailList;

    public UserPojo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserPojo userInfo) {
        this.userInfo = userInfo;
    }

    public List<CardPojo> getTimeCardList() {
        return timeCardList;
    }

    public void setTimeCardList(List<CardPojo> timeCardList) {
        this.timeCardList = timeCardList;
    }

    public List<CardPojo> getFrequencyCardList() {
        return frequencyCardList;
    }

    public void setFrequencyCardList(List<CardPojo> frequencyCardList) {
        this.frequencyCardList = frequencyCardList;
    }

    public List<ConsumptionDetailPojo> getConsumptionDetailList() {
        return consumptionDetailList;
    }

    public void setConsumptionDetailList(List<ConsumptionDetailPojo> consumptionDetailList) {
        this.consumptionDetailList = consumptionDetailList;
    }

    public List<ScoreDetailPojo> getScoreDetailList() {
        return scoreDetailList;
    }

    public void setScoreDetailList(List<ScoreDetailPojo> scoreDetailList) {
        this.scoreDetailList = scoreDetailList;
    }
}
