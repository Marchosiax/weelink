package com.marchosiax.weelink.service;

import com.marchosiax.weelink.error.AppError;
import com.marchosiax.weelink.model.Link;
import com.marchosiax.weelink.model.enums.LinkType;
import com.marchosiax.weelink.repository.LinkRepository;
import com.marchosiax.weelink.utils.SecurityUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkManagerService {

    private final LinkRepository linkRepository;

    public LinkManagerService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String openLink(String alias, String password) throws Throwable {
        Link link = linkRepository.findByAlias(alias)
                    .orElseThrow(AppError.LINK_NOT_FOUND::exception);

        if (link.getType() == LinkType.PASSWORD_PROTECTED) {
            if (password == null || password.isEmpty())
                throw AppError.INCORRECT_LINK_PASSWORD.exception();

            var hashedPassword = SecurityUtils.hashSHA256(password);
            if (!hashedPassword.equals(password))
                throw AppError.INCORRECT_LINK_PASSWORD.exception();
        }

        return link.getOrigin();
    }

}
