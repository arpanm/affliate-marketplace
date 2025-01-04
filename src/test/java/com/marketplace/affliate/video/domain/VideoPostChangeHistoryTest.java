package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.VideoPostChangeHistoryTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VideoPostChangeHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoPostChangeHistory.class);
        VideoPostChangeHistory videoPostChangeHistory1 = getVideoPostChangeHistorySample1();
        VideoPostChangeHistory videoPostChangeHistory2 = new VideoPostChangeHistory();
        assertThat(videoPostChangeHistory1).isNotEqualTo(videoPostChangeHistory2);

        videoPostChangeHistory2.setId(videoPostChangeHistory1.getId());
        assertThat(videoPostChangeHistory1).isEqualTo(videoPostChangeHistory2);

        videoPostChangeHistory2 = getVideoPostChangeHistorySample2();
        assertThat(videoPostChangeHistory1).isNotEqualTo(videoPostChangeHistory2);
    }

    @Test
    void postTest() {
        VideoPostChangeHistory videoPostChangeHistory = getVideoPostChangeHistoryRandomSampleGenerator();
        VideoPost videoPostBack = getVideoPostRandomSampleGenerator();

        videoPostChangeHistory.setPost(videoPostBack);
        assertThat(videoPostChangeHistory.getPost()).isEqualTo(videoPostBack);

        videoPostChangeHistory.post(null);
        assertThat(videoPostChangeHistory.getPost()).isNull();
    }
}
