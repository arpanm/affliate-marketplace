package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.CompetitionAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.Competition;
import com.marketplace.affliate.video.domain.enumeration.CompetitionPaymentStatus;
import com.marketplace.affliate.video.repository.CompetitionRepository;
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
 * Integration tests for the {@link CompetitionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CompetitionResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final CompetitionPaymentStatus DEFAULT_STATUS = CompetitionPaymentStatus.PaymentPendingFromSponsor;
    private static final CompetitionPaymentStatus UPDATED_STATUS = CompetitionPaymentStatus.PaymentReceivedFromSponsor;

    private static final Boolean DEFAULT_IS_BLOCKED = false;
    private static final Boolean UPDATED_IS_BLOCKED = true;

    private static final String DEFAULT_BLOCK_REASON = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCKED_BY = "AAAAAAAAAA";
    private static final String UPDATED_BLOCKED_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PAUSED = false;
    private static final Boolean UPDATED_IS_PAUSED = true;

    private static final String DEFAULT_PAUSE_REASON = "AAAAAAAAAA";
    private static final String UPDATED_PAUSE_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_PAUSED_BY = "AAAAAAAAAA";
    private static final String UPDATED_PAUSED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_BANNER_1_URL = "AAAAAAAAAA";
    private static final String UPDATED_BANNER_1_URL = "BBBBBBBBBB";

    private static final String DEFAULT_BANNER_2_URL = "AAAAAAAAAA";
    private static final String UPDATED_BANNER_2_URL = "BBBBBBBBBB";

    private static final String DEFAULT_BANNER_3_URL = "AAAAAAAAAA";
    private static final String UPDATED_BANNER_3_URL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LANDING_URL = "AAAAAAAAAA";
    private static final String UPDATED_LANDING_URL = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_PRIZE_VALUE = 1D;
    private static final Double UPDATED_TOTAL_PRIZE_VALUE = 2D;

    private static final String DEFAULT_INVOICE_TO_SPONSOR_URL = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_TO_SPONSOR_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TNC_URL = "AAAAAAAAAA";
    private static final String UPDATED_TNC_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TNC = "AAAAAAAAAA";
    private static final String UPDATED_TNC = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/competitions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompetitionMockMvc;

    private Competition competition;

    private Competition insertedCompetition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Competition createEntity() {
        return new Competition()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .isBlocked(DEFAULT_IS_BLOCKED)
            .blockReason(DEFAULT_BLOCK_REASON)
            .blockedBy(DEFAULT_BLOCKED_BY)
            .isPaused(DEFAULT_IS_PAUSED)
            .pauseReason(DEFAULT_PAUSE_REASON)
            .pausedBy(DEFAULT_PAUSED_BY)
            .banner1Url(DEFAULT_BANNER_1_URL)
            .banner2Url(DEFAULT_BANNER_2_URL)
            .banner3Url(DEFAULT_BANNER_3_URL)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .landingUrl(DEFAULT_LANDING_URL)
            .totalPrizeValue(DEFAULT_TOTAL_PRIZE_VALUE)
            .invoiceToSponsorUrl(DEFAULT_INVOICE_TO_SPONSOR_URL)
            .tncUrl(DEFAULT_TNC_URL)
            .tnc(DEFAULT_TNC)
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
    public static Competition createUpdatedEntity() {
        return new Competition()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .isBlocked(UPDATED_IS_BLOCKED)
            .blockReason(UPDATED_BLOCK_REASON)
            .blockedBy(UPDATED_BLOCKED_BY)
            .isPaused(UPDATED_IS_PAUSED)
            .pauseReason(UPDATED_PAUSE_REASON)
            .pausedBy(UPDATED_PAUSED_BY)
            .banner1Url(UPDATED_BANNER_1_URL)
            .banner2Url(UPDATED_BANNER_2_URL)
            .banner3Url(UPDATED_BANNER_3_URL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .landingUrl(UPDATED_LANDING_URL)
            .totalPrizeValue(UPDATED_TOTAL_PRIZE_VALUE)
            .invoiceToSponsorUrl(UPDATED_INVOICE_TO_SPONSOR_URL)
            .tncUrl(UPDATED_TNC_URL)
            .tnc(UPDATED_TNC)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        competition = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCompetition != null) {
            competitionRepository.delete(insertedCompetition);
            insertedCompetition = null;
        }
    }

    @Test
    @Transactional
    void createCompetition() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Competition
        var returnedCompetition = om.readValue(
            restCompetitionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competition)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Competition.class
        );

        // Validate the Competition in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCompetitionUpdatableFieldsEquals(returnedCompetition, getPersistedCompetition(returnedCompetition));

        insertedCompetition = returnedCompetition;
    }

    @Test
    @Transactional
    void createCompetitionWithExistingId() throws Exception {
        // Create the Competition with an existing ID
        competition.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetitionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competition)))
            .andExpect(status().isBadRequest());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCompetitions() throws Exception {
        // Initialize the database
        insertedCompetition = competitionRepository.saveAndFlush(competition);

        // Get all the competitionList
        restCompetitionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competition.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].isBlocked").value(hasItem(DEFAULT_IS_BLOCKED)))
            .andExpect(jsonPath("$.[*].blockReason").value(hasItem(DEFAULT_BLOCK_REASON)))
            .andExpect(jsonPath("$.[*].blockedBy").value(hasItem(DEFAULT_BLOCKED_BY)))
            .andExpect(jsonPath("$.[*].isPaused").value(hasItem(DEFAULT_IS_PAUSED)))
            .andExpect(jsonPath("$.[*].pauseReason").value(hasItem(DEFAULT_PAUSE_REASON)))
            .andExpect(jsonPath("$.[*].pausedBy").value(hasItem(DEFAULT_PAUSED_BY)))
            .andExpect(jsonPath("$.[*].banner1Url").value(hasItem(DEFAULT_BANNER_1_URL)))
            .andExpect(jsonPath("$.[*].banner2Url").value(hasItem(DEFAULT_BANNER_2_URL)))
            .andExpect(jsonPath("$.[*].banner3Url").value(hasItem(DEFAULT_BANNER_3_URL)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].landingUrl").value(hasItem(DEFAULT_LANDING_URL)))
            .andExpect(jsonPath("$.[*].totalPrizeValue").value(hasItem(DEFAULT_TOTAL_PRIZE_VALUE)))
            .andExpect(jsonPath("$.[*].invoiceToSponsorUrl").value(hasItem(DEFAULT_INVOICE_TO_SPONSOR_URL)))
            .andExpect(jsonPath("$.[*].tncUrl").value(hasItem(DEFAULT_TNC_URL)))
            .andExpect(jsonPath("$.[*].tnc").value(hasItem(DEFAULT_TNC)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getCompetition() throws Exception {
        // Initialize the database
        insertedCompetition = competitionRepository.saveAndFlush(competition);

        // Get the competition
        restCompetitionMockMvc
            .perform(get(ENTITY_API_URL_ID, competition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(competition.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.isBlocked").value(DEFAULT_IS_BLOCKED))
            .andExpect(jsonPath("$.blockReason").value(DEFAULT_BLOCK_REASON))
            .andExpect(jsonPath("$.blockedBy").value(DEFAULT_BLOCKED_BY))
            .andExpect(jsonPath("$.isPaused").value(DEFAULT_IS_PAUSED))
            .andExpect(jsonPath("$.pauseReason").value(DEFAULT_PAUSE_REASON))
            .andExpect(jsonPath("$.pausedBy").value(DEFAULT_PAUSED_BY))
            .andExpect(jsonPath("$.banner1Url").value(DEFAULT_BANNER_1_URL))
            .andExpect(jsonPath("$.banner2Url").value(DEFAULT_BANNER_2_URL))
            .andExpect(jsonPath("$.banner3Url").value(DEFAULT_BANNER_3_URL))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.landingUrl").value(DEFAULT_LANDING_URL))
            .andExpect(jsonPath("$.totalPrizeValue").value(DEFAULT_TOTAL_PRIZE_VALUE))
            .andExpect(jsonPath("$.invoiceToSponsorUrl").value(DEFAULT_INVOICE_TO_SPONSOR_URL))
            .andExpect(jsonPath("$.tncUrl").value(DEFAULT_TNC_URL))
            .andExpect(jsonPath("$.tnc").value(DEFAULT_TNC))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCompetition() throws Exception {
        // Get the competition
        restCompetitionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCompetition() throws Exception {
        // Initialize the database
        insertedCompetition = competitionRepository.saveAndFlush(competition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competition
        Competition updatedCompetition = competitionRepository.findById(competition.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCompetition are not directly saved in db
        em.detach(updatedCompetition);
        updatedCompetition
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .isBlocked(UPDATED_IS_BLOCKED)
            .blockReason(UPDATED_BLOCK_REASON)
            .blockedBy(UPDATED_BLOCKED_BY)
            .isPaused(UPDATED_IS_PAUSED)
            .pauseReason(UPDATED_PAUSE_REASON)
            .pausedBy(UPDATED_PAUSED_BY)
            .banner1Url(UPDATED_BANNER_1_URL)
            .banner2Url(UPDATED_BANNER_2_URL)
            .banner3Url(UPDATED_BANNER_3_URL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .landingUrl(UPDATED_LANDING_URL)
            .totalPrizeValue(UPDATED_TOTAL_PRIZE_VALUE)
            .invoiceToSponsorUrl(UPDATED_INVOICE_TO_SPONSOR_URL)
            .tncUrl(UPDATED_TNC_URL)
            .tnc(UPDATED_TNC)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCompetition.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCompetition))
            )
            .andExpect(status().isOk());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCompetitionToMatchAllProperties(updatedCompetition);
    }

    @Test
    @Transactional
    void putNonExistingCompetition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competition.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, competition.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competition))
            )
            .andExpect(status().isBadRequest());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCompetition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competition.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competition))
            )
            .andExpect(status().isBadRequest());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCompetition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competition.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competition)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCompetitionWithPatch() throws Exception {
        // Initialize the database
        insertedCompetition = competitionRepository.saveAndFlush(competition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competition using partial update
        Competition partialUpdatedCompetition = new Competition();
        partialUpdatedCompetition.setId(competition.getId());

        partialUpdatedCompetition
            .title(UPDATED_TITLE)
            .blockReason(UPDATED_BLOCK_REASON)
            .isPaused(UPDATED_IS_PAUSED)
            .banner1Url(UPDATED_BANNER_1_URL)
            .landingUrl(UPDATED_LANDING_URL)
            .totalPrizeValue(UPDATED_TOTAL_PRIZE_VALUE)
            .invoiceToSponsorUrl(UPDATED_INVOICE_TO_SPONSOR_URL)
            .tncUrl(UPDATED_TNC_URL)
            .updatedBy(UPDATED_UPDATED_BY);

        restCompetitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetition))
            )
            .andExpect(status().isOk());

        // Validate the Competition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCompetition, competition),
            getPersistedCompetition(competition)
        );
    }

    @Test
    @Transactional
    void fullUpdateCompetitionWithPatch() throws Exception {
        // Initialize the database
        insertedCompetition = competitionRepository.saveAndFlush(competition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competition using partial update
        Competition partialUpdatedCompetition = new Competition();
        partialUpdatedCompetition.setId(competition.getId());

        partialUpdatedCompetition
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .isBlocked(UPDATED_IS_BLOCKED)
            .blockReason(UPDATED_BLOCK_REASON)
            .blockedBy(UPDATED_BLOCKED_BY)
            .isPaused(UPDATED_IS_PAUSED)
            .pauseReason(UPDATED_PAUSE_REASON)
            .pausedBy(UPDATED_PAUSED_BY)
            .banner1Url(UPDATED_BANNER_1_URL)
            .banner2Url(UPDATED_BANNER_2_URL)
            .banner3Url(UPDATED_BANNER_3_URL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .landingUrl(UPDATED_LANDING_URL)
            .totalPrizeValue(UPDATED_TOTAL_PRIZE_VALUE)
            .invoiceToSponsorUrl(UPDATED_INVOICE_TO_SPONSOR_URL)
            .tncUrl(UPDATED_TNC_URL)
            .tnc(UPDATED_TNC)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetition))
            )
            .andExpect(status().isOk());

        // Validate the Competition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionUpdatableFieldsEquals(partialUpdatedCompetition, getPersistedCompetition(partialUpdatedCompetition));
    }

    @Test
    @Transactional
    void patchNonExistingCompetition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competition.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, competition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competition))
            )
            .andExpect(status().isBadRequest());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCompetition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competition.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competition))
            )
            .andExpect(status().isBadRequest());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCompetition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competition.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(competition)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Competition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCompetition() throws Exception {
        // Initialize the database
        insertedCompetition = competitionRepository.saveAndFlush(competition);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the competition
        restCompetitionMockMvc
            .perform(delete(ENTITY_API_URL_ID, competition.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return competitionRepository.count();
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

    protected Competition getPersistedCompetition(Competition competition) {
        return competitionRepository.findById(competition.getId()).orElseThrow();
    }

    protected void assertPersistedCompetitionToMatchAllProperties(Competition expectedCompetition) {
        assertCompetitionAllPropertiesEquals(expectedCompetition, getPersistedCompetition(expectedCompetition));
    }

    protected void assertPersistedCompetitionToMatchUpdatableProperties(Competition expectedCompetition) {
        assertCompetitionAllUpdatablePropertiesEquals(expectedCompetition, getPersistedCompetition(expectedCompetition));
    }
}
