package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.AiToolAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.AiTool;
import com.marketplace.affliate.video.repository.AiToolRepository;
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
 * Integration tests for the {@link AiToolResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AiToolResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_URL = "AAAAAAAAAA";
    private static final String UPDATED_LINK_URL = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/ai-tools";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AiToolRepository aiToolRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAiToolMockMvc;

    private AiTool aiTool;

    private AiTool insertedAiTool;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AiTool createEntity() {
        return new AiTool()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .vendor(DEFAULT_VENDOR)
            .linkUrl(DEFAULT_LINK_URL)
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
    public static AiTool createUpdatedEntity() {
        return new AiTool()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .vendor(UPDATED_VENDOR)
            .linkUrl(UPDATED_LINK_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        aiTool = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAiTool != null) {
            aiToolRepository.delete(insertedAiTool);
            insertedAiTool = null;
        }
    }

    @Test
    @Transactional
    void createAiTool() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AiTool
        var returnedAiTool = om.readValue(
            restAiToolMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiTool)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AiTool.class
        );

        // Validate the AiTool in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAiToolUpdatableFieldsEquals(returnedAiTool, getPersistedAiTool(returnedAiTool));

        insertedAiTool = returnedAiTool;
    }

    @Test
    @Transactional
    void createAiToolWithExistingId() throws Exception {
        // Create the AiTool with an existing ID
        aiTool.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAiToolMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiTool)))
            .andExpect(status().isBadRequest());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAiTools() throws Exception {
        // Initialize the database
        insertedAiTool = aiToolRepository.saveAndFlush(aiTool);

        // Get all the aiToolList
        restAiToolMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aiTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].vendor").value(hasItem(DEFAULT_VENDOR)))
            .andExpect(jsonPath("$.[*].linkUrl").value(hasItem(DEFAULT_LINK_URL)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getAiTool() throws Exception {
        // Initialize the database
        insertedAiTool = aiToolRepository.saveAndFlush(aiTool);

        // Get the aiTool
        restAiToolMockMvc
            .perform(get(ENTITY_API_URL_ID, aiTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aiTool.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.vendor").value(DEFAULT_VENDOR))
            .andExpect(jsonPath("$.linkUrl").value(DEFAULT_LINK_URL))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAiTool() throws Exception {
        // Get the aiTool
        restAiToolMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAiTool() throws Exception {
        // Initialize the database
        insertedAiTool = aiToolRepository.saveAndFlush(aiTool);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiTool
        AiTool updatedAiTool = aiToolRepository.findById(aiTool.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAiTool are not directly saved in db
        em.detach(updatedAiTool);
        updatedAiTool
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .vendor(UPDATED_VENDOR)
            .linkUrl(UPDATED_LINK_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAiTool.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAiTool))
            )
            .andExpect(status().isOk());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAiToolToMatchAllProperties(updatedAiTool);
    }

    @Test
    @Transactional
    void putNonExistingAiTool() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiTool.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolMockMvc
            .perform(put(ENTITY_API_URL_ID, aiTool.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiTool)))
            .andExpect(status().isBadRequest());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAiTool() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiTool.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(aiTool))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAiTool() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiTool.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiTool)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAiToolWithPatch() throws Exception {
        // Initialize the database
        insertedAiTool = aiToolRepository.saveAndFlush(aiTool);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiTool using partial update
        AiTool partialUpdatedAiTool = new AiTool();
        partialUpdatedAiTool.setId(aiTool.getId());

        partialUpdatedAiTool
            .description(UPDATED_DESCRIPTION)
            .vendor(UPDATED_VENDOR)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiTool.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiTool))
            )
            .andExpect(status().isOk());

        // Validate the AiTool in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedAiTool, aiTool), getPersistedAiTool(aiTool));
    }

    @Test
    @Transactional
    void fullUpdateAiToolWithPatch() throws Exception {
        // Initialize the database
        insertedAiTool = aiToolRepository.saveAndFlush(aiTool);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiTool using partial update
        AiTool partialUpdatedAiTool = new AiTool();
        partialUpdatedAiTool.setId(aiTool.getId());

        partialUpdatedAiTool
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .vendor(UPDATED_VENDOR)
            .linkUrl(UPDATED_LINK_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiTool.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiTool))
            )
            .andExpect(status().isOk());

        // Validate the AiTool in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolUpdatableFieldsEquals(partialUpdatedAiTool, getPersistedAiTool(partialUpdatedAiTool));
    }

    @Test
    @Transactional
    void patchNonExistingAiTool() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiTool.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, aiTool.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(aiTool))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAiTool() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiTool.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aiTool))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAiTool() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiTool.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(aiTool)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiTool in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAiTool() throws Exception {
        // Initialize the database
        insertedAiTool = aiToolRepository.saveAndFlush(aiTool);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the aiTool
        restAiToolMockMvc
            .perform(delete(ENTITY_API_URL_ID, aiTool.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return aiToolRepository.count();
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

    protected AiTool getPersistedAiTool(AiTool aiTool) {
        return aiToolRepository.findById(aiTool.getId()).orElseThrow();
    }

    protected void assertPersistedAiToolToMatchAllProperties(AiTool expectedAiTool) {
        assertAiToolAllPropertiesEquals(expectedAiTool, getPersistedAiTool(expectedAiTool));
    }

    protected void assertPersistedAiToolToMatchUpdatableProperties(AiTool expectedAiTool) {
        assertAiToolAllUpdatablePropertiesEquals(expectedAiTool, getPersistedAiTool(expectedAiTool));
    }
}
