package com.marchosiax.weelink.service;

import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import com.marchosiax.weelink.error.AppError;
import com.marchosiax.weelink.model.LinkHit;
import com.marchosiax.weelink.repository.LinkHitRepository;
import com.marchosiax.weelink.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LinkStatService {

    private final LinkRepository linkRepository;
    private final LinkHitRepository linkHitRepository;
    private final UserAgentParser userAgentParser;

    public LinkStatService(LinkRepository linkRepository, LinkHitRepository linkHitRepository) throws IOException, ParseException {
        this.linkRepository = linkRepository;
        this.linkHitRepository = linkHitRepository;
        this.userAgentParser = new UserAgentService().loadParser();
    }

    public void recordHit(String linkUUID, String ip, String host, String agentHeader)
            throws Throwable {
        var link = linkRepository.findByUuid(linkUUID).orElseThrow(AppError.LINK_NOT_FOUND::exception);
        var props = parseAgent(agentHeader);
        var linkHit = new LinkHit(link, ip, host, props.deviceType, props.os, props.browser);
        linkHitRepository.save(linkHit);
    }

    private CallerProps parseAgent(String agent) {
        var agentInfo = userAgentParser.parse(agent);
        return new CallerProps(
                agentInfo.getDeviceType(),
                agentInfo.getPlatform(),
                agentInfo.getBrowser()
        );
    }

    private static record CallerProps(String deviceType, String os, String browser) {
    }

}
