package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.AiToolChatTestSamples.*;
import static com.marketplace.affliate.video.domain.AiToolPaymentTestSamples.*;
import static com.marketplace.affliate.video.domain.AiToolSessionTestSamples.*;
import static com.marketplace.affliate.video.domain.AiToolTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AiToolSessionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AiToolSession.class);
        AiToolSession aiToolSession1 = getAiToolSessionSample1();
        AiToolSession aiToolSession2 = new AiToolSession();
        assertThat(aiToolSession1).isNotEqualTo(aiToolSession2);

        aiToolSession2.setId(aiToolSession1.getId());
        assertThat(aiToolSession1).isEqualTo(aiToolSession2);

        aiToolSession2 = getAiToolSessionSample2();
        assertThat(aiToolSession1).isNotEqualTo(aiToolSession2);
    }

    @Test
    void chatsTest() {
        AiToolSession aiToolSession = getAiToolSessionRandomSampleGenerator();
        AiToolChat aiToolChatBack = getAiToolChatRandomSampleGenerator();

        aiToolSession.addChats(aiToolChatBack);
        assertThat(aiToolSession.getChats()).containsOnly(aiToolChatBack);
        assertThat(aiToolChatBack.getSession()).isEqualTo(aiToolSession);

        aiToolSession.removeChats(aiToolChatBack);
        assertThat(aiToolSession.getChats()).doesNotContain(aiToolChatBack);
        assertThat(aiToolChatBack.getSession()).isNull();

        aiToolSession.chats(new HashSet<>(Set.of(aiToolChatBack)));
        assertThat(aiToolSession.getChats()).containsOnly(aiToolChatBack);
        assertThat(aiToolChatBack.getSession()).isEqualTo(aiToolSession);

        aiToolSession.setChats(new HashSet<>());
        assertThat(aiToolSession.getChats()).doesNotContain(aiToolChatBack);
        assertThat(aiToolChatBack.getSession()).isNull();
    }

    @Test
    void paymentsTest() {
        AiToolSession aiToolSession = getAiToolSessionRandomSampleGenerator();
        AiToolPayment aiToolPaymentBack = getAiToolPaymentRandomSampleGenerator();

        aiToolSession.addPayments(aiToolPaymentBack);
        assertThat(aiToolSession.getPayments()).containsOnly(aiToolPaymentBack);
        assertThat(aiToolPaymentBack.getSession()).isEqualTo(aiToolSession);

        aiToolSession.removePayments(aiToolPaymentBack);
        assertThat(aiToolSession.getPayments()).doesNotContain(aiToolPaymentBack);
        assertThat(aiToolPaymentBack.getSession()).isNull();

        aiToolSession.payments(new HashSet<>(Set.of(aiToolPaymentBack)));
        assertThat(aiToolSession.getPayments()).containsOnly(aiToolPaymentBack);
        assertThat(aiToolPaymentBack.getSession()).isEqualTo(aiToolSession);

        aiToolSession.setPayments(new HashSet<>());
        assertThat(aiToolSession.getPayments()).doesNotContain(aiToolPaymentBack);
        assertThat(aiToolPaymentBack.getSession()).isNull();
    }

    @Test
    void userTest() {
        AiToolSession aiToolSession = getAiToolSessionRandomSampleGenerator();
        VideoUser videoUserBack = getVideoUserRandomSampleGenerator();

        aiToolSession.setUser(videoUserBack);
        assertThat(aiToolSession.getUser()).isEqualTo(videoUserBack);

        aiToolSession.user(null);
        assertThat(aiToolSession.getUser()).isNull();
    }

    @Test
    void toolTest() {
        AiToolSession aiToolSession = getAiToolSessionRandomSampleGenerator();
        AiTool aiToolBack = getAiToolRandomSampleGenerator();

        aiToolSession.setTool(aiToolBack);
        assertThat(aiToolSession.getTool()).isEqualTo(aiToolBack);

        aiToolSession.tool(null);
        assertThat(aiToolSession.getTool()).isNull();
    }
}
