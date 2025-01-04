package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.Affinity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Affinity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AffinityRepository extends JpaRepository<Affinity, Long> {}
