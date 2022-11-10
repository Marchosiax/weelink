package com.marchosiax.weelink.error;

import org.springframework.http.HttpStatus;

public enum AppError {

    INTERNAL_SERVER_ERROR(1001, HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(1002, HttpStatus.UNAUTHORIZED),
    BAD_REQUEST(1003, HttpStatus.BAD_REQUEST),
    NOT_FOUND(1004, HttpStatus.NOT_FOUND),
    FORBIDDEN(1005, HttpStatus.FORBIDDEN),

    ALIAS_ALREADY_TAKEN(2001, HttpStatus.BAD_REQUEST),
    SPACE_NOT_FOUND(2002, HttpStatus.NOT_FOUND),
    INVALID_DATE(2003, HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_DEFINED(2004, HttpStatus.BAD_REQUEST);

    private int code;
    private HttpStatus status;

    AppError(int code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Throwable exception() {
        return new AppException(this);
    }

    public Throwable exception(Object... messageArgs) {
        return new AppException(this, messageArgs);
    }
}
