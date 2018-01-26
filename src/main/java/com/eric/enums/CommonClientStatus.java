package com.eric.enums;

/**
 * @author junwei.yu
 * @date 2016/6/17 17:30
 */
public enum CommonClientStatus {

    OK(0, "成功"), // 成功
    SWIFT_UPLOAD_ERROR(101, "swift文件上传失败"), // swift文件上传失败
    SWIFT_NO_FILE_ERROR(102, "swift不存在该文件"), // swift不存在该文件
    SFTP_DOWNLOAD_ERROR(103, "sftp文件下载失败"), // sftp文件下载失败
    ;

    private Integer code;
    private String des;

    private CommonClientStatus(Integer code, String des) {
        this.code = code;
        this.des = des;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
