package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VideoTagTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static VideoTag getVideoTagSample1() {
        return new VideoTag()
            .id(1L)
            .name("name1")
            .code("code1")
            .deletionReason("deletionReason1")
            .mergedWithTagName("mergedWithTagName1")
            .mergedWithTagCode("mergedWithTagCode1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static VideoTag getVideoTagSample2() {
        return new VideoTag()
            .id(2L)
            .name("name2")
            .code("code2")
            .deletionReason("deletionReason2")
            .mergedWithTagName("mergedWithTagName2")
            .mergedWithTagCode("mergedWithTagCode2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static VideoTag getVideoTagRandomSampleGenerator() {
        return new VideoTag()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .deletionReason(UUID.randomUUID().toString())
            .mergedWithTagName(UUID.randomUUID().toString())
            .mergedWithTagCode(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
