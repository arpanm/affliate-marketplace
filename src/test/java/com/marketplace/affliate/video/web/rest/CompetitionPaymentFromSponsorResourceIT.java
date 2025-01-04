package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsorAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsor;
import com.marketplace.affliate.video.domain.enumeration.TransactionStatus;
import com.marketplace.affliate.video.repository.CompetitionPaymentFromSponsorRepository;
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
 * Integration tests for the {@link CompetitionPaymentFromSponsorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CompetitionPaymentFromSponsorResourceIT {

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_SCREENSHOT_URL = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_SCREENSHOT_URL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TRANSACTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRANSACTION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final TransactionStatus DEFAULT_TRANSACTION_STATUS = TransactionStatus.Initiated;
    private static final TransactionStatus UPDATED_TRANSACTION_STATUS = TransactionStatus.Received;

    private static final String DEFAULT_PAID_BY = "AAAAAAAAAA";
    private static final String UPDATED_PAID_BY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_RECEIPT_URL = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_RECEIPT_URL = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/competition-payment-from-sponsors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CompetitionPaymentFromSponsorRepository competitionPaymentFromSponsorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompetitionPaymentFromSponsorMockMvc;

    private CompetitionPaymentFromSponsor competitionPaymentFromSponsor;

    private CompetitionPaymentFromSponsor insertedCompetitionPaymentFromSponsor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompetitionPaymentFromSponsor createEntity() {
        return new CompetitionPaymentFromSponsor()
            .amount(DEFAULT_AMOUNT)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .transactionScreenshotUrl(DEFAULT_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .transactionStatus(DEFAULT_TRANSACTION_STATUS)
            .paidBy(DEFAULT_PAID_BY)
            .paymentReceiptUrl(DEFAULT_PAYMENT_RECEIPT_URL)
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
    public static CompetitionPaymentFromSponsor createUpdatedEntity() {
        return new CompetitionPaymentFromSponsor()
            .amount(UPDATED_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionScreenshotUrl(UPDATED_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .paidBy(UPDATED_PAID_BY)
            .paymentReceiptUrl(UPDATED_PAYMENT_RECEIPT_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        competitionPaymentFromSponsor = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCompetitionPaymentFromSponsor != null) {
            competitionPaymentFromSponsorRepository.delete(insertedCompetitionPaymentFromSponsor);
            insertedCompetitionPaymentFromSponsor = null;
        }
    }

    @Test
    @Transactional
    void createCompetitionPaymentFromSponsor() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CompetitionPaymentFromSponsor
        var returnedCompetitionPaymentFromSponsor = om.readValue(
            restCompetitionPaymentFromSponsorMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(competitionPaymentFromSponsor))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CompetitionPaymentFromSponsor.class
        );

        // Validate the CompetitionPaymentFromSponsor in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCompetitionPaymentFromSponsorUpdatableFieldsEquals(
            returnedCompetitionPaymentFromSponsor,
            getPersistedCompetitionPaymentFromSponsor(returnedCompetitionPaymentFromSponsor)
        );

        insertedCompetitionPaymentFromSponsor = returnedCompetitionPaymentFromSponsor;
    }

    @Test
    @Transactional
    void createCompetitionPaymentFromSponsorWithExistingId() throws Exception {
        // Create the CompetitionPaymentFromSponsor with an existing ID
        competitionPaymentFromSponsor.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionPaymentFromSponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCompetitionPaymentFromSponsors() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.saveAndFlush(competitionPaymentFromSponsor);

        // Get all the competitionPaymentFromSponsorList
        restCompetitionPaymentFromSponsorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competitionPaymentFromSponsor.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID)))
            .andExpect(jsonPath("$.[*].transactionScreenshotUrl").value(hasItem(DEFAULT_TRANSACTION_SCREENSHOT_URL)))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].transactionStatus").value(hasItem(DEFAULT_TRANSACTION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].paidBy").value(hasItem(DEFAULT_PAID_BY)))
            .andExpect(jsonPath("$.[*].paymentReceiptUrl").value(hasItem(DEFAULT_PAYMENT_RECEIPT_URL)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getCompetitionPaymentFromSponsor() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.saveAndFlush(competitionPaymentFromSponsor);

        // Get the competitionPaymentFromSponsor
        restCompetitionPaymentFromSponsorMockMvc
            .perform(get(ENTITY_API_URL_ID, competitionPaymentFromSponsor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(competitionPaymentFromSponsor.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.transactionId").value(DEFAULT_TRANSACTION_ID))
            .andExpect(jsonPath("$.transactionScreenshotUrl").value(DEFAULT_TRANSACTION_SCREENSHOT_URL))
            .andExpect(jsonPath("$.transactionDate").value(DEFAULT_TRANSACTION_DATE.toString()))
            .andExpect(jsonPath("$.transactionStatus").value(DEFAULT_TRANSACTION_STATUS.toString()))
            .andExpect(jsonPath("$.paidBy").value(DEFAULT_PAID_BY))
            .andExpect(jsonPath("$.paymentReceiptUrl").value(DEFAULT_PAYMENT_RECEIPT_URL))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCompetitionPaymentFromSponsor() throws Exception {
        // Get the competitionPaymentFromSponsor
        restCompetitionPaymentFromSponsorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCompetitionPaymentFromSponsor() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.saveAndFlush(competitionPaymentFromSponsor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionPaymentFromSponsor
        CompetitionPaymentFromSponsor updatedCompetitionPaymentFromSponsor = competitionPaymentFromSponsorRepository
            .findById(competitionPaymentFromSponsor.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCompetitionPaymentFromSponsor are not directly saved in db
        em.detach(updatedCompetitionPaymentFromSponsor);
        updatedCompetitionPaymentFromSponsor
            .amount(UPDATED_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionScreenshotUrl(UPDATED_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .paidBy(UPDATED_PAID_BY)
            .paymentReceiptUrl(UPDATED_PAYMENT_RECEIPT_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCompetitionPaymentFromSponsor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCompetitionPaymentFromSponsor))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCompetitionPaymentFromSponsorToMatchAllProperties(updatedCompetitionPaymentFromSponsor);
    }

    @Test
    @Transactional
    void putNonExistingCompetitionPaymentFromSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentFromSponsor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, competitionPaymentFromSponsor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competitionPaymentFromSponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCompetitionPaymentFromSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentFromSponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competitionPaymentFromSponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCompetitionPaymentFromSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentFromSponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionPaymentFromSponsor))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCompetitionPaymentFromSponsorWithPatch() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.saveAndFlush(competitionPaymentFromSponsor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionPaymentFromSponsor using partial update
        CompetitionPaymentFromSponsor partialUpdatedCompetitionPaymentFromSponsor = new CompetitionPaymentFromSponsor();
        partialUpdatedCompetitionPaymentFromSponsor.setId(competitionPaymentFromSponsor.getId());

        partialUpdatedCompetitionPaymentFromSponsor
            .amount(UPDATED_AMOUNT)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .paidBy(UPDATED_PAID_BY)
            .paymentReceiptUrl(UPDATED_PAYMENT_RECEIPT_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY);

        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetitionPaymentFromSponsor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetitionPaymentFromSponsor))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionPaymentFromSponsor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionPaymentFromSponsorUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCompetitionPaymentFromSponsor, competitionPaymentFromSponsor),
            getPersistedCompetitionPaymentFromSponsor(competitionPaymentFromSponsor)
        );
    }

    @Test
    @Transactional
    void fullUpdateCompetitionPaymentFromSponsorWithPatch() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.saveAndFlush(competitionPaymentFromSponsor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionPaymentFromSponsor using partial update
        CompetitionPaymentFromSponsor partialUpdatedCompetitionPaymentFromSponsor = new CompetitionPaymentFromSponsor();
        partialUpdatedCompetitionPaymentFromSponsor.setId(competitionPaymentFromSponsor.getId());

        partialUpdatedCompetitionPaymentFromSponsor
            .amount(UPDATED_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionScreenshotUrl(UPDATED_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .paidBy(UPDATED_PAID_BY)
            .paymentReceiptUrl(UPDATED_PAYMENT_RECEIPT_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetitionPaymentFromSponsor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetitionPaymentFromSponsor))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionPaymentFromSponsor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionPaymentFromSponsorUpdatableFieldsEquals(
            partialUpdatedCompetitionPaymentFromSponsor,
            getPersistedCompetitionPaymentFromSponsor(partialUpdatedCompetitionPaymentFromSponsor)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCompetitionPaymentFromSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentFromSponsor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, competitionPaymentFromSponsor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competitionPaymentFromSponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCompetitionPaymentFromSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentFromSponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competitionPaymentFromSponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCompetitionPaymentFromSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentFromSponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentFromSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competitionPaymentFromSponsor))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CompetitionPaymentFromSponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCompetitionPaymentFromSponsor() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.saveAndFlush(competitionPaymentFromSponsor);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the competitionPaymentFromSponsor
        restCompetitionPaymentFromSponsorMockMvc
            .perform(delete(ENTITY_API_URL_ID, competitionPaymentFromSponsor.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return competitionPaymentFromSponsorRepository.count();
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

    protected CompetitionPaymentFromSponsor getPersistedCompetitionPaymentFromSponsor(
        CompetitionPaymentFromSponsor competitionPaymentFromSponsor
    ) {
        return competitionPaymentFromSponsorRepository.findById(competitionPaymentFromSponsor.getId()).orElseThrow();
    }

    protected void assertPersistedCompetitionPaymentFromSponsorToMatchAllProperties(
        CompetitionPaymentFromSponsor expectedCompetitionPaymentFromSponsor
    ) {
        assertCompetitionPaymentFromSponsorAllPropertiesEquals(
            expectedCompetitionPaymentFromSponsor,
            getPersistedCompetitionPaymentFromSponsor(expectedCompetitionPaymentFromSponsor)
        );
    }

    protected void assertPersistedCompetitionPaymentFromSponsorToMatchUpdatableProperties(
        CompetitionPaymentFromSponsor expectedCompetitionPaymentFromSponsor
    ) {
        assertCompetitionPaymentFromSponsorAllUpdatablePropertiesEquals(
            expectedCompetitionPaymentFromSponsor,
            getPersistedCompetitionPaymentFromSponsor(expectedCompetitionPaymentFromSponsor)
        );
    }
}
