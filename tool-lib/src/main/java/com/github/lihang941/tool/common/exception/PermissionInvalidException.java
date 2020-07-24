package com.github.lihang941.tool.common.exception;

/**
 * 鉴权失败异常
 * User: lihang1329@gmail.com
 */
public class PermissionInvalidException extends RuntimeException {

    private String errorMessage;

    public PermissionInvalidException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public PermissionInvalidException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
