package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ContactTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Contact getContactSample1() {
        return new Contact().id(1L).message("message1").createdBy("createdBy1").updatedBy("updatedBy1");
    }

    public static Contact getContactSample2() {
        return new Contact().id(2L).message("message2").createdBy("createdBy2").updatedBy("updatedBy2");
    }

    public static Contact getContactRandomSampleGenerator() {
        return new Contact()
            .id(longCount.incrementAndGet())
            .message(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
