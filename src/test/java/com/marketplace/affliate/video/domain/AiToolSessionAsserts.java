package com.marketplace.affliate.video.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class AiToolSessionAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAiToolSessionAllPropertiesEquals(AiToolSession expected, AiToolSession actual) {
        assertAiToolSessionAutoGeneratedPropertiesEquals(expected, actual);
        assertAiToolSessionAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAiToolSessionAllUpdatablePropertiesEquals(AiToolSession expected, AiToolSession actual) {
        assertAiToolSessionUpdatableFieldsEquals(expected, actual);
        assertAiToolSessionUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAiToolSessionAutoGeneratedPropertiesEquals(AiToolSession expected, AiToolSession actual) {
        assertThat(expected)
            .as("Verify AiToolSession auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAiToolSessionUpdatableFieldsEquals(AiToolSession expected, AiToolSession actual) {
        assertThat(expected)
            .as("Verify AiToolSession relevant properties")
            .satisfies(e ->
                assertThat(e.getIsPaymentLinkGenerated()).as("check isPaymentLinkGenerated").isEqualTo(actual.getIsPaymentLinkGenerated())
            )
            .satisfies(e -> assertThat(e.getIsPaid()).as("check isPaid").isEqualTo(actual.getIsPaid()))
            .satisfies(e -> assertThat(e.getIsVideoGenerated()).as("check isVideoGenerated").isEqualTo(actual.getIsVideoGenerated()))
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
    public static void assertAiToolSessionUpdatableRelationshipsEquals(AiToolSession expected, AiToolSession actual) {
        assertThat(expected)
            .as("Verify AiToolSession relationships")
            .satisfies(e -> assertThat(e.getUser()).as("check user").isEqualTo(actual.getUser()))
            .satisfies(e -> assertThat(e.getTool()).as("check tool").isEqualTo(actual.getTool()));
    }
}
