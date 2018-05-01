package com.eric.pojo;

import com.qunar.mobile.car.qb.drivcommon.utils.ParamUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangweihua on 2018/4/30.
 */
public class ConvertCardDetailReqPojo {
    private String phone;
    private String card_no;
    private String source_remark;
    private Integer point;

    public String getSource_remark() {
        return source_remark;
    }

    public void setSource_remark(String source_remark) {
        this.source_remark = source_remark;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if(phone != null){
            map.put("phone", phone);
        }
        if(card_no != null){
            map.put("card_no", card_no);
        }
        if(source_remark != null){
            map.put("source_remark", source_remark);
        }
        if(point != null){
            map.put("point", point);
        }
        return map;
    }
}
