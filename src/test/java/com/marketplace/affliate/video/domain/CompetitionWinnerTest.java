package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.CompetitionPaymentToWinnerTestSamples.*;
import static com.marketplace.affliate.video.domain.CompetitionWinnerTestSamples.*;
import static com.marketplace.affliate.video.domain.PrizeTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoPostTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CompetitionWinnerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetitionWinner.class);
        CompetitionWinner competitionWinner1 = getCompetitionWinnerSample1();
        CompetitionWinner competitionWinner2 = new CompetitionWinner();
        assertThat(competitionWinner1).isNotEqualTo(competitionWinner2);

        competitionWinner2.setId(competitionWinner1.getId());
        assertThat(competitionWinner1).isEqualTo(competitionWinner2);

        competitionWinner2 = getCompetitionWinnerSample2();
        assertThat(competitionWinner1).isNotEqualTo(competitionWinner2);
    }

    @Test
    void winningPostTest() {
        CompetitionWinner competitionWinner = getCompetitionWinnerRandomSampleGenerator();
        VideoPost videoPostBack = getVideoPostRandomSampleGenerator();

        competitionWinner.setWinningPost(videoPostBack);
        assertThat(competitionWinner.getWinningPost()).isEqualTo(videoPostBack);

        competitionWinner.winningPost(null);
        assertThat(competitionWinner.getWinningPost()).isNull();
    }

    @Test
    void paymentToWinnerTest() {
        CompetitionWinner competitionWinner = getCompetitionWinnerRandomSampleGenerator();
        CompetitionPaymentToWinner competitionPaymentToWinnerBack = getCompetitionPaymentToWinnerRandomSampleGenerator();

        competitionWinner.setPaymentToWinner(competitionPaymentToWinnerBack);
        assertThat(competitionWinner.getPaymentToWinner()).isEqualTo(competitionPaymentToWinnerBack);

        competitionWinner.paymentToWinner(null);
        assertThat(competitionWinner.getPaymentToWinner()).isNull();
    }

    @Test
    void competitionPrizeTest() {
        CompetitionWinner competitionWinner = getCompetitionWinnerRandomSampleGenerator();
        Prize prizeBack = getPrizeRandomSampleGenerator();

        competitionWinner.setCompetitionPrize(prizeBack);
        assertThat(competitionWinner.getCompetitionPrize()).isEqualTo(prizeBack);

        competitionWinner.competitionPrize(null);
        assertThat(competitionWinner.getCompetitionPrize()).isNull();
    }
}
