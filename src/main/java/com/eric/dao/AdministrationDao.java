package com.eric.dao;

import com.eric.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdministrationDao {
    /**
     * 查询门店客户
     */
    List<UserPojo> getUserListWithPage(@Param("userId") String userId, @Param("page") Integer page);

    /**
     * 查询门店客户总数量
     */
    int queryUserCount(String userId);

    /**
     * 查询客户是否存在
     */
    int queryUser(@Param("phone") String phone,
                  @Param("storeId") String storeId);

    /**
     * 插入客户信息
     */
    void insertUserInfo(@Param("name") String name,
                        @Param("phone") String phone,
                        @Param("birthday") Date birthday,
                        @Param("sex") String sex,
                        @Param("storeId") String storeId);
    /**
     * 获取客户id
     */
    int getUserId(@Param("phone") String phone,
                  @Param("storeId") String storeId);

    /**
     * 更新客户id
     */
    void updateUserId(@Param("phone") String phone, @Param("userId") String userId);

    /**
     * 插入baby生日
     */
    void insertBaby(@Param("birthday") Date birthday, @Param("userId") String userId);

    /**
     * 获取一级项目列表
     */
    List<ProjectOnePojo> getProjectOneList(String userId);

    /**
     * 获取二级项目列表
     */
    List<ProjectTwoPojo> getProjectTwoList(@Param("userId") String userId, @Param("oneProjectId") String oneProjectId);

    /**
     * 获取技术列表
     */
    List<AdministrationTechnicianPojo> getTechnicianList(String storeId);

    /**
     * 通过手机号码查询客户
     */
    UserPojo queryCustomerWithPhone(@Param("phone") String phone,
                                    @Param("storeId") String storeId);

    /**
     * 开单
     */
    void insertOrder(@Param("userId") String userId,
                     @Param("storeId") String storeId,
                     @Param("renewProject") String renewProject,
                     @Param("giveProject") String giveProject,
                     @Param("courseOfTreatment") Integer courseOfTreatment,
                     @Param("day") Integer day,
                     @Param("price") Double price,
                     @Param("point") Double point,
                     @Param("type") Integer type,
                     @Param("technicianId") String technicianId,
                     @Param("reason") String reason,
                     @Param("shopPoint") Double shopPoint,
                     @Param("giveCardId") String giveCardId);

    /**
     * 查询orderId
     */
    String queryOrderId(@Param("userId") String userId,
                        @Param("storeId") String storeId);

    /**
     * 新增时间卡
     */
    void insertTimeCard(@Param("userId") String userId,
                        @Param("storeId") String storeId,
                        @Param("orderId") String orderId,
                        @Param("renewProject") String renewProject,
                        @Param("day") Integer day);

    /**
     * 新增疗程卡
     */
    void insertCourseCard(@Param("userId") String userId,
                          @Param("storeId") String storeId,
                          @Param("orderId") String orderId,
                          @Param("renewProject") String renewProject,
                          @Param("courseOfTreatment") Integer courseOfTreatment);

    /**
     * 插入积分流水
     */
    void insertScoreFlow(@Param("userId") String userId,
                         @Param("projectName") String projectName,
                         @Param("storePoint") Double storePoint,
                         @Param("type") Integer type);

    /**
     * 更新order状态
     */
    void updateOrder(String orderId);

    /**
     * 通过UserId获取UserInfo
     */
    UserPojo queryUserInfoWithUserId(String userId);

    /**
     * 通过userId获得宝贝生日
     */
    Date getBabyBirthday(String userId);

    /**
     * 通过userId查询时间卡
     */
    List<CardPojo> queryTimeCard(String userId);

    /**
     * 通过id查询疗程卡
     */
    List<CardPojo> queryCurseCard(String userId);

    /**
     * 通过id获取项目名
     */
    String getProjectName(String projectId);

    /**
     * 插入消费明细
     */
    void insertConsumptionDetail(@Param("userId") String userId,
                                 @Param("projectName") String projectName,
                                 @Param("price") Double price,
                                 @Param("day") Integer day,
                                 @Param("process") Integer process,
                                 @Param("technicianId") String technicianId,
                                 @Param("fee") Double fee,
                                 @Param("reason") String reason,
                                 @Param("orderId") String orderId);

    /**
     * 获取积分流水
     */
    List<ScoreDetailPojo> getScoreDetailList(@Param("userId") String userId,
                                             @Param("type") Integer type);

    /**
     * 更新客户信息
     */
    void updateUserInfo(@Param("userId") String userId,
                        @Param("name") String name,
                        @Param("phone") String phone,
                        @Param("birthday") Date birthday,
                        @Param("sex") String sex,
                        @Param("address") String address);

    /**
     * 更新宝贝生日
     */
    void updateBabyInfo(@Param("userId") String userId,
                        @Param("birthday") Date birthday);

    /**
     * 获取id
     */
    Integer getLastId();

    /**
     * 插入一级项目
     */
    void insertOneProject(@Param("name") String name,
                          @Param("storeId") String storeId,
                          @Param("projectId") String projectId);

    /**
     * 获取二级项目id
     */
    Integer getTwoProjectIdLast();

    /**
     * 插入二级项目
     */
    void insertTwoProject(@Param("name") String name,
                          @Param("storeId") String storeId,
                          @Param("projectId") String projectId,
                          @Param("oneProjectId") String oneProjectId,
                          @Param("price") Double price,
                          @Param("oncePrice") Double oncePrice);

    /**
     * 查询技师详情
     */
    TechnicianPojo queryTechnicianInfo(String technicianId);

    /**
     * 更新技师详情
     */
    void updateTechnicianInfo(@Param("technicianId") String technicianId,
                              @Param("name") String name,
                              @Param("phone") String phone,
                              @Param("level") String level,
                              @Param("grad") String grad,
                              @Param("inTime") Date inTime);

    /**
     * 新增技师
     */
    void insertTechnicianInfo(@Param("storeId") String storeId,
                              @Param("name") String name,
                              @Param("phone") String phone,
                              @Param("level") String level,
                              @Param("id") String id,
                              @Param("grad") String grad,
                              @Param("inTime") Date inTime);

    /**
     * 获取最后一个技师id
     */
    Integer getTechniciansId();

    /**
     * 删除二级项目
     */
    void deleteProjectTwo(String projectId);

    /**
     * 查询门店一级库存
     */
    List<StockOnePojo> queryStockOnePojoList(String storeId);

    /**
     * 查询门店二级库存
     */
    List<StockTwoPojo> queryStockTwoPojoList(@Param("storeId") String storeId,
                                             @Param("id") String id);

    /**
     * 查询公司一级库存
     */
    List<StockOnePojo> queryStockOnePojoListOfCompany(String companyId);

    /**
     * 查询公司二级库存
     */
    List<StockTwoPojo> queryStockTwoPojoListOfCompany(@Param("companyId") String companyId,
                                             @Param("id") String id);

    /**
     * 查询公司所有门店
     */
    List<StoreInfoPojo> queryStoreInfoPojoList(String companyId);

    /**
     * 新增门店账户
     */
    void insertStoreInfo(@Param("storeId") String storeId,
                         @Param("name") String name,
                         @Param("password") String password,
                         @Param("companyId") String companyId);

    /**
     * 查询门店账号是否存在
     */
    int queryStoreInfo(String storeId);

    /**
     * 查询门店业绩
     */
    List<AchievementPojo> getAchievementPojoList(@Param("storeId") String storeId,
                                                 @Param("beginTime") Date beginTime,
                                                 @Param("endTime") Date endTime);

    /**
     * 查询门店本月业绩
     */
    List<Double> getAchievementPojos(String storeId);

    /**
     * 新增一级库存
     */
    void insertStockOne(@Param("companyId") String companyId,
                        @Param("name") String name);

    /**
     * 新增二级库存
     */
    void insertCompanyStockTwo(@Param("companyId") String companyId,
                               @Param("id") String id,
                               @Param("name") String name,
                               @Param("inPrice") Double inPrice,
                               @Param("outPrice") Double outPrice,
                               @Param("number") Integer number,
                               @Param("endTime") Date endTime);

    /**
     * 获取消费明细
     */
    List<ConsumptionDetailPojo> getConsumptionDetailList(String userId);

    /**
     * 疗程卡消费一次
     */
    void updateProcessCard(String cardId);

    /**
     * 获取疗程卡的项目id
     */
    String getProcessCardProjectId(String cardId);

    /**
     * 获取疗程卡限制
     */
    Integer getProcessCardLimit(String cardId);

    /**
     * 获取疗程卡进度
     */
    Integer getProcessCardProcess(String cardId);

    /**
     * 获取时间卡项目id
     */
    String getTimeCardProjectId(String cardId);

    /**
     * 获取时间卡限制
     */
    Integer getTimeCardLimit(String cardId);

    /**
     * 获取时间卡创建时间
     */
    Date getTimeCardCreateTime(String cardId);

    /**
     * 获取时间卡状态
     */
    Integer getTimeCardStatus(Integer cardId);

    /**
     * 获取疗程卡状态
     */
    Integer getCardStatus(Integer cardId);

    /**
     * 更新时间卡状态
     */
    void updateTimeCardStatus(@Param("type") Integer type,
                              @Param("cardId") Integer cardId);

    /**
     * 更新疗程卡状态
     */
    void updateCardStatus(@Param("type") Integer type,
                          @Param("cardId") Integer cardId);

    /**
     * 获取时间卡更新时间
     */
    Date getUpdateTime(String cardId);

    /**
     * 更新时间卡创建时间
     */
    void updateCreateTime(@Param("cardId") String cardId,
                          @Param("createTime") Date createTime);

    /**
     * 查询技师当月开单费
     */
    List<Double> getOrderFeeList(String id);

    /**
     * 查询技师所有开单费
     */
    List<Double> getTotalOrderFeeList(String id);

    /**
     * 查询技师当月手工费
     */
    List<Double> getOnceFeeList(String id);

    /**
     * 查询技师所有手工费
     */
    List<Double> getTotalOnceFeeList(String id);

    /**
     * 获取项目手工费
     */
    Double getProjectFee(String projectId);

    /**
     * 获取技师手工费明细
     */
    List<TechnicianDetailedPojo> getConsumptionDetails(@Param("technicianId") String technicianId,
                                                       @Param("beginTime") Date beginTime,
                                                       @Param("endTime") Date endTime);

    /**
     * 获取技师开单明细
     */
    List<TechnicianDetailedPojo> getOrderDetails(@Param("technicianId") String technicianId,
                                                       @Param("beginTime") Date beginTime,
                                                       @Param("endTime") Date endTime);

    /**
     * 公司出库
     */
    void insertOutInCompany(@Param("storeId") String storeId,
                            @Param("id") String id,
                            @Param("number") Integer number,
                            @Param("companyId") String companyId,
                            @Param("oneId") String oneId,
                            @Param("oneName") String oneName,
                            @Param("twoName") String twoName,
                            @Param("outPrice") Double outPrice,
                            @Param("inPrice") Double inPrice,
                            @Param("opr") String opr,
                            @Param("reason") String reason);

    /**
     * 更新公司库存
     */
    void updateCompanyStockCount(@Param("id") Integer id,
                                 @Param("number") Integer number);

    /**
     * 门店入库
     */
//    StoreInPojo queryStoreInPojo(@Param("number") String number,
//                                 @Param("storeId") String storeId);

    /**
     * 获取一级项目名
     */
    String getOneName(String id);

    /**
     * 门店出入库列表
     */
    List<OutInDetailPojo> getOutInDetailPojoList(@Param("storeId") String storeId,
                                                 @Param("endTime") Date endTime,
                                                 @Param("beginTime") Date beginTime);

    /**
     * 获取一级库存id
     */
    String queryOneId(String id);

    /**
     * 获取一级库存名
     */
    String queryOneName(String id);

    /**
     * 获取二级库存名
     */
    String queryTwoName(String id);

    /**
     * 获取出库价格
     */
    Double queryOutPrice(String id);

    /**
     * 获取公司库存入库价格
     */
    Double queryInPrice(String id);

    /**
     * 门店入库查询详情
     */
    StoreInPojo queryStoreInPojo(@Param("number") String number,
                                 @Param("storeId") String storeId);

    /**
     * 查询库存过期时间
     */
    Date queryEndTime(String id);

    /**
     * 新增门店一级库存
     */
    void insertStockOneStore(@Param("name") String name,
                             @Param("storeId") String storeId);

    /**
     * 查询一级门店库存id
     */
    Integer queryStockId(@Param("storeId") String storeId,
                         @Param("name") String name);

    /**
     * 新增门店二级库存
     */
    void insertStockTwoStore(@Param("oneId") String oneId,
                             @Param("twoName") String twoName,
                             @Param("storeId") String storeId,
                             @Param("fee") Double fee,
                             @Param("number") Integer number,
                             @Param("endTime") Date endTime,
                             @Param("idNumber") String idNumber);

    /**
     * 更新公司出入库状态
     */
    void updateOutInCompany(@Param("id") String id,
                            @Param("storeId") String storeId);

    /**
     * 查询核销
     */
    int queryConsumption(@Param("userId") String userId,
                         @Param("day") Integer day);

    /**
     * 插入待核销队列
     */
    void insertConsumptionOrder(@Param("userId") String userId,
                                @Param("storeId") String storeId,
                                @Param("cardId") String cardId,
                                @Param("projectName") String projectName,
                                @Param("status") Integer status,
                                @Param("type") Integer type);

    /**
     * 刷新待核销队列
     */
    void updateConsumptionOrder(@Param("id") Integer id,
                                @Param("status") Integer status);

    /**
     * 查询待核销
     */
    Integer getConsumptionOrderId(@Param("userId") String userId,
                                  @Param("storeId") String storeId,
                                  @Param("cardId") String cardId,
                                  @Param("projectName") String projectName,
                                  @Param("status") Integer status,
                                  @Param("type") Integer type);

    /**
     * 插入积分待消费队列
     */
    void insertScoreWait(@Param("userId") String userId,
                         @Param("storeId") String storeId,
                         @Param("name") String name,
                         @Param("status") Integer status,
                         @Param("score") Double score);

    /**
     * 获取待消费积分id
     */
    Integer getScoreWaitId(@Param("userId") String userId,
                           @Param("storeId") String storeId,
                           @Param("name") String name,
                           @Param("status") Integer status,
                           @Param("score") Double score);

    /**
     * 更新待消费积分
     */
    void updateScoreWait(@Param("id") Integer id,
                         @Param("status") Integer status);

    /**
     * 查询公司库存剩余量
     */
    Integer queryCompanyStockCount(Integer id);

    /**
     * 查询门店是否存在该批号
     */
    Integer queryStoreStock(@Param("number") String number,
                            @Param("storeId") String storeId);

    /**
     * 更新门店库存
     */
    void updateStock(@Param("count") Integer count,
                     @Param("number") String number);

    /**
     * 插入门店出入库记录
     */
    void insertOutInStore(@Param("oneId") String oneId,
                          @Param("oneName") String oneName,
                          @Param("twoId") String twoId,
                          @Param("twoName") String twoName,
                          @Param("operation") String operation,
                          @Param("storeId") String storeId,
                          @Param("price") Double price,
                          @Param("number") Integer number,
                          @Param("reason") String reason);

    /**
     * 查询门店库存id
     */
    Integer getStoreStockId(@Param("number") String number,
                         @Param("storeId") String storeId);

    /**
     * 查询门店一级库存id
     */
    String getStoreStockOneId(Integer twoId);

    /**
     * 查询门店一级库存名
     */
    String getStoreStockOneName(Integer id);

    /**
     * 查询门店库存数量
     */
    Integer gerStoreStockNumber(@Param("id") String id,
                                @Param("storeId") String storeId);

    /**
     * 更新门店库存
     */
    void updateStoreStock(@Param("storeId") String storeId,
                          @Param("id") String id,
                          @Param("number") Integer count);

    /**
     * 查询门店库存二级名
     */
    String getTwoNameStockStore(@Param("id") String id,
                                @Param("storeId") String storeId);

    /**
     * 查询门店一级库存是否存在
     */
    int queryStoreStockOne(@Param("name") String name,
                           @Param("storeId") String storeId);

    /**
     * 查询公司出入库流水
     */
    List<OutInDetailPojo> queryCompanyOutIn(@Param("companyId") String companyId,
                                            @Param("beginTime") Date beginTime,
                                            @Param("endTime") Date endTime);

    /**
     * 查询门店名
     */
    String getStoreName(String storeId);

    /**
     * 获取公司库存二级id
     */
    Integer getTwoIdCompany(@Param("companyId") String companyId,
                            @Param("id") String id,
                            @Param("name") String name);

    /**
     * 获取用户名
     */
    String getUserName(String userId);

    /**
     * 获取用户手机号
     */
    String getUserPhone(String userId);

    /**
     * 插入门店业绩
     */
    void insertAchievement(@Param("cardId") String cardId,
                           @Param("projectName") String projectName,
                           @Param("price") Double price,
                           @Param("userName") String userName,
                           @Param("userPhone") String userPhone,
                           @Param("reason") String reason,
                           @Param("technicianIds") String technicianId,
                           @Param("type") String type,
                           @Param("storeId") String storeId);

    /**
     * 获取时间卡id
     */
    Integer queryCardIdTime(@Param("userId") String userId,
                           @Param("storeId") String storeId,
                           @Param("orderId") String orderId);

    /**
     * 获取疗程卡id
     */
    Integer queryCardId(@Param("userId") String userId,
                        @Param("storeId") String storeId,
                        @Param("orderId") String orderId);

    /**
     * 获取时间卡订单id
     */
    String getTimeCardOrderId(Integer cardId);

    /**
     * 获取疗程卡订单id
     */
    String getCardOrderId(Integer cardId);

    /**
     * 更新生单金额
     */
    void updateOrderPrice(@Param("price") Double price,
                          @Param("id") Integer id);

    /**
     * 查询门店未结束时间卡
     */
    List<CardPojo> queryTimeCardList(String storeId);

    /**
     * 查询门店未结束疗程卡
     */
    List<CardPojo> queryCardList(String storeId);

    /**
     * 查询生单金额
     */
    Double getPriceWithOrder(Integer orderId);

    /**
     * 更新门店一级项目名
     */
    void updateProjectOne(@Param("id") String id,
                          @Param("name") String name);

    /**
     * 修改门店一级库存名
     */
    void updateStockStoreOne(@Param("id") Integer id,
                             @Param("name") String name);

    /**
     * 修改公司一级库存名
     */
    void updateCompanyStockOne(@Param("id") Integer id,
                             @Param("name") String name);

    /**
     * 导出公司结余
     */
    List<StockTwoPojo> querycompanyStockRemain(String companyId);

    /**
     * 更新核销表金额
     */
    void updateConsumptionDetailPrice(@Param("id") String id,
                                 @Param("fee") Double fee);

    /**
     * 更新业绩金额
     */
    void updateAchievementPrice(@Param("cardId") String cardId,
                                @Param("fee") Double fee,
                                @Param("reason") String reason);
}
