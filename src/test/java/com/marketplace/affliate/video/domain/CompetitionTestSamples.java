package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CompetitionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Competition getCompetitionSample1() {
        return new Competition()
            .id(1L)
            .title("title1")
            .description("description1")
            .blockReason("blockReason1")
            .blockedBy("blockedBy1")
            .pauseReason("pauseReason1")
            .pausedBy("pausedBy1")
            .banner1Url("banner1Url1")
            .banner2Url("banner2Url1")
            .banner3Url("banner3Url1")
            .landingUrl("landingUrl1")
            .invoiceToSponsorUrl("invoiceToSponsorUrl1")
            .tncUrl("tncUrl1")
            .tnc("tnc1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static Competition getCompetitionSample2() {
        return new Competition()
            .id(2L)
            .title("title2")
            .description("description2")
            .blockReason("blockReason2")
            .blockedBy("blockedBy2")
            .pauseReason("pauseReason2")
            .pausedBy("pausedBy2")
            .banner1Url("banner1Url2")
            .banner2Url("banner2Url2")
            .banner3Url("banner3Url2")
            .landingUrl("landingUrl2")
            .invoiceToSponsorUrl("invoiceToSponsorUrl2")
            .tncUrl("tncUrl2")
            .tnc("tnc2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static Competition getCompetitionRandomSampleGenerator() {
        return new Competition()
            .id(longCount.incrementAndGet())
            .title(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .blockReason(UUID.randomUUID().toString())
            .blockedBy(UUID.randomUUID().toString())
            .pauseReason(UUID.randomUUID().toString())
            .pausedBy(UUID.randomUUID().toString())
            .banner1Url(UUID.randomUUID().toString())
            .banner2Url(UUID.randomUUID().toString())
            .banner3Url(UUID.randomUUID().toString())
            .landingUrl(UUID.randomUUID().toString())
            .invoiceToSponsorUrl(UUID.randomUUID().toString())
            .tncUrl(UUID.randomUUID().toString())
            .tnc(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
