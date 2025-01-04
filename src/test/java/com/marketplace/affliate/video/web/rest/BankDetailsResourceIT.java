package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.BankDetailsAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.BankDetails;
import com.marketplace.affliate.video.repository.BankDetailsRepository;
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
 * Integration tests for the {@link BankDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BankDetailsResourceIT {

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC = "AAAAAAAAAA";
    private static final String UPDATED_IFSC = "BBBBBBBBBB";

    private static final String DEFAULT_PROOF_URL = "AAAAAAAAAA";
    private static final String UPDATED_PROOF_URL = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/bank-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBankDetailsMockMvc;

    private BankDetails bankDetails;

    private BankDetails insertedBankDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankDetails createEntity() {
        return new BankDetails()
            .accountName(DEFAULT_ACCOUNT_NAME)
            .accountNo(DEFAULT_ACCOUNT_NO)
            .bankName(DEFAULT_BANK_NAME)
            .ifsc(DEFAULT_IFSC)
            .proofUrl(DEFAULT_PROOF_URL)
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
    public static BankDetails createUpdatedEntity() {
        return new BankDetails()
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountNo(UPDATED_ACCOUNT_NO)
            .bankName(UPDATED_BANK_NAME)
            .ifsc(UPDATED_IFSC)
            .proofUrl(UPDATED_PROOF_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        bankDetails = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedBankDetails != null) {
            bankDetailsRepository.delete(insertedBankDetails);
            insertedBankDetails = null;
        }
    }

    @Test
    @Transactional
    void createBankDetails() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the BankDetails
        var returnedBankDetails = om.readValue(
            restBankDetailsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bankDetails)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            BankDetails.class
        );

        // Validate the BankDetails in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBankDetailsUpdatableFieldsEquals(returnedBankDetails, getPersistedBankDetails(returnedBankDetails));

        insertedBankDetails = returnedBankDetails;
    }

    @Test
    @Transactional
    void createBankDetailsWithExistingId() throws Exception {
        // Create the BankDetails with an existing ID
        bankDetails.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bankDetails)))
            .andExpect(status().isBadRequest());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBankDetails() throws Exception {
        // Initialize the database
        insertedBankDetails = bankDetailsRepository.saveAndFlush(bankDetails);

        // Get all the bankDetailsList
        restBankDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].ifsc").value(hasItem(DEFAULT_IFSC)))
            .andExpect(jsonPath("$.[*].proofUrl").value(hasItem(DEFAULT_PROOF_URL)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getBankDetails() throws Exception {
        // Initialize the database
        insertedBankDetails = bankDetailsRepository.saveAndFlush(bankDetails);

        // Get the bankDetails
        restBankDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, bankDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bankDetails.getId().intValue()))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.accountNo").value(DEFAULT_ACCOUNT_NO))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME))
            .andExpect(jsonPath("$.ifsc").value(DEFAULT_IFSC))
            .andExpect(jsonPath("$.proofUrl").value(DEFAULT_PROOF_URL))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBankDetails() throws Exception {
        // Get the bankDetails
        restBankDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBankDetails() throws Exception {
        // Initialize the database
        insertedBankDetails = bankDetailsRepository.saveAndFlush(bankDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bankDetails
        BankDetails updatedBankDetails = bankDetailsRepository.findById(bankDetails.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBankDetails are not directly saved in db
        em.detach(updatedBankDetails);
        updatedBankDetails
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountNo(UPDATED_ACCOUNT_NO)
            .bankName(UPDATED_BANK_NAME)
            .ifsc(UPDATED_IFSC)
            .proofUrl(UPDATED_PROOF_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restBankDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBankDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBankDetails))
            )
            .andExpect(status().isOk());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBankDetailsToMatchAllProperties(updatedBankDetails);
    }

    @Test
    @Transactional
    void putNonExistingBankDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankDetails.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bankDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(bankDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBankDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(bankDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBankDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankDetailsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bankDetails)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBankDetailsWithPatch() throws Exception {
        // Initialize the database
        insertedBankDetails = bankDetailsRepository.saveAndFlush(bankDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bankDetails using partial update
        BankDetails partialUpdatedBankDetails = new BankDetails();
        partialUpdatedBankDetails.setId(bankDetails.getId());

        partialUpdatedBankDetails.accountName(UPDATED_ACCOUNT_NAME).createdBy(UPDATED_CREATED_BY).createdOn(UPDATED_CREATED_ON);

        restBankDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBankDetails))
            )
            .andExpect(status().isOk());

        // Validate the BankDetails in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBankDetailsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBankDetails, bankDetails),
            getPersistedBankDetails(bankDetails)
        );
    }

    @Test
    @Transactional
    void fullUpdateBankDetailsWithPatch() throws Exception {
        // Initialize the database
        insertedBankDetails = bankDetailsRepository.saveAndFlush(bankDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bankDetails using partial update
        BankDetails partialUpdatedBankDetails = new BankDetails();
        partialUpdatedBankDetails.setId(bankDetails.getId());

        partialUpdatedBankDetails
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountNo(UPDATED_ACCOUNT_NO)
            .bankName(UPDATED_BANK_NAME)
            .ifsc(UPDATED_IFSC)
            .proofUrl(UPDATED_PROOF_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restBankDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBankDetails))
            )
            .andExpect(status().isOk());

        // Validate the BankDetails in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBankDetailsUpdatableFieldsEquals(partialUpdatedBankDetails, getPersistedBankDetails(partialUpdatedBankDetails));
    }

    @Test
    @Transactional
    void patchNonExistingBankDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankDetails.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bankDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bankDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBankDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bankDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBankDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankDetailsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(bankDetails)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BankDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBankDetails() throws Exception {
        // Initialize the database
        insertedBankDetails = bankDetailsRepository.saveAndFlush(bankDetails);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the bankDetails
        restBankDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, bankDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return bankDetailsRepository.count();
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

    protected BankDetails getPersistedBankDetails(BankDetails bankDetails) {
        return bankDetailsRepository.findById(bankDetails.getId()).orElseThrow();
    }

    protected void assertPersistedBankDetailsToMatchAllProperties(BankDetails expectedBankDetails) {
        assertBankDetailsAllPropertiesEquals(expectedBankDetails, getPersistedBankDetails(expectedBankDetails));
    }

    protected void assertPersistedBankDetailsToMatchUpdatableProperties(BankDetails expectedBankDetails) {
        assertBankDetailsAllUpdatablePropertiesEquals(expectedBankDetails, getPersistedBankDetails(expectedBankDetails));
    }
}
