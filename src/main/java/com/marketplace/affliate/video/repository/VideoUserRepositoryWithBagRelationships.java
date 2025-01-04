package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.VideoUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface VideoUserRepositoryWithBagRelationships {
    Optional<VideoUser> fetchBagRelationships(Optional<VideoUser> videoUser);

    List<VideoUser> fetchBagRelationships(List<VideoUser> videoUsers);

    Page<VideoUser> fetchBagRelationships(Page<VideoUser> videoUsers);
}
