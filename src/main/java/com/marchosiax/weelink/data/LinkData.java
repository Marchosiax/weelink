package com.marchosiax.weelink.data;

import com.marchosiax.weelink.model.enums.LinkType;

import java.time.LocalDateTime;

public record LinkData(
        String id,
        String fullLink,
        String briefLink,
        String space,
        String origin,
        LocalDateTime expirationTime,
        LocalDateTime availabilityTime,
        LinkType linkType
) {
}
