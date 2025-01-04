package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AiToolChatTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AiToolChat getAiToolChatSample1() {
        return new AiToolChat()
            .id(1L)
            .message("message1")
            .videoUrl("videoUrl1")
            .paymentUrl("paymentUrl1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static AiToolChat getAiToolChatSample2() {
        return new AiToolChat()
            .id(2L)
            .message("message2")
            .videoUrl("videoUrl2")
            .paymentUrl("paymentUrl2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static AiToolChat getAiToolChatRandomSampleGenerator() {
        return new AiToolChat()
            .id(longCount.incrementAndGet())
            .message(UUID.randomUUID().toString())
            .videoUrl(UUID.randomUUID().toString())
            .paymentUrl(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
