package com.marketplace.affliate.video.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionAllPropertiesEquals(Competition expected, Competition actual) {
        assertCompetitionAutoGeneratedPropertiesEquals(expected, actual);
        assertCompetitionAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionAllUpdatablePropertiesEquals(Competition expected, Competition actual) {
        assertCompetitionUpdatableFieldsEquals(expected, actual);
        assertCompetitionUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionAutoGeneratedPropertiesEquals(Competition expected, Competition actual) {
        assertThat(expected)
            .as("Verify Competition auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionUpdatableFieldsEquals(Competition expected, Competition actual) {
        assertThat(expected)
            .as("Verify Competition relevant properties")
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getIsBlocked()).as("check isBlocked").isEqualTo(actual.getIsBlocked()))
            .satisfies(e -> assertThat(e.getBlockReason()).as("check blockReason").isEqualTo(actual.getBlockReason()))
            .satisfies(e -> assertThat(e.getBlockedBy()).as("check blockedBy").isEqualTo(actual.getBlockedBy()))
            .satisfies(e -> assertThat(e.getIsPaused()).as("check isPaused").isEqualTo(actual.getIsPaused()))
            .satisfies(e -> assertThat(e.getPauseReason()).as("check pauseReason").isEqualTo(actual.getPauseReason()))
            .satisfies(e -> assertThat(e.getPausedBy()).as("check pausedBy").isEqualTo(actual.getPausedBy()))
            .satisfies(e -> assertThat(e.getBanner1Url()).as("check banner1Url").isEqualTo(actual.getBanner1Url()))
            .satisfies(e -> assertThat(e.getBanner2Url()).as("check banner2Url").isEqualTo(actual.getBanner2Url()))
            .satisfies(e -> assertThat(e.getBanner3Url()).as("check banner3Url").isEqualTo(actual.getBanner3Url()))
            .satisfies(e -> assertThat(e.getStartDate()).as("check startDate").isEqualTo(actual.getStartDate()))
            .satisfies(e -> assertThat(e.getEndDate()).as("check endDate").isEqualTo(actual.getEndDate()))
            .satisfies(e -> assertThat(e.getLandingUrl()).as("check landingUrl").isEqualTo(actual.getLandingUrl()))
            .satisfies(e -> assertThat(e.getTotalPrizeValue()).as("check totalPrizeValue").isEqualTo(actual.getTotalPrizeValue()))
            .satisfies(e ->
                assertThat(e.getInvoiceToSponsorUrl()).as("check invoiceToSponsorUrl").isEqualTo(actual.getInvoiceToSponsorUrl())
            )
            .satisfies(e -> assertThat(e.getTncUrl()).as("check tncUrl").isEqualTo(actual.getTncUrl()))
            .satisfies(e -> assertThat(e.getTnc()).as("check tnc").isEqualTo(actual.getTnc()))
            .satisfies(e -> assertThat(e.getIsActive()).as("check isActive").isEqualTo(actual.getIsActive()))
            .satisfies(e -> assertThat(e.getCreatedBy()).as("check createdBy").isEqualTo(actual.getCreatedBy()))
            .satisfies(e -> assertThat(e.getCreatedOn()).as("check createdOn").isEqualTo(actual.getCreatedOn()))
            .satisfies(e -> assertThat(e.getUpdatedBy()).as("check updatedBy").isEqualTo(actual.getUpdatedBy()))
            .satisfies(e -> assertThat(e.getUpdatedOn()).as("check updatedOn").isEqualTo(actual.getUpdatedOn()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionUpdatableRelationshipsEquals(Competition expected, Competition actual) {
        assertThat(expected)
            .as("Verify Competition relationships")
            .satisfies(e -> assertThat(e.getSponsor()).as("check sponsor").isEqualTo(actual.getSponsor()));
    }
}