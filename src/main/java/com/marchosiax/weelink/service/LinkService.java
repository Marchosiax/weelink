package com.marchosiax.weelink.service;

import com.marchosiax.weelink.data.LinkData;
import com.marchosiax.weelink.error.AppError;
import com.marchosiax.weelink.model.Link;
import com.marchosiax.weelink.model.Space;
import com.marchosiax.weelink.model.enums.LinkType;
import com.marchosiax.weelink.repository.LinkRepository;
import com.marchosiax.weelink.repository.SpaceRepository;
import com.marchosiax.weelink.utils.AliasGenerator;
import com.marchosiax.weelink.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final SpaceRepository spaceRepository;

    @Value("${app.link.domain}")
    private String domain;
    @Value("${app.link.alias-length}")
    private int aliasLength;

    public LinkService(LinkRepository linkRepository, SpaceRepository spaceRepository) {
        this.linkRepository = linkRepository;
        this.spaceRepository = spaceRepository;
    }

    public LinkData create(String alias,
                           String origin,
                           String space,
                           String password,
                           LocalDateTime expiration,
                           LocalDateTime availability,
                           LinkType linkType) throws Throwable {
        //TODO validate origin
        var finalAlias = alias;
        if (finalAlias == null || finalAlias.isBlank() || finalAlias.isEmpty()) {
            finalAlias = AliasGenerator.generate(aliasLength);
        } else {
            var link = linkRepository.findByAlias(alias);
            if (link.isPresent())
                throw AppError.ALIAS_ALREADY_TAKEN.exception();
        }

        Space spaceEntity;
        if (space == null)
            spaceEntity = spaceRepository.getDefault().orElseThrow(AppError.SPACE_NOT_FOUND::exception);
        else
            spaceEntity = spaceRepository.findByLabel(space).orElseThrow(AppError.SPACE_NOT_FOUND::exception);

        var now = LocalDateTime.now();
        if (expiration.isBefore(now))
            throw AppError.INVALID_DATE.exception();

        if (linkType == LinkType.PASSWORD_PROTECTED && (password == null || password.isEmpty()))
            throw AppError.PASSWORD_NOT_DEFINED.exception();

        var hashedPassword = SecurityUtils.hashSHA256(password);
        var linkEntity = linkRepository.save(
                new Link(spaceEntity, finalAlias, origin, hashedPassword, expiration, availability, linkType)
        );

        String fullLink;
        if (space == null)
            fullLink = domain + "/" + finalAlias;
        else
            fullLink = domain + "/" + space + "/" + finalAlias;

        return new LinkData(
                linkEntity.getUuid(),
                fullLink,
                fullLink.replace("https://", "").replace("http://", ""),
                space,
                origin,
                expiration,
                availability,
                linkType
        );
    }

}
