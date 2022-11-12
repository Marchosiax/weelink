package com.marchosiax.weelink.controller;

import com.marchosiax.weelink.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {

    private final LinkService linkService;

    public RedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/{alias}")
    public void redirect(HttpServletResponse response, @PathVariable String alias) throws Throwable {
        var origin = linkService.getOriginLink(alias, null);
        response.sendRedirect(origin);
    }

}
