package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.BankDetailsTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VideoUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoUser.class);
        VideoUser videoUser1 = getVideoUserSample1();
        VideoUser videoUser2 = new VideoUser();
        assertThat(videoUser1).isNotEqualTo(videoUser2);

        videoUser2.setId(videoUser1.getId());
        assertThat(videoUser1).isEqualTo(videoUser2);

        videoUser2 = getVideoUserSample2();
        assertThat(videoUser1).isNotEqualTo(videoUser2);
    }

    @Test
    void bankTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        BankDetails bankDetailsBack = getBankDetailsRandomSampleGenerator();

        videoUser.setBank(bankDetailsBack);
        assertThat(videoUser.getBank()).isEqualTo(bankDetailsBack);

        videoUser.bank(null);
        assertThat(videoUser.getBank()).isNull();
    }
}
