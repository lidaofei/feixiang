package com.feixiang.spring.web.common;

import java.io.Serializable;

/**
 * @Author: lidaofei
 * @Date: 2019/7/11 15:49
 * @Description:
 */
public class Result implements Serializable {
    private Integer code;

    private String message;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
