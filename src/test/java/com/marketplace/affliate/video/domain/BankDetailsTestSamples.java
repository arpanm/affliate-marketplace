package com.marketplace.affliate.video.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BankDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static BankDetails getBankDetailsSample1() {
        return new BankDetails()
            .id(1L)
            .accountName("accountName1")
            .accountNo("accountNo1")
            .bankName("bankName1")
            .ifsc("ifsc1")
            .proofUrl("proofUrl1")
            .upiHandle("upiHandle1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static BankDetails getBankDetailsSample2() {
        return new BankDetails()
            .id(2L)
            .accountName("accountName2")
            .accountNo("accountNo2")
            .bankName("bankName2")
            .ifsc("ifsc2")
            .proofUrl("proofUrl2")
            .upiHandle("upiHandle2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static BankDetails getBankDetailsRandomSampleGenerator() {
        return new BankDetails()
            .id(longCount.incrementAndGet())
            .accountName(UUID.randomUUID().toString())
            .accountNo(UUID.randomUUID().toString())
            .bankName(UUID.randomUUID().toString())
            .ifsc(UUID.randomUUID().toString())
            .proofUrl(UUID.randomUUID().toString())
            .upiHandle(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
