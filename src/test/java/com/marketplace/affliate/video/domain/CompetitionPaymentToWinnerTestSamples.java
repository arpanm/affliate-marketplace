package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CompetitionPaymentToWinnerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CompetitionPaymentToWinner getCompetitionPaymentToWinnerSample1() {
        return new CompetitionPaymentToWinner()
            .id(1L)
            .prizeTitle("prizeTitle1")
            .deductionReason("deductionReason1")
            .deductionJsonData("deductionJsonData1")
            .transactionId("transactionId1")
            .transactionScreenshotUrl("transactionScreenshotUrl1")
            .paidBy("paidBy1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static CompetitionPaymentToWinner getCompetitionPaymentToWinnerSample2() {
        return new CompetitionPaymentToWinner()
            .id(2L)
            .prizeTitle("prizeTitle2")
            .deductionReason("deductionReason2")
            .deductionJsonData("deductionJsonData2")
            .transactionId("transactionId2")
            .transactionScreenshotUrl("transactionScreenshotUrl2")
            .paidBy("paidBy2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static CompetitionPaymentToWinner getCompetitionPaymentToWinnerRandomSampleGenerator() {
        return new CompetitionPaymentToWinner()
            .id(longCount.incrementAndGet())
            .prizeTitle(UUID.randomUUID().toString())
            .deductionReason(UUID.randomUUID().toString())
            .deductionJsonData(UUID.randomUUID().toString())
            .transactionId(UUID.randomUUID().toString())
            .transactionScreenshotUrl(UUID.randomUUID().toString())
            .paidBy(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
