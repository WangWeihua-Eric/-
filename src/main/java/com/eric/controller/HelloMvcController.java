package com.eric.controller;

import com.eric.pojo.ConvertCardDetailReqPojo;
import com.eric.pojo.HelloMvcPojo;
import com.eric.result.SysResult;
import com.eric.rpc.ConvertCardDetailRsp;
import com.eric.rpc.RPC;
import com.qunar.mobile.car.common.log.UnifyLogger;
import com.qunar.mobile.car.common.util.JsonUtil;
import com.qunar.mobile.car.qb.drivcommon.enums.CommonBaseStatus;
import com.qunar.mobile.car.qb.drivcommon.exception.BaseException;
import com.qunar.mobile.car.qb.drivcommon.model.BaseRsp;
import com.qunar.mobile.car.qb.drivcommon.utils.LogIdUtils;
import org.codehaus.jackson.type.TypeReference;
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
    @Autowired
    private RPC rpc;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/json")
    @ResponseBody
    public Object helloJson(HelloMvcPojo helloMvcPojo, HttpServletRequest request){
        logger.info("eric_hello/json_request:"+ JsonUtil.toJson(helloMvcPojo));
        SysResult sysResult = new SysResult();
        sysResult.getBstatus().setDes("Helle Json/您好 Json");
        logger.info("eric_hello/json_result:"+ JsonUtil.toJson(sysResult));

        ConvertCardDetailReqPojo req = new ConvertCardDetailReqPojo();
        req.setPhone("13989275543");
        req.setCard_no("24160612");
        try {
            String convertCardDetailRspBaseRsp = rpc.queryListByCondition(req, "https://api.xhhmei.com/Open/convertCardDetail");
            ConvertCardDetailRsp convertCardDetailRsp = JsonUtil.fromJson(convertCardDetailRspBaseRsp,
                    new TypeReference<ConvertCardDetailRsp>() {
                    });
            if(convertCardDetailRsp == null || convertCardDetailRsp.getCode() != 200){
                throw new BaseException(CommonBaseStatus.PARAM_ERROR);
            }
            convertCardDetailRspBaseRsp = rpc.queryListByCondition(req, "https://api.xhhmei.com/Open/convertCardUse");
            convertCardDetailRsp = JsonUtil.fromJson(convertCardDetailRspBaseRsp,
                    new TypeReference<ConvertCardDetailRsp>() {
                    });
            if(convertCardDetailRsp == null || convertCardDetailRsp.getCode() != 200){
                throw new BaseException(CommonBaseStatus.PARAM_ERROR);
            }
        }catch (Exception e){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }

        return sysResult;
    }
}
