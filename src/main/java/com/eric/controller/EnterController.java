package com.eric.controller;

import com.eric.pojo.EnterPojo;
import com.eric.result.SysResult;
import com.eric.service.IEnterService;
import com.google.common.base.Strings;
import com.qunar.mobile.car.common.util.JsonUtil;
import com.qunar.mobile.car.qb.drivcommon.enums.CommonBaseStatus;
import com.qunar.mobile.car.qb.drivcommon.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangweihua on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/user")
public class EnterController {
    @Autowired
    private IEnterService iEnterService;

    /**
     * 登录接口
     * @param userId
     * @param userPassword
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(@RequestParam(value = "userId", required = false) String userId,
                         @RequestParam(value = "password", required = false) String userPassword,
                         @RequestParam(value = "type", required = false) Integer type,
                         HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(userPassword) || type == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(type < 1 || type > 2){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        EnterPojo enterPojo = new EnterPojo();
        enterPojo.setUserId(userId);
        enterPojo.setUserPassword(userPassword);
        enterPojo.setType(type);
        if(type == 1){
            return iEnterService.login(enterPojo).getName();
        }else {
            return iEnterService.login(enterPojo);
        }
    }
}
