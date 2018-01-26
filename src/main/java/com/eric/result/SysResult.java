package com.eric.result;

import java.util.HashMap;

/**
 * 系统统一返回值
 * 
 * @author linbin.shi
 * 
 */
public class SysResult {
    private Object data = new HashMap<String, Object>();

    public Object getData() {
        return data;
    }

    public BStatus getBstatus() {
        return bstatus;
    }

    private final BStatus bstatus = new BStatus();

    public void setData(Object data) {
        this.data = data;
    }

    public void setBstatus(BStatusCode BStatusCod) {
        this.bstatus.setCode(BStatusCod.getCode());
        this.bstatus.setDes(BStatusCod.getDes());
    }

    public void setBstatus(Integer code, String des) {
        this.bstatus.setCode(code);
        this.bstatus.setDes(des);
    }

    public static class BStatus {
        private int code = 0;
        private String des = "成功";

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

    }

}
