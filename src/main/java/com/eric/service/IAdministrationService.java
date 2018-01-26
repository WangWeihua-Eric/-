package com.eric.service;

import com.eric.pojo.*;

import java.util.Date;
import java.util.List;

public interface IAdministrationService {
    /**
     * 获取客户列表
     */
    AdministrationCustomerPojo getUserListWithPage(String userId, Integer page);

    /**
     * 添加客户信息
     */
    void addUserInfo(String name, String phone, Date birthday, Date babyBirthday, String sex, String userId);

    /**
     * 获取项目首页信息
     */
    AdministrationProjectPojo getAdministrationProject(String userId);

    /**
     * 获取技师列表
     */
    List<AdministrationTechnicianPojo> getTechnicianList(String storeId);

    /**
     * 获取二级项目列表
     */
    List<ProjectTwoPojo> getprojectTwoPojoList(String storeId, String projectId);
}
