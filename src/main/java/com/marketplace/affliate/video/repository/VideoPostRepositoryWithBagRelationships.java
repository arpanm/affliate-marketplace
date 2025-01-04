package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.VideoPost;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface VideoPostRepositoryWithBagRelationships {
    Optional<VideoPost> fetchBagRelationships(Optional<VideoPost> videoPost);

    List<VideoPost> fetchBagRelationships(List<VideoPost> videoPosts);

    Page<VideoPost> fetchBagRelationships(Page<VideoPost> videoPosts);
}
