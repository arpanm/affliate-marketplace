package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsorTestSamples.*;
import static com.marketplace.affliate.video.domain.CompetitionTestSamples.*;
import static com.marketplace.affliate.video.domain.PrizeTestSamples.*;
import static com.marketplace.affliate.video.domain.SponsorTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CompetitionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Competition.class);
        Competition competition1 = getCompetitionSample1();
        Competition competition2 = new Competition();
        assertThat(competition1).isNotEqualTo(competition2);

        competition2.setId(competition1.getId());
        assertThat(competition1).isEqualTo(competition2);

        competition2 = getCompetitionSample2();
        assertThat(competition1).isNotEqualTo(competition2);
    }

    @Test
    void prizesTest() {
        Competition competition = getCompetitionRandomSampleGenerator();
        Prize prizeBack = getPrizeRandomSampleGenerator();

        competition.addPrizes(prizeBack);
        assertThat(competition.getPrizes()).containsOnly(prizeBack);
        assertThat(prizeBack.getCompetition()).isEqualTo(competition);

        competition.removePrizes(prizeBack);
        assertThat(competition.getPrizes()).doesNotContain(prizeBack);
        assertThat(prizeBack.getCompetition()).isNull();

        competition.prizes(new HashSet<>(Set.of(prizeBack)));
        assertThat(competition.getPrizes()).containsOnly(prizeBack);
        assertThat(prizeBack.getCompetition()).isEqualTo(competition);

        competition.setPrizes(new HashSet<>());
        assertThat(competition.getPrizes()).doesNotContain(prizeBack);
        assertThat(prizeBack.getCompetition()).isNull();
    }

    @Test
    void paymentsFromSponsorTest() {
        Competition competition = getCompetitionRandomSampleGenerator();
        CompetitionPaymentFromSponsor competitionPaymentFromSponsorBack = getCompetitionPaymentFromSponsorRandomSampleGenerator();

        competition.addPaymentsFromSponsor(competitionPaymentFromSponsorBack);
        assertThat(competition.getPaymentsFromSponsors()).containsOnly(competitionPaymentFromSponsorBack);
        assertThat(competitionPaymentFromSponsorBack.getCompetition()).isEqualTo(competition);

        competition.removePaymentsFromSponsor(competitionPaymentFromSponsorBack);
        assertThat(competition.getPaymentsFromSponsors()).doesNotContain(competitionPaymentFromSponsorBack);
        assertThat(competitionPaymentFromSponsorBack.getCompetition()).isNull();

        competition.paymentsFromSponsors(new HashSet<>(Set.of(competitionPaymentFromSponsorBack)));
        assertThat(competition.getPaymentsFromSponsors()).containsOnly(competitionPaymentFromSponsorBack);
        assertThat(competitionPaymentFromSponsorBack.getCompetition()).isEqualTo(competition);

        competition.setPaymentsFromSponsors(new HashSet<>());
        assertThat(competition.getPaymentsFromSponsors()).doesNotContain(competitionPaymentFromSponsorBack);
        assertThat(competitionPaymentFromSponsorBack.getCompetition()).isNull();
    }

    @Test
    void sponsorTest() {
        Competition competition = getCompetitionRandomSampleGenerator();
        Sponsor sponsorBack = getSponsorRandomSampleGenerator();

        competition.setSponsor(sponsorBack);
        assertThat(competition.getSponsor()).isEqualTo(sponsorBack);

        competition.sponsor(null);
        assertThat(competition.getSponsor()).isNull();
    }

    @Test
    void competitionPostsTest() {
        Competition competition = getCompetitionRandomSampleGenerator();
        VideoPost videoPostBack = getVideoPostRandomSampleGenerator();

        competition.addCompetitionPosts(videoPostBack);
        assertThat(competition.getCompetitionPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getCompetition()).isEqualTo(competition);

        competition.removeCompetitionPosts(videoPostBack);
        assertThat(competition.getCompetitionPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getCompetition()).isNull();

        competition.competitionPosts(new HashSet<>(Set.of(videoPostBack)));
        assertThat(competition.getCompetitionPosts()).containsOnly(videoPostBack);
        assertThat(videoPostBack.getCompetition()).isEqualTo(competition);

        competition.setCompetitionPosts(new HashSet<>());
        assertThat(competition.getCompetitionPosts()).doesNotContain(videoPostBack);
        assertThat(videoPostBack.getCompetition()).isNull();
    }
}
