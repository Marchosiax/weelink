package com.marchosiax.weelink.error;

import java.util.Date;

public record ErrorResponse(int code, String error, String message, long timestamp) {

    public ErrorResponse(int code, String error, String message) {
        this(code, error, message, new Date().getTime());
    }
}
