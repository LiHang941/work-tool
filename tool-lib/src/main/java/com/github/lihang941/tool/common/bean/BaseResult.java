package com.github.lihang941.tool.common.bean;

/**
 * @author : lihang1329@gmail.com
 */
public class BaseResult<T> {

    private int code;
    private String msg;
    private T data;

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(T data) {
        this.code = 200;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public BaseResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
