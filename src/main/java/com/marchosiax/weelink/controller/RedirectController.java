package com.marchosiax.weelink.controller;

import com.marchosiax.weelink.service.LinkManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {

    private final LinkManagerService linkManagerService;

    public RedirectController(LinkManagerService linkManagerService) {
        this.linkManagerService = linkManagerService;
    }

    @GetMapping("/{alias}")
    public void open(
            HttpServletResponse response,
            @PathVariable String alias,
            @RequestParam(required = false)
            String password
    ) throws Throwable {
        var origin = linkManagerService.openLink(alias, password);
        response.sendRedirect(origin);
    }

}
