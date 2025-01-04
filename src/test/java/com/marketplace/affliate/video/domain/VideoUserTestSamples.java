package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VideoUserTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static VideoUser getVideoUserSample1() {
        return new VideoUser()
            .id(1L)
            .userId("userId1")
            .userName("userName1")
            .name("name1")
            .phone(1L)
            .email("email1")
            .description("description1")
            .imageUrl("imageUrl1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static VideoUser getVideoUserSample2() {
        return new VideoUser()
            .id(2L)
            .userId("userId2")
            .userName("userName2")
            .name("name2")
            .phone(2L)
            .email("email2")
            .description("description2")
            .imageUrl("imageUrl2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static VideoUser getVideoUserRandomSampleGenerator() {
        return new VideoUser()
            .id(longCount.incrementAndGet())
            .userId(UUID.randomUUID().toString())
            .userName(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .phone(longCount.incrementAndGet())
            .email(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .imageUrl(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
