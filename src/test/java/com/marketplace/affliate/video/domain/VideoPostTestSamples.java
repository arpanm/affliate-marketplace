package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VideoPostTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static VideoPost getVideoPostSample1() {
        return new VideoPost()
            .id(1L)
            .title("title1")
            .description("description1")
            .url("url1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static VideoPost getVideoPostSample2() {
        return new VideoPost()
            .id(2L)
            .title("title2")
            .description("description2")
            .url("url2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static VideoPost getVideoPostRandomSampleGenerator() {
        return new VideoPost()
            .id(longCount.incrementAndGet())
            .title(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .url(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
