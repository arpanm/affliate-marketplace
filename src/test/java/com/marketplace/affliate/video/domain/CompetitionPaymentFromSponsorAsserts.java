package com.marketplace.affliate.video.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionPaymentFromSponsorAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionPaymentFromSponsorAllPropertiesEquals(
        CompetitionPaymentFromSponsor expected,
        CompetitionPaymentFromSponsor actual
    ) {
        assertCompetitionPaymentFromSponsorAutoGeneratedPropertiesEquals(expected, actual);
        assertCompetitionPaymentFromSponsorAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionPaymentFromSponsorAllUpdatablePropertiesEquals(
        CompetitionPaymentFromSponsor expected,
        CompetitionPaymentFromSponsor actual
    ) {
        assertCompetitionPaymentFromSponsorUpdatableFieldsEquals(expected, actual);
        assertCompetitionPaymentFromSponsorUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionPaymentFromSponsorAutoGeneratedPropertiesEquals(
        CompetitionPaymentFromSponsor expected,
        CompetitionPaymentFromSponsor actual
    ) {
        assertThat(expected)
            .as("Verify CompetitionPaymentFromSponsor auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCompetitionPaymentFromSponsorUpdatableFieldsEquals(
        CompetitionPaymentFromSponsor expected,
        CompetitionPaymentFromSponsor actual
    ) {
        assertThat(expected)
            .as("Verify CompetitionPaymentFromSponsor relevant properties")
            .satisfies(e -> assertThat(e.getAmount()).as("check amount").isEqualTo(actual.getAmount()))
            .satisfies(e -> assertThat(e.getTransactionId()).as("check transactionId").isEqualTo(actual.getTransactionId()))
            .satisfies(e ->
                assertThat(e.getTransactionScreenshotUrl())
                    .as("check transactionScreenshotUrl")
                    .isEqualTo(actual.getTransactionScreenshotUrl())
            )
            .satisfies(e -> assertThat(e.getTransactionDate()).as("check transactionDate").isEqualTo(actual.getTransactionDate()))
            .satisfies(e -> assertThat(e.getTransactionStatus()).as("check transactionStatus").isEqualTo(actual.getTransactionStatus()))
            .satisfies(e -> assertThat(e.getPaidBy()).as("check paidBy").isEqualTo(actual.getPaidBy()))
            .satisfies(e -> assertThat(e.getPaymentReceiptUrl()).as("check paymentReceiptUrl").isEqualTo(actual.getPaymentReceiptUrl()))
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
    public static void assertCompetitionPaymentFromSponsorUpdatableRelationshipsEquals(
        CompetitionPaymentFromSponsor expected,
        CompetitionPaymentFromSponsor actual
    ) {
        assertThat(expected)
            .as("Verify CompetitionPaymentFromSponsor relationships")
            .satisfies(e -> assertThat(e.getCompetition()).as("check competition").isEqualTo(actual.getCompetition()));
    }
}
