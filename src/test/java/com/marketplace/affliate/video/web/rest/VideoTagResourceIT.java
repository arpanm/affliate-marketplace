package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.VideoTagAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.VideoTag;
import com.marketplace.affliate.video.repository.VideoTagRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VideoTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VideoTagResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MODERATED = false;
    private static final Boolean UPDATED_IS_MODERATED = true;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_DELETION_REASON = "AAAAAAAAAA";
    private static final String UPDATED_DELETION_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_MERGED_WITH_TAG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MERGED_WITH_TAG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MERGED_WITH_TAG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MERGED_WITH_TAG_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/video-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VideoTagRepository videoTagRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVideoTagMockMvc;

    private VideoTag videoTag;

    private VideoTag insertedVideoTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoTag createEntity() {
        return new VideoTag()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .isModerated(DEFAULT_IS_MODERATED)
            .isDeleted(DEFAULT_IS_DELETED)
            .deletionReason(DEFAULT_DELETION_REASON)
            .mergedWithTagName(DEFAULT_MERGED_WITH_TAG_NAME)
            .mergedWithTagCode(DEFAULT_MERGED_WITH_TAG_CODE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoTag createUpdatedEntity() {
        return new VideoTag()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .isModerated(UPDATED_IS_MODERATED)
            .isDeleted(UPDATED_IS_DELETED)
            .deletionReason(UPDATED_DELETION_REASON)
            .mergedWithTagName(UPDATED_MERGED_WITH_TAG_NAME)
            .mergedWithTagCode(UPDATED_MERGED_WITH_TAG_CODE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        videoTag = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedVideoTag != null) {
            videoTagRepository.delete(insertedVideoTag);
            insertedVideoTag = null;
        }
    }

    @Test
    @Transactional
    void createVideoTag() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the VideoTag
        var returnedVideoTag = om.readValue(
            restVideoTagMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoTag)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            VideoTag.class
        );

        // Validate the VideoTag in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVideoTagUpdatableFieldsEquals(returnedVideoTag, getPersistedVideoTag(returnedVideoTag));

        insertedVideoTag = returnedVideoTag;
    }

    @Test
    @Transactional
    void createVideoTagWithExistingId() throws Exception {
        // Create the VideoTag with an existing ID
        videoTag.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVideoTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoTag)))
            .andExpect(status().isBadRequest());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVideoTags() throws Exception {
        // Initialize the database
        insertedVideoTag = videoTagRepository.saveAndFlush(videoTag);

        // Get all the videoTagList
        restVideoTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(videoTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].isModerated").value(hasItem(DEFAULT_IS_MODERATED)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED)))
            .andExpect(jsonPath("$.[*].deletionReason").value(hasItem(DEFAULT_DELETION_REASON)))
            .andExpect(jsonPath("$.[*].mergedWithTagName").value(hasItem(DEFAULT_MERGED_WITH_TAG_NAME)))
            .andExpect(jsonPath("$.[*].mergedWithTagCode").value(hasItem(DEFAULT_MERGED_WITH_TAG_CODE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getVideoTag() throws Exception {
        // Initialize the database
        insertedVideoTag = videoTagRepository.saveAndFlush(videoTag);

        // Get the videoTag
        restVideoTagMockMvc
            .perform(get(ENTITY_API_URL_ID, videoTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(videoTag.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.isModerated").value(DEFAULT_IS_MODERATED))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED))
            .andExpect(jsonPath("$.deletionReason").value(DEFAULT_DELETION_REASON))
            .andExpect(jsonPath("$.mergedWithTagName").value(DEFAULT_MERGED_WITH_TAG_NAME))
            .andExpect(jsonPath("$.mergedWithTagCode").value(DEFAULT_MERGED_WITH_TAG_CODE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVideoTag() throws Exception {
        // Get the videoTag
        restVideoTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVideoTag() throws Exception {
        // Initialize the database
        insertedVideoTag = videoTagRepository.saveAndFlush(videoTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoTag
        VideoTag updatedVideoTag = videoTagRepository.findById(videoTag.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVideoTag are not directly saved in db
        em.detach(updatedVideoTag);
        updatedVideoTag
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .isModerated(UPDATED_IS_MODERATED)
            .isDeleted(UPDATED_IS_DELETED)
            .deletionReason(UPDATED_DELETION_REASON)
            .mergedWithTagName(UPDATED_MERGED_WITH_TAG_NAME)
            .mergedWithTagCode(UPDATED_MERGED_WITH_TAG_CODE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVideoTag.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVideoTag))
            )
            .andExpect(status().isOk());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVideoTagToMatchAllProperties(updatedVideoTag);
    }

    @Test
    @Transactional
    void putNonExistingVideoTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoTag.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, videoTag.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVideoTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(videoTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVideoTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoTagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoTag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVideoTagWithPatch() throws Exception {
        // Initialize the database
        insertedVideoTag = videoTagRepository.saveAndFlush(videoTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoTag using partial update
        VideoTag partialUpdatedVideoTag = new VideoTag();
        partialUpdatedVideoTag.setId(videoTag.getId());

        partialUpdatedVideoTag
            .name(UPDATED_NAME)
            .isModerated(UPDATED_IS_MODERATED)
            .mergedWithTagCode(UPDATED_MERGED_WITH_TAG_CODE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON);

        restVideoTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoTag))
            )
            .andExpect(status().isOk());

        // Validate the VideoTag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoTagUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedVideoTag, videoTag), getPersistedVideoTag(videoTag));
    }

    @Test
    @Transactional
    void fullUpdateVideoTagWithPatch() throws Exception {
        // Initialize the database
        insertedVideoTag = videoTagRepository.saveAndFlush(videoTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoTag using partial update
        VideoTag partialUpdatedVideoTag = new VideoTag();
        partialUpdatedVideoTag.setId(videoTag.getId());

        partialUpdatedVideoTag
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .isModerated(UPDATED_IS_MODERATED)
            .isDeleted(UPDATED_IS_DELETED)
            .deletionReason(UPDATED_DELETION_REASON)
            .mergedWithTagName(UPDATED_MERGED_WITH_TAG_NAME)
            .mergedWithTagCode(UPDATED_MERGED_WITH_TAG_CODE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoTag))
            )
            .andExpect(status().isOk());

        // Validate the VideoTag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoTagUpdatableFieldsEquals(partialUpdatedVideoTag, getPersistedVideoTag(partialUpdatedVideoTag));
    }

    @Test
    @Transactional
    void patchNonExistingVideoTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoTag.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, videoTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVideoTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVideoTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoTagMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(videoTag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVideoTag() throws Exception {
        // Initialize the database
        insertedVideoTag = videoTagRepository.saveAndFlush(videoTag);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the videoTag
        restVideoTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, videoTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return videoTagRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected VideoTag getPersistedVideoTag(VideoTag videoTag) {
        return videoTagRepository.findById(videoTag.getId()).orElseThrow();
    }

    protected void assertPersistedVideoTagToMatchAllProperties(VideoTag expectedVideoTag) {
        assertVideoTagAllPropertiesEquals(expectedVideoTag, getPersistedVideoTag(expectedVideoTag));
    }

    protected void assertPersistedVideoTagToMatchUpdatableProperties(VideoTag expectedVideoTag) {
        assertVideoTagAllUpdatablePropertiesEquals(expectedVideoTag, getPersistedVideoTag(expectedVideoTag));
    }
}
