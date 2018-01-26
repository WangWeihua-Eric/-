package com.eric.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CallDirection {
    
    SEND(1, "send", "发送"),
    RECEIVE(2, "receive", "接受");
    
    /**
     * 枚举MAP形式
     */
    private static final Map<Integer, CallDirection> map = new HashMap<Integer, CallDirection>();
    
    static {
        for(CallDirection s : EnumSet.allOf(CallDirection.class)){
            map.put(s.getIndex(), s);
        }
    }
    
    /**
     * 索引
     */
    private Integer index;
    
    /**
     * 值
     */
    private String value;
    
    /**
     * 备注
     */
    private String remark;

    private CallDirection(Integer index, String value, String remark){
        this.index = index;
        this.value = value;
        this.remark = remark;
    }

    public Integer getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    /**
     * 根据索引获取枚举
     * @param value
     * @return
     */
    public static CallDirection get(int index) {
        return map.get(index);
    }

    @Override
    public String toString() {
        return value;
    }
    
}
