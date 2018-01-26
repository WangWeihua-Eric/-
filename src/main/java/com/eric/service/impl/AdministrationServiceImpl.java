package com.eric.service.impl;

import com.eric.dao.AdministrationDao;
import com.eric.pojo.*;
import com.eric.service.IAdministrationService;
import com.qunar.mobile.car.qb.drivcommon.enums.CommonBaseStatus;
import com.qunar.mobile.car.qb.drivcommon.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AdministrationServiceImpl implements IAdministrationService {
    @Autowired
    private AdministrationDao administrationDao;

    @Override
    public AdministrationCustomerPojo getUserListWithPage(String userId, Integer page) {
        AdministrationCustomerPojo administrationCustomerPojo = new AdministrationCustomerPojo();
        List<UserPojo> userPojo = null;
        List<UserPojo> userPojoList = null;
        try {
            page = page * 10;
            userPojoList = administrationDao.getUserListWithPage(userId, page);
            page = page / 10;
            int size = administrationDao.queryUserCount(userId);
            if((page - 1) * 10 < size){
                if(page * 10 - 1 < size){
                    userPojo = userPojoList.subList((page - 1) * 10, page * 10);
                }else {
                    userPojo = userPojoList.subList((page - 1) * 10, size);
                }
            }

            if(userPojo != null){
                Date now = new Date();
                for(UserPojo userPojoTemp: userPojo){
                    if(userPojoTemp != null){
                        Date birthday = userPojoTemp.getBirthday();
                        if(birthday != null){
                            userPojoTemp.setAge(getAge(birthday));
                            long diff = dayDiff(birthday, now);
                            if(diff < 8){
                                userPojoTemp.setBirthdayFlag(1);
                            }else {
                                userPojoTemp.setBirthdayFlag(0);
                            }
                        }
                        String id = userPojoTemp.getUserId();
                        int temp = administrationDao.queryConsumption(id, 7);
                        if(temp == 0){
                            userPojoTemp.setConsumptionFalg(1);
                        }else {
                            userPojoTemp.setConsumptionFalg(0);
                        }
                    }
                }
                administrationCustomerPojo.setCount(size);
            }
            administrationCustomerPojo.setUserList(userPojo);
        }catch (Exception e){
            throw new BaseException(CommonBaseStatus.DATABASE_ERROR);
        }
        return administrationCustomerPojo;
    }

    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    //由出生日期获得年龄
    public  int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }

    @Override
    public void addUserInfo(String name, String phone, Date birthday, Date babyBirthday, String sex, String userId) {
        try {
            int temp = administrationDao.queryUser(phone, userId);
            if(temp == 0){
                administrationDao.insertUserInfo(name, phone, birthday, sex, userId);
            }
            int id = administrationDao.getUserId(phone, userId);
            if(id == 0){
                throw new BaseException(CommonBaseStatus.DATABASE_ERROR);
            }else if(temp == 0) {
                administrationDao.updateUserId(phone, String.valueOf(id));
            }
            if(babyBirthday != null){
                administrationDao.insertBaby(babyBirthday, String.valueOf(id));
            }
        }catch (Exception e){
            throw new BaseException(CommonBaseStatus.DATABASE_ERROR);
        }
    }

    @Override
    public AdministrationProjectPojo getAdministrationProject(String userId) {
        AdministrationProjectPojo administrationProjectPojo = new AdministrationProjectPojo();
        try{
            List<ProjectOnePojo> projectOnePojoList = administrationDao.getProjectOneList(userId);
            if(projectOnePojoList != null){
                administrationProjectPojo.setProjectOnePojoList(projectOnePojoList);
                if(projectOnePojoList.size() != 0){
                    List<ProjectTwoPojo> projectTwoPojoList = administrationDao.getProjectTwoList(userId, projectOnePojoList.get(0).getProjectId());
                    if(projectTwoPojoList != null){
                        administrationProjectPojo.setProjectTwoPojoList(projectTwoPojoList);
                    }
                }
            }
        }catch (Exception e){
            throw new BaseException(CommonBaseStatus.DATABASE_ERROR);
        }
        return administrationProjectPojo;
    }

    @Override
    public List<AdministrationTechnicianPojo> getTechnicianList(String storeId) {
        List<AdministrationTechnicianPojo> administrationTechnicianPojoList = null;
        try{
            administrationTechnicianPojoList = administrationDao.getTechnicianList(storeId);
            if(administrationTechnicianPojoList != null){
                for(AdministrationTechnicianPojo administrationTechnicianPojo:administrationTechnicianPojoList){
                    administrationTechnicianPojo.setManualFee(200.0);
                    administrationTechnicianPojo.setTotalFee(54554.0);
                }
            }
        }catch (Exception e){
            throw new BaseException(CommonBaseStatus.DATABASE_ERROR);
        }
        return administrationTechnicianPojoList;
    }

    @Override
    public List<ProjectTwoPojo> getprojectTwoPojoList(String storeId, String projectId) {
        List<ProjectTwoPojo> projectTwoPojoList = null;
        try{
            projectTwoPojoList = administrationDao.getProjectTwoList(storeId, projectId);
        }catch (Exception e){
            throw new BaseException(CommonBaseStatus.DATABASE_ERROR);
        }
        return projectTwoPojoList;
    }
}
