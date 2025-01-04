package com.marketplace.affliate.video.repository;

import com.marketplace.affliate.video.domain.VideoUser;
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
public class VideoUserRepositoryWithBagRelationshipsImpl implements VideoUserRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String VIDEOUSERS_PARAMETER = "videoUsers";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VideoUser> fetchBagRelationships(Optional<VideoUser> videoUser) {
        return videoUser.map(this::fetchAffinityVectors);
    }

    @Override
    public Page<VideoUser> fetchBagRelationships(Page<VideoUser> videoUsers) {
        return new PageImpl<>(fetchBagRelationships(videoUsers.getContent()), videoUsers.getPageable(), videoUsers.getTotalElements());
    }

    @Override
    public List<VideoUser> fetchBagRelationships(List<VideoUser> videoUsers) {
        return Optional.of(videoUsers).map(this::fetchAffinityVectors).orElse(Collections.emptyList());
    }

    VideoUser fetchAffinityVectors(VideoUser result) {
        return entityManager
            .createQuery(
                "select videoUser from VideoUser videoUser left join fetch videoUser.affinityVectors where videoUser.id = :id",
                VideoUser.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<VideoUser> fetchAffinityVectors(List<VideoUser> videoUsers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, videoUsers.size()).forEach(index -> order.put(videoUsers.get(index).getId(), index));
        List<VideoUser> result = entityManager
            .createQuery(
                "select videoUser from VideoUser videoUser left join fetch videoUser.affinityVectors where videoUser in :videoUsers",
                VideoUser.class
            )
            .setParameter(VIDEOUSERS_PARAMETER, videoUsers)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
