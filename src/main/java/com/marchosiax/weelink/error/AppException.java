package com.marchosiax.weelink.error;

public class AppException extends RuntimeException {

    private AppError error;

    public AppException(AppError error) {
        super(error.name());
        this.error = error;
    }

    public AppError getError() {
        return error;
    }

    public void setError(AppError error) {
        this.error = error;
    }
}
