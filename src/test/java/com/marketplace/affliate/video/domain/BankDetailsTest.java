package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.BankDetailsTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BankDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankDetails.class);
        BankDetails bankDetails1 = getBankDetailsSample1();
        BankDetails bankDetails2 = new BankDetails();
        assertThat(bankDetails1).isNotEqualTo(bankDetails2);

        bankDetails2.setId(bankDetails1.getId());
        assertThat(bankDetails1).isEqualTo(bankDetails2);

        bankDetails2 = getBankDetailsSample2();
        assertThat(bankDetails1).isNotEqualTo(bankDetails2);
    }

    @Test
    void userTest() {
        BankDetails bankDetails = getBankDetailsRandomSampleGenerator();
        VideoUser videoUserBack = getVideoUserRandomSampleGenerator();

        bankDetails.setUser(videoUserBack);
        assertThat(bankDetails.getUser()).isEqualTo(videoUserBack);
        assertThat(videoUserBack.getBank()).isEqualTo(bankDetails);

        bankDetails.user(null);
        assertThat(bankDetails.getUser()).isNull();
        assertThat(videoUserBack.getBank()).isNull();
    }
}
