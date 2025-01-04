package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.ReviewChangeHistoryTestSamples.*;
import static com.marketplace.affliate.video.domain.ReviewTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ReviewTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Review.class);
        Review review1 = getReviewSample1();
        Review review2 = new Review();
        assertThat(review1).isNotEqualTo(review2);

        review2.setId(review1.getId());
        assertThat(review1).isEqualTo(review2);

        review2 = getReviewSample2();
        assertThat(review1).isNotEqualTo(review2);
    }

    @Test
    void changesHistoryTest() {
        Review review = getReviewRandomSampleGenerator();
        ReviewChangeHistory reviewChangeHistoryBack = getReviewChangeHistoryRandomSampleGenerator();

        review.addChangesHistory(reviewChangeHistoryBack);
        assertThat(review.getChangesHistories()).containsOnly(reviewChangeHistoryBack);
        assertThat(reviewChangeHistoryBack.getReview()).isEqualTo(review);

        review.removeChangesHistory(reviewChangeHistoryBack);
        assertThat(review.getChangesHistories()).doesNotContain(reviewChangeHistoryBack);
        assertThat(reviewChangeHistoryBack.getReview()).isNull();

        review.changesHistories(new HashSet<>(Set.of(reviewChangeHistoryBack)));
        assertThat(review.getChangesHistories()).containsOnly(reviewChangeHistoryBack);
        assertThat(reviewChangeHistoryBack.getReview()).isEqualTo(review);

        review.setChangesHistories(new HashSet<>());
        assertThat(review.getChangesHistories()).doesNotContain(reviewChangeHistoryBack);
        assertThat(reviewChangeHistoryBack.getReview()).isNull();
    }

    @Test
    void reviewerTest() {
        Review review = getReviewRandomSampleGenerator();
        VideoUser videoUserBack = getVideoUserRandomSampleGenerator();

        review.setReviewer(videoUserBack);
        assertThat(review.getReviewer()).isEqualTo(videoUserBack);

        review.reviewer(null);
        assertThat(review.getReviewer()).isNull();
    }

    @Test
    void postTest() {
        Review review = getReviewRandomSampleGenerator();
        VideoPost videoPostBack = getVideoPostRandomSampleGenerator();

        review.setPost(videoPostBack);
        assertThat(review.getPost()).isEqualTo(videoPostBack);

        review.post(null);
        assertThat(review.getPost()).isNull();
    }
}
