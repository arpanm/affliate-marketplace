package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.CompetitionPaymentToWinner;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CompetitionPaymentToWinner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetitionPaymentToWinnerRepository extends JpaRepository<CompetitionPaymentToWinner, Long> {}
