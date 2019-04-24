package com.kkai.oauth.enity;

/**
 */
public enum RespStatus {
    /**
     * 成功和失败
     */
    OK(1, "成功"),
    /**
     * 通用错误
     */
    BAD_REQUEST(400, "参数错误"),

    UNAUTHORIZED(401, "用户未授权"),
    FORBIDDEN(403, "拒绝访问"),
    NOT_FOUND(404, "不存在"),
    NOT_ACCEPTABLE(406, "无法接受此请求"),
    DUPLICATION(408, "重复"), // 举例：ID、用户名等已存在
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    OUT_OF_RANGE(509, "访问频率被限制"),
    COMMON_ERROR(600, "业务逻辑错误"),
    BAD_USER(601,"用户名密码错误"),
    /**
     * 临时错误
     */
    TODO(900, "业务正在开发中");




    private int code;

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    RespStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Return the integer value of this status code.
     */
    public int code() {
        return this.code;
    }

    /**
     * Return the string value of this status message.
     */
    public String message() {
        return this.message;
    }

}
