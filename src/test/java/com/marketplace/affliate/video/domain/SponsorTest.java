package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.CompetitionTestSamples.*;
import static com.marketplace.affliate.video.domain.SponsorTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SponsorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sponsor.class);
        Sponsor sponsor1 = getSponsorSample1();
        Sponsor sponsor2 = new Sponsor();
        assertThat(sponsor1).isNotEqualTo(sponsor2);

        sponsor2.setId(sponsor1.getId());
        assertThat(sponsor1).isEqualTo(sponsor2);

        sponsor2 = getSponsorSample2();
        assertThat(sponsor1).isNotEqualTo(sponsor2);
    }

    @Test
    void adminUsersTest() {
        Sponsor sponsor = getSponsorRandomSampleGenerator();
        VideoUser videoUserBack = getVideoUserRandomSampleGenerator();

        sponsor.addAdminUsers(videoUserBack);
        assertThat(sponsor.getAdminUsers()).containsOnly(videoUserBack);
        assertThat(videoUserBack.getCompany()).isEqualTo(sponsor);

        sponsor.removeAdminUsers(videoUserBack);
        assertThat(sponsor.getAdminUsers()).doesNotContain(videoUserBack);
        assertThat(videoUserBack.getCompany()).isNull();

        sponsor.adminUsers(new HashSet<>(Set.of(videoUserBack)));
        assertThat(sponsor.getAdminUsers()).containsOnly(videoUserBack);
        assertThat(videoUserBack.getCompany()).isEqualTo(sponsor);

        sponsor.setAdminUsers(new HashSet<>());
        assertThat(sponsor.getAdminUsers()).doesNotContain(videoUserBack);
        assertThat(videoUserBack.getCompany()).isNull();
    }

    @Test
    void sponsoredCompetitionsTest() {
        Sponsor sponsor = getSponsorRandomSampleGenerator();
        Competition competitionBack = getCompetitionRandomSampleGenerator();

        sponsor.addSponsoredCompetitions(competitionBack);
        assertThat(sponsor.getSponsoredCompetitions()).containsOnly(competitionBack);
        assertThat(competitionBack.getSponsor()).isEqualTo(sponsor);

        sponsor.removeSponsoredCompetitions(competitionBack);
        assertThat(sponsor.getSponsoredCompetitions()).doesNotContain(competitionBack);
        assertThat(competitionBack.getSponsor()).isNull();

        sponsor.sponsoredCompetitions(new HashSet<>(Set.of(competitionBack)));
        assertThat(sponsor.getSponsoredCompetitions()).containsOnly(competitionBack);
        assertThat(competitionBack.getSponsor()).isEqualTo(sponsor);

        sponsor.setSponsoredCompetitions(new HashSet<>());
        assertThat(sponsor.getSponsoredCompetitions()).doesNotContain(competitionBack);
        assertThat(competitionBack.getSponsor()).isNull();
    }
}
