package com.github.lihang941.tool.common.exception;

/**
 * Token鉴权失败异常
 * User: lihang1329@gmail.com
 */
public class TokenInvalidException extends RuntimeException {

    private String errorMessage;

    public TokenInvalidException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public TokenInvalidException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
