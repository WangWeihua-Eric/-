package com.eric.pojo;

import java.util.Date;

public class UserPojo {
    private String userId;
    private String phoneNumber;
    private String name;
    private Date birthday;
    private Integer age;
    private String sex;
    private Date babyBirthday;
    private String address;
    private Double balance;
    private Integer consumptionFalg; //0：有到店，1：7日无到店核销记录
    private Integer birthdayFlag; //0：生日还早，1：7日生日
    private Integer birthdayDiff;
    private String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getBirthdayDiff() {
        return birthdayDiff;
    }

    public void setBirthdayDiff(Integer birthdayDiff) {
        this.birthdayDiff = birthdayDiff;
    }

    public Integer getConsumptionFalg() {
        return consumptionFalg;
    }

    public void setConsumptionFalg(Integer consumptionFalg) {
        this.consumptionFalg = consumptionFalg;
    }

    public Integer getBirthdayFlag() {
        return birthdayFlag;
    }

    public void setBirthdayFlag(Integer birthdayFlag) {
        this.birthdayFlag = birthdayFlag;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getBabyBirthday() {
        return babyBirthday;
    }

    public void setBabyBirthday(Date babyBirthday) {
        this.babyBirthday = babyBirthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
