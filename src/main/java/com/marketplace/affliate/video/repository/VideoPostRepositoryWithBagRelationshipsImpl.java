package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.VideoPost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class VideoPostRepositoryWithBagRelationshipsImpl implements VideoPostRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String VIDEOPOSTS_PARAMETER = "videoPosts";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VideoPost> fetchBagRelationships(Optional<VideoPost> videoPost) {
        return videoPost.map(this::fetchTags);
    }

    @Override
    public Page<VideoPost> fetchBagRelationships(Page<VideoPost> videoPosts) {
        return new PageImpl<>(fetchBagRelationships(videoPosts.getContent()), videoPosts.getPageable(), videoPosts.getTotalElements());
    }

    @Override
    public List<VideoPost> fetchBagRelationships(List<VideoPost> videoPosts) {
        return Optional.of(videoPosts).map(this::fetchTags).orElse(Collections.emptyList());
    }

    VideoPost fetchTags(VideoPost result) {
        return entityManager
            .createQuery(
                "select videoPost from VideoPost videoPost left join fetch videoPost.tags where videoPost.id = :id",
                VideoPost.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<VideoPost> fetchTags(List<VideoPost> videoPosts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, videoPosts.size()).forEach(index -> order.put(videoPosts.get(index).getId(), index));
        List<VideoPost> result = entityManager
            .createQuery(
                "select videoPost from VideoPost videoPost left join fetch videoPost.tags where videoPost in :videoPosts",
                VideoPost.class
            )
            .setParameter(VIDEOPOSTS_PARAMETER, videoPosts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
