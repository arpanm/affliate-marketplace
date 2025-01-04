package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.AiToolPaymentTestSamples.*;
import static com.marketplace.affliate.video.domain.AiToolSessionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AiToolPaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AiToolPayment.class);
        AiToolPayment aiToolPayment1 = getAiToolPaymentSample1();
        AiToolPayment aiToolPayment2 = new AiToolPayment();
        assertThat(aiToolPayment1).isNotEqualTo(aiToolPayment2);

        aiToolPayment2.setId(aiToolPayment1.getId());
        assertThat(aiToolPayment1).isEqualTo(aiToolPayment2);

        aiToolPayment2 = getAiToolPaymentSample2();
        assertThat(aiToolPayment1).isNotEqualTo(aiToolPayment2);
    }

    @Test
    void sessionTest() {
        AiToolPayment aiToolPayment = getAiToolPaymentRandomSampleGenerator();
        AiToolSession aiToolSessionBack = getAiToolSessionRandomSampleGenerator();

        aiToolPayment.setSession(aiToolSessionBack);
        assertThat(aiToolPayment.getSession()).isEqualTo(aiToolSessionBack);

        aiToolPayment.session(null);
        assertThat(aiToolPayment.getSession()).isNull();
    }
}
