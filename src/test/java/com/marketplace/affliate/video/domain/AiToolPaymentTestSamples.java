package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AiToolPaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AiToolPayment getAiToolPaymentSample1() {
        return new AiToolPayment()
            .id(1L)
            .paymentLinkUrl("paymentLinkUrl1")
            .pgId("pgId1")
            .pgStatus("pgStatus1")
            .pgRequestJson("pgRequestJson1")
            .pgResponseJson("pgResponseJson1")
            .pgRequestTimeStamp("pgRequestTimeStamp1")
            .pgResponseTimeStamp("pgResponseTimeStamp1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static AiToolPayment getAiToolPaymentSample2() {
        return new AiToolPayment()
            .id(2L)
            .paymentLinkUrl("paymentLinkUrl2")
            .pgId("pgId2")
            .pgStatus("pgStatus2")
            .pgRequestJson("pgRequestJson2")
            .pgResponseJson("pgResponseJson2")
            .pgRequestTimeStamp("pgRequestTimeStamp2")
            .pgResponseTimeStamp("pgResponseTimeStamp2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static AiToolPayment getAiToolPaymentRandomSampleGenerator() {
        return new AiToolPayment()
            .id(longCount.incrementAndGet())
            .paymentLinkUrl(UUID.randomUUID().toString())
            .pgId(UUID.randomUUID().toString())
            .pgStatus(UUID.randomUUID().toString())
            .pgRequestJson(UUID.randomUUID().toString())
            .pgResponseJson(UUID.randomUUID().toString())
            .pgRequestTimeStamp(UUID.randomUUID().toString())
            .pgResponseTimeStamp(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
