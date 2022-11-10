package com.marchosiax.weelink.error;

public class AppException extends RuntimeException {

    private AppError error;
    private Object[] messageArgs;

    public AppException(AppError error) {
        super(error.name());
        this.error = error;
    }

    public AppException(AppError error, Object... messageArgs) {
        super(error.name());
        this.error = error;
        this.messageArgs = messageArgs;
    }

    public AppError getError() {
        return error;
    }

    public void setError(AppError error) {
        this.error = error;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public void setMessageArgs(Object[] messageArgs) {
        this.messageArgs = messageArgs;
    }
}
