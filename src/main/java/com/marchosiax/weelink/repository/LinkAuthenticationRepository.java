package com.marchosiax.weelink.repository;

import com.marchosiax.weelink.model.LinkAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkAuthenticationRepository extends JpaRepository<LinkAuthentication, Long> {
}
