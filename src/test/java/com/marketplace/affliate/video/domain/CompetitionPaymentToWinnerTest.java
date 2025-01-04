package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.CompetitionPaymentToWinnerTestSamples.*;
import static com.marketplace.affliate.video.domain.CompetitionWinnerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CompetitionPaymentToWinnerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetitionPaymentToWinner.class);
        CompetitionPaymentToWinner competitionPaymentToWinner1 = getCompetitionPaymentToWinnerSample1();
        CompetitionPaymentToWinner competitionPaymentToWinner2 = new CompetitionPaymentToWinner();
        assertThat(competitionPaymentToWinner1).isNotEqualTo(competitionPaymentToWinner2);

        competitionPaymentToWinner2.setId(competitionPaymentToWinner1.getId());
        assertThat(competitionPaymentToWinner1).isEqualTo(competitionPaymentToWinner2);

        competitionPaymentToWinner2 = getCompetitionPaymentToWinnerSample2();
        assertThat(competitionPaymentToWinner1).isNotEqualTo(competitionPaymentToWinner2);
    }

    @Test
    void winnerTest() {
        CompetitionPaymentToWinner competitionPaymentToWinner = getCompetitionPaymentToWinnerRandomSampleGenerator();
        CompetitionWinner competitionWinnerBack = getCompetitionWinnerRandomSampleGenerator();

        competitionPaymentToWinner.setWinner(competitionWinnerBack);
        assertThat(competitionPaymentToWinner.getWinner()).isEqualTo(competitionWinnerBack);
        assertThat(competitionWinnerBack.getPaymentToWinner()).isEqualTo(competitionPaymentToWinner);

        competitionPaymentToWinner.winner(null);
        assertThat(competitionPaymentToWinner.getWinner()).isNull();
        assertThat(competitionWinnerBack.getPaymentToWinner()).isNull();
    }
}
