package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.VideoPostChangeHistoryAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.VideoPostChangeHistory;
import com.marketplace.affliate.video.domain.enumeration.ChangeType;
import com.marketplace.affliate.video.domain.enumeration.UrlType;
import com.marketplace.affliate.video.repository.VideoPostChangeHistoryRepository;
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
 * Integration tests for the {@link VideoPostChangeHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VideoPostChangeHistoryResourceIT {

    private static final ChangeType DEFAULT_CHANGE_TYPE = ChangeType.ModeratorUpdateContent;
    private static final ChangeType UPDATED_CHANGE_TYPE = ChangeType.ModeratorUpdateActiveStatus;

    private static final String DEFAULT_OLDTITLE = "AAAAAAAAAA";
    private static final String UPDATED_OLDTITLE = "BBBBBBBBBB";

    private static final String DEFAULT_OLD_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_OLD_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_OLD_URL = "AAAAAAAAAA";
    private static final String UPDATED_OLD_URL = "BBBBBBBBBB";

    private static final UrlType DEFAULT_OLD_URL_TYPE = UrlType.YouTube;
    private static final UrlType UPDATED_OLD_URL_TYPE = UrlType.Instagram;

    private static final Boolean DEFAULT_OLD_IS_AI_GENERATED = false;
    private static final Boolean UPDATED_OLD_IS_AI_GENERATED = true;

    private static final Boolean DEFAULT_OLD_IS_PREMIUM = false;
    private static final Boolean UPDATED_OLD_IS_PREMIUM = true;

    private static final Boolean DEFAULT_OLD_IS_BLOCKED = false;
    private static final Boolean UPDATED_OLD_IS_BLOCKED = true;

    private static final Boolean DEFAULT_OLD_IS_MODERATED = false;
    private static final Boolean UPDATED_OLD_IS_MODERATED = true;

    private static final Boolean DEFAULT_OLD_IS_ACTIVE = false;
    private static final Boolean UPDATED_OLD_IS_ACTIVE = true;

    private static final String DEFAULT_OLD_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_OLD_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OLD_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OLD_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OLD_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_OLD_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OLD_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OLD_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/video-post-change-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VideoPostChangeHistoryRepository videoPostChangeHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVideoPostChangeHistoryMockMvc;

    private VideoPostChangeHistory videoPostChangeHistory;

    private VideoPostChangeHistory insertedVideoPostChangeHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoPostChangeHistory createEntity() {
        return new VideoPostChangeHistory()
            .changeType(DEFAULT_CHANGE_TYPE)
            .oldtitle(DEFAULT_OLDTITLE)
            .oldDescription(DEFAULT_OLD_DESCRIPTION)
            .oldUrl(DEFAULT_OLD_URL)
            .oldUrlType(DEFAULT_OLD_URL_TYPE)
            .oldIsAIGenerated(DEFAULT_OLD_IS_AI_GENERATED)
            .oldIsPremium(DEFAULT_OLD_IS_PREMIUM)
            .oldIsBlocked(DEFAULT_OLD_IS_BLOCKED)
            .oldIsModerated(DEFAULT_OLD_IS_MODERATED)
            .oldIsActive(DEFAULT_OLD_IS_ACTIVE)
            .oldCreatedBy(DEFAULT_OLD_CREATED_BY)
            .oldCreatedOn(DEFAULT_OLD_CREATED_ON)
            .oldUpdatedBy(DEFAULT_OLD_UPDATED_BY)
            .oldUpdatedOn(DEFAULT_OLD_UPDATED_ON);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoPostChangeHistory createUpdatedEntity() {
        return new VideoPostChangeHistory()
            .changeType(UPDATED_CHANGE_TYPE)
            .oldtitle(UPDATED_OLDTITLE)
            .oldDescription(UPDATED_OLD_DESCRIPTION)
            .oldUrl(UPDATED_OLD_URL)
            .oldUrlType(UPDATED_OLD_URL_TYPE)
            .oldIsAIGenerated(UPDATED_OLD_IS_AI_GENERATED)
            .oldIsPremium(UPDATED_OLD_IS_PREMIUM)
            .oldIsBlocked(UPDATED_OLD_IS_BLOCKED)
            .oldIsModerated(UPDATED_OLD_IS_MODERATED)
            .oldIsActive(UPDATED_OLD_IS_ACTIVE)
            .oldCreatedBy(UPDATED_OLD_CREATED_BY)
            .oldCreatedOn(UPDATED_OLD_CREATED_ON)
            .oldUpdatedBy(UPDATED_OLD_UPDATED_BY)
            .oldUpdatedOn(UPDATED_OLD_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        videoPostChangeHistory = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedVideoPostChangeHistory != null) {
            videoPostChangeHistoryRepository.delete(insertedVideoPostChangeHistory);
            insertedVideoPostChangeHistory = null;
        }
    }

    @Test
    @Transactional
    void createVideoPostChangeHistory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the VideoPostChangeHistory
        var returnedVideoPostChangeHistory = om.readValue(
            restVideoPostChangeHistoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoPostChangeHistory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            VideoPostChangeHistory.class
        );

        // Validate the VideoPostChangeHistory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVideoPostChangeHistoryUpdatableFieldsEquals(
            returnedVideoPostChangeHistory,
            getPersistedVideoPostChangeHistory(returnedVideoPostChangeHistory)
        );

        insertedVideoPostChangeHistory = returnedVideoPostChangeHistory;
    }

    @Test
    @Transactional
    void createVideoPostChangeHistoryWithExistingId() throws Exception {
        // Create the VideoPostChangeHistory with an existing ID
        videoPostChangeHistory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVideoPostChangeHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoPostChangeHistory)))
            .andExpect(status().isBadRequest());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVideoPostChangeHistories() throws Exception {
        // Initialize the database
        insertedVideoPostChangeHistory = videoPostChangeHistoryRepository.saveAndFlush(videoPostChangeHistory);

        // Get all the videoPostChangeHistoryList
        restVideoPostChangeHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(videoPostChangeHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].changeType").value(hasItem(DEFAULT_CHANGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].oldtitle").value(hasItem(DEFAULT_OLDTITLE)))
            .andExpect(jsonPath("$.[*].oldDescription").value(hasItem(DEFAULT_OLD_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].oldUrl").value(hasItem(DEFAULT_OLD_URL)))
            .andExpect(jsonPath("$.[*].oldUrlType").value(hasItem(DEFAULT_OLD_URL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].oldIsAIGenerated").value(hasItem(DEFAULT_OLD_IS_AI_GENERATED)))
            .andExpect(jsonPath("$.[*].oldIsPremium").value(hasItem(DEFAULT_OLD_IS_PREMIUM)))
            .andExpect(jsonPath("$.[*].oldIsBlocked").value(hasItem(DEFAULT_OLD_IS_BLOCKED)))
            .andExpect(jsonPath("$.[*].oldIsModerated").value(hasItem(DEFAULT_OLD_IS_MODERATED)))
            .andExpect(jsonPath("$.[*].oldIsActive").value(hasItem(DEFAULT_OLD_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].oldCreatedBy").value(hasItem(DEFAULT_OLD_CREATED_BY)))
            .andExpect(jsonPath("$.[*].oldCreatedOn").value(hasItem(DEFAULT_OLD_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].oldUpdatedBy").value(hasItem(DEFAULT_OLD_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].oldUpdatedOn").value(hasItem(DEFAULT_OLD_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getVideoPostChangeHistory() throws Exception {
        // Initialize the database
        insertedVideoPostChangeHistory = videoPostChangeHistoryRepository.saveAndFlush(videoPostChangeHistory);

        // Get the videoPostChangeHistory
        restVideoPostChangeHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, videoPostChangeHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(videoPostChangeHistory.getId().intValue()))
            .andExpect(jsonPath("$.changeType").value(DEFAULT_CHANGE_TYPE.toString()))
            .andExpect(jsonPath("$.oldtitle").value(DEFAULT_OLDTITLE))
            .andExpect(jsonPath("$.oldDescription").value(DEFAULT_OLD_DESCRIPTION))
            .andExpect(jsonPath("$.oldUrl").value(DEFAULT_OLD_URL))
            .andExpect(jsonPath("$.oldUrlType").value(DEFAULT_OLD_URL_TYPE.toString()))
            .andExpect(jsonPath("$.oldIsAIGenerated").value(DEFAULT_OLD_IS_AI_GENERATED))
            .andExpect(jsonPath("$.oldIsPremium").value(DEFAULT_OLD_IS_PREMIUM))
            .andExpect(jsonPath("$.oldIsBlocked").value(DEFAULT_OLD_IS_BLOCKED))
            .andExpect(jsonPath("$.oldIsModerated").value(DEFAULT_OLD_IS_MODERATED))
            .andExpect(jsonPath("$.oldIsActive").value(DEFAULT_OLD_IS_ACTIVE))
            .andExpect(jsonPath("$.oldCreatedBy").value(DEFAULT_OLD_CREATED_BY))
            .andExpect(jsonPath("$.oldCreatedOn").value(DEFAULT_OLD_CREATED_ON.toString()))
            .andExpect(jsonPath("$.oldUpdatedBy").value(DEFAULT_OLD_UPDATED_BY))
            .andExpect(jsonPath("$.oldUpdatedOn").value(DEFAULT_OLD_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVideoPostChangeHistory() throws Exception {
        // Get the videoPostChangeHistory
        restVideoPostChangeHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVideoPostChangeHistory() throws Exception {
        // Initialize the database
        insertedVideoPostChangeHistory = videoPostChangeHistoryRepository.saveAndFlush(videoPostChangeHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoPostChangeHistory
        VideoPostChangeHistory updatedVideoPostChangeHistory = videoPostChangeHistoryRepository
            .findById(videoPostChangeHistory.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedVideoPostChangeHistory are not directly saved in db
        em.detach(updatedVideoPostChangeHistory);
        updatedVideoPostChangeHistory
            .changeType(UPDATED_CHANGE_TYPE)
            .oldtitle(UPDATED_OLDTITLE)
            .oldDescription(UPDATED_OLD_DESCRIPTION)
            .oldUrl(UPDATED_OLD_URL)
            .oldUrlType(UPDATED_OLD_URL_TYPE)
            .oldIsAIGenerated(UPDATED_OLD_IS_AI_GENERATED)
            .oldIsPremium(UPDATED_OLD_IS_PREMIUM)
            .oldIsBlocked(UPDATED_OLD_IS_BLOCKED)
            .oldIsModerated(UPDATED_OLD_IS_MODERATED)
            .oldIsActive(UPDATED_OLD_IS_ACTIVE)
            .oldCreatedBy(UPDATED_OLD_CREATED_BY)
            .oldCreatedOn(UPDATED_OLD_CREATED_ON)
            .oldUpdatedBy(UPDATED_OLD_UPDATED_BY)
            .oldUpdatedOn(UPDATED_OLD_UPDATED_ON);

        restVideoPostChangeHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVideoPostChangeHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVideoPostChangeHistory))
            )
            .andExpect(status().isOk());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVideoPostChangeHistoryToMatchAllProperties(updatedVideoPostChangeHistory);
    }

    @Test
    @Transactional
    void putNonExistingVideoPostChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPostChangeHistory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoPostChangeHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, videoPostChangeHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(videoPostChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVideoPostChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPostChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostChangeHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(videoPostChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVideoPostChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPostChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostChangeHistoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoPostChangeHistory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVideoPostChangeHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedVideoPostChangeHistory = videoPostChangeHistoryRepository.saveAndFlush(videoPostChangeHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoPostChangeHistory using partial update
        VideoPostChangeHistory partialUpdatedVideoPostChangeHistory = new VideoPostChangeHistory();
        partialUpdatedVideoPostChangeHistory.setId(videoPostChangeHistory.getId());

        partialUpdatedVideoPostChangeHistory
            .changeType(UPDATED_CHANGE_TYPE)
            .oldDescription(UPDATED_OLD_DESCRIPTION)
            .oldUrl(UPDATED_OLD_URL)
            .oldUrlType(UPDATED_OLD_URL_TYPE)
            .oldIsBlocked(UPDATED_OLD_IS_BLOCKED)
            .oldCreatedBy(UPDATED_OLD_CREATED_BY)
            .oldCreatedOn(UPDATED_OLD_CREATED_ON)
            .oldUpdatedBy(UPDATED_OLD_UPDATED_BY);

        restVideoPostChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoPostChangeHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoPostChangeHistory))
            )
            .andExpect(status().isOk());

        // Validate the VideoPostChangeHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoPostChangeHistoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedVideoPostChangeHistory, videoPostChangeHistory),
            getPersistedVideoPostChangeHistory(videoPostChangeHistory)
        );
    }

    @Test
    @Transactional
    void fullUpdateVideoPostChangeHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedVideoPostChangeHistory = videoPostChangeHistoryRepository.saveAndFlush(videoPostChangeHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoPostChangeHistory using partial update
        VideoPostChangeHistory partialUpdatedVideoPostChangeHistory = new VideoPostChangeHistory();
        partialUpdatedVideoPostChangeHistory.setId(videoPostChangeHistory.getId());

        partialUpdatedVideoPostChangeHistory
            .changeType(UPDATED_CHANGE_TYPE)
            .oldtitle(UPDATED_OLDTITLE)
            .oldDescription(UPDATED_OLD_DESCRIPTION)
            .oldUrl(UPDATED_OLD_URL)
            .oldUrlType(UPDATED_OLD_URL_TYPE)
            .oldIsAIGenerated(UPDATED_OLD_IS_AI_GENERATED)
            .oldIsPremium(UPDATED_OLD_IS_PREMIUM)
            .oldIsBlocked(UPDATED_OLD_IS_BLOCKED)
            .oldIsModerated(UPDATED_OLD_IS_MODERATED)
            .oldIsActive(UPDATED_OLD_IS_ACTIVE)
            .oldCreatedBy(UPDATED_OLD_CREATED_BY)
            .oldCreatedOn(UPDATED_OLD_CREATED_ON)
            .oldUpdatedBy(UPDATED_OLD_UPDATED_BY)
            .oldUpdatedOn(UPDATED_OLD_UPDATED_ON);

        restVideoPostChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoPostChangeHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoPostChangeHistory))
            )
            .andExpect(status().isOk());

        // Validate the VideoPostChangeHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoPostChangeHistoryUpdatableFieldsEquals(
            partialUpdatedVideoPostChangeHistory,
            getPersistedVideoPostChangeHistory(partialUpdatedVideoPostChangeHistory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingVideoPostChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPostChangeHistory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoPostChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, videoPostChangeHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoPostChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVideoPostChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPostChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoPostChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVideoPostChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPostChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(videoPostChangeHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoPostChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVideoPostChangeHistory() throws Exception {
        // Initialize the database
        insertedVideoPostChangeHistory = videoPostChangeHistoryRepository.saveAndFlush(videoPostChangeHistory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the videoPostChangeHistory
        restVideoPostChangeHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, videoPostChangeHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return videoPostChangeHistoryRepository.count();
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

    protected VideoPostChangeHistory getPersistedVideoPostChangeHistory(VideoPostChangeHistory videoPostChangeHistory) {
        return videoPostChangeHistoryRepository.findById(videoPostChangeHistory.getId()).orElseThrow();
    }

    protected void assertPersistedVideoPostChangeHistoryToMatchAllProperties(VideoPostChangeHistory expectedVideoPostChangeHistory) {
        assertVideoPostChangeHistoryAllPropertiesEquals(
            expectedVideoPostChangeHistory,
            getPersistedVideoPostChangeHistory(expectedVideoPostChangeHistory)
        );
    }

    protected void assertPersistedVideoPostChangeHistoryToMatchUpdatableProperties(VideoPostChangeHistory expectedVideoPostChangeHistory) {
        assertVideoPostChangeHistoryAllUpdatablePropertiesEquals(
            expectedVideoPostChangeHistory,
            getPersistedVideoPostChangeHistory(expectedVideoPostChangeHistory)
        );
    }
}
