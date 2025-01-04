package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.AiToolSessionTestSamples.*;
import static com.marketplace.affliate.video.domain.AiToolTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AiToolTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AiTool.class);
        AiTool aiTool1 = getAiToolSample1();
        AiTool aiTool2 = new AiTool();
        assertThat(aiTool1).isNotEqualTo(aiTool2);

        aiTool2.setId(aiTool1.getId());
        assertThat(aiTool1).isEqualTo(aiTool2);

        aiTool2 = getAiToolSample2();
        assertThat(aiTool1).isNotEqualTo(aiTool2);
    }

    @Test
    void aiSessionsTest() {
        AiTool aiTool = getAiToolRandomSampleGenerator();
        AiToolSession aiToolSessionBack = getAiToolSessionRandomSampleGenerator();

        aiTool.addAiSessions(aiToolSessionBack);
        assertThat(aiTool.getAiSessions()).containsOnly(aiToolSessionBack);
        assertThat(aiToolSessionBack.getTool()).isEqualTo(aiTool);

        aiTool.removeAiSessions(aiToolSessionBack);
        assertThat(aiTool.getAiSessions()).doesNotContain(aiToolSessionBack);
        assertThat(aiToolSessionBack.getTool()).isNull();

        aiTool.aiSessions(new HashSet<>(Set.of(aiToolSessionBack)));
        assertThat(aiTool.getAiSessions()).containsOnly(aiToolSessionBack);
        assertThat(aiToolSessionBack.getTool()).isEqualTo(aiTool);

        aiTool.setAiSessions(new HashSet<>());
        assertThat(aiTool.getAiSessions()).doesNotContain(aiToolSessionBack);
        assertThat(aiToolSessionBack.getTool()).isNull();
    }
}
