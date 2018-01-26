package com.eric.controller;

import com.eric.pojo.HelloMvcPojo;
import com.eric.result.SysResult;
import com.qunar.mobile.car.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangweihua on 2017/9/29.
 */

@Controller
@RequestMapping(value = "/eric/hello")
public class HelloMvcController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/json")
    @ResponseBody
    public Object helloJson(HelloMvcPojo helloMvcPojo, HttpServletRequest request){
        logger.info("eric_hello/json_request:"+ JsonUtil.toJson(helloMvcPojo));
        SysResult sysResult = new SysResult();
        sysResult.getBstatus().setDes("Helle Json/您好 Json");
        logger.info("eric_hello/json_result:"+ JsonUtil.toJson(sysResult));
        return sysResult;
    }
}
