package com.marchosiax.weelink.controller;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import com.marchosiax.weelink.service.LinkManagerService;
import com.marchosiax.weelink.utils.HttpReqRespUtils;
import jdk.jfr.Frequency;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RedirectController {

    private final LinkManagerService linkManagerService;
    private final UserAgentParser userAgentParser;

    public RedirectController(LinkManagerService linkManagerService) throws IOException, ParseException {
        this.linkManagerService = linkManagerService;
        this.userAgentParser = new UserAgentService().loadParser();
    }

    @GetMapping("/{alias}")
    public void open(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String alias,
            @RequestParam(required = false)
            String password
    ) throws Throwable {
        var ip = HttpReqRespUtils.getRequestIP();
        var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        var agentInfo = userAgentParser.parse(userAgent);
        var origin = linkManagerService.openLink(alias, password, ip);
        response.sendRedirect(origin);
    }

}
