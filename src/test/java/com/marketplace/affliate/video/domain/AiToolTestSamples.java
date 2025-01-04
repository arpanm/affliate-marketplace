package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AiToolTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AiTool getAiToolSample1() {
        return new AiTool()
            .id(1L)
            .name("name1")
            .description("description1")
            .vendor("vendor1")
            .linkUrl("linkUrl1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static AiTool getAiToolSample2() {
        return new AiTool()
            .id(2L)
            .name("name2")
            .description("description2")
            .vendor("vendor2")
            .linkUrl("linkUrl2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static AiTool getAiToolRandomSampleGenerator() {
        return new AiTool()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .vendor(UUID.randomUUID().toString())
            .linkUrl(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
