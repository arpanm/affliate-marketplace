package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CompetitionPaymentFromSponsor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetitionPaymentFromSponsorRepository extends JpaRepository<CompetitionPaymentFromSponsor, Long> {}
