package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.CompetitionPaymentToWinnerAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.CompetitionPaymentToWinner;
import com.marketplace.affliate.video.domain.enumeration.TransactionStatus;
import com.marketplace.affliate.video.repository.CompetitionPaymentToWinnerRepository;
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
 * Integration tests for the {@link CompetitionPaymentToWinnerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CompetitionPaymentToWinnerResourceIT {

    private static final String DEFAULT_PRIZE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_PRIZE_TITLE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIZE_AMOUNT = 1D;
    private static final Double UPDATED_PRIZE_AMOUNT = 2D;

    private static final Double DEFAULT_TDS_AMOUNT = 1D;
    private static final Double UPDATED_TDS_AMOUNT = 2D;

    private static final Double DEFAULT_TDS_CERTIFICATE_URL = 1D;
    private static final Double UPDATED_TDS_CERTIFICATE_URL = 2D;

    private static final Double DEFAULT_OTHER_DEDUCTION_AMOUNT = 1D;
    private static final Double UPDATED_OTHER_DEDUCTION_AMOUNT = 2D;

    private static final String DEFAULT_DEDUCTION_REASON = "AAAAAAAAAA";
    private static final String UPDATED_DEDUCTION_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_DEDUCTION_JSON_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DEDUCTION_JSON_DATA = "BBBBBBBBBB";

    private static final Double DEFAULT_DEDUCTION_CERTIFICATE_URL = 1D;
    private static final Double UPDATED_DEDUCTION_CERTIFICATE_URL = 2D;

    private static final Double DEFAULT_PAID_AMOUNT = 1D;
    private static final Double UPDATED_PAID_AMOUNT = 2D;

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

    private static final String ENTITY_API_URL = "/api/competition-payment-to-winners";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CompetitionPaymentToWinnerRepository competitionPaymentToWinnerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompetitionPaymentToWinnerMockMvc;

    private CompetitionPaymentToWinner competitionPaymentToWinner;

    private CompetitionPaymentToWinner insertedCompetitionPaymentToWinner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompetitionPaymentToWinner createEntity() {
        return new CompetitionPaymentToWinner()
            .prizeTitle(DEFAULT_PRIZE_TITLE)
            .prizeAmount(DEFAULT_PRIZE_AMOUNT)
            .tdsAmount(DEFAULT_TDS_AMOUNT)
            .tdsCertificateUrl(DEFAULT_TDS_CERTIFICATE_URL)
            .otherDeductionAmount(DEFAULT_OTHER_DEDUCTION_AMOUNT)
            .deductionReason(DEFAULT_DEDUCTION_REASON)
            .deductionJsonData(DEFAULT_DEDUCTION_JSON_DATA)
            .deductionCertificateUrl(DEFAULT_DEDUCTION_CERTIFICATE_URL)
            .paidAmount(DEFAULT_PAID_AMOUNT)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .transactionScreenshotUrl(DEFAULT_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .transactionStatus(DEFAULT_TRANSACTION_STATUS)
            .paidBy(DEFAULT_PAID_BY)
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
    public static CompetitionPaymentToWinner createUpdatedEntity() {
        return new CompetitionPaymentToWinner()
            .prizeTitle(UPDATED_PRIZE_TITLE)
            .prizeAmount(UPDATED_PRIZE_AMOUNT)
            .tdsAmount(UPDATED_TDS_AMOUNT)
            .tdsCertificateUrl(UPDATED_TDS_CERTIFICATE_URL)
            .otherDeductionAmount(UPDATED_OTHER_DEDUCTION_AMOUNT)
            .deductionReason(UPDATED_DEDUCTION_REASON)
            .deductionJsonData(UPDATED_DEDUCTION_JSON_DATA)
            .deductionCertificateUrl(UPDATED_DEDUCTION_CERTIFICATE_URL)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionScreenshotUrl(UPDATED_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .paidBy(UPDATED_PAID_BY)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        competitionPaymentToWinner = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCompetitionPaymentToWinner != null) {
            competitionPaymentToWinnerRepository.delete(insertedCompetitionPaymentToWinner);
            insertedCompetitionPaymentToWinner = null;
        }
    }

    @Test
    @Transactional
    void createCompetitionPaymentToWinner() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CompetitionPaymentToWinner
        var returnedCompetitionPaymentToWinner = om.readValue(
            restCompetitionPaymentToWinnerMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionPaymentToWinner))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CompetitionPaymentToWinner.class
        );

        // Validate the CompetitionPaymentToWinner in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCompetitionPaymentToWinnerUpdatableFieldsEquals(
            returnedCompetitionPaymentToWinner,
            getPersistedCompetitionPaymentToWinner(returnedCompetitionPaymentToWinner)
        );

        insertedCompetitionPaymentToWinner = returnedCompetitionPaymentToWinner;
    }

    @Test
    @Transactional
    void createCompetitionPaymentToWinnerWithExistingId() throws Exception {
        // Create the CompetitionPaymentToWinner with an existing ID
        competitionPaymentToWinner.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetitionPaymentToWinnerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionPaymentToWinner)))
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCompetitionPaymentToWinners() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentToWinner = competitionPaymentToWinnerRepository.saveAndFlush(competitionPaymentToWinner);

        // Get all the competitionPaymentToWinnerList
        restCompetitionPaymentToWinnerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competitionPaymentToWinner.getId().intValue())))
            .andExpect(jsonPath("$.[*].prizeTitle").value(hasItem(DEFAULT_PRIZE_TITLE)))
            .andExpect(jsonPath("$.[*].prizeAmount").value(hasItem(DEFAULT_PRIZE_AMOUNT)))
            .andExpect(jsonPath("$.[*].tdsAmount").value(hasItem(DEFAULT_TDS_AMOUNT)))
            .andExpect(jsonPath("$.[*].tdsCertificateUrl").value(hasItem(DEFAULT_TDS_CERTIFICATE_URL)))
            .andExpect(jsonPath("$.[*].otherDeductionAmount").value(hasItem(DEFAULT_OTHER_DEDUCTION_AMOUNT)))
            .andExpect(jsonPath("$.[*].deductionReason").value(hasItem(DEFAULT_DEDUCTION_REASON)))
            .andExpect(jsonPath("$.[*].deductionJsonData").value(hasItem(DEFAULT_DEDUCTION_JSON_DATA)))
            .andExpect(jsonPath("$.[*].deductionCertificateUrl").value(hasItem(DEFAULT_DEDUCTION_CERTIFICATE_URL)))
            .andExpect(jsonPath("$.[*].paidAmount").value(hasItem(DEFAULT_PAID_AMOUNT)))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID)))
            .andExpect(jsonPath("$.[*].transactionScreenshotUrl").value(hasItem(DEFAULT_TRANSACTION_SCREENSHOT_URL)))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].transactionStatus").value(hasItem(DEFAULT_TRANSACTION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].paidBy").value(hasItem(DEFAULT_PAID_BY)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getCompetitionPaymentToWinner() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentToWinner = competitionPaymentToWinnerRepository.saveAndFlush(competitionPaymentToWinner);

        // Get the competitionPaymentToWinner
        restCompetitionPaymentToWinnerMockMvc
            .perform(get(ENTITY_API_URL_ID, competitionPaymentToWinner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(competitionPaymentToWinner.getId().intValue()))
            .andExpect(jsonPath("$.prizeTitle").value(DEFAULT_PRIZE_TITLE))
            .andExpect(jsonPath("$.prizeAmount").value(DEFAULT_PRIZE_AMOUNT))
            .andExpect(jsonPath("$.tdsAmount").value(DEFAULT_TDS_AMOUNT))
            .andExpect(jsonPath("$.tdsCertificateUrl").value(DEFAULT_TDS_CERTIFICATE_URL))
            .andExpect(jsonPath("$.otherDeductionAmount").value(DEFAULT_OTHER_DEDUCTION_AMOUNT))
            .andExpect(jsonPath("$.deductionReason").value(DEFAULT_DEDUCTION_REASON))
            .andExpect(jsonPath("$.deductionJsonData").value(DEFAULT_DEDUCTION_JSON_DATA))
            .andExpect(jsonPath("$.deductionCertificateUrl").value(DEFAULT_DEDUCTION_CERTIFICATE_URL))
            .andExpect(jsonPath("$.paidAmount").value(DEFAULT_PAID_AMOUNT))
            .andExpect(jsonPath("$.transactionId").value(DEFAULT_TRANSACTION_ID))
            .andExpect(jsonPath("$.transactionScreenshotUrl").value(DEFAULT_TRANSACTION_SCREENSHOT_URL))
            .andExpect(jsonPath("$.transactionDate").value(DEFAULT_TRANSACTION_DATE.toString()))
            .andExpect(jsonPath("$.transactionStatus").value(DEFAULT_TRANSACTION_STATUS.toString()))
            .andExpect(jsonPath("$.paidBy").value(DEFAULT_PAID_BY))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCompetitionPaymentToWinner() throws Exception {
        // Get the competitionPaymentToWinner
        restCompetitionPaymentToWinnerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCompetitionPaymentToWinner() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentToWinner = competitionPaymentToWinnerRepository.saveAndFlush(competitionPaymentToWinner);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionPaymentToWinner
        CompetitionPaymentToWinner updatedCompetitionPaymentToWinner = competitionPaymentToWinnerRepository
            .findById(competitionPaymentToWinner.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCompetitionPaymentToWinner are not directly saved in db
        em.detach(updatedCompetitionPaymentToWinner);
        updatedCompetitionPaymentToWinner
            .prizeTitle(UPDATED_PRIZE_TITLE)
            .prizeAmount(UPDATED_PRIZE_AMOUNT)
            .tdsAmount(UPDATED_TDS_AMOUNT)
            .tdsCertificateUrl(UPDATED_TDS_CERTIFICATE_URL)
            .otherDeductionAmount(UPDATED_OTHER_DEDUCTION_AMOUNT)
            .deductionReason(UPDATED_DEDUCTION_REASON)
            .deductionJsonData(UPDATED_DEDUCTION_JSON_DATA)
            .deductionCertificateUrl(UPDATED_DEDUCTION_CERTIFICATE_URL)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionScreenshotUrl(UPDATED_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .paidBy(UPDATED_PAID_BY)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionPaymentToWinnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCompetitionPaymentToWinner.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCompetitionPaymentToWinner))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCompetitionPaymentToWinnerToMatchAllProperties(updatedCompetitionPaymentToWinner);
    }

    @Test
    @Transactional
    void putNonExistingCompetitionPaymentToWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentToWinner.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionPaymentToWinnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, competitionPaymentToWinner.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competitionPaymentToWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCompetitionPaymentToWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentToWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentToWinnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(competitionPaymentToWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCompetitionPaymentToWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentToWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentToWinnerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(competitionPaymentToWinner)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCompetitionPaymentToWinnerWithPatch() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentToWinner = competitionPaymentToWinnerRepository.saveAndFlush(competitionPaymentToWinner);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionPaymentToWinner using partial update
        CompetitionPaymentToWinner partialUpdatedCompetitionPaymentToWinner = new CompetitionPaymentToWinner();
        partialUpdatedCompetitionPaymentToWinner.setId(competitionPaymentToWinner.getId());

        partialUpdatedCompetitionPaymentToWinner
            .prizeTitle(UPDATED_PRIZE_TITLE)
            .prizeAmount(UPDATED_PRIZE_AMOUNT)
            .tdsAmount(UPDATED_TDS_AMOUNT)
            .deductionJsonData(UPDATED_DEDUCTION_JSON_DATA)
            .deductionCertificateUrl(UPDATED_DEDUCTION_CERTIFICATE_URL)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionScreenshotUrl(UPDATED_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionPaymentToWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetitionPaymentToWinner.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetitionPaymentToWinner))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionPaymentToWinner in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionPaymentToWinnerUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCompetitionPaymentToWinner, competitionPaymentToWinner),
            getPersistedCompetitionPaymentToWinner(competitionPaymentToWinner)
        );
    }

    @Test
    @Transactional
    void fullUpdateCompetitionPaymentToWinnerWithPatch() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentToWinner = competitionPaymentToWinnerRepository.saveAndFlush(competitionPaymentToWinner);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the competitionPaymentToWinner using partial update
        CompetitionPaymentToWinner partialUpdatedCompetitionPaymentToWinner = new CompetitionPaymentToWinner();
        partialUpdatedCompetitionPaymentToWinner.setId(competitionPaymentToWinner.getId());

        partialUpdatedCompetitionPaymentToWinner
            .prizeTitle(UPDATED_PRIZE_TITLE)
            .prizeAmount(UPDATED_PRIZE_AMOUNT)
            .tdsAmount(UPDATED_TDS_AMOUNT)
            .tdsCertificateUrl(UPDATED_TDS_CERTIFICATE_URL)
            .otherDeductionAmount(UPDATED_OTHER_DEDUCTION_AMOUNT)
            .deductionReason(UPDATED_DEDUCTION_REASON)
            .deductionJsonData(UPDATED_DEDUCTION_JSON_DATA)
            .deductionCertificateUrl(UPDATED_DEDUCTION_CERTIFICATE_URL)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionScreenshotUrl(UPDATED_TRANSACTION_SCREENSHOT_URL)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .paidBy(UPDATED_PAID_BY)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restCompetitionPaymentToWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompetitionPaymentToWinner.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompetitionPaymentToWinner))
            )
            .andExpect(status().isOk());

        // Validate the CompetitionPaymentToWinner in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompetitionPaymentToWinnerUpdatableFieldsEquals(
            partialUpdatedCompetitionPaymentToWinner,
            getPersistedCompetitionPaymentToWinner(partialUpdatedCompetitionPaymentToWinner)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCompetitionPaymentToWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentToWinner.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitionPaymentToWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, competitionPaymentToWinner.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competitionPaymentToWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCompetitionPaymentToWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentToWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentToWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(competitionPaymentToWinner))
            )
            .andExpect(status().isBadRequest());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCompetitionPaymentToWinner() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        competitionPaymentToWinner.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompetitionPaymentToWinnerMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(competitionPaymentToWinner))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CompetitionPaymentToWinner in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCompetitionPaymentToWinner() throws Exception {
        // Initialize the database
        insertedCompetitionPaymentToWinner = competitionPaymentToWinnerRepository.saveAndFlush(competitionPaymentToWinner);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the competitionPaymentToWinner
        restCompetitionPaymentToWinnerMockMvc
            .perform(delete(ENTITY_API_URL_ID, competitionPaymentToWinner.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return competitionPaymentToWinnerRepository.count();
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

    protected CompetitionPaymentToWinner getPersistedCompetitionPaymentToWinner(CompetitionPaymentToWinner competitionPaymentToWinner) {
        return competitionPaymentToWinnerRepository.findById(competitionPaymentToWinner.getId()).orElseThrow();
    }

    protected void assertPersistedCompetitionPaymentToWinnerToMatchAllProperties(
        CompetitionPaymentToWinner expectedCompetitionPaymentToWinner
    ) {
        assertCompetitionPaymentToWinnerAllPropertiesEquals(
            expectedCompetitionPaymentToWinner,
            getPersistedCompetitionPaymentToWinner(expectedCompetitionPaymentToWinner)
        );
    }

    protected void assertPersistedCompetitionPaymentToWinnerToMatchUpdatableProperties(
        CompetitionPaymentToWinner expectedCompetitionPaymentToWinner
    ) {
        assertCompetitionPaymentToWinnerAllUpdatablePropertiesEquals(
            expectedCompetitionPaymentToWinner,
            getPersistedCompetitionPaymentToWinner(expectedCompetitionPaymentToWinner)
        );
    }
}
