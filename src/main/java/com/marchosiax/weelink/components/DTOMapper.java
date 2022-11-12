package com.marchosiax.weelink.components;

import com.marchosiax.weelink.dto.LinkData;
import com.marchosiax.weelink.model.Link;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    @Value("${app.link.domain}")
    private String domain;

    public LinkData linkAsLinkData(Link link){
        var alias = link.getAlias();
        var spaceLabel = link.getSpace().getLabel();
        String fullLink;
        if (spaceLabel.equals("default"))
            fullLink = domain + "/" + alias;
        else
            fullLink = domain + "/" + spaceLabel + "/" + alias;

        return new LinkData(
                link.getUuid(),
                fullLink,
                fullLink.replace("https://", "").replace("http://", ""),
                spaceLabel,
                link.getOrigin(),
                link.getExpirationTime(),
                link.getAvailabilityTime(),
                link.getType()
        );
    }

}
