package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.ReviewChangeHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReviewChangeHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReviewChangeHistoryRepository extends JpaRepository<ReviewChangeHistory, Long> {}
