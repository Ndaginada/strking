package com.kkai.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
@JsonPropertyOrder(value = {"code","hint", "event", "data"})//此注解意思为:定义json数据的排序
public class RespMsg<T> {
    @JsonIgnore
    private static final Logger logger = LoggerFactory.getLogger(RespMsg.class);

    public int code = 0;
    //开发调用信息
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String msg = null;
    //给客户端的提示
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String hint = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)//此注解意思为:如果为空则不会返回这个字段
    public T data = null;

    public RespMsg() {
        this.code = RespStatus.OK.code();
    }

    public RespMsg(RespStatus respStatus) {
        this.code = respStatus.code();
        this.msg = respStatus.message();
    }

    public void setRespStatus(RespStatus respStatus) {
        this.code = respStatus.code();
        this.msg = respStatus.message();
    }

    @JsonIgnore
    public Boolean isOK() {
        return this.code == RespStatus.OK.code();
    }

    public RespMsg(RespStatus respStatus, String msg) {
        this(respStatus.code(), msg, msg);
    }

    public RespMsg(int code) {
        this(code, null, null);
    }


    public RespMsg(int code, String msg) {
        this(code, msg, msg);
    }


//    public RespMsg(int code, String event, Object data) {
//        this.code = code;
//        this.event = event;
//        this.data = data;
//    }

    public RespMsg(int code, String msg, String hint) {
        this.code = code;
        this.msg = msg;
        this.hint = hint;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        if (logger.isDebugEnabled()) {
            return msg;
        } else {
            return null;
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
