package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AiToolSessionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AiToolSession getAiToolSessionSample1() {
        return new AiToolSession().id(1L).createdBy("createdBy1").updatedBy("updatedBy1");
    }

    public static AiToolSession getAiToolSessionSample2() {
        return new AiToolSession().id(2L).createdBy("createdBy2").updatedBy("updatedBy2");
    }

    public static AiToolSession getAiToolSessionRandomSampleGenerator() {
        return new AiToolSession()
            .id(longCount.incrementAndGet())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
