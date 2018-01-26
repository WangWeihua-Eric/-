package com.eric.dao;

import com.eric.pojo.EnterPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wangweihua on 2017/10/19.
 */
@Repository
public interface EnterDao {
    void signUp(EnterPojo enterPojo);

    /**
     *查询门店用户是否存在
     */
    int queryWithUserName(@Param("userId") String userId);

    /**
     * 查询公司用户是否存在
     */
    int queryWithUserNameOfCompany(@Param("userId") String userId);

    /**
     * 获取门店用户密码
     */
    String getPasswrodWithUserId(String userId);

    /**
     * 获取公司用户密码
     */
    String getPasswrodOfCompanyWithUserId(String userId);

    /**
     * 获取门店用户名
     */
    String getName(String userId);

    /**
     * 获取公司用户名
     */
    String getNameOfCompany(String userId);

    /**
     * 获取公司等级
     */
    Integer getLevelOfCompany(String userId);
}
