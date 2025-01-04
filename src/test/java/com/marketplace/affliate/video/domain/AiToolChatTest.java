package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.AiToolChatTestSamples.*;
import static com.marketplace.affliate.video.domain.AiToolSessionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AiToolChatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AiToolChat.class);
        AiToolChat aiToolChat1 = getAiToolChatSample1();
        AiToolChat aiToolChat2 = new AiToolChat();
        assertThat(aiToolChat1).isNotEqualTo(aiToolChat2);

        aiToolChat2.setId(aiToolChat1.getId());
        assertThat(aiToolChat1).isEqualTo(aiToolChat2);

        aiToolChat2 = getAiToolChatSample2();
        assertThat(aiToolChat1).isNotEqualTo(aiToolChat2);
    }

    @Test
    void sessionTest() {
        AiToolChat aiToolChat = getAiToolChatRandomSampleGenerator();
        AiToolSession aiToolSessionBack = getAiToolSessionRandomSampleGenerator();

        aiToolChat.setSession(aiToolSessionBack);
        assertThat(aiToolChat.getSession()).isEqualTo(aiToolSessionBack);

        aiToolChat.session(null);
        assertThat(aiToolChat.getSession()).isNull();
    }
}
