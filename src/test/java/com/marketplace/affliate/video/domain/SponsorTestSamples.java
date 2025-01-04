package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SponsorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Sponsor getSponsorSample1() {
        return new Sponsor()
            .id(1L)
            .sponsorName("sponsorName1")
            .sponsorDescription("sponsorDescription1")
            .sponsorBanner1Url("sponsorBanner1Url1")
            .sponsorBanner2Url("sponsorBanner2Url1")
            .sponsorBanner3Url("sponsorBanner3Url1")
            .sponsorExternalUrl("sponsorExternalUrl1")
            .sponsorLogoUrl("sponsorLogoUrl1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static Sponsor getSponsorSample2() {
        return new Sponsor()
            .id(2L)
            .sponsorName("sponsorName2")
            .sponsorDescription("sponsorDescription2")
            .sponsorBanner1Url("sponsorBanner1Url2")
            .sponsorBanner2Url("sponsorBanner2Url2")
            .sponsorBanner3Url("sponsorBanner3Url2")
            .sponsorExternalUrl("sponsorExternalUrl2")
            .sponsorLogoUrl("sponsorLogoUrl2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static Sponsor getSponsorRandomSampleGenerator() {
        return new Sponsor()
            .id(longCount.incrementAndGet())
            .sponsorName(UUID.randomUUID().toString())
            .sponsorDescription(UUID.randomUUID().toString())
            .sponsorBanner1Url(UUID.randomUUID().toString())
            .sponsorBanner2Url(UUID.randomUUID().toString())
            .sponsorBanner3Url(UUID.randomUUID().toString())
            .sponsorExternalUrl(UUID.randomUUID().toString())
            .sponsorLogoUrl(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
