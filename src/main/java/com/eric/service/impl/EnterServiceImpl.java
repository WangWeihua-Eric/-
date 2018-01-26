package com.eric.service.impl;

import com.eric.dao.EnterDao;
import com.eric.pojo.CompanyPojo;
import com.eric.pojo.EnterPojo;
import com.eric.result.BStatusCode;
import com.eric.result.SysResult;
import com.eric.service.IEnterService;
import com.qunar.mobile.car.common.util.JsonUtil;
import com.qunar.mobile.car.qb.drivcommon.enums.CommonBaseStatus;
import com.qunar.mobile.car.qb.drivcommon.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wangweihua on 2017/10/19.
 */
@Service
public class EnterServiceImpl implements IEnterService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterDao enterDao;

    @Override
    public SysResult.BStatus signUp(EnterPojo enterPojo) {
        SysResult.BStatus bStatus = new SysResult.BStatus();
        try {
            if(enterDao.queryWithUserName(enterPojo.getUserName()) == 0){
                enterDao.signUp(enterPojo);
            }else {
                bStatus.setCode(BStatusCode.ALREARDY_SIGHUP.getCode());
                bStatus.setDes(BStatusCode.ALREARDY_SIGHUP.getDes());
            }
        }catch (Exception e){
            logger.error("Error:" + JsonUtil.toJson(e));
            throw new BaseException(CommonBaseStatus.DATABASE_ERROR);
        }
        return bStatus;
    }

    @Override
    public CompanyPojo login(EnterPojo enterPojo) {
        Integer type = enterPojo.getType();
        String userId = enterPojo.getUserId();
        String passwrod = enterPojo.getUserPassword();
        String name = "";
        Integer level = 2;
        if(type == 1){
            if(enterDao.queryWithUserName(userId) == 0){
                throw new BaseException(CommonBaseStatus.PARAM_ERROR);
            }else {
                String userPasswrod = enterDao.getPasswrodWithUserId(userId);
                if(!passwrod.equals(userPasswrod)){
                    throw new BaseException(CommonBaseStatus.PARAM_ERROR);
                }else {
                    name = enterDao.getName(userId);
                }
            }
        }else {
            if(enterDao.queryWithUserNameOfCompany(userId) == 0){
                throw new BaseException(CommonBaseStatus.PARAM_ERROR);
            }else {
                String userPasswrod = enterDao.getPasswrodOfCompanyWithUserId(userId);
                if(!passwrod.equals(userPasswrod)){
                    throw new BaseException(CommonBaseStatus.PARAM_ERROR);
                }else {
                    name = enterDao.getNameOfCompany(userId);
                    level = enterDao.getLevelOfCompany(userId);
                }
            }
        }
        CompanyPojo companyPojo = new CompanyPojo();
        companyPojo.setName(name);
        companyPojo.setLevel(level);
        return companyPojo;
    }
}
