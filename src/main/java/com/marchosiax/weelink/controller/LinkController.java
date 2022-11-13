package com.marchosiax.weelink.controller;

import com.marchosiax.weelink.dto.CreateLinkBody;
import com.marchosiax.weelink.dto.LinkData;
import com.marchosiax.weelink.service.LinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/link")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public LinkData createLink(@RequestBody CreateLinkBody body) throws Throwable {
        //TODO validate alias chars
        return linkService.create(
                body.alias(),
                body.origin(),
                body.password(),
                body.expirationTime(),
                body.availabilityTime(),
                body.linkType()
        );
    }

}
