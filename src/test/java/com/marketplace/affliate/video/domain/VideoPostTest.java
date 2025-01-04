package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoTagTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VideoPostTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoPost.class);
        VideoPost videoPost1 = getVideoPostSample1();
        VideoPost videoPost2 = new VideoPost();
        assertThat(videoPost1).isNotEqualTo(videoPost2);

        videoPost2.setId(videoPost1.getId());
        assertThat(videoPost1).isEqualTo(videoPost2);

        videoPost2 = getVideoPostSample2();
        assertThat(videoPost1).isNotEqualTo(videoPost2);
    }

    @Test
    void tagsTest() {
        VideoPost videoPost = getVideoPostRandomSampleGenerator();
        VideoTag videoTagBack = getVideoTagRandomSampleGenerator();

        videoPost.addTags(videoTagBack);
        assertThat(videoPost.getTags()).containsOnly(videoTagBack);

        videoPost.removeTags(videoTagBack);
        assertThat(videoPost.getTags()).doesNotContain(videoTagBack);

        videoPost.tags(new HashSet<>(Set.of(videoTagBack)));
        assertThat(videoPost.getTags()).containsOnly(videoTagBack);

        videoPost.setTags(new HashSet<>());
        assertThat(videoPost.getTags()).doesNotContain(videoTagBack);
    }

    @Test
    void creatorTest() {
        VideoPost videoPost = getVideoPostRandomSampleGenerator();
        VideoUser videoUserBack = getVideoUserRandomSampleGenerator();

        videoPost.setCreator(videoUserBack);
        assertThat(videoPost.getCreator()).isEqualTo(videoUserBack);

        videoPost.creator(null);
        assertThat(videoPost.getCreator()).isNull();
    }
}
