package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.ReviewChangeHistoryTestSamples.*;
import static com.marketplace.affliate.video.domain.ReviewTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReviewChangeHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReviewChangeHistory.class);
        ReviewChangeHistory reviewChangeHistory1 = getReviewChangeHistorySample1();
        ReviewChangeHistory reviewChangeHistory2 = new ReviewChangeHistory();
        assertThat(reviewChangeHistory1).isNotEqualTo(reviewChangeHistory2);

        reviewChangeHistory2.setId(reviewChangeHistory1.getId());
        assertThat(reviewChangeHistory1).isEqualTo(reviewChangeHistory2);

        reviewChangeHistory2 = getReviewChangeHistorySample2();
        assertThat(reviewChangeHistory1).isNotEqualTo(reviewChangeHistory2);
    }

    @Test
    void reviewTest() {
        ReviewChangeHistory reviewChangeHistory = getReviewChangeHistoryRandomSampleGenerator();
        Review reviewBack = getReviewRandomSampleGenerator();

        reviewChangeHistory.setReview(reviewBack);
        assertThat(reviewChangeHistory.getReview()).isEqualTo(reviewBack);

        reviewChangeHistory.review(null);
        assertThat(reviewChangeHistory.getReview()).isNull();
    }
}
