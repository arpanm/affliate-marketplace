package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.CompetitionWinner;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CompetitionWinner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetitionWinnerRepository extends JpaRepository<CompetitionWinner, Long> {}
