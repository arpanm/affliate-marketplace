package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoTagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VideoTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoTag.class);
        VideoTag videoTag1 = getVideoTagSample1();
        VideoTag videoTag2 = new VideoTag();
        assertThat(videoTag1).isNotEqualTo(videoTag2);

        videoTag2.setId(videoTag1.getId());
        assertThat(videoTag1).isEqualTo(videoTag2);

        videoTag2 = getVideoTagSample2();
        assertThat(videoTag1).isNotEqualTo(videoTag2);
    }

    @Test
    void postsTest() {
        VideoTag videoTag = getVideoTagRandomSampleGenerator();
        VideoPost videoPostBack = getVideoPostRandomSampleGenerator();

        videoTag.addPosts(videoPostBack);
        assertThat(videoTag.getPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getTags()).containsOnly(videoTag);

        videoTag.removePosts(videoPostBack);
        assertThat(videoTag.getPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getTags()).doesNotContain(videoTag);

        videoTag.posts(new HashSet<>(Set.of(videoPostBack)));
        assertThat(videoTag.getPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getTags()).containsOnly(videoTag);

        videoTag.setPosts(new HashSet<>());
        assertThat(videoTag.getPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getTags()).doesNotContain(videoTag);
    }
}
