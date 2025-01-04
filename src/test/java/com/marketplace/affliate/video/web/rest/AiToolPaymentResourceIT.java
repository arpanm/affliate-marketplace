package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.AiToolPaymentAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.AiToolPayment;
import com.marketplace.affliate.video.domain.enumeration.PaymentStatus;
import com.marketplace.affliate.video.domain.enumeration.PgType;
import com.marketplace.affliate.video.repository.AiToolPaymentRepository;
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
 * Integration tests for the {@link AiToolPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AiToolPaymentResourceIT {

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final PaymentStatus DEFAULT_STATUS = PaymentStatus.Initiate;
    private static final PaymentStatus UPDATED_STATUS = PaymentStatus.PendingOnPg;

    private static final String DEFAULT_PAYMENT_LINK_URL = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_LINK_URL = "BBBBBBBBBB";

    private static final PgType DEFAULT_PG_TYPE = PgType.RazorPay;
    private static final PgType UPDATED_PG_TYPE = PgType.PayU;

    private static final String DEFAULT_PG_ID = "AAAAAAAAAA";
    private static final String UPDATED_PG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PG_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PG_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PG_REQUEST_JSON = "AAAAAAAAAA";
    private static final String UPDATED_PG_REQUEST_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_PG_RESPONSE_JSON = "AAAAAAAAAA";
    private static final String UPDATED_PG_RESPONSE_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_PG_REQUEST_TIME_STAMP = "AAAAAAAAAA";
    private static final String UPDATED_PG_REQUEST_TIME_STAMP = "BBBBBBBBBB";

    private static final String DEFAULT_PG_RESPONSE_TIME_STAMP = "AAAAAAAAAA";
    private static final String UPDATED_PG_RESPONSE_TIME_STAMP = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/ai-tool-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AiToolPaymentRepository aiToolPaymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAiToolPaymentMockMvc;

    private AiToolPayment aiToolPayment;

    private AiToolPayment insertedAiToolPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AiToolPayment createEntity() {
        return new AiToolPayment()
            .amount(DEFAULT_AMOUNT)
            .status(DEFAULT_STATUS)
            .paymentLinkUrl(DEFAULT_PAYMENT_LINK_URL)
            .pgType(DEFAULT_PG_TYPE)
            .pgId(DEFAULT_PG_ID)
            .pgStatus(DEFAULT_PG_STATUS)
            .pgRequestJson(DEFAULT_PG_REQUEST_JSON)
            .pgResponseJson(DEFAULT_PG_RESPONSE_JSON)
            .pgRequestTimeStamp(DEFAULT_PG_REQUEST_TIME_STAMP)
            .pgResponseTimeStamp(DEFAULT_PG_RESPONSE_TIME_STAMP)
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
    public static AiToolPayment createUpdatedEntity() {
        return new AiToolPayment()
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .paymentLinkUrl(UPDATED_PAYMENT_LINK_URL)
            .pgType(UPDATED_PG_TYPE)
            .pgId(UPDATED_PG_ID)
            .pgStatus(UPDATED_PG_STATUS)
            .pgRequestJson(UPDATED_PG_REQUEST_JSON)
            .pgResponseJson(UPDATED_PG_RESPONSE_JSON)
            .pgRequestTimeStamp(UPDATED_PG_REQUEST_TIME_STAMP)
            .pgResponseTimeStamp(UPDATED_PG_RESPONSE_TIME_STAMP)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        aiToolPayment = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAiToolPayment != null) {
            aiToolPaymentRepository.delete(insertedAiToolPayment);
            insertedAiToolPayment = null;
        }
    }

    @Test
    @Transactional
    void createAiToolPayment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AiToolPayment
        var returnedAiToolPayment = om.readValue(
            restAiToolPaymentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolPayment)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AiToolPayment.class
        );

        // Validate the AiToolPayment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAiToolPaymentUpdatableFieldsEquals(returnedAiToolPayment, getPersistedAiToolPayment(returnedAiToolPayment));

        insertedAiToolPayment = returnedAiToolPayment;
    }

    @Test
    @Transactional
    void createAiToolPaymentWithExistingId() throws Exception {
        // Create the AiToolPayment with an existing ID
        aiToolPayment.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAiToolPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolPayment)))
            .andExpect(status().isBadRequest());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAiToolPayments() throws Exception {
        // Initialize the database
        insertedAiToolPayment = aiToolPaymentRepository.saveAndFlush(aiToolPayment);

        // Get all the aiToolPaymentList
        restAiToolPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aiToolPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].paymentLinkUrl").value(hasItem(DEFAULT_PAYMENT_LINK_URL)))
            .andExpect(jsonPath("$.[*].pgType").value(hasItem(DEFAULT_PG_TYPE.toString())))
            .andExpect(jsonPath("$.[*].pgId").value(hasItem(DEFAULT_PG_ID)))
            .andExpect(jsonPath("$.[*].pgStatus").value(hasItem(DEFAULT_PG_STATUS)))
            .andExpect(jsonPath("$.[*].pgRequestJson").value(hasItem(DEFAULT_PG_REQUEST_JSON)))
            .andExpect(jsonPath("$.[*].pgResponseJson").value(hasItem(DEFAULT_PG_RESPONSE_JSON)))
            .andExpect(jsonPath("$.[*].pgRequestTimeStamp").value(hasItem(DEFAULT_PG_REQUEST_TIME_STAMP)))
            .andExpect(jsonPath("$.[*].pgResponseTimeStamp").value(hasItem(DEFAULT_PG_RESPONSE_TIME_STAMP)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getAiToolPayment() throws Exception {
        // Initialize the database
        insertedAiToolPayment = aiToolPaymentRepository.saveAndFlush(aiToolPayment);

        // Get the aiToolPayment
        restAiToolPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, aiToolPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aiToolPayment.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.paymentLinkUrl").value(DEFAULT_PAYMENT_LINK_URL))
            .andExpect(jsonPath("$.pgType").value(DEFAULT_PG_TYPE.toString()))
            .andExpect(jsonPath("$.pgId").value(DEFAULT_PG_ID))
            .andExpect(jsonPath("$.pgStatus").value(DEFAULT_PG_STATUS))
            .andExpect(jsonPath("$.pgRequestJson").value(DEFAULT_PG_REQUEST_JSON))
            .andExpect(jsonPath("$.pgResponseJson").value(DEFAULT_PG_RESPONSE_JSON))
            .andExpect(jsonPath("$.pgRequestTimeStamp").value(DEFAULT_PG_REQUEST_TIME_STAMP))
            .andExpect(jsonPath("$.pgResponseTimeStamp").value(DEFAULT_PG_RESPONSE_TIME_STAMP))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAiToolPayment() throws Exception {
        // Get the aiToolPayment
        restAiToolPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAiToolPayment() throws Exception {
        // Initialize the database
        insertedAiToolPayment = aiToolPaymentRepository.saveAndFlush(aiToolPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolPayment
        AiToolPayment updatedAiToolPayment = aiToolPaymentRepository.findById(aiToolPayment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAiToolPayment are not directly saved in db
        em.detach(updatedAiToolPayment);
        updatedAiToolPayment
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .paymentLinkUrl(UPDATED_PAYMENT_LINK_URL)
            .pgType(UPDATED_PG_TYPE)
            .pgId(UPDATED_PG_ID)
            .pgStatus(UPDATED_PG_STATUS)
            .pgRequestJson(UPDATED_PG_REQUEST_JSON)
            .pgResponseJson(UPDATED_PG_RESPONSE_JSON)
            .pgRequestTimeStamp(UPDATED_PG_REQUEST_TIME_STAMP)
            .pgResponseTimeStamp(UPDATED_PG_RESPONSE_TIME_STAMP)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAiToolPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAiToolPayment))
            )
            .andExpect(status().isOk());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAiToolPaymentToMatchAllProperties(updatedAiToolPayment);
    }

    @Test
    @Transactional
    void putNonExistingAiToolPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolPayment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, aiToolPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(aiToolPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAiToolPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolPayment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(aiToolPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAiToolPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolPayment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolPaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAiToolPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedAiToolPayment = aiToolPaymentRepository.saveAndFlush(aiToolPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolPayment using partial update
        AiToolPayment partialUpdatedAiToolPayment = new AiToolPayment();
        partialUpdatedAiToolPayment.setId(aiToolPayment.getId());

        partialUpdatedAiToolPayment
            .status(UPDATED_STATUS)
            .paymentLinkUrl(UPDATED_PAYMENT_LINK_URL)
            .pgType(UPDATED_PG_TYPE)
            .pgId(UPDATED_PG_ID)
            .pgStatus(UPDATED_PG_STATUS)
            .pgResponseJson(UPDATED_PG_RESPONSE_JSON)
            .pgRequestTimeStamp(UPDATED_PG_REQUEST_TIME_STAMP)
            .pgResponseTimeStamp(UPDATED_PG_RESPONSE_TIME_STAMP)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON);

        restAiToolPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiToolPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiToolPayment))
            )
            .andExpect(status().isOk());

        // Validate the AiToolPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolPaymentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAiToolPayment, aiToolPayment),
            getPersistedAiToolPayment(aiToolPayment)
        );
    }

    @Test
    @Transactional
    void fullUpdateAiToolPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedAiToolPayment = aiToolPaymentRepository.saveAndFlush(aiToolPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolPayment using partial update
        AiToolPayment partialUpdatedAiToolPayment = new AiToolPayment();
        partialUpdatedAiToolPayment.setId(aiToolPayment.getId());

        partialUpdatedAiToolPayment
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .paymentLinkUrl(UPDATED_PAYMENT_LINK_URL)
            .pgType(UPDATED_PG_TYPE)
            .pgId(UPDATED_PG_ID)
            .pgStatus(UPDATED_PG_STATUS)
            .pgRequestJson(UPDATED_PG_REQUEST_JSON)
            .pgResponseJson(UPDATED_PG_RESPONSE_JSON)
            .pgRequestTimeStamp(UPDATED_PG_REQUEST_TIME_STAMP)
            .pgResponseTimeStamp(UPDATED_PG_RESPONSE_TIME_STAMP)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiToolPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiToolPayment))
            )
            .andExpect(status().isOk());

        // Validate the AiToolPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolPaymentUpdatableFieldsEquals(partialUpdatedAiToolPayment, getPersistedAiToolPayment(partialUpdatedAiToolPayment));
    }

    @Test
    @Transactional
    void patchNonExistingAiToolPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolPayment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, aiToolPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aiToolPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAiToolPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolPayment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aiToolPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAiToolPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolPayment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolPaymentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(aiToolPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiToolPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAiToolPayment() throws Exception {
        // Initialize the database
        insertedAiToolPayment = aiToolPaymentRepository.saveAndFlush(aiToolPayment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the aiToolPayment
        restAiToolPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, aiToolPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return aiToolPaymentRepository.count();
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

    protected AiToolPayment getPersistedAiToolPayment(AiToolPayment aiToolPayment) {
        return aiToolPaymentRepository.findById(aiToolPayment.getId()).orElseThrow();
    }

    protected void assertPersistedAiToolPaymentToMatchAllProperties(AiToolPayment expectedAiToolPayment) {
        assertAiToolPaymentAllPropertiesEquals(expectedAiToolPayment, getPersistedAiToolPayment(expectedAiToolPayment));
    }

    protected void assertPersistedAiToolPaymentToMatchUpdatableProperties(AiToolPayment expectedAiToolPayment) {
        assertAiToolPaymentAllUpdatablePropertiesEquals(expectedAiToolPayment, getPersistedAiToolPayment(expectedAiToolPayment));
    }
}
