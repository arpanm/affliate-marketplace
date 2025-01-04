package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CompetitionWinnerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CompetitionWinner getCompetitionWinnerSample1() {
        return new CompetitionWinner()
            .id(1L)
            .prizeTitle("prizeTitle1")
            .citation("citation1")
            .certificateUrl("certificateUrl1")
            .selectedBy("selectedBy1")
            .selectionReason("selectionReason1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static CompetitionWinner getCompetitionWinnerSample2() {
        return new CompetitionWinner()
            .id(2L)
            .prizeTitle("prizeTitle2")
            .citation("citation2")
            .certificateUrl("certificateUrl2")
            .selectedBy("selectedBy2")
            .selectionReason("selectionReason2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static CompetitionWinner getCompetitionWinnerRandomSampleGenerator() {
        return new CompetitionWinner()
            .id(longCount.incrementAndGet())
            .prizeTitle(UUID.randomUUID().toString())
            .citation(UUID.randomUUID().toString())
            .certificateUrl(UUID.randomUUID().toString())
            .selectedBy(UUID.randomUUID().toString())
            .selectionReason(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
