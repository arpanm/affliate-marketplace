package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReviewTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Review getReviewSample1() {
        return new Review()
            .id(1L)
            .rating(1L)
            .comment("comment1")
            .reportReason("reportReason1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static Review getReviewSample2() {
        return new Review()
            .id(2L)
            .rating(2L)
            .comment("comment2")
            .reportReason("reportReason2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static Review getReviewRandomSampleGenerator() {
        return new Review()
            .id(longCount.incrementAndGet())
            .rating(longCount.incrementAndGet())
            .comment(UUID.randomUUID().toString())
            .reportReason(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
