package com.eric.pojo;

import java.util.List;

public class AdministrationCustomerPojo {
    private List<UserPojo> userList;
    private Integer count;

    public List<UserPojo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserPojo> userList) {
        this.userList = userList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
