package com.marchosiax.weelink.repository;

import com.marchosiax.weelink.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByAlias(String alias);

    Optional<Link> findByUuid(String uuid);

}
