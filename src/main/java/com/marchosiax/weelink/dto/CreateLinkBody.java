package com.marchosiax.weelink.dto;

import com.marchosiax.weelink.model.enums.LinkType;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public record CreateLinkBody(
        String alias,
        @NonNull
        @URL
        String origin,
        String space,
        String password,
        LocalDateTime expirationTime,
        LocalDateTime availabilityTime,
        @NonNull
        LinkType linkType
) {
}
