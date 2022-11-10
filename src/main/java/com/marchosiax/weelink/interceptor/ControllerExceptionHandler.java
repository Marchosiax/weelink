package com.marchosiax.weelink.interceptor;

import com.marchosiax.weelink.components.MessageTranslator;
import com.marchosiax.weelink.error.AppError;
import com.marchosiax.weelink.error.AppException;
import com.marchosiax.weelink.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    private final MessageTranslator translator;

    public ControllerExceptionHandler(MessageTranslator translator) {
        this.translator = translator;
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException e) {
        LOGGER.error("App error", e);
        return createResponse(e.getError(), e.getMessageArgs());
    }

    private ResponseEntity<ErrorResponse> createResponse(AppError e, Object... messageArgs) {
        return ResponseEntity.status(e.getStatus())
                .body(new ErrorResponse(e.getCode(), e.name(), translator.translate(e, messageArgs)));
    }

}
