package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.AiToolSession;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AiToolSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AiToolSessionRepository extends JpaRepository<AiToolSession, Long> {}
