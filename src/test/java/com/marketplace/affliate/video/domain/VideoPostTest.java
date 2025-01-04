package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.AffinityTestSamples.*;
import static com.marketplace.affliate.video.domain.CompetitionTestSamples.*;
import static com.marketplace.affliate.video.domain.CompetitionWinnerTestSamples.*;
import static com.marketplace.affliate.video.domain.ReviewTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoPostChangeHistoryTestSamples.*;
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
    void reviewsTest() {
        VideoPost videoPost = getVideoPostRandomSampleGenerator();
        Review reviewBack = getReviewRandomSampleGenerator();

        videoPost.addReviews(reviewBack);
        assertThat(videoPost.getReviews()).containsOnly(reviewBack);
        assertThat(reviewBack.getPost()).isEqualTo(videoPost);

        videoPost.removeReviews(reviewBack);
        assertThat(videoPost.getReviews()).doesNotContain(reviewBack);
        assertThat(reviewBack.getPost()).isNull();

        videoPost.reviews(new HashSet<>(Set.of(reviewBack)));
        assertThat(videoPost.getReviews()).containsOnly(reviewBack);
        assertThat(reviewBack.getPost()).isEqualTo(videoPost);

        videoPost.setReviews(new HashSet<>());
        assertThat(videoPost.getReviews()).doesNotContain(reviewBack);
        assertThat(reviewBack.getPost()).isNull();
    }

    @Test
    void changesHistoryTest() {
        VideoPost videoPost = getVideoPostRandomSampleGenerator();
        VideoPostChangeHistory videoPostChangeHistoryBack = getVideoPostChangeHistoryRandomSampleGenerator();

        videoPost.addChangesHistory(videoPostChangeHistoryBack);
        assertThat(videoPost.getChangesHistories()).containsOnly(videoPostChangeHistoryBack);
        assertThat(videoPostChangeHistoryBack.getPost()).isEqualTo(videoPost);

        videoPost.removeChangesHistory(videoPostChangeHistoryBack);
        assertThat(videoPost.getChangesHistories()).doesNotContain(videoPostChangeHistoryBack);
        assertThat(videoPostChangeHistoryBack.getPost()).isNull();

        videoPost.changesHistories(new HashSet<>(Set.of(videoPostChangeHistoryBack)));
        assertThat(videoPost.getChangesHistories()).containsOnly(videoPostChangeHistoryBack);
        assertThat(videoPostChangeHistoryBack.getPost()).isEqualTo(videoPost);

        videoPost.setChangesHistories(new HashSet<>());
        assertThat(videoPost.getChangesHistories()).doesNotContain(videoPostChangeHistoryBack);
        assertThat(videoPostChangeHistoryBack.getPost()).isNull();
    }

    @Test
    void competitionTest() {
        VideoPost videoPost = getVideoPostRandomSampleGenerator();
        Competition competitionBack = getCompetitionRandomSampleGenerator();

        videoPost.setCompetition(competitionBack);
        assertThat(videoPost.getCompetition()).isEqualTo(competitionBack);

        videoPost.competition(null);
        assertThat(videoPost.getCompetition()).isNull();
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
    void affinityVectorsTest() {
        VideoPost videoPost = getVideoPostRandomSampleGenerator();
        Affinity affinityBack = getAffinityRandomSampleGenerator();

        videoPost.addAffinityVectors(affinityBack);
        assertThat(videoPost.getAffinityVectors()).containsOnly(affinityBack);

        videoPost.removeAffinityVectors(affinityBack);
        assertThat(videoPost.getAffinityVectors()).doesNotContain(affinityBack);

        videoPost.affinityVectors(new HashSet<>(Set.of(affinityBack)));
        assertThat(videoPost.getAffinityVectors()).containsOnly(affinityBack);

        videoPost.setAffinityVectors(new HashSet<>());
        assertThat(videoPost.getAffinityVectors()).doesNotContain(affinityBack);
    }

    @Test
    void competitionWinnerTest() {
        VideoPost videoPost = getVideoPostRandomSampleGenerator();
        CompetitionWinner competitionWinnerBack = getCompetitionWinnerRandomSampleGenerator();

        videoPost.setCompetitionWinner(competitionWinnerBack);
        assertThat(videoPost.getCompetitionWinner()).isEqualTo(competitionWinnerBack);
        assertThat(competitionWinnerBack.getWinningPost()).isEqualTo(videoPost);

        videoPost.competitionWinner(null);
        assertThat(videoPost.getCompetitionWinner()).isNull();
        assertThat(competitionWinnerBack.getWinningPost()).isNull();
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
