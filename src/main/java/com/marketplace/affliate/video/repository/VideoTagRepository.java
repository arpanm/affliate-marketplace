package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.VideoTag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VideoTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoTagRepository extends JpaRepository<VideoTag, Long> {}
