package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReviewChangeHistoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReviewChangeHistory getReviewChangeHistorySample1() {
        return new ReviewChangeHistory()
            .id(1L)
            .oldRating(1L)
            .oldComment("oldComment1")
            .oldReportReason("oldReportReason1")
            .oldCreatedBy("oldCreatedBy1")
            .oldUpdatedBy("oldUpdatedBy1");
    }

    public static ReviewChangeHistory getReviewChangeHistorySample2() {
        return new ReviewChangeHistory()
            .id(2L)
            .oldRating(2L)
            .oldComment("oldComment2")
            .oldReportReason("oldReportReason2")
            .oldCreatedBy("oldCreatedBy2")
            .oldUpdatedBy("oldUpdatedBy2");
    }

    public static ReviewChangeHistory getReviewChangeHistoryRandomSampleGenerator() {
        return new ReviewChangeHistory()
            .id(longCount.incrementAndGet())
            .oldRating(longCount.incrementAndGet())
            .oldComment(UUID.randomUUID().toString())
            .oldReportReason(UUID.randomUUID().toString())
            .oldCreatedBy(UUID.randomUUID().toString())
            .oldUpdatedBy(UUID.randomUUID().toString());
    }
}
