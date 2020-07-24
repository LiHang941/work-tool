package com.github.lihang941.tool.common.exception;

import java.text.MessageFormat;

/**
 * 通用业务异常
 *
 * @author : lihang1329@gmail.com
 */
public class ErrorMsgException extends RuntimeException {


    /**
     * 错误内容
     */
    private String errorMessage;

    /**
     * 错误码
     */
    private int code = 400;

    /**
     * 其他需要携带的备注参数
     */
    private Object params;

    /**
     * 支持 MessageFormat.format(errorMessage, args)
     *
     * @param errorMessage 异常内容
     * @param args         参数
     */
    public ErrorMsgException(String errorMessage, Object... args) {
        super(errorMessage);
        if (args == null || args.length == 0) {
            this.errorMessage = errorMessage;
        } else {
            this.errorMessage = MessageFormat.format(errorMessage, args);
        }

    }

    /**
     * 带错误码的异常
     *
     * @param code         错误码
     * @param errorMessage 异常内容
     * @param args         参数
     */
    public ErrorMsgException(int code, String errorMessage, Object... args) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.code = code;
        if (args == null || args.length == 0) {
            this.errorMessage = errorMessage;
        } else {
            this.errorMessage = MessageFormat.format(errorMessage, args);
        }
    }

    public Object getParams() {
        return params;
    }


    public ErrorMsgException setParams(Object params) {
        this.params = params;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public int getCode() {
        return code;
    }

}
