package com.eric.service;

import com.eric.pojo.CompanyPojo;
import com.eric.pojo.EnterPojo;
import com.eric.result.SysResult;

/**
 * Created by wangweihua on 2017/10/19.
 */
public interface IEnterService {
    SysResult.BStatus signUp(EnterPojo enterPojo);

    /**
     * 登录
     */
    CompanyPojo login(EnterPojo enterPojo);
}
