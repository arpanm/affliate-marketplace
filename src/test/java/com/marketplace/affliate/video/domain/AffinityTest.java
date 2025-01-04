package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.AffinityTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AffinityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Affinity.class);
        Affinity affinity1 = getAffinitySample1();
        Affinity affinity2 = new Affinity();
        assertThat(affinity1).isNotEqualTo(affinity2);

        affinity2.setId(affinity1.getId());
        assertThat(affinity1).isEqualTo(affinity2);

        affinity2 = getAffinitySample2();
        assertThat(affinity1).isNotEqualTo(affinity2);
    }

    @Test
    void postsTest() {
        Affinity affinity = getAffinityRandomSampleGenerator();
        VideoPost videoPostBack = getVideoPostRandomSampleGenerator();

        affinity.addPosts(videoPostBack);
        assertThat(affinity.getPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getAffinityVectors()).containsOnly(affinity);

        affinity.removePosts(videoPostBack);
        assertThat(affinity.getPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getAffinityVectors()).doesNotContain(affinity);

        affinity.posts(new HashSet<>(Set.of(videoPostBack)));
        assertThat(affinity.getPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getAffinityVectors()).containsOnly(affinity);

        affinity.setPosts(new HashSet<>());
        assertThat(affinity.getPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getAffinityVectors()).doesNotContain(affinity);
    }

    @Test
    void usersTest() {
        Affinity affinity = getAffinityRandomSampleGenerator();
        VideoUser videoUserBack = getVideoUserRandomSampleGenerator();

        affinity.addUsers(videoUserBack);
        assertThat(affinity.getUsers()).containsOnly(videoUserBack);
        assertThat(videoUserBack.getAffinityVectors()).containsOnly(affinity);

        affinity.removeUsers(videoUserBack);
        assertThat(affinity.getUsers()).doesNotContain(videoUserBack);
        assertThat(videoUserBack.getAffinityVectors()).doesNotContain(affinity);

        affinity.users(new HashSet<>(Set.of(videoUserBack)));
        assertThat(affinity.getUsers()).containsOnly(videoUserBack);
        assertThat(videoUserBack.getAffinityVectors()).containsOnly(affinity);

        affinity.setUsers(new HashSet<>());
        assertThat(affinity.getUsers()).doesNotContain(videoUserBack);
        assertThat(videoUserBack.getAffinityVectors()).doesNotContain(affinity);
    }
}
