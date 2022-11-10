package com.marchosiax.weelink.repository;

import com.marchosiax.weelink.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

    @Query("SELECT t FROM space t WHERE label = 'default'")
    Optional<Space> getDefault();

}