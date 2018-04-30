package com.eric.rpc;

import com.eric.pojo.ConvertCardDetailDataRsp;

/**
 * Created by wangweihua on 2018/4/30.
 */
public class ConvertCardDetailRsp {
    private Integer code;
    private ConvertCardDetailDataRsp data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ConvertCardDetailDataRsp getData() {
        return data;
    }

    public void setData(ConvertCardDetailDataRsp data) {
        this.data = data;
    }
}
