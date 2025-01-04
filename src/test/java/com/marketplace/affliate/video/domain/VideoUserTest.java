package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.AffinityTestSamples.*;
import static com.marketplace.affliate.video.domain.AiToolSessionTestSamples.*;
import static com.marketplace.affliate.video.domain.BankDetailsTestSamples.*;
import static com.marketplace.affliate.video.domain.ContactTestSamples.*;
import static com.marketplace.affliate.video.domain.ReviewTestSamples.*;
import static com.marketplace.affliate.video.domain.SponsorTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
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

    @Test
    void postsTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        VideoPost videoPostBack = getVideoPostRandomSampleGenerator();

        videoUser.addPosts(videoPostBack);
        assertThat(videoUser.getPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getCreator()).isEqualTo(videoUser);

        videoUser.removePosts(videoPostBack);
        assertThat(videoUser.getPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getCreator()).isNull();

        videoUser.posts(new HashSet<>(Set.of(videoPostBack)));
        assertThat(videoUser.getPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getCreator()).isEqualTo(videoUser);

        videoUser.setPosts(new HashSet<>());
        assertThat(videoUser.getPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getCreator()).isNull();
    }

    @Test
    void reviewsTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        Review reviewBack = getReviewRandomSampleGenerator();

        videoUser.addReviews(reviewBack);
        assertThat(videoUser.getReviews()).containsOnly(reviewBack);
        assertThat(reviewBack.getReviewer()).isEqualTo(videoUser);

        videoUser.removeReviews(reviewBack);
        assertThat(videoUser.getReviews()).doesNotContain(reviewBack);
        assertThat(reviewBack.getReviewer()).isNull();

        videoUser.reviews(new HashSet<>(Set.of(reviewBack)));
        assertThat(videoUser.getReviews()).containsOnly(reviewBack);
        assertThat(reviewBack.getReviewer()).isEqualTo(videoUser);

        videoUser.setReviews(new HashSet<>());
        assertThat(videoUser.getReviews()).doesNotContain(reviewBack);
        assertThat(reviewBack.getReviewer()).isNull();
    }

    @Test
    void aiSessionsTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        AiToolSession aiToolSessionBack = getAiToolSessionRandomSampleGenerator();

        videoUser.addAiSessions(aiToolSessionBack);
        assertThat(videoUser.getAiSessions()).containsOnly(aiToolSessionBack);
        assertThat(aiToolSessionBack.getUser()).isEqualTo(videoUser);

        videoUser.removeAiSessions(aiToolSessionBack);
        assertThat(videoUser.getAiSessions()).doesNotContain(aiToolSessionBack);
        assertThat(aiToolSessionBack.getUser()).isNull();

        videoUser.aiSessions(new HashSet<>(Set.of(aiToolSessionBack)));
        assertThat(videoUser.getAiSessions()).containsOnly(aiToolSessionBack);
        assertThat(aiToolSessionBack.getUser()).isEqualTo(videoUser);

        videoUser.setAiSessions(new HashSet<>());
        assertThat(videoUser.getAiSessions()).doesNotContain(aiToolSessionBack);
        assertThat(aiToolSessionBack.getUser()).isNull();
    }

    @Test
    void affinityVectorsTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        Affinity affinityBack = getAffinityRandomSampleGenerator();

        videoUser.addAffinityVectors(affinityBack);
        assertThat(videoUser.getAffinityVectors()).containsOnly(affinityBack);

        videoUser.removeAffinityVectors(affinityBack);
        assertThat(videoUser.getAffinityVectors()).doesNotContain(affinityBack);

        videoUser.affinityVectors(new HashSet<>(Set.of(affinityBack)));
        assertThat(videoUser.getAffinityVectors()).containsOnly(affinityBack);

        videoUser.setAffinityVectors(new HashSet<>());
        assertThat(videoUser.getAffinityVectors()).doesNotContain(affinityBack);
    }

    @Test
    void companyTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        Sponsor sponsorBack = getSponsorRandomSampleGenerator();

        videoUser.setCompany(sponsorBack);
        assertThat(videoUser.getCompany()).isEqualTo(sponsorBack);

        videoUser.company(null);
        assertThat(videoUser.getCompany()).isNull();
    }

    @Test
    void contactsMadeTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        Contact contactBack = getContactRandomSampleGenerator();

        videoUser.addContactsMade(contactBack);
        assertThat(videoUser.getContactsMades()).containsOnly(contactBack);
        assertThat(contactBack.getSender()).isEqualTo(videoUser);

        videoUser.removeContactsMade(contactBack);
        assertThat(videoUser.getContactsMades()).doesNotContain(contactBack);
        assertThat(contactBack.getSender()).isNull();

        videoUser.contactsMades(new HashSet<>(Set.of(contactBack)));
        assertThat(videoUser.getContactsMades()).containsOnly(contactBack);
        assertThat(contactBack.getSender()).isEqualTo(videoUser);

        videoUser.setContactsMades(new HashSet<>());
        assertThat(videoUser.getContactsMades()).doesNotContain(contactBack);
        assertThat(contactBack.getSender()).isNull();
    }

    @Test
    void contactsReceivedTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        Contact contactBack = getContactRandomSampleGenerator();

        videoUser.addContactsReceived(contactBack);
        assertThat(videoUser.getContactsReceiveds()).containsOnly(contactBack);
        assertThat(contactBack.getReceiver()).isEqualTo(videoUser);

        videoUser.removeContactsReceived(contactBack);
        assertThat(videoUser.getContactsReceiveds()).doesNotContain(contactBack);
        assertThat(contactBack.getReceiver()).isNull();

        videoUser.contactsReceiveds(new HashSet<>(Set.of(contactBack)));
        assertThat(videoUser.getContactsReceiveds()).containsOnly(contactBack);
        assertThat(contactBack.getReceiver()).isEqualTo(videoUser);

        videoUser.setContactsReceiveds(new HashSet<>());
        assertThat(videoUser.getContactsReceiveds()).doesNotContain(contactBack);
        assertThat(contactBack.getReceiver()).isNull();
    }
}
