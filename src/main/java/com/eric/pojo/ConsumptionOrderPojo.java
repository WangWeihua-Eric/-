package com.eric.pojo;

import java.util.Date;

/**
 * Created by wangweihua on 2018/3/16.
 */
public class ConsumptionOrderPojo {
    private Integer id;
    private String userId;
    private String storeId;
    private String cardId;
    private String projectName;
    private Integer status;
    private Integer type;
    private Date updateTime;
    private Integer second;
    private CardPojo cardPojo;

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public CardPojo getCardPojo() {
        return cardPojo;
    }

    public void setCardPojo(CardPojo cardPojo) {
        this.cardPojo = cardPojo;
    }

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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
