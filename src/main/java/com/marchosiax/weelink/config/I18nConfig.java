package com.marchosiax.weelink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class I18nConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        var bundle = new ResourceBundleMessageSource();
        bundle.setBasename("i18n/message");
        bundle.setDefaultEncoding("UTF-8");
        bundle.setUseCodeAsDefaultMessage(true);
        return bundle;
    }

}
