package com.eric.controller;

import com.eric.dao.AdministrationDao;
import com.eric.pojo.*;
import com.eric.service.IAdministrationService;
import com.google.common.base.Strings;
import com.qunar.mobile.car.qb.drivcommon.enums.CommonBaseStatus;
import com.qunar.mobile.car.qb.drivcommon.exception.BaseException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/administration")
public class AdministrationController {
    @Autowired
    private IAdministrationService administrationService;
    @Autowired
    private AdministrationDao administrationDao;

    private String companyIdTemp = "ymhui888";

    /**
     * 客户列表页接口
     * @param storeId
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer/list")
    @ResponseBody
    public Object getUserList(@RequestParam(value = "storeId", required = false) String storeId,
                              @RequestParam(value = "page", required = false) Integer page,
                              HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || page == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(page < 1){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        AdministrationCustomerPojo administrationCustomerPojo = administrationService.getUserListWithPage(storeId, page);
        return administrationCustomerPojo;
    }

    /**
     * 手机号码搜索接口
     * @param storeId
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer/query")
    @ResponseBody
    public Object queryCustomerWithPhone(@RequestParam(value = "storeId", required = false) String storeId,
                                         @RequestParam(value = "phone", required = false) String phone,
                                         HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(phone)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        UserPojo userPojo = administrationDao.queryCustomerWithPhone(phone, storeId);
        if(userPojo != null){
            Date birthday = userPojo.getBirthday();
            if(birthday != null){
                userPojo.setAge(getAge(birthday));
                long diff = dayDiff(birthday, new Date());
                if(diff < 8){
                    userPojo.setBirthdayFlag(1);
                }else {
                    userPojo.setBirthdayFlag(0);
                }
            }
            String id = userPojo.getUserId();
            int temp = administrationDao.queryConsumption(id, 7);
            if(temp == 0){
                userPojo.setConsumptionFalg(1);
            }else {
                userPojo.setConsumptionFalg(0);
            }
        }
        return userPojo;
    }

    /**
     * 新增客户接口
     * @param name
     * @param phone
     * @param birthday
     * @param babyBirthday
     * @param sex
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer/add")
    @ResponseBody
    public Object addUser(@RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "phone", required = false) String phone,
                      @RequestParam(value = "birthday", required = false) Date birthday,
                      @RequestParam(value = "babyBirthday", required = false) Date babyBirthday,
                      @RequestParam(value = "sex", required = false) String sex,
                      @RequestParam(value = "storeId", required = false) String storeId,
                      HttpServletRequest request){
        if(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(sex)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(birthday == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        UserPojo userPojo = administrationDao.queryCustomerWithPhone(phone, storeId);
        if(userPojo != null && userPojo.getPhoneNumber()!= null && userPojo.getPhoneNumber().length() != 0){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationService.addUserInfo(name, phone, birthday, babyBirthday, sex, storeId);
        return "ok";
    }

    /**
     * 项目首页列表接口
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/list")
    @ResponseBody
    public Object getProjectList(@RequestParam(value = "storeId", required = false) String storeId,
                                 HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        AdministrationProjectPojo administrationProjectPojo = administrationService.getAdministrationProject(storeId);
        return administrationProjectPojo;
    }

    /**
     * 二级项目接口
     * @param storeId
     * @param projectId
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/two/list")
    @ResponseBody
    public Object getTwoProjectList(@RequestParam(value = "storeId", required = false) String storeId,
                                    @RequestParam(value = "projectId", required = false) String projectId,
                                    HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(projectId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<ProjectTwoPojo> projectTwoPojoList = administrationService.getprojectTwoPojoList(storeId, projectId);
        return projectTwoPojoList;
    }

    /**
     * 更新门店一级项目名
     * @param storeId
     * @param projectId
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/one/update")
    @ResponseBody
    public Object updateProjectOne(@RequestParam(value = "storeId", required = false) String storeId,
                                    @RequestParam(value = "projectId", required = false) String projectId,
                                   @RequestParam(value = "name", required = false) String name,
                                    HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(projectId) || Strings.isNullOrEmpty(name)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateProjectOne(projectId, name);
        return "ok";
    }

    /**
     * 技师列表接口
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/technician/list")
    @ResponseBody
    public Object getTechnicianList(@RequestParam(value = "storeId", required = false) String storeId,
                                    HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<AdministrationTechnicianPojo> administrationTechnicianPojoList = administrationService.getTechnicianList(storeId);
        if(administrationTechnicianPojoList != null){
            for(AdministrationTechnicianPojo administrationTechnicianPojo: administrationTechnicianPojoList){
                List<Double> orderFee = administrationDao.getOrderFeeList(administrationTechnicianPojo.getUserId());
                Double orderFeeSum = 0.0;
                if(orderFee != null){
                    for(Double temp: orderFee){
                        orderFeeSum = orderFeeSum + temp;
                    }
                }
                orderFeeSum = this.formatDouble1(orderFeeSum);
                administrationTechnicianPojo.setTotalFee(orderFeeSum);
                List<Double> onceFee = administrationDao.getOnceFeeList(administrationTechnicianPojo.getUserId());
                Double onceFeeSum = 0.0;
                if(onceFee != null){
                    for(Double temp: onceFee){
                        onceFeeSum = onceFeeSum + temp;
                    }
                }
                onceFeeSum = this.formatDouble1(onceFeeSum);
                administrationTechnicianPojo.setManualFee(onceFeeSum);
            }
        }
        return administrationTechnicianPojoList;
    }

    public static double formatDouble1(double d) {
        return (double)Math.round(d*100)/100;
    }

    /**
     * 生单接口
     * @param userId
     * @param storeId
     * @param renewProject
     * @param giveProject
     * @param courseOfTreatment
     * @param day
     * @param price
     * @param point
     * @param type
     * @param technicianId
     * @param reason
     * @param request
     * @return
     */
    @RequestMapping(value = "/take/order")
    @ResponseBody
    public Object takeOrder(@RequestParam(value = "userId", required = false) String userId,
                            @RequestParam(value = "storeId", required = false) String storeId,
                            @RequestParam(value = "renewProject", required = false) String renewProject,
                            @RequestParam(value = "giveProject", required = false) String giveProject,
                            @RequestParam(value = "giveCourseOfTreatment", required = false) Integer giveCourseOfTreatment,
                            @RequestParam(value = "courseOfTreatment", required = false) Integer courseOfTreatment,
                            @RequestParam(value = "day", required = false) Integer day,
                            @RequestParam(value = "price", required = false) Double price,
                            @RequestParam(value = "storePoint", required = false) Double storePoint,
                            @RequestParam(value = "point", required = false) Double point,
                            @RequestParam(value = "type", required = false) Integer type,
                            @RequestParam(value = "technicianId", required = false) String technicianId,
                            @RequestParam(value = "reason", required = false) String reason,
                            @RequestParam(value = "giveCardId", required = false) String giveCardId,
                            HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(renewProject)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(courseOfTreatment != null && day != null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(courseOfTreatment == null && day == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(price == null){
            price = 0.0;
        }
        if(price < 0){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(type == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }else {
            if(type != 3){
                if(Strings.isNullOrEmpty(technicianId)){
                    throw new BaseException(CommonBaseStatus.PARAM_ERROR);
                }
            }else {
                if(Strings.isNullOrEmpty(technicianId)){
                    technicianId = "";
                }
            }
        }
        if(!Strings.isNullOrEmpty(giveProject)){
            if(giveCourseOfTreatment == null || giveCourseOfTreatment < 1){
                giveCourseOfTreatment = 1;
            }
        }
        String typeTemp = "";
        if(type == 1){
            typeTemp = "普通";
        }else if(type == 2){
            typeTemp = "特价";
        }else if(type == 3){
            typeTemp = "体验";
        }
        if(storePoint == null){
            storePoint = 0.0;
        }
        if(point == null){
            point = 0.0;
        }
        if(Strings.isNullOrEmpty(reason)){
            reason = "";
        }
        administrationDao.insertOrder(userId, storeId, renewProject, giveProject, courseOfTreatment, day, price, storePoint, type, technicianId, reason, point, giveCardId);
        String orderId = administrationDao.queryOrderId(userId, storeId);
        String projectName = administrationDao.getProjectName(renewProject);
        String userName = administrationDao.getUserName(userId);
        String userPhone = administrationDao.getUserPhone(userId);
        if(day != null){
            administrationDao.insertTimeCard(userId, storeId, orderId, renewProject, day);
            Integer cardId = administrationDao.queryCardIdTime(userId, storeId, orderId);
            administrationDao.insertConsumptionDetail(userId, projectName, price, day, 0, "", 0.0, "生单", orderId, reason);
            administrationDao.insertAchievement("时间卡：" + cardId, projectName, price, userName, userPhone, "开单", technicianId, typeTemp, storeId, reason);
        }else if(courseOfTreatment != null){
            administrationDao.insertCourseCard(userId, storeId, orderId, renewProject, courseOfTreatment);
            Integer cardId = administrationDao.queryCardId(userId, storeId, orderId);
            administrationDao.insertConsumptionDetail(userId, projectName, price, courseOfTreatment, 0, "", 0.0, "生单", orderId, reason);
            administrationDao.insertAchievement("疗程卡：" + cardId, projectName, price, userName, userPhone, "开单", technicianId, typeTemp, storeId, reason);
        }
        if(storePoint != null && storePoint != 0){
            administrationDao.insertScoreFlow(userId, projectName, storePoint, 1);
        }
        if(point != null && point != 0){
            administrationDao.insertScoreFlow(userId, projectName, point, 2);
        }
        if(giveProject != null){
            String giveProjectName = administrationDao.getProjectName(giveProject);
            administrationDao.insertCourseCard(userId, storeId, orderId, giveProject, giveCourseOfTreatment);
            Integer cardId = administrationDao.queryCardId(userId, storeId, orderId);
            administrationDao.insertConsumptionDetail(userId, giveProjectName, 0.0, giveCourseOfTreatment, 0, "", 0.0, "生单赠送", "", reason);
            administrationDao.insertAchievement("疗程卡：" + cardId, projectName, 0.0, userName, userPhone, "开单", technicianId, typeTemp, storeId, reason);
        }
        return "ok";
    }

    /**
     * 确认支付接口
     * @param storeId
     * @param orderId
     * @param request
     * @return
     */
    @RequestMapping(value = "/confirmation/pay")
    @ResponseBody
    public Object confirmationOfPayment(@RequestParam(value = "storeId", required = false) String storeId,
                                        @RequestParam(value = "orderId", required = false) String orderId,
                                        HttpServletRequest request){
        if(Strings.isNullOrEmpty(orderId) || Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateOrder(orderId);
        return "ok";
    }

    /**
     * 客户详情接口
     * @param storeId
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer/info")
    @ResponseBody
    public Object customerInfo(@RequestParam(value = "storeId", required = false) String storeId,
                               @RequestParam(value = "userId", required = false) String userId,
                               HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        UserPojo userInfo = administrationDao.queryUserInfoWithUserId(userId);
        userInfo.setBabyBirthday(administrationDao.getBabyBirthday(userId));
        userInfo.setAge(this.getAge(userInfo.getBirthday()));
        List<CardPojo> timeCardList = administrationDao.queryTimeCard(userId);
        List<CardPojo> timeCardListTemp = new ArrayList();
        Date nowDate = new Date();
        for(CardPojo cardPojo: timeCardList){
            Integer limit = cardPojo.getLimit();
            String projectId = cardPojo.getProjectId();
            String projectName = administrationDao.getProjectName(projectId);
            cardPojo.setCardName(projectName + limit + "天卡");
            cardPojo.setProjectName(projectName);
            cardPojo.setTechnicianFee(administrationDao.getProjectFee(projectId));
            String cardId = cardPojo.getCardId();
            Date timeCardCreateTime = administrationDao.getTimeCardCreateTime(cardId);
            cardPojo.setCreateTime(timeCardCreateTime);
            long diff = dayDiff(timeCardCreateTime, nowDate);
            cardPojo.setProgress((int)diff);
            if(cardPojo.getStatus() != 1){
                if(administrationDao.getTimeCardStatus(Integer.valueOf(cardId)) == 2){
                    cardPojo.setStatus(2);
                }
                if(diff > limit && cardPojo.getStatus() != 2){
                    cardPojo.setStatus(1);
                    administrationDao.updateTimeCardStatus(1,Integer.valueOf(cardPojo.getCardId()));
                }else {
                    timeCardListTemp.add(cardPojo);
                }
            }
        }
        List<CardPojo> frequencyCardList = administrationDao.queryCurseCard(userId);
        List<CardPojo> frequencyCardListTemp = new ArrayList();
        for(CardPojo cardPojo: frequencyCardList){
            Integer limit = cardPojo.getLimit();
            String projectId = cardPojo.getProjectId();
            String projectName = administrationDao.getProjectName(projectId);
            cardPojo.setCardName(projectName + limit + "次卡");
            cardPojo.setProjectName(projectName);
            cardPojo.setTechnicianFee(administrationDao.getProjectFee(projectId));
            cardPojo.setCreateTime(cardPojo.getBeginTime());
            if(cardPojo.getStatus() != 1){
                if(cardPojo.getProgress() >= limit){
                    cardPojo.setStatus(1);
                    administrationDao.updateCardStatus(1, Integer.valueOf(cardPojo.getCardId()));
                }else {
                    frequencyCardListTemp.add(cardPojo);
                }
            }
        }
        List<ConsumptionDetailPojo> consumptionDetailList = administrationDao.getConsumptionDetailList(userId);
        if(consumptionDetailList != null){
            for(ConsumptionDetailPojo consumptionDetailPojo: consumptionDetailList){
                if(consumptionDetailPojo.getReason().equals("生单") || consumptionDetailPojo.getReason().equals("生单赠送")){
                    if(Strings.isNullOrEmpty(consumptionDetailPojo.getRemarks())){
                        if(Strings.isNullOrEmpty(consumptionDetailPojo.getOrderId())){
                            continue;
                        }else {
                            String remarkTemp = administrationDao.getReasonOfOrders(Integer.valueOf(consumptionDetailPojo.getOrderId()));
                            if(Strings.isNullOrEmpty(remarkTemp)){
                                continue;
                            }else {
                                consumptionDetailPojo.setRemarks(remarkTemp);
                            }
                        }
                    }
                }
            }
        }
        List<ScoreDetailPojo> scoreDetailList = administrationDao.getScoreDetailList(userId, 1);
        Double sum = 0.0;
        if(scoreDetailList != null){
            for(ScoreDetailPojo scoreDetailPojo: scoreDetailList){
                sum = sum + scoreDetailPojo.getScoreChange();
            }
        }
        userInfo.setBalance(sum);
        userInfoPojo.setUserInfo(userInfo);
        userInfoPojo.setTimeCardList(timeCardListTemp);
        userInfoPojo.setFrequencyCardList(frequencyCardListTemp);
        userInfoPojo.setConsumptionDetailList(consumptionDetailList);
        userInfoPojo.setScoreDetailList(scoreDetailList);
        return userInfoPojo;
    }

    //由出生日期获得年龄
    public  int getAge(Date birthDay){
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

    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * 更新客户信息接口
     * @param storeId
     * @param userId
     * @param name
     * @param phone
     * @param birthday
     * @param babyBirthday
     * @param sex
     * @param address
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer/update/info")
    @ResponseBody
    public Object updateCustomerInfo(@RequestParam(value = "storeId", required = false) String storeId,
                                     @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "birthday", required = false) Date birthday,
                                     @RequestParam(value = "babyBirthday", required = false) Date babyBirthday,
                                     @RequestParam(value = "sex", required = false) String sex,
                                     @RequestParam(value = "address", required = false) String address,
                                     HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateUserInfo(userId, name, phone, birthday, sex, address);
        if(babyBirthday != null){
            Date temp = administrationDao.getBabyBirthday(userId);
            if(temp != null){
                administrationDao.updateBabyInfo(userId, babyBirthday);
            }else {
                administrationDao.insertBaby(babyBirthday, userId);
            }
        }
        return "ok";
    }

    /**
     * 项目核销确认消费接口
     * @param userId
     * @param technicianId
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/settlement")
    @ResponseBody
    public Object projectSettlement(@RequestParam(value = "userId", required = false) String userId,
                                    @RequestParam(value = "storeId", required = false) String storeId,
                                    @RequestParam(value = "cardId", required = false) String cardId,
                                    @RequestParam(value = "type", required = false) Integer type,
                                        @RequestParam(value = "status", required = false) Integer status,
                                     @RequestParam(value = "technicianId", required = false) String technicianId,
                                    @RequestParam(value = "remarks", required = false) String remarks,
                                     HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(technicianId) || Strings.isNullOrEmpty(cardId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(type == null || status == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(status == 1){
            //提交
            String projectId = "";
            if(type == 1){
                projectId = administrationDao.getTimeCardProjectId(cardId);
            }else {
                projectId = administrationDao.getProcessCardProjectId(cardId);
            }
            if(projectId != null && projectId.length() != 0){
                String projectName = administrationDao.getProjectName(projectId);
                administrationDao.insertConsumptionOrder(userId, storeId, cardId, projectName, 0, type);
            }
        }else {
            //确认核销
            if(Strings.isNullOrEmpty(remarks)){
                remarks = "";
            }
            String userName = administrationDao.getUserName(userId);
            String userPhone = administrationDao.getUserPhone(userId);
            String[] technicianIdArray = technicianId.split(",");
            if(type == 2){
                administrationDao.updateProcessCard(cardId);
                String projectId = administrationDao.getProcessCardProjectId(cardId);
                String projectName = administrationDao.getProjectName(projectId);
                Integer limit = administrationDao.getProcessCardLimit(cardId);
                Integer process = administrationDao.getProcessCardProcess(cardId);
                Double fee = administrationDao.getProjectFee(projectId);
                int size = technicianIdArray.length;
                Double temp = fee / size;
                for (int i = 0; i < technicianIdArray.length; i++) {
                    administrationDao.insertConsumptionDetail(userId, projectName, 0.0, limit, process, technicianIdArray[i], temp, "核销", "", remarks);
                }
                administrationDao.insertAchievement("疗程卡：" + cardId, projectName, 0.0, userName, userPhone, "核销", technicianId, "", storeId, remarks);
                Integer id = administrationDao.getConsumptionOrderId(userId, storeId, cardId, projectName, 0, type);
                if(id != null){
                    administrationDao.updateConsumptionOrder(id, 1);
                }
            }else {
                String projectId = administrationDao.getTimeCardProjectId(cardId);
                String projectName = administrationDao.getProjectName(projectId);
                Integer limit = administrationDao.getTimeCardLimit(cardId);
                Date createTime = administrationDao.getTimeCardCreateTime(cardId);
                long diff = dayDiff(createTime, new Date());
                Double fee = administrationDao.getProjectFee(projectId);
                int size = technicianIdArray.length;
                Double temp = fee / size;
                for (int i = 0; i < technicianIdArray.length; i++) {
                    administrationDao.insertConsumptionDetail(userId, projectName, 0.0, limit, (int)diff, technicianIdArray[i], temp, "核销", "", remarks);
                }
                administrationDao.insertAchievement("时间卡：" + cardId, projectName, 0.0, userName, userPhone, "核销", technicianId, "", storeId, remarks);
                Integer id = administrationDao.getConsumptionOrderId(userId, storeId, cardId, projectName, 0, type);
                if(id != null){
                    administrationDao.updateConsumptionOrder(id, 1);
                }
            }
        }
        return "ok";
    }

    /**
     * 平台拉取核销确认信息
     * @param phoneNumber
     * @param userName
     * @param request
     * @return
     */
    @RequestMapping(value = "/platform/settlement")
    @ResponseBody
    public Object platformSettlement(@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                @RequestParam(value = "userName", required = false) String userName,
                                HttpServletRequest request){
        List<UserPojo> userPojoList = administrationDao.queryUserPojoList(phoneNumber, userName);
        return null;
    }

    /**
     * 积分兑换项目确认兑换接口
     * @param projectId
     * @param userId
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "/score/exchange")
    @ResponseBody
    public Object scoreExchange(@RequestParam(value = "projectId", required = false) String projectId,
                                    @RequestParam(value = "userId", required = false) String userId,
                                @RequestParam(value = "storeId", required = false) String storeId,
                                @RequestParam(value = "point", required = false) Double point,
                                @RequestParam(value = "status", required = false) Integer status,
                                    HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(projectId) || Strings.isNullOrEmpty(storeId) || point == null || point < 0 || status == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        String giveProjectName = administrationDao.getProjectName(projectId);
        if(status == 1){
            //提交
            administrationDao.insertScoreWait(userId, storeId, giveProjectName, 0, -point);
        }else {
            //确认
            administrationDao.insertCourseCard(userId, storeId, "", projectId, 1);
            Integer cardId = administrationDao.queryCardId(userId, storeId, "");
            administrationDao.insertConsumptionDetail(userId, giveProjectName, 0.0, 1, 0, "", 0.0, "积分兑换", "", "");
            if(point != null && point >= 0){
                administrationDao.insertScoreFlow(userId, giveProjectName, -point, 1);
            }
            String userName = administrationDao.getUserName(userId);
            String userPhone = administrationDao.getUserPhone(userId);
            administrationDao.insertAchievement("疗程卡：" + cardId, giveProjectName, 0.0, userName, userPhone, "积分兑换", "", "体验", storeId, "");
            Integer id = administrationDao.getScoreWaitId(userId, storeId, giveProjectName, 0, -point);
            if(id != null){
                administrationDao.updateScoreWait(id, 1);
            }
        }
        return "ok";
    }

    /**
     * 停复时间卡
     * @param storeId
     * @param userId
     * @param cardId
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "/card/time/update")
    @ResponseBody
    public Object updateTimeCard(@RequestParam(value = "storeId", required = false) String storeId,
                                @RequestParam(value = "userId", required = false) String userId,
                                @RequestParam(value = "cardId", required = false) String cardId,
                                 @RequestParam(value = "type", required = false) Integer type,
                                HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(cardId) || type == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(type == 1){
            //停卡
            if(administrationDao.getTimeCardStatus(Integer.valueOf(cardId)) == 0){
                administrationDao.updateTimeCardStatus(2, Integer.valueOf(cardId));
            }
        }else {
            //复卡
            if(administrationDao.getTimeCardStatus(Integer.valueOf(cardId)) == 2){
                Date updateTime = administrationDao.getUpdateTime(cardId);
                Date createTime = administrationDao.getTimeCardCreateTime(cardId);
                Date now = new Date();
                Double temp = (now.getTime() - updateTime.getTime()) * 1.00 / 86400000 * 24 * 3600;
                Calendar c = Calendar.getInstance();
                c.setTime(createTime);
                c.add(Calendar.SECOND, temp.intValue());
                createTime = c.getTime();
                administrationDao.updateCreateTime(cardId, createTime);
                administrationDao.updateTimeCardStatus(0, Integer.valueOf(cardId));
            }
        }
        return "ok";
    }

    /**
     * 退卡
     * @param storeId
     * @param userId
     * @param cardId
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "/card/out")
    @ResponseBody
    public Object cardOut(@RequestParam(value = "storeId", required = false) String storeId,
                                 @RequestParam(value = "userId", required = false) String userId,
                                 @RequestParam(value = "cardId", required = false) String cardId,
                                 @RequestParam(value = "type", required = false) Integer type,
                          @RequestParam(value = "price", required = false) Double price,
                                 HttpServletRequest request){
        if(Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(cardId) || type == null || price == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        String userName = administrationDao.getUserName(userId);
        String userPhone = administrationDao.getUserPhone(userId);
        if(type == 1){
            //时间卡
            Integer status = administrationDao.getTimeCardStatus(Integer.valueOf(cardId));
            if(status == 1){
                return "该卡已经结束";
            }
            String id = administrationDao.getTimeCardOrderId(Integer.valueOf(cardId));
            administrationDao.updateTimeCardStatus(1, Integer.valueOf(cardId));
            Double fee = 0.0;
            if(id != null && id.length() != 0){
                administrationDao.updateOrderPrice(price, Integer.valueOf(id));
                fee = administrationDao.getPriceWithOrder(Integer.valueOf(id));
                administrationDao.updateConsumptionDetailPrice(id, fee);
                administrationDao.updateAchievementPrice("时间卡：" + cardId, fee, "开单");
            }
            String projectId = administrationDao.getTimeCardProjectId(cardId);
            String projectName = administrationDao.getProjectName(projectId);
            administrationDao.insertAchievement("时间卡：" + cardId, projectName, fee, userName, userPhone, "退卡", "", "", storeId, "");
            administrationDao.insertConsumptionDetail(userId, projectName, fee, 0, 0, "", 0.0, "退卡", id, "");
        }else {
            //疗程卡
            Integer status = administrationDao.getCardStatus(Integer.valueOf(cardId));
            if(status == 1){
                return "该卡已结束";
            }
            String id = administrationDao.getCardOrderId(Integer.valueOf(cardId));
            administrationDao.updateCardStatus(1, Integer.valueOf(cardId));
            Double fee = 0.0;
            if(id != null && id.length() != 0){
                administrationDao.updateOrderPrice(price, Integer.valueOf(id));
                fee = administrationDao.getPriceWithOrder(Integer.valueOf(id));
                administrationDao.updateConsumptionDetailPrice(id, fee);
                administrationDao.updateAchievementPrice("疗程卡：" + cardId, fee, "开单");
            }
            String projectId = administrationDao.getProcessCardProjectId(cardId);
            String projectName = administrationDao.getProjectName(projectId);
            administrationDao.insertAchievement("疗程卡：" + cardId, projectName, fee, userName, userPhone, "退卡", "", "", storeId, "");
            administrationDao.insertConsumptionDetail(userId, projectName, fee, 0, 0, "", 0.0, "退卡", id, "");
        }
        return "ok";
    }

    /**
     * 出库
     * @param projectId
     * @param storeId
     * @param reason
     * @param number
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/output")
    @ResponseBody
    public Object projectOutput(@RequestParam(value = "projectId", required = false) String projectId,
                                 @RequestParam(value = "storeId", required = false) String storeId,
                                 @RequestParam(value = "reason", required = false) String reason,
                                 @RequestParam(value = "number", required = false) Integer number,
                                 HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(projectId) || number == null || number < 1){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        return "ok";
    }

    /**
     * 库存列表
     * @param storeId
     * @param beginTime
     * @param endTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/surplus/list")
    @ResponseBody
    public Object projectSurplusList(@RequestParam(value = "storeId", required = false) String storeId,
                                @RequestParam(value = "beginTime", required = false) Date beginTime,
                                @RequestParam(value = "endTime", required = false) Date endTime,
                                HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || beginTime == null || endTime == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        return "ok";
    }

    /**
     * 技师详情
     * @param technicianId
     * @param request
     * @return
     */
    @RequestMapping(value = "/technicians/info")
    @ResponseBody
    public Object technicianInfo(@RequestParam(value = "technicianId", required = false) String technicianId,
                                     HttpServletRequest request){
        if(Strings.isNullOrEmpty(technicianId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        TechnicianPojo technicianPojo = administrationDao.queryTechnicianInfo(technicianId);
        List<Double> orderFee = administrationDao.getOrderFeeList(technicianId);
        Double orderFeeSum = 0.0;
        if(orderFee != null){
            for(Double temp: orderFee){
                orderFeeSum = orderFeeSum + temp;
            }
        }
        orderFeeSum = this.formatDouble1(orderFeeSum);
        technicianPojo.setOrderFee(orderFeeSum);

        List<Double> onceFee = administrationDao.getOnceFeeList(technicianId);
        Double onceFeeSum = 0.0;
        if(onceFee != null){
            for(Double temp: onceFee){
                onceFeeSum = onceFeeSum + temp;
            }
        }
        onceFeeSum = this.formatDouble1(onceFeeSum);
        technicianPojo.setFee(onceFeeSum);

        orderFee = administrationDao.getTotalOrderFeeList(technicianId);
        orderFeeSum = 0.0;
        if(orderFee != null){
            for(Double temp: orderFee){
                orderFeeSum = orderFeeSum + temp;
            }
        }
        orderFeeSum = this.formatDouble1(orderFeeSum);
        technicianPojo.setTotalOrderFee(orderFeeSum);

        onceFee = administrationDao.getTotalOnceFeeList(technicianId);
        onceFeeSum = 0.0;
        if(onceFee != null){
            for(Double temp: onceFee){
                onceFeeSum = onceFeeSum + temp;
            }
        }
        onceFeeSum = this.formatDouble1(onceFeeSum);
        technicianPojo.setTotalFee(onceFeeSum);
        return technicianPojo;
    }

    /**
     * 技师详情更新接口
     * @param storeId
     * @param technicianId
     * @param name
     * @param phone
     * @param level
     * @param grad
     * @param request
     * @return
     */
    @RequestMapping(value = "/technicians/update/info")
    @ResponseBody
    public Object updateTechnicianInfo(@RequestParam(value = "storeId", required = false) String storeId,
                                       @RequestParam(value = "technicianId", required = false) String technicianId,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "phone", required = false) String phone,
                                       @RequestParam(value = "level", required = false) String level,
                                       @RequestParam(value = "grad", required = false) String grad,
                                       @RequestParam(value = "inTime", required = false) Date inTime,
                                 HttpServletRequest request){
        if(Strings.isNullOrEmpty(technicianId) || Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(level) || Strings.isNullOrEmpty(grad) || inTime == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateTechnicianInfo(technicianId, name, phone, level, grad, inTime);
        return "ok";
    }

    /**
     * 技师业绩
     * @param storeId
     * @param technicianId
     * @param beginTime
     * @param endTime
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "/technicians/detailed")
    @ResponseBody
    public Object technicianDetailed(@RequestParam(value = "storeId", required = false) String storeId,
                                       @RequestParam(value = "technicianId", required = false) String technicianId,
                                       @RequestParam(value = "beginTime", required = false) Date beginTime,
                                       @RequestParam(value = "endTime", required = false) Date endTime,
                                       @RequestParam(value = "type", required = false) Integer type,
                                       HttpServletRequest request){
        if(Strings.isNullOrEmpty(technicianId) || Strings.isNullOrEmpty(storeId) || beginTime == null || endTime == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(type == null || type < 1 || type > 2){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<TechnicianDetailedPojo> technicianDetailedPojoList = new ArrayList();
        if(type == 1){
            technicianDetailedPojoList = administrationDao.getConsumptionDetails(technicianId, beginTime, endTime);
            if(technicianDetailedPojoList != null){
                for(TechnicianDetailedPojo technicianDetailedPojo: technicianDetailedPojoList){
                    UserPojo userPojo = administrationDao.queryUserInfoWithUserId(technicianDetailedPojo.getUserId());
                    technicianDetailedPojo.setUserName(userPojo.getName());
                }
            }
        }else {
            technicianDetailedPojoList = administrationDao.getOrderDetails(technicianId, beginTime, endTime);
            if(technicianDetailedPojoList != null){
                for(TechnicianDetailedPojo technicianDetailedPojo: technicianDetailedPojoList){
                    UserPojo userPojo = administrationDao.queryUserInfoWithUserId(technicianDetailedPojo.getUserId());
                    technicianDetailedPojo.setUserName(userPojo.getName());
                    technicianDetailedPojo.setProjectName(administrationDao.getProjectName(technicianDetailedPojo.getProjectId()));
                    Integer temp = technicianDetailedPojo.getTypeTmep();
                    if(temp == 1){
                        technicianDetailedPojo.setType("普通");
                    }else if(temp == 2){
                        technicianDetailedPojo.setType("特价");
                    }else {
                        technicianDetailedPojo.setType("体验");
                    }
                }
            }
        }
        return technicianDetailedPojoList;
    }

    /**
     * 新增技师
     * @param name
     * @param phone
     * @param level
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/technicians/add")
    @ResponseBody
    public Object technicianAdd(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "phone", required = false) String phone,
                                @RequestParam(value = "level", required = false) String level,
                                @RequestParam(value = "grad", required = false) String grad,
                                @RequestParam(value = "storeId", required = false) String storeId,
                                @RequestParam(value = "inTime", required = false) Date inTime,
                                 HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(level) || Strings.isNullOrEmpty(grad)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        if(inTime == null){
            inTime = new Date();
        }
        Integer id = administrationDao.getTechniciansId();
        administrationDao.insertTechnicianInfo(storeId, name, phone, level, String.valueOf(id + 1), grad, inTime);
        return "ok";
    }

    /**
     * 删除技师
     * @param technicianId
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/technicians/delate")
    @ResponseBody
    public Object technicianDelate(@RequestParam(value = "technicianId", required = false) String technicianId,
                                @RequestParam(value = "storeId", required = false) String storeId,
                                HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(technicianId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateTechnicianDelate(Integer.valueOf(technicianId), 1);
        return "ok";
    }

    /**
     * 新增一级项目
     * @param name
     * @param stroeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/one/add")
    @ResponseBody
    public Object projectOneAdd(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "stroeId", required = false) String stroeId,
                                HttpServletRequest request){
        if(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(stroeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        Integer id = administrationDao.getLastId();
        if(id != null){
            administrationDao.insertOneProject(name, stroeId, String.valueOf(id + 1));
        }
        return "ok";
    }

    /**
     * 新增二级项目
     * @param name
     * @param stroeId
     * @param price
     * @param oncePrice
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/two/add")
    @ResponseBody
    public Object projectTwoAdd(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "storeId", required = false) String stroeId,
                                @RequestParam(value = "price", required = false) Double price,
                                @RequestParam(value = "oncePrice", required = false) Double oncePrice,
                                @RequestParam(value = "oneProjectId", required = false) String oneProjectId,
                                HttpServletRequest request){
        if(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(oneProjectId) || Strings.isNullOrEmpty(stroeId) || price == null || price < 0 || oncePrice == null || oncePrice < 0){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        Integer id = administrationDao.getTwoProjectIdLast();
        if(id != null){
            administrationDao.insertTwoProject(name, stroeId, String.valueOf(id + 1), oneProjectId, price, oncePrice);
        }
        return "ok";
    }

    /**
     * 二级项目删除接口
     * @param projectId
     * @param request
     * @return
     */
    @RequestMapping(value = "/project/two/delete")
    @ResponseBody
    public Object deleteProjectTwo(@RequestParam(value = "projectId", required = false) String projectId,
                                HttpServletRequest request){
        if(Strings.isNullOrEmpty(projectId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.deleteProjectTwo(projectId);
        return "ok";
    }

    /**
     * 门店一级库存接口
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/stock/one")
    @ResponseBody
    public Object stockOne(@RequestParam(value = "storeId", required = false) String storeId,
                                   HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        StockPojo stockPojo = new StockPojo();
        List<StockOnePojo> stockOnePojoList = administrationDao.queryStockOnePojoList(storeId);
        stockPojo.setStockOnePojoList(stockOnePojoList);
        if(stockOnePojoList.size() > 0){
            String id = String.valueOf(stockOnePojoList.get(0).getId());
            String oneName = administrationDao.getOneName(id);
            List<StockTwoPojo> stockTwoPojoList = administrationDao.queryStockTwoPojoList(storeId, id);
            if(stockTwoPojoList != null){
                for(StockTwoPojo stockTwoPojo: stockTwoPojoList){
                    stockTwoPojo.setOneName(oneName);
                }
            }
            stockPojo.setStockTwoPojoList(stockTwoPojoList);
        }
        return stockPojo;
    }

    /**
     * 门店二级库存接口
     * @param storeId
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/stock/two")
    @ResponseBody
    public Object stockTwo(@RequestParam(value = "storeId", required = false) String storeId,
                           @RequestParam(value = "id", required = false) String id,
                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(id)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        String oneName = administrationDao.getOneName(id);
        List<StockTwoPojo> stockTwoPojoList = administrationDao.queryStockTwoPojoList(storeId, id);
        if(stockTwoPojoList != null){
            for(StockTwoPojo stockTwoPojo: stockTwoPojoList){
                stockTwoPojo.setOneName(oneName);
            }
        }
        return stockTwoPojoList;
    }

    /**
     * 门店入库弹窗
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/stock/in")
    @ResponseBody
    public Object stockIn(@RequestParam(value = "storeId", required = false) String storeId,
                           @RequestParam(value = "number", required = false) String number,
                           @RequestParam(value = "type", required = false) Integer type,
                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(number) || type == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        StoreInPojo storeInPojo = administrationDao.queryStoreInPojo(number, storeId);
        if(storeInPojo != null){
            storeInPojo.setEndTime(administrationDao.queryEndTime(number));
        }
        if(type == 1){
            return storeInPojo;
        }else {
            String name = storeInPojo.getOneName();
            int temp = administrationDao.queryStoreStock(number, storeId);
            if(temp == 0){
                int tempOneName = administrationDao.queryStoreStockOne(name, storeId);
                if(tempOneName == 0){
                    administrationDao.insertStockOneStore(name, storeId);
                }
                Integer id = administrationDao.queryStockId(storeId, name);
                String oneName = administrationDao.getStoreStockOneName(id);
                administrationDao.insertStockTwoStore(String.valueOf(id), storeInPojo.getTwoName(), storeId, storeInPojo.getFee(), storeInPojo.getCount(), storeInPojo.getEndTime(), number);
                Integer twoId = administrationDao.getStoreStockId(number, storeId);
                administrationDao.insertOutInStore(id.toString(), oneName, number, storeInPojo.getTwoName(), "入库", storeId, storeInPojo.getFee(), storeInPojo.getCount(), "入库");
            }else {
                administrationDao.updateStock(storeInPojo.getCount(), number);
                Integer twoId = administrationDao.getStoreStockId(number, storeId);
                String oneId = administrationDao.getStoreStockOneId(twoId);
                String oneName = administrationDao.getStoreStockOneName(Integer.valueOf(oneId));
                administrationDao.insertOutInStore(oneId, oneName, number, storeInPojo.getTwoName(), "入库", storeId, storeInPojo.getFee(), storeInPojo.getCount(), "入库");
            }
            administrationDao.updateOutInCompany(number, storeId);

            return "ok";
        }
    }

    /**
     * 门店出库弹窗
     * @param storeId
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/stock/out")
    @ResponseBody
    public Object stockOut(@RequestParam(value = "storeId", required = false) String storeId,
                           @RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "count", required = false) Integer count,
                           @RequestParam(value = "reason", required = false) String reason,
                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(id) || count == null || count < 1){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        Integer number = administrationDao.gerStoreStockNumber(id, storeId);
        if(count > number){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }else {
            administrationDao.updateStoreStock(storeId, id, count);
            String twoName =administrationDao.getTwoNameStockStore(id, storeId);
            Integer twoId = administrationDao.getStoreStockId(id, storeId);
            String oneId = administrationDao.getStoreStockOneId(twoId);
            String oneName = administrationDao.getStoreStockOneName(Integer.valueOf(oneId));
            administrationDao.insertOutInStore(oneId,oneName,id,twoName,"出库", storeId,0.0, count, reason);
        }
        return "ok";
    }

    /**
     * 门店出入库记录
     * @param storeId
     * @param endTime
     * @param beginTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/stock/detail/out/in")
    @ResponseBody
    public Object stockOutIn(@RequestParam(value = "storeId", required = false) String storeId,
                           @RequestParam(value = "endTime", required = false) Date endTime,
                           @RequestParam(value = "beginTime", required = false) Date beginTime,
                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || endTime == null || beginTime == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<OutInDetailPojo> outInDetailPojoList = administrationDao.getOutInDetailPojoList(storeId, endTime, beginTime);
        return outInDetailPojoList;
    }

    /**
     * 修改门店一级库存名
     * @param storeId
     * @param projectId
     * @param name
     * @param request
     * @return
     */
    @RequestMapping(value = "/stock/update/store/one")
    @ResponseBody
    public Object updateStockOne(@RequestParam(value = "storeId", required = false) String storeId,
                             @RequestParam(value = "projectId", required = false) String projectId,
                             @RequestParam(value = "name", required = false) String name,
                             HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(projectId) || Strings.isNullOrEmpty(name)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateStockStoreOne(Integer.valueOf(projectId), name);
        return "ok";
    }

    /**
     * 更新公司一级库存名
     * @param companyId
     * @param projectId
     * @param name
     * @param request
     * @return
     */
    @RequestMapping(value = "/stock/update/company/one")
    @ResponseBody
    public Object updateCompanyStockOne(@RequestParam(value = "companyId", required = false) String companyId,
                                 @RequestParam(value = "projectId", required = false) String projectId,
                                 @RequestParam(value = "name", required = false) String name,
                                 HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId) || Strings.isNullOrEmpty(projectId) || Strings.isNullOrEmpty(name)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateCompanyStockOne(Integer.valueOf(projectId), name);
        return "ok";
    }

    /**
     * 公司账户管理接口
     * @param companyId
     * @param request
     * @return
     */
    @RequestMapping(value = "/store/list/query")
    @ResponseBody
    public Object queryStoreList(@RequestParam(value = "companyId", required = false) String companyId,
                                   HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        List<StoreInfoPojo> storeInfoPojoList = administrationDao.queryStoreInfoPojoList(companyId);
        return storeInfoPojoList;
    }

    /**
     * 新增门店接口
     * @param storeId
     * @param name
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/store/add")
    @ResponseBody
    public Object storeAdd(@RequestParam(value = "storeId", required = false) String storeId,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "password", required = false) String password,
                           @RequestParam(value = "companyId", required = false) String companyId,
                                 HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(companyId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        int temp = administrationDao.queryStoreInfo(storeId);
        if(temp != 0){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.insertStoreInfo(storeId, name, password, companyId);
        return "ok";
    }

    /**
     * 门店业绩金额接口
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/store/achievement/fee/query")
    @ResponseBody
    public Object queryStoreFeeAchievement(@RequestParam(value = "storeId", required = false) String storeId,
                                        HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<Double> feeList = administrationDao.getAchievementPojos(storeId);
        Double fee = 0.0;
        if(feeList != null){
            for(Double temp: feeList){
                fee = fee + temp;
            }
        }
        return fee;
    }

    /**
     * 门店业绩查询接口
     * @param storeId
     * @param beginTime
     * @param endTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/store/achievement/query")
    @ResponseBody
    public Object queryStoreAchievement(@RequestParam(value = "storeId", required = false) String storeId,
                           @RequestParam(value = "beginTime", required = false) Date beginTime,
                           @RequestParam(value = "endTime", required = false) Date endTime,
                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || beginTime == null || endTime == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<AchievementPojo> achievementPojoList = administrationDao.getAchievementPojoList(storeId, beginTime, endTime);
        if(achievementPojoList != null){
            for(AchievementPojo achievementPojo: achievementPojoList){
                String TIds = achievementPojo.getTechniciansId();
                String TNs = "";
                if(TIds != null){
                    String[] technicianIdArray = TIds.split(",");
                    for (int i = 0; i < technicianIdArray.length; i++) {
                        TechnicianPojo technicianPojo = administrationDao.queryTechnicianInfo(technicianIdArray[i]);
                        if(technicianPojo != null){
                            TNs = TNs + technicianPojo.getName();
                        }
                    }
                }
                achievementPojo.setTechniciansName(TNs);
            }
        }
        return achievementPojoList;
    }

    /**
     * 公司一级库存
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/stock/one")
    @ResponseBody
    public Object companyStockOne(@RequestParam(value = "companyId", required = false) String companyId,
                                        HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        StockPojo stockPojo = new StockPojo();
        List<StockOnePojo> stockOnePojoList = administrationDao.queryStockOnePojoListOfCompany(companyId);
        stockPojo.setStockOnePojoList(stockOnePojoList);
        if(stockOnePojoList.size() > 0){
            List<StockTwoPojo> stockTwoPojoList = administrationDao.queryStockTwoPojoListOfCompany(companyId, String.valueOf(stockOnePojoList.get(0).getId()));
            stockPojo.setStockTwoPojoList(stockTwoPojoList);
        }
        return stockPojo;
    }

    /**
     * 公司二级库存接口
     * @param companyId
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/stock/two")
    @ResponseBody
    public Object companyStockTwo(@RequestParam(value = "companyId", required = false) String companyId,
                           @RequestParam(value = "id", required = false) String id,
                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId) || Strings.isNullOrEmpty(id)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        List<StockTwoPojo> stockTwoPojoList = administrationDao.queryStockTwoPojoListOfCompany(companyId, id);
        return stockTwoPojoList;
    }

    /**
     * 导出公司结余
     * @param companyId
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/stock/remain")
    @ResponseBody
    public Object companyStockRemain(@RequestParam(value = "companyId", required = false) String companyId,
                                  HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        List<StockTwoPojo> stockTwoPojoList = administrationDao.querycompanyStockRemain(companyId);
        if(stockTwoPojoList != null){
            for(StockTwoPojo stockTwoPojo: stockTwoPojoList){
                if(stockTwoPojo.getOneId() != null){
                    stockTwoPojo.setOneName(administrationDao.queryOneName(stockTwoPojo.getOneId()));
                }
            }
        }
        return stockTwoPojoList;
    }

    /**
     * 新增一级库存
     * @param companyId
     * @param name
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/add/stock/one")
    @ResponseBody
    public Object companyAddStockOne(@RequestParam(value = "companyId", required = false) String companyId,
                                  @RequestParam(value = "name", required = false) String name,
                                  HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId) || Strings.isNullOrEmpty(name)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        administrationDao.insertStockOne(companyId, name);
        return "ok";
    }

    /**
     * 公司出库确认
     * @param number
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/out")
    @ResponseBody
    public Object outPutOfCompany(@RequestParam(value = "storeId", required = false) String storeId,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "number", required = false) Integer number,
                                  @RequestParam(value = "companyId", required = false) String companyId,
                                  HttpServletRequest request){
        if(Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(id) || Strings.isNullOrEmpty(companyId) || number == null || number < 1){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        String oneId = administrationDao.queryOneId(id);
        String oneName = administrationDao.queryOneName(oneId);
        String twoName = administrationDao.queryTwoName(id);
        Double outPrice = administrationDao.queryOutPrice(id);
        Double inPrice = administrationDao.queryInPrice(id);
        Integer temp = administrationDao.queryCompanyStockCount(Integer.valueOf(id));
        if(number <= temp){
            administrationDao.updateCompanyStockCount(Integer.valueOf(id), number);
            administrationDao.insertOutInCompany(storeId, id, number, companyId, oneId, oneName, twoName, outPrice, inPrice, "出库", "门店未确认");
        }else {
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        return "ok";
    }

    /**
     * 公司入库确认
     * @param number
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/in")
    @ResponseBody
    public Object inPutOfCompany(@RequestParam(value = "companyId", required = false) String companyId,
                                        @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "inPrice", required = false) Double inPrice,
                                  @RequestParam(value = "outPrice", required = false) Double outPrice,
                                        @RequestParam(value = "number", required = false) Integer number,
                                  @RequestParam(value = "endTime", required = false) Date endTime,
                                        HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId) || Strings.isNullOrEmpty(id) || Strings.isNullOrEmpty(name) || number == null || number < 1 || inPrice == null || inPrice < 0 || outPrice == null || outPrice < 0 || endTime == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        administrationDao.insertCompanyStockTwo(companyId, id, name, inPrice, outPrice, number, endTime);
        Integer twoId = administrationDao.getTwoIdCompany(companyId, id, name);
        String oneName = administrationDao.queryOneName(id);
        administrationDao.insertOutInCompany("", twoId.toString(), number, companyId, id, oneName, name, outPrice, inPrice, "入库", "");
        return "ok";
    }

    /**
     * 公司出入库记录
     * @param companyId
     * @param beginTime
     * @param endTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/out/list/query")
    @ResponseBody
    public Object queryOutPutOfCompanyList(@RequestParam(value = "companyId", required = false) String companyId,
                                        @RequestParam(value = "beginTime", required = false) Date beginTime,
                                        @RequestParam(value = "endTime", required = false) Date endTime,
                                        HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId) || beginTime == null || endTime == null){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        companyId = this.companyIdTemp;
        List<OutInDetailPojo> outInDetailPojoList = administrationDao.queryCompanyOutIn(companyId, beginTime, endTime);
        if(outInDetailPojoList != null){
            for(OutInDetailPojo outInDetailPojo: outInDetailPojoList){
                String storeId = outInDetailPojo.getStoreId();
                if(storeId != null && storeId.length() != 0){
                    outInDetailPojo.setReason(administrationDao.getStoreName(storeId) + "," + outInDetailPojo.getReason());
                }
            }
        }
        return outInDetailPojoList;
    }

    /**
     * 查询门店卡结余
     * @param companyId
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/store/card")
    @ResponseBody
    public Object queryStoreCard(@RequestParam(value = "companyId", required = false) String companyId,
                                 @RequestParam(value = "storeId", required = false) String storeId,
                                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId) || Strings.isNullOrEmpty(storeId)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<CardPojo> cardPojoList = new ArrayList();
        List<CardPojo> timeCardList = administrationDao.queryTimeCardList(storeId);
        List<CardPojo> cardList = administrationDao.queryCardList(storeId);
        Date nowDate = new Date();
        if(timeCardList != null){
            for(CardPojo cardPojo: timeCardList){
                Integer limit = cardPojo.getLimit();
                String projectId = cardPojo.getProjectId();
                String projectName = administrationDao.getProjectName(projectId);
                cardPojo.setCardName(projectName + limit + "天卡");
                cardPojo.setProjectName(projectName);
                cardPojo.setTechnicianFee(administrationDao.getProjectFee(projectId));
                String cardId = cardPojo.getCardId();
                Date timeCardCreateTime = administrationDao.getTimeCardCreateTime(cardId);
                cardPojo.setCreateTime(timeCardCreateTime);
                long diff = dayDiff(timeCardCreateTime, nowDate);
                cardPojo.setProgress((int)diff);
                if(diff <= limit){
                    cardPojo.setCardType("时间卡");
                    String userId = cardPojo.getUserId();
                    cardPojo.setUserName(administrationDao.getUserName(userId));
                    cardPojo.setUserPhone(administrationDao.getUserPhone(userId));
                    String orderId = cardPojo.getOrderId();
                    if(orderId != null && orderId.length() != 0){
                        cardPojo.setPrice(administrationDao.getPriceWithOrder(Integer.valueOf(orderId)));
                    }else {
                        cardPojo.setPrice(0.0);
                    }
                    cardPojoList.add(cardPojo);
                }
            }
        }
        if(cardList != null){
            for(CardPojo cardPojo: cardList){
                Integer limit = cardPojo.getLimit();
                String projectId = cardPojo.getProjectId();
                String projectName = administrationDao.getProjectName(projectId);
                cardPojo.setCardName(projectName + limit + "次卡");
                cardPojo.setProjectName(projectName);
                cardPojo.setTechnicianFee(administrationDao.getProjectFee(projectId));
                cardPojo.setCreateTime(cardPojo.getBeginTime());
                if(cardPojo.getProgress() < limit){
                    cardPojo.setCardType("疗程卡");
                    String userId = cardPojo.getUserId();
                    cardPojo.setUserName(administrationDao.getUserName(userId));
                    cardPojo.setUserPhone(administrationDao.getUserPhone(userId));
                    String orderId = cardPojo.getOrderId();
                    if(orderId != null && orderId.length() != 0){
                        cardPojo.setPrice(administrationDao.getPriceWithOrder(Integer.valueOf(orderId)));
                    }else {
                        cardPojo.setPrice(0.0);
                    }
                    cardPojoList.add(cardPojo);
                }
            }
        }
        return cardPojoList;
    }

    /**
     * 修改门店密码
     * @param companyId
     * @param storeId
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/store/update/info")
    @ResponseBody
    public Object updateStoreInfo(@RequestParam(value = "companyId", required = false) String companyId,
                                           @RequestParam(value = "storeId", required = false) String storeId,
                                           @RequestParam(value = "password", required = false) String password,
                                           HttpServletRequest request){
        if(Strings.isNullOrEmpty(companyId) || Strings.isNullOrEmpty(storeId) || Strings.isNullOrEmpty(password)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        administrationDao.updateStoreInfo(storeId, password);
        return "ok";
    }
}
