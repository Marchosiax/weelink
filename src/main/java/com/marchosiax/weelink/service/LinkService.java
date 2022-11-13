package com.marchosiax.weelink.service;

import com.marchosiax.weelink.components.DTOMapper;
import com.marchosiax.weelink.dto.LinkData;
import com.marchosiax.weelink.error.AppError;
import com.marchosiax.weelink.model.Link;
import com.marchosiax.weelink.model.enums.LinkType;
import com.marchosiax.weelink.repository.LinkRepository;
import com.marchosiax.weelink.utils.AliasGenerator;
import com.marchosiax.weelink.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final DTOMapper dtoMapper;

    @Value("${app.link.alias-length}")
    private int aliasLength;

    public LinkService(LinkRepository linkRepository, DTOMapper dtoMapper) {
        this.linkRepository = linkRepository;
        this.dtoMapper = dtoMapper;
    }

    public LinkData create(
            String alias,
            String origin,
            String password,
            LocalDateTime expiration,
            LocalDateTime availability,
            LinkType linkType
    ) throws Throwable {
        //TODO validate origin
        var finalAlias = alias;
        if (finalAlias == null || finalAlias.isBlank() || finalAlias.isEmpty()) {
            finalAlias = AliasGenerator.generate(aliasLength);
        } else {
            var link = linkRepository.findByAlias(alias);
            if (link.isPresent())
                throw AppError.ALIAS_ALREADY_TAKEN.exception();
        }
        var now = LocalDateTime.now();
        if (expiration != null && expiration.isBefore(now))
            throw AppError.INVALID_DATE.exception();

        var linkEntity = switch (linkType) {
            case PLAIN -> storePlainLink(finalAlias, origin, expiration, availability);
            case PASSWORD_PROTECTED -> storePasswordProtectedLink(finalAlias, origin, password, expiration, availability);
        };

        return dtoMapper.linkAsLinkData(linkEntity);
    }

    public LinkData getLinkData(String alias) throws Throwable {
        var link = linkRepository.findByAlias(alias).orElseThrow(AppError.LINK_NOT_FOUND::exception);
        return dtoMapper.linkAsLinkData(link);
    }

    private Link storePlainLink(
            String alias,
            String origin,
            LocalDateTime expiration,
            LocalDateTime availability
    ) {
        return linkRepository.save(
                new Link(alias, origin, null, expiration, availability, LinkType.PLAIN)
        );
    }

    private Link storePasswordProtectedLink(
            String alias,
            String origin,
            String password,
            LocalDateTime expiration,
            LocalDateTime availability
    ) throws Throwable {
        if (password == null || password.isEmpty())
            throw AppError.PASSWORD_NOT_DEFINED.exception();

        var hashedPassword = SecurityUtils.hashSHA256(password);
        //var encryptedOrigin = SecurityUtils.encryptAES(origin, password);
        return linkRepository.save(
                new Link(alias, origin, hashedPassword, expiration, availability, LinkType.PASSWORD_PROTECTED)
        );
    }

}
