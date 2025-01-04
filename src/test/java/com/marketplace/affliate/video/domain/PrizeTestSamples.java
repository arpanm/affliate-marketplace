package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PrizeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Prize getPrizeSample1() {
        return new Prize()
            .id(1L)
            .prizeTag("prizeTag1")
            .prizeDetails("prizeDetails1")
            .countOfPossibleWinners(1L)
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static Prize getPrizeSample2() {
        return new Prize()
            .id(2L)
            .prizeTag("prizeTag2")
            .prizeDetails("prizeDetails2")
            .countOfPossibleWinners(2L)
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static Prize getPrizeRandomSampleGenerator() {
        return new Prize()
            .id(longCount.incrementAndGet())
            .prizeTag(UUID.randomUUID().toString())
            .prizeDetails(UUID.randomUUID().toString())
            .countOfPossibleWinners(longCount.incrementAndGet())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
