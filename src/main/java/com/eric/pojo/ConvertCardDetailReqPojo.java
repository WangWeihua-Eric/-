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
        map.put("phone", phone);
        map.put("card_no", card_no);
        return map;
    }
}
