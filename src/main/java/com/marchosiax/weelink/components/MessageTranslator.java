package com.marchosiax.weelink.components;

import com.marchosiax.weelink.error.AppError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageTranslator {

    private final ResourceBundleMessageSource messageSource;

    @Value("${app.error.locale}")
    private String localeCode;
    private Locale locale;

    public MessageTranslator(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String translate(AppError error, Object... args) {
        return messageSource.getMessage("ERROR_" + error.getCode(), args, error.name(), getLocale());
    }

    private Locale getLocale() {
        if (locale == null)
            locale = new Locale(localeCode);
        return locale;
    }

}
