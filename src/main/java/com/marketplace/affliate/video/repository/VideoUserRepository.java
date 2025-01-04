package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.VideoUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VideoUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoUserRepository extends JpaRepository<VideoUser, Long> {}
