package com.marketplace.affliate.video.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoTagAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVideoTagAllPropertiesEquals(VideoTag expected, VideoTag actual) {
        assertVideoTagAutoGeneratedPropertiesEquals(expected, actual);
        assertVideoTagAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVideoTagAllUpdatablePropertiesEquals(VideoTag expected, VideoTag actual) {
        assertVideoTagUpdatableFieldsEquals(expected, actual);
        assertVideoTagUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVideoTagAutoGeneratedPropertiesEquals(VideoTag expected, VideoTag actual) {
        assertThat(expected)
            .as("Verify VideoTag auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVideoTagUpdatableFieldsEquals(VideoTag expected, VideoTag actual) {
        assertThat(expected)
            .as("Verify VideoTag relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getIsModerated()).as("check isModerated").isEqualTo(actual.getIsModerated()))
            .satisfies(e -> assertThat(e.getIsDeleted()).as("check isDeleted").isEqualTo(actual.getIsDeleted()))
            .satisfies(e -> assertThat(e.getDeletionReason()).as("check deletionReason").isEqualTo(actual.getDeletionReason()))
            .satisfies(e -> assertThat(e.getMergedWithTagName()).as("check mergedWithTagName").isEqualTo(actual.getMergedWithTagName()))
            .satisfies(e -> assertThat(e.getMergedWithTagCode()).as("check mergedWithTagCode").isEqualTo(actual.getMergedWithTagCode()))
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
    public static void assertVideoTagUpdatableRelationshipsEquals(VideoTag expected, VideoTag actual) {
        assertThat(expected)
            .as("Verify VideoTag relationships")
            .satisfies(e -> assertThat(e.getPosts()).as("check posts").isEqualTo(actual.getPosts()));
    }
}