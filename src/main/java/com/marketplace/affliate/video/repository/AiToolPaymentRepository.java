package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.AiToolPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AiToolPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AiToolPaymentRepository extends JpaRepository<AiToolPayment, Long> {}
