package com.marchosiax.weelink.repository;

import com.marchosiax.weelink.model.LinkHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkHitRepository extends JpaRepository<LinkHit, Long> {
}
