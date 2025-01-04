package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.AiToolSessionAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.AiToolSession;
import com.marketplace.affliate.video.repository.AiToolSessionRepository;
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
 * Integration tests for the {@link AiToolSessionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AiToolSessionResourceIT {

    private static final Boolean DEFAULT_IS_PAYMENT_LINK_GENERATED = false;
    private static final Boolean UPDATED_IS_PAYMENT_LINK_GENERATED = true;

    private static final Boolean DEFAULT_IS_PAID = false;
    private static final Boolean UPDATED_IS_PAID = true;

    private static final Boolean DEFAULT_IS_VIDEO_GENERATED = false;
    private static final Boolean UPDATED_IS_VIDEO_GENERATED = true;

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

    private static final String ENTITY_API_URL = "/api/ai-tool-sessions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AiToolSessionRepository aiToolSessionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAiToolSessionMockMvc;

    private AiToolSession aiToolSession;

    private AiToolSession insertedAiToolSession;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AiToolSession createEntity() {
        return new AiToolSession()
            .isPaymentLinkGenerated(DEFAULT_IS_PAYMENT_LINK_GENERATED)
            .isPaid(DEFAULT_IS_PAID)
            .isVideoGenerated(DEFAULT_IS_VIDEO_GENERATED)
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
    public static AiToolSession createUpdatedEntity() {
        return new AiToolSession()
            .isPaymentLinkGenerated(UPDATED_IS_PAYMENT_LINK_GENERATED)
            .isPaid(UPDATED_IS_PAID)
            .isVideoGenerated(UPDATED_IS_VIDEO_GENERATED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        aiToolSession = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAiToolSession != null) {
            aiToolSessionRepository.delete(insertedAiToolSession);
            insertedAiToolSession = null;
        }
    }

    @Test
    @Transactional
    void createAiToolSession() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AiToolSession
        var returnedAiToolSession = om.readValue(
            restAiToolSessionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolSession)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AiToolSession.class
        );

        // Validate the AiToolSession in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAiToolSessionUpdatableFieldsEquals(returnedAiToolSession, getPersistedAiToolSession(returnedAiToolSession));

        insertedAiToolSession = returnedAiToolSession;
    }

    @Test
    @Transactional
    void createAiToolSessionWithExistingId() throws Exception {
        // Create the AiToolSession with an existing ID
        aiToolSession.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAiToolSessionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolSession)))
            .andExpect(status().isBadRequest());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAiToolSessions() throws Exception {
        // Initialize the database
        insertedAiToolSession = aiToolSessionRepository.saveAndFlush(aiToolSession);

        // Get all the aiToolSessionList
        restAiToolSessionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aiToolSession.getId().intValue())))
            .andExpect(jsonPath("$.[*].isPaymentLinkGenerated").value(hasItem(DEFAULT_IS_PAYMENT_LINK_GENERATED)))
            .andExpect(jsonPath("$.[*].isPaid").value(hasItem(DEFAULT_IS_PAID)))
            .andExpect(jsonPath("$.[*].isVideoGenerated").value(hasItem(DEFAULT_IS_VIDEO_GENERATED)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getAiToolSession() throws Exception {
        // Initialize the database
        insertedAiToolSession = aiToolSessionRepository.saveAndFlush(aiToolSession);

        // Get the aiToolSession
        restAiToolSessionMockMvc
            .perform(get(ENTITY_API_URL_ID, aiToolSession.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aiToolSession.getId().intValue()))
            .andExpect(jsonPath("$.isPaymentLinkGenerated").value(DEFAULT_IS_PAYMENT_LINK_GENERATED))
            .andExpect(jsonPath("$.isPaid").value(DEFAULT_IS_PAID))
            .andExpect(jsonPath("$.isVideoGenerated").value(DEFAULT_IS_VIDEO_GENERATED))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAiToolSession() throws Exception {
        // Get the aiToolSession
        restAiToolSessionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAiToolSession() throws Exception {
        // Initialize the database
        insertedAiToolSession = aiToolSessionRepository.saveAndFlush(aiToolSession);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolSession
        AiToolSession updatedAiToolSession = aiToolSessionRepository.findById(aiToolSession.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAiToolSession are not directly saved in db
        em.detach(updatedAiToolSession);
        updatedAiToolSession
            .isPaymentLinkGenerated(UPDATED_IS_PAYMENT_LINK_GENERATED)
            .isPaid(UPDATED_IS_PAID)
            .isVideoGenerated(UPDATED_IS_VIDEO_GENERATED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolSessionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAiToolSession.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAiToolSession))
            )
            .andExpect(status().isOk());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAiToolSessionToMatchAllProperties(updatedAiToolSession);
    }

    @Test
    @Transactional
    void putNonExistingAiToolSession() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolSession.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolSessionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, aiToolSession.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(aiToolSession))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAiToolSession() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolSession.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolSessionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(aiToolSession))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAiToolSession() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolSession.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolSessionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolSession)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAiToolSessionWithPatch() throws Exception {
        // Initialize the database
        insertedAiToolSession = aiToolSessionRepository.saveAndFlush(aiToolSession);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolSession using partial update
        AiToolSession partialUpdatedAiToolSession = new AiToolSession();
        partialUpdatedAiToolSession.setId(aiToolSession.getId());

        partialUpdatedAiToolSession
            .isPaymentLinkGenerated(UPDATED_IS_PAYMENT_LINK_GENERATED)
            .isVideoGenerated(UPDATED_IS_VIDEO_GENERATED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON);

        restAiToolSessionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiToolSession.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiToolSession))
            )
            .andExpect(status().isOk());

        // Validate the AiToolSession in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolSessionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAiToolSession, aiToolSession),
            getPersistedAiToolSession(aiToolSession)
        );
    }

    @Test
    @Transactional
    void fullUpdateAiToolSessionWithPatch() throws Exception {
        // Initialize the database
        insertedAiToolSession = aiToolSessionRepository.saveAndFlush(aiToolSession);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolSession using partial update
        AiToolSession partialUpdatedAiToolSession = new AiToolSession();
        partialUpdatedAiToolSession.setId(aiToolSession.getId());

        partialUpdatedAiToolSession
            .isPaymentLinkGenerated(UPDATED_IS_PAYMENT_LINK_GENERATED)
            .isPaid(UPDATED_IS_PAID)
            .isVideoGenerated(UPDATED_IS_VIDEO_GENERATED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolSessionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiToolSession.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiToolSession))
            )
            .andExpect(status().isOk());

        // Validate the AiToolSession in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolSessionUpdatableFieldsEquals(partialUpdatedAiToolSession, getPersistedAiToolSession(partialUpdatedAiToolSession));
    }

    @Test
    @Transactional
    void patchNonExistingAiToolSession() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolSession.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolSessionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, aiToolSession.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aiToolSession))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAiToolSession() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolSession.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolSessionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aiToolSession))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAiToolSession() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolSession.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolSessionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(aiToolSession)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiToolSession in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAiToolSession() throws Exception {
        // Initialize the database
        insertedAiToolSession = aiToolSessionRepository.saveAndFlush(aiToolSession);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the aiToolSession
        restAiToolSessionMockMvc
            .perform(delete(ENTITY_API_URL_ID, aiToolSession.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return aiToolSessionRepository.count();
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

    protected AiToolSession getPersistedAiToolSession(AiToolSession aiToolSession) {
        return aiToolSessionRepository.findById(aiToolSession.getId()).orElseThrow();
    }

    protected void assertPersistedAiToolSessionToMatchAllProperties(AiToolSession expectedAiToolSession) {
        assertAiToolSessionAllPropertiesEquals(expectedAiToolSession, getPersistedAiToolSession(expectedAiToolSession));
    }

    protected void assertPersistedAiToolSessionToMatchUpdatableProperties(AiToolSession expectedAiToolSession) {
        assertAiToolSessionAllUpdatablePropertiesEquals(expectedAiToolSession, getPersistedAiToolSession(expectedAiToolSession));
    }
}
