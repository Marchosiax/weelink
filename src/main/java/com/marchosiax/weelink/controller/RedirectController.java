package com.marchosiax.weelink.controller;

import com.marchosiax.weelink.service.LinkService;
import com.marchosiax.weelink.service.LinkStatService;
import com.marchosiax.weelink.utils.HttpReqRespUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {

    private final LinkService linkService;
    private final LinkStatService linkStatService;

    public RedirectController(LinkService linkService, LinkStatService linkStatService) {
        this.linkService = linkService;
        this.linkStatService = linkStatService;
    }

    @GetMapping("/{alias}")
    public void open(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String alias,
            @RequestParam(required = false) String password
    ) throws Throwable {
        var ip = HttpReqRespUtils.getRequestIP();
        var host = request.getRemoteHost();
        var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        var link = linkService.openLink(alias, password);

        linkStatService.recordHit(link.id(), ip, host, userAgent);
        response.sendRedirect(link.origin());
    }

}
