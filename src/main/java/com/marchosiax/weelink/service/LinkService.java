package com.marchosiax.weelink.service;

import com.marchosiax.weelink.components.DTOMapper;
import com.marchosiax.weelink.dto.LinkData;
import com.marchosiax.weelink.error.AppError;
import com.marchosiax.weelink.model.Link;
import com.marchosiax.weelink.model.Space;
import com.marchosiax.weelink.model.enums.LinkType;
import com.marchosiax.weelink.repository.LinkRepository;
import com.marchosiax.weelink.repository.SpaceRepository;
import com.marchosiax.weelink.utils.AliasGenerator;
import com.marchosiax.weelink.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final SpaceRepository spaceRepository;
    private final DTOMapper dtoMapper;

    @Value("${app.link.alias-length}")
    private int aliasLength;

    public LinkService(LinkRepository linkRepository, SpaceRepository spaceRepository, DTOMapper dtoMapper) {
        this.linkRepository = linkRepository;
        this.spaceRepository = spaceRepository;
        this.dtoMapper = dtoMapper;
    }

    public LinkData create(
            String alias,
            String origin,
            String space,
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

        Space spaceEntity;
        if (space == null)
            spaceEntity = spaceRepository.getDefault().orElseThrow(AppError.SPACE_NOT_FOUND::exception);
        else
            spaceEntity = spaceRepository.findByLabel(space).orElseThrow(AppError.SPACE_NOT_FOUND::exception);

        var now = LocalDateTime.now();
        if (expiration != null && expiration.isBefore(now))
            throw AppError.INVALID_DATE.exception();

        var linkEntity = switch (linkType) {
            case PLAIN -> storePlainLink(finalAlias, origin, spaceEntity, expiration, availability);
            case PASSWORD_PROTECTED -> storePasswordProtectedLink(finalAlias, origin, spaceEntity, password, expiration, availability);
        };

        return dtoMapper.linkAsLinkData(linkEntity);
    }

    public LinkData getLinkData(String alias) throws Throwable {
        var link = linkRepository.findByAlias(alias).orElseThrow(AppError.LINK_NOT_FOUND::exception);
        return dtoMapper.linkAsLinkData(link);
    }

    public String getRedirect(String alias, String password) throws Throwable {
        var link = linkRepository.findByAlias(alias)
                .orElseThrow(AppError.ALIAS_ALREADY_TAKEN::exception);

        if (link.getType() == LinkType.PASSWORD_PROTECTED) {
            if (password == null || password.isEmpty())
                throw AppError.INCORRECT_LINK_PASSWORD.exception();

            var hashedPassword = SecurityUtils.hashSHA256(password);
            if (!hashedPassword.equals(password))
                throw AppError.INCORRECT_LINK_PASSWORD.exception();
        }

        return link.getOrigin();
    }

    private Link storePlainLink(
            String alias,
            String origin,
            Space space,
            LocalDateTime expiration,
            LocalDateTime availability
    ) {
        return linkRepository.save(
                new Link(space, alias, origin, null, expiration, availability, LinkType.PLAIN)
        );
    }

    private Link storePasswordProtectedLink(
            String alias,
            String origin,
            Space space,
            String password,
            LocalDateTime expiration,
            LocalDateTime availability
    ) throws Throwable {
        if (password == null || password.isEmpty())
            throw AppError.PASSWORD_NOT_DEFINED.exception();

        var hashedPassword = SecurityUtils.hashSHA256(password);
        //var encryptedOrigin = SecurityUtils.encryptAES(origin, password);
        return linkRepository.save(
                new Link(space, alias, origin, hashedPassword, expiration, availability, LinkType.PASSWORD_PROTECTED)
        );
    }

}
