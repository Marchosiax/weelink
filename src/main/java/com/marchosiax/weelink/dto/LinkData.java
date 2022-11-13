package com.marchosiax.weelink.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marchosiax.weelink.model.enums.LinkType;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LinkData(
        String id,
        String fullLink,
        String briefLink,
        String origin,
        LocalDateTime expirationTime,
        LocalDateTime availabilityTime,
        LinkType linkType
) {
}
