package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsorTestSamples.*;
import static com.marketplace.affliate.video.domain.CompetitionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CompetitionPaymentFromSponsorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetitionPaymentFromSponsor.class);
        CompetitionPaymentFromSponsor competitionPaymentFromSponsor1 = getCompetitionPaymentFromSponsorSample1();
        CompetitionPaymentFromSponsor competitionPaymentFromSponsor2 = new CompetitionPaymentFromSponsor();
        assertThat(competitionPaymentFromSponsor1).isNotEqualTo(competitionPaymentFromSponsor2);

        competitionPaymentFromSponsor2.setId(competitionPaymentFromSponsor1.getId());
        assertThat(competitionPaymentFromSponsor1).isEqualTo(competitionPaymentFromSponsor2);

        competitionPaymentFromSponsor2 = getCompetitionPaymentFromSponsorSample2();
        assertThat(competitionPaymentFromSponsor1).isNotEqualTo(competitionPaymentFromSponsor2);
    }

    @Test
    void competitionTest() {
        CompetitionPaymentFromSponsor competitionPaymentFromSponsor = getCompetitionPaymentFromSponsorRandomSampleGenerator();
        Competition competitionBack = getCompetitionRandomSampleGenerator();

        competitionPaymentFromSponsor.setCompetition(competitionBack);
        assertThat(competitionPaymentFromSponsor.getCompetition()).isEqualTo(competitionBack);

        competitionPaymentFromSponsor.competition(null);
        assertThat(competitionPaymentFromSponsor.getCompetition()).isNull();
    }
}
