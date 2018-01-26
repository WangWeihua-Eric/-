package com.eric.result;

/**
 * 错误码定义
 * 
 * @author shuaishuai.yang
 * 
 */
public enum BStatusCode {
    SUCCESS(0, "成功"), FAIL_DBIO(1,"数据库读写出错"),ALREARDY_SIGHUP(2,"该用户已经注册");
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

    BStatusCode(int code, String des) {
        this.code = code;
        this.des = des;
    }

}
