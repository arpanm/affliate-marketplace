package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AffinityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Affinity getAffinitySample1() {
        return new Affinity().id(1L).score(1L).createdBy("createdBy1").updatedBy("updatedBy1");
    }

    public static Affinity getAffinitySample2() {
        return new Affinity().id(2L).score(2L).createdBy("createdBy2").updatedBy("updatedBy2");
    }

    public static Affinity getAffinityRandomSampleGenerator() {
        return new Affinity()
            .id(longCount.incrementAndGet())
            .score(longCount.incrementAndGet())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
