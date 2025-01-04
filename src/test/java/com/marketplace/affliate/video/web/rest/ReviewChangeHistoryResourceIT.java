package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.ReviewChangeHistoryAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.ReviewChangeHistory;
import com.marketplace.affliate.video.domain.enumeration.ChangeType;
import com.marketplace.affliate.video.repository.ReviewChangeHistoryRepository;
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
 * Integration tests for the {@link ReviewChangeHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReviewChangeHistoryResourceIT {

    private static final ChangeType DEFAULT_CHANGE_TYPE = ChangeType.ModeratorUpdateContent;
    private static final ChangeType UPDATED_CHANGE_TYPE = ChangeType.ModeratorUpdateActiveStatus;

    private static final Boolean DEFAULT_OLD_IS_LIKED = false;
    private static final Boolean UPDATED_OLD_IS_LIKED = true;

    private static final Boolean DEFAULT_OLD_IS_SKIPPED = false;
    private static final Boolean UPDATED_OLD_IS_SKIPPED = true;

    private static final Boolean DEFAULT_OLD_IS_DISLIKED = false;
    private static final Boolean UPDATED_OLD_IS_DISLIKED = true;

    private static final Boolean DEFAULT_OLD_IS_WATCHED = false;
    private static final Boolean UPDATED_OLD_IS_WATCHED = true;

    private static final Boolean DEFAULT_OLD_IS_FULLY_WATCHED = false;
    private static final Boolean UPDATED_OLD_IS_FULLY_WATCHED = true;

    private static final Boolean DEFAULT_OLD_IS_REPORTED = false;
    private static final Boolean UPDATED_OLD_IS_REPORTED = true;

    private static final Long DEFAULT_OLD_RATING = 1L;
    private static final Long UPDATED_OLD_RATING = 2L;

    private static final String DEFAULT_OLD_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_OLD_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_OLD_REPORT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_OLD_REPORT_REASON = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/review-change-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ReviewChangeHistoryRepository reviewChangeHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReviewChangeHistoryMockMvc;

    private ReviewChangeHistory reviewChangeHistory;

    private ReviewChangeHistory insertedReviewChangeHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReviewChangeHistory createEntity() {
        return new ReviewChangeHistory()
            .changeType(DEFAULT_CHANGE_TYPE)
            .oldIsLiked(DEFAULT_OLD_IS_LIKED)
            .oldIsSkipped(DEFAULT_OLD_IS_SKIPPED)
            .oldIsDisliked(DEFAULT_OLD_IS_DISLIKED)
            .oldIsWatched(DEFAULT_OLD_IS_WATCHED)
            .oldIsFullyWatched(DEFAULT_OLD_IS_FULLY_WATCHED)
            .oldIsReported(DEFAULT_OLD_IS_REPORTED)
            .oldRating(DEFAULT_OLD_RATING)
            .oldComment(DEFAULT_OLD_COMMENT)
            .oldReportReason(DEFAULT_OLD_REPORT_REASON)
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
    public static ReviewChangeHistory createUpdatedEntity() {
        return new ReviewChangeHistory()
            .changeType(UPDATED_CHANGE_TYPE)
            .oldIsLiked(UPDATED_OLD_IS_LIKED)
            .oldIsSkipped(UPDATED_OLD_IS_SKIPPED)
            .oldIsDisliked(UPDATED_OLD_IS_DISLIKED)
            .oldIsWatched(UPDATED_OLD_IS_WATCHED)
            .oldIsFullyWatched(UPDATED_OLD_IS_FULLY_WATCHED)
            .oldIsReported(UPDATED_OLD_IS_REPORTED)
            .oldRating(UPDATED_OLD_RATING)
            .oldComment(UPDATED_OLD_COMMENT)
            .oldReportReason(UPDATED_OLD_REPORT_REASON)
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
        reviewChangeHistory = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedReviewChangeHistory != null) {
            reviewChangeHistoryRepository.delete(insertedReviewChangeHistory);
            insertedReviewChangeHistory = null;
        }
    }

    @Test
    @Transactional
    void createReviewChangeHistory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ReviewChangeHistory
        var returnedReviewChangeHistory = om.readValue(
            restReviewChangeHistoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reviewChangeHistory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ReviewChangeHistory.class
        );

        // Validate the ReviewChangeHistory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertReviewChangeHistoryUpdatableFieldsEquals(
            returnedReviewChangeHistory,
            getPersistedReviewChangeHistory(returnedReviewChangeHistory)
        );

        insertedReviewChangeHistory = returnedReviewChangeHistory;
    }

    @Test
    @Transactional
    void createReviewChangeHistoryWithExistingId() throws Exception {
        // Create the ReviewChangeHistory with an existing ID
        reviewChangeHistory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReviewChangeHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reviewChangeHistory)))
            .andExpect(status().isBadRequest());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReviewChangeHistories() throws Exception {
        // Initialize the database
        insertedReviewChangeHistory = reviewChangeHistoryRepository.saveAndFlush(reviewChangeHistory);

        // Get all the reviewChangeHistoryList
        restReviewChangeHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reviewChangeHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].changeType").value(hasItem(DEFAULT_CHANGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].oldIsLiked").value(hasItem(DEFAULT_OLD_IS_LIKED)))
            .andExpect(jsonPath("$.[*].oldIsSkipped").value(hasItem(DEFAULT_OLD_IS_SKIPPED)))
            .andExpect(jsonPath("$.[*].oldIsDisliked").value(hasItem(DEFAULT_OLD_IS_DISLIKED)))
            .andExpect(jsonPath("$.[*].oldIsWatched").value(hasItem(DEFAULT_OLD_IS_WATCHED)))
            .andExpect(jsonPath("$.[*].oldIsFullyWatched").value(hasItem(DEFAULT_OLD_IS_FULLY_WATCHED)))
            .andExpect(jsonPath("$.[*].oldIsReported").value(hasItem(DEFAULT_OLD_IS_REPORTED)))
            .andExpect(jsonPath("$.[*].oldRating").value(hasItem(DEFAULT_OLD_RATING.intValue())))
            .andExpect(jsonPath("$.[*].oldComment").value(hasItem(DEFAULT_OLD_COMMENT)))
            .andExpect(jsonPath("$.[*].oldReportReason").value(hasItem(DEFAULT_OLD_REPORT_REASON)))
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
    void getReviewChangeHistory() throws Exception {
        // Initialize the database
        insertedReviewChangeHistory = reviewChangeHistoryRepository.saveAndFlush(reviewChangeHistory);

        // Get the reviewChangeHistory
        restReviewChangeHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, reviewChangeHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reviewChangeHistory.getId().intValue()))
            .andExpect(jsonPath("$.changeType").value(DEFAULT_CHANGE_TYPE.toString()))
            .andExpect(jsonPath("$.oldIsLiked").value(DEFAULT_OLD_IS_LIKED))
            .andExpect(jsonPath("$.oldIsSkipped").value(DEFAULT_OLD_IS_SKIPPED))
            .andExpect(jsonPath("$.oldIsDisliked").value(DEFAULT_OLD_IS_DISLIKED))
            .andExpect(jsonPath("$.oldIsWatched").value(DEFAULT_OLD_IS_WATCHED))
            .andExpect(jsonPath("$.oldIsFullyWatched").value(DEFAULT_OLD_IS_FULLY_WATCHED))
            .andExpect(jsonPath("$.oldIsReported").value(DEFAULT_OLD_IS_REPORTED))
            .andExpect(jsonPath("$.oldRating").value(DEFAULT_OLD_RATING.intValue()))
            .andExpect(jsonPath("$.oldComment").value(DEFAULT_OLD_COMMENT))
            .andExpect(jsonPath("$.oldReportReason").value(DEFAULT_OLD_REPORT_REASON))
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
    void getNonExistingReviewChangeHistory() throws Exception {
        // Get the reviewChangeHistory
        restReviewChangeHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReviewChangeHistory() throws Exception {
        // Initialize the database
        insertedReviewChangeHistory = reviewChangeHistoryRepository.saveAndFlush(reviewChangeHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reviewChangeHistory
        ReviewChangeHistory updatedReviewChangeHistory = reviewChangeHistoryRepository.findById(reviewChangeHistory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedReviewChangeHistory are not directly saved in db
        em.detach(updatedReviewChangeHistory);
        updatedReviewChangeHistory
            .changeType(UPDATED_CHANGE_TYPE)
            .oldIsLiked(UPDATED_OLD_IS_LIKED)
            .oldIsSkipped(UPDATED_OLD_IS_SKIPPED)
            .oldIsDisliked(UPDATED_OLD_IS_DISLIKED)
            .oldIsWatched(UPDATED_OLD_IS_WATCHED)
            .oldIsFullyWatched(UPDATED_OLD_IS_FULLY_WATCHED)
            .oldIsReported(UPDATED_OLD_IS_REPORTED)
            .oldRating(UPDATED_OLD_RATING)
            .oldComment(UPDATED_OLD_COMMENT)
            .oldReportReason(UPDATED_OLD_REPORT_REASON)
            .oldIsBlocked(UPDATED_OLD_IS_BLOCKED)
            .oldIsModerated(UPDATED_OLD_IS_MODERATED)
            .oldIsActive(UPDATED_OLD_IS_ACTIVE)
            .oldCreatedBy(UPDATED_OLD_CREATED_BY)
            .oldCreatedOn(UPDATED_OLD_CREATED_ON)
            .oldUpdatedBy(UPDATED_OLD_UPDATED_BY)
            .oldUpdatedOn(UPDATED_OLD_UPDATED_ON);

        restReviewChangeHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReviewChangeHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedReviewChangeHistory))
            )
            .andExpect(status().isOk());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedReviewChangeHistoryToMatchAllProperties(updatedReviewChangeHistory);
    }

    @Test
    @Transactional
    void putNonExistingReviewChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewChangeHistory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReviewChangeHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reviewChangeHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reviewChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReviewChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewChangeHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reviewChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReviewChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewChangeHistoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reviewChangeHistory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReviewChangeHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedReviewChangeHistory = reviewChangeHistoryRepository.saveAndFlush(reviewChangeHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reviewChangeHistory using partial update
        ReviewChangeHistory partialUpdatedReviewChangeHistory = new ReviewChangeHistory();
        partialUpdatedReviewChangeHistory.setId(reviewChangeHistory.getId());

        partialUpdatedReviewChangeHistory
            .changeType(UPDATED_CHANGE_TYPE)
            .oldIsLiked(UPDATED_OLD_IS_LIKED)
            .oldIsDisliked(UPDATED_OLD_IS_DISLIKED)
            .oldIsWatched(UPDATED_OLD_IS_WATCHED)
            .oldRating(UPDATED_OLD_RATING)
            .oldIsModerated(UPDATED_OLD_IS_MODERATED)
            .oldCreatedBy(UPDATED_OLD_CREATED_BY)
            .oldCreatedOn(UPDATED_OLD_CREATED_ON);

        restReviewChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReviewChangeHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReviewChangeHistory))
            )
            .andExpect(status().isOk());

        // Validate the ReviewChangeHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReviewChangeHistoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedReviewChangeHistory, reviewChangeHistory),
            getPersistedReviewChangeHistory(reviewChangeHistory)
        );
    }

    @Test
    @Transactional
    void fullUpdateReviewChangeHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedReviewChangeHistory = reviewChangeHistoryRepository.saveAndFlush(reviewChangeHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reviewChangeHistory using partial update
        ReviewChangeHistory partialUpdatedReviewChangeHistory = new ReviewChangeHistory();
        partialUpdatedReviewChangeHistory.setId(reviewChangeHistory.getId());

        partialUpdatedReviewChangeHistory
            .changeType(UPDATED_CHANGE_TYPE)
            .oldIsLiked(UPDATED_OLD_IS_LIKED)
            .oldIsSkipped(UPDATED_OLD_IS_SKIPPED)
            .oldIsDisliked(UPDATED_OLD_IS_DISLIKED)
            .oldIsWatched(UPDATED_OLD_IS_WATCHED)
            .oldIsFullyWatched(UPDATED_OLD_IS_FULLY_WATCHED)
            .oldIsReported(UPDATED_OLD_IS_REPORTED)
            .oldRating(UPDATED_OLD_RATING)
            .oldComment(UPDATED_OLD_COMMENT)
            .oldReportReason(UPDATED_OLD_REPORT_REASON)
            .oldIsBlocked(UPDATED_OLD_IS_BLOCKED)
            .oldIsModerated(UPDATED_OLD_IS_MODERATED)
            .oldIsActive(UPDATED_OLD_IS_ACTIVE)
            .oldCreatedBy(UPDATED_OLD_CREATED_BY)
            .oldCreatedOn(UPDATED_OLD_CREATED_ON)
            .oldUpdatedBy(UPDATED_OLD_UPDATED_BY)
            .oldUpdatedOn(UPDATED_OLD_UPDATED_ON);

        restReviewChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReviewChangeHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReviewChangeHistory))
            )
            .andExpect(status().isOk());

        // Validate the ReviewChangeHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReviewChangeHistoryUpdatableFieldsEquals(
            partialUpdatedReviewChangeHistory,
            getPersistedReviewChangeHistory(partialUpdatedReviewChangeHistory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingReviewChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewChangeHistory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReviewChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reviewChangeHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reviewChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReviewChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewChangeHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reviewChangeHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReviewChangeHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewChangeHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewChangeHistoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(reviewChangeHistory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReviewChangeHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReviewChangeHistory() throws Exception {
        // Initialize the database
        insertedReviewChangeHistory = reviewChangeHistoryRepository.saveAndFlush(reviewChangeHistory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the reviewChangeHistory
        restReviewChangeHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, reviewChangeHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return reviewChangeHistoryRepository.count();
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

    protected ReviewChangeHistory getPersistedReviewChangeHistory(ReviewChangeHistory reviewChangeHistory) {
        return reviewChangeHistoryRepository.findById(reviewChangeHistory.getId()).orElseThrow();
    }

    protected void assertPersistedReviewChangeHistoryToMatchAllProperties(ReviewChangeHistory expectedReviewChangeHistory) {
        assertReviewChangeHistoryAllPropertiesEquals(
            expectedReviewChangeHistory,
            getPersistedReviewChangeHistory(expectedReviewChangeHistory)
        );
    }

    protected void assertPersistedReviewChangeHistoryToMatchUpdatableProperties(ReviewChangeHistory expectedReviewChangeHistory) {
        assertReviewChangeHistoryAllUpdatablePropertiesEquals(
            expectedReviewChangeHistory,
            getPersistedReviewChangeHistory(expectedReviewChangeHistory)
        );
    }
}
