package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.AiToolChat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AiToolChat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AiToolChatRepository extends JpaRepository<AiToolChat, Long> {}
