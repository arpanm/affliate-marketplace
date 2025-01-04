package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.CompetitionWinnerAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.CompetitionWinner;
import com.marketplace.affliate.video.repository.CompetitionWinnerRepository;
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
 * Integration tests for the {@link CompetitionWinnerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CompetitionWinnerResourceIT {

    private static final String DEFAULT_PRIZE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_PRIZE_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CITATION = "AAAAAAAAAA";
    private static final String UPDATED_CITATION = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATE_URL = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SELECTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SELECTED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_SELECTION_REASON = "AAAAAAAAAA";
    private static final String UPDATED_SELECTION_REASON = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/competition-winners";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CompetitionWinnerRepository competitionWinnerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompetitionWinnerMockMvc;

    private CompetitionWinner competitionWinner;

    private CompetitionWinner insertedCompetitionWinner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompetitionWinner createEntity() {
        return new CompetitionWinner()
            .prizeTitle(DEFAULT_PRIZE_TITLE)
            .citation(DEFAULT_CITATION)
            .certificateUrl(DEFAULT_CERTIFICATE_URL)
            .selectedBy(DEFAULT_SELECTED_BY)
            .selectionReason(DEFAULT_SELECTION_REASON)
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
    public static CompetitionWinner createUpdatedEntity() {
        return new CompetitionWinner()
            .prizeTitle(UPDATED_PRIZE_TITLE)
            .citation(UPDATED_CITATION)
            .certificateUrl(UPDATED_CERTIFICATE_URL)
            .selectedBy(UPDATED_SELECTED_BY)
            .selectionReason(UPDATED_SELECTION_REASON)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        competitionWinner = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCompetitionWinner != null) {
            competitionWinnerRepository.delete(insertedCompetitionWinner);
            insertedCompetitionWinner = null;
        }
    }

    @Test
    @Transactional
    void createCompetitionWinner() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CompetitionWinner
        var returnedCompetitionWinner = om.readValue(
            restCompetitionWinnerMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionWinner)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CompetitionWinner.class
        );

        // Validate the CompetitionWinner in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCompetitionWinnerUpdatableFieldsEquals(returnedCompetitionWinner, getPersistedCompetitionWinner(returnedCompetitionWinner));

        insertedCompetitionWinner = returnedCompetitionWinner;
    }

    @Test
    @Transactional
    void createCompetitionWinnerWithExistingId() throws Exception {
        // Create the CompetitionWinner with an existing ID
        competitionWinner.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetitionWinnerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionWinner)))
            .andExpect(status().isBadRequest());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCompetitionWinners() throws Exception {
        // Initialize the database
        insertedCompetitionWinner = competitionWinnerRepository.saveAndFlush(competitionWinner);

        // Get all the competitionWinnerList
        restCompetitionWinnerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competitionWinner.getId().intValue())))
            .andExpect(jsonPath("$.[*].prizeTitle").value(hasItem(DEFAULT_PRIZE_TITLE)))
            .andExpect(jsonPath("$.[*].citation").value(hasItem(DEFAULT_CITATION)))
            .andExpect(jsonPath("$.[*].certificateUrl").value(hasItem(DEFAULT_CERTIFICATE_URL)))
            .andExpect(jsonPath("$.[*].selectedBy").value(hasItem(DEFAULT_SELECTED_BY)))
            .andExpect(jsonPath("$.[*].selectionReason").value(hasItem(DEFAULT_SELECTION_REASON)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getCompetitionWinner() throws Exception {
        // Initialize the database
        insertedCompetitionWinner = competitionWinnerRepository.saveAndFlush(competitionWinner);

        // Get the competitionWinner
        restCompetitionWinnerMockMvc
            .perform(get(ENTITY_API_URL_ID, competitionWinner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(competitionWinner.getId().intValue()))
            .andExpect(jsonPath("$.prizeTitle").value(DEFAULT_PRIZE_TITLE))
            .andExpect(jsonPath("$.citation").value(DEFAULT_CITATION))
            .andExpect(jsonPath("$.certificateUrl").value(DEFAULT_CERTIFICATE_URL))
            .andExpect(jsonPath("$.selectedBy").value(DEFAULT_SELECTED_BY))
            .andExpect(jsonPath("$.selectionReason").value(DEFAULT_SELECTION_REASON))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCompetitionWinner() throws Exception {
        // Get the competitionWinner
        restCompetitionWinnerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCompetitionWinner() throws Exception {
        // Initialize the database
        insertedCompetitionWinner = competitionWinnerRepository.saveAndFlush(competitionWinner);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionWinner
        CompetitionWinner updatedCompetitionWinner = competitionWinnerRepository.findById(competitionWinner.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCompetitionWinner are not directly saved in db
        em.detach(updatedCompetitionWinner);
        updatedCompetitionWinner
            .prizeTitle(UPDATED_PRIZE_TITLE)
            .citation(UPDATED_CITATION)
            .certificateUrl(UPDATED_CERTIFICATE_URL)
            .selectedBy(UPDATED_SELECTED_BY)
            .selectionReason(UPDATED_SELECTION_REASON)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionWinnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCompetitionWinner.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCompetitionWinner))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCompetitionWinnerToMatchAllProperties(updatedCompetitionWinner);
    }

    @Test
    @Transactional
    void putNonExistingCompetitionWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionWinner.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionWinnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, competitionWinner.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competitionWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCompetitionWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionWinnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competitionWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCompetitionWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionWinnerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionWinner)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCompetitionWinnerWithPatch() throws Exception {
        // Initialize the database
        insertedCompetitionWinner = competitionWinnerRepository.saveAndFlush(competitionWinner);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionWinner using partial update
        CompetitionWinner partialUpdatedCompetitionWinner = new CompetitionWinner();
        partialUpdatedCompetitionWinner.setId(competitionWinner.getId());

        partialUpdatedCompetitionWinner
            .citation(UPDATED_CITATION)
            .selectedBy(UPDATED_SELECTED_BY)
            .selectionReason(UPDATED_SELECTION_REASON)
            .createdBy(UPDATED_CREATED_BY);

        restCompetitionWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetitionWinner.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetitionWinner))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionWinner in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionWinnerUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCompetitionWinner, competitionWinner),
            getPersistedCompetitionWinner(competitionWinner)
        );
    }

    @Test
    @Transactional
    void fullUpdateCompetitionWinnerWithPatch() throws Exception {
        // Initialize the database
        insertedCompetitionWinner = competitionWinnerRepository.saveAndFlush(competitionWinner);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionWinner using partial update
        CompetitionWinner partialUpdatedCompetitionWinner = new CompetitionWinner();
        partialUpdatedCompetitionWinner.setId(competitionWinner.getId());

        partialUpdatedCompetitionWinner
            .prizeTitle(UPDATED_PRIZE_TITLE)
            .citation(UPDATED_CITATION)
            .certificateUrl(UPDATED_CERTIFICATE_URL)
            .selectedBy(UPDATED_SELECTED_BY)
            .selectionReason(UPDATED_SELECTION_REASON)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetitionWinner.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetitionWinner))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionWinner in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionWinnerUpdatableFieldsEquals(
            partialUpdatedCompetitionWinner,
            getPersistedCompetitionWinner(partialUpdatedCompetitionWinner)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCompetitionWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionWinner.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, competitionWinner.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competitionWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCompetitionWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competitionWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCompetitionWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionWinnerMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(competitionWinner)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CompetitionWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCompetitionWinner() throws Exception {
        // Initialize the database
        insertedCompetitionWinner = competitionWinnerRepository.saveAndFlush(competitionWinner);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the competitionWinner
        restCompetitionWinnerMockMvc
            .perform(delete(ENTITY_API_URL_ID, competitionWinner.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return competitionWinnerRepository.count();
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

    protected CompetitionWinner getPersistedCompetitionWinner(CompetitionWinner competitionWinner) {
        return competitionWinnerRepository.findById(competitionWinner.getId()).orElseThrow();
    }

    protected void assertPersistedCompetitionWinnerToMatchAllProperties(CompetitionWinner expectedCompetitionWinner) {
        assertCompetitionWinnerAllPropertiesEquals(expectedCompetitionWinner, getPersistedCompetitionWinner(expectedCompetitionWinner));
    }

    protected void assertPersistedCompetitionWinnerToMatchUpdatableProperties(CompetitionWinner expectedCompetitionWinner) {
        assertCompetitionWinnerAllUpdatablePropertiesEquals(
            expectedCompetitionWinner,
            getPersistedCompetitionWinner(expectedCompetitionWinner)
        );
    }
}
