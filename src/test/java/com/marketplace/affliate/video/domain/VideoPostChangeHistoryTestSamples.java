package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VideoPostChangeHistoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static VideoPostChangeHistory getVideoPostChangeHistorySample1() {
        return new VideoPostChangeHistory()
            .id(1L)
            .oldtitle("oldtitle1")
            .oldDescription("oldDescription1")
            .oldUrl("oldUrl1")
            .oldCreatedBy("oldCreatedBy1")
            .oldUpdatedBy("oldUpdatedBy1");
    }

    public static VideoPostChangeHistory getVideoPostChangeHistorySample2() {
        return new VideoPostChangeHistory()
            .id(2L)
            .oldtitle("oldtitle2")
            .oldDescription("oldDescription2")
            .oldUrl("oldUrl2")
            .oldCreatedBy("oldCreatedBy2")
            .oldUpdatedBy("oldUpdatedBy2");
    }

    public static VideoPostChangeHistory getVideoPostChangeHistoryRandomSampleGenerator() {
        return new VideoPostChangeHistory()
            .id(longCount.incrementAndGet())
            .oldtitle(UUID.randomUUID().toString())
            .oldDescription(UUID.randomUUID().toString())
            .oldUrl(UUID.randomUUID().toString())
            .oldCreatedBy(UUID.randomUUID().toString())
            .oldUpdatedBy(UUID.randomUUID().toString());
    }
}
