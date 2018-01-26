package com.eric.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CallType {

    HTTP(1, "http", "发送"),
    DUBBO(2, "dubbo", "接受"),
    QMQ(3,"qmq","接受");
    
    /**
     * 枚举MAP形式
     */
    private static final Map<Integer, CallType> map = new HashMap<Integer, CallType>();
    
    static {
        for(CallType s : EnumSet.allOf(CallType.class)){
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

    private CallType(Integer index, String value, String remark){
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
    public static CallType get(int index) {
        return map.get(index);
    }

    @Override
    public String toString() {
        return value;
    }
    
}
