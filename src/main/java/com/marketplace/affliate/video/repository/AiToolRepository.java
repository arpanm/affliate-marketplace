package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.AiTool;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AiTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AiToolRepository extends JpaRepository<AiTool, Long> {}
