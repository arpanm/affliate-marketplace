package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CompetitionPaymentFromSponsorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CompetitionPaymentFromSponsor getCompetitionPaymentFromSponsorSample1() {
        return new CompetitionPaymentFromSponsor()
            .id(1L)
            .transactionId("transactionId1")
            .transactionScreenshotUrl("transactionScreenshotUrl1")
            .paidBy("paidBy1")
            .paymentReceiptUrl("paymentReceiptUrl1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static CompetitionPaymentFromSponsor getCompetitionPaymentFromSponsorSample2() {
        return new CompetitionPaymentFromSponsor()
            .id(2L)
            .transactionId("transactionId2")
            .transactionScreenshotUrl("transactionScreenshotUrl2")
            .paidBy("paidBy2")
            .paymentReceiptUrl("paymentReceiptUrl2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static CompetitionPaymentFromSponsor getCompetitionPaymentFromSponsorRandomSampleGenerator() {
        return new CompetitionPaymentFromSponsor()
            .id(longCount.incrementAndGet())
            .transactionId(UUID.randomUUID().toString())
            .transactionScreenshotUrl(UUID.randomUUID().toString())
            .paidBy(UUID.randomUUID().toString())
            .paymentReceiptUrl(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
