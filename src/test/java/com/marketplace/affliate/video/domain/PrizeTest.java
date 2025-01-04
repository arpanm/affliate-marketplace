package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.CompetitionTestSamples.*;
import static com.marketplace.affliate.video.domain.CompetitionWinnerTestSamples.*;
import static com.marketplace.affliate.video.domain.PrizeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Prize.class);
        Prize prize1 = getPrizeSample1();
        Prize prize2 = new Prize();
        assertThat(prize1).isNotEqualTo(prize2);

        prize2.setId(prize1.getId());
        assertThat(prize1).isEqualTo(prize2);

        prize2 = getPrizeSample2();
        assertThat(prize1).isNotEqualTo(prize2);
    }

    @Test
    void winnersTest() {
        Prize prize = getPrizeRandomSampleGenerator();
        CompetitionWinner competitionWinnerBack = getCompetitionWinnerRandomSampleGenerator();

        prize.addWinners(competitionWinnerBack);
        assertThat(prize.getWinners()).containsOnly(competitionWinnerBack);
        assertThat(competitionWinnerBack.getCompetitionPrize()).isEqualTo(prize);

        prize.removeWinners(competitionWinnerBack);
        assertThat(prize.getWinners()).doesNotContain(competitionWinnerBack);
        assertThat(competitionWinnerBack.getCompetitionPrize()).isNull();

        prize.winners(new HashSet<>(Set.of(competitionWinnerBack)));
        assertThat(prize.getWinners()).containsOnly(competitionWinnerBack);
        assertThat(competitionWinnerBack.getCompetitionPrize()).isEqualTo(prize);

        prize.setWinners(new HashSet<>());
        assertThat(prize.getWinners()).doesNotContain(competitionWinnerBack);
        assertThat(competitionWinnerBack.getCompetitionPrize()).isNull();
    }

    @Test
    void competitionTest() {
        Prize prize = getPrizeRandomSampleGenerator();
        Competition competitionBack = getCompetitionRandomSampleGenerator();

        prize.setCompetition(competitionBack);
        assertThat(prize.getCompetition()).isEqualTo(competitionBack);

        prize.competition(null);
        assertThat(prize.getCompetition()).isNull();
    }
}
