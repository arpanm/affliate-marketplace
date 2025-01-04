package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.VideoPostChangeHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VideoPostChangeHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoPostChangeHistoryRepository extends JpaRepository<VideoPostChangeHistory, Long> {}
