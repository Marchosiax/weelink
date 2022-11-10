package com.marchosiax.weelink.service;

import com.marchosiax.weelink.data.LinkData;
import com.marchosiax.weelink.model.Space;
import com.marchosiax.weelink.model.enums.LinkType;
import com.marchosiax.weelink.repository.LinkAuthenticationRepository;
import com.marchosiax.weelink.repository.LinkRepository;
import com.marchosiax.weelink.repository.SpaceRepository;
import com.marchosiax.weelink.utils.AliasGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final SpaceRepository spaceRepository;
    private final LinkAuthenticationRepository authenticationRepository;

    @Value("${app.link.domain}")
    private String domain;
    @Value("${app.link.alias-length}")
    private int aliasLength;

    public LinkService(LinkRepository linkRepository, SpaceRepository spaceRepository, LinkAuthenticationRepository authenticationRepository) {
        this.linkRepository = linkRepository;
        this.spaceRepository = spaceRepository;
        this.authenticationRepository = authenticationRepository;
    }

    public LinkData create(String alias,
                           String origin,
                           String space,
                           String password,
                           LocalDateTime expiration,
                           LocalDateTime availability,
                           LinkType linkType) {
        //TODO invalidate origin
        var finalAlias = alias;
        if (finalAlias == null || finalAlias.isBlank() || finalAlias.isEmpty()) {
            finalAlias = AliasGenerator.generate(aliasLength);
        }

        Space spaceEntity;
        if (space == null) {
            spaceEntity = spaceRepository.getReferenceById()
        }
    }

}
