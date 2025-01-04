package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.AffinityAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.Affinity;
import com.marketplace.affliate.video.domain.enumeration.Segment;
import com.marketplace.affliate.video.repository.AffinityRepository;
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
 * Integration tests for the {@link AffinityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AffinityResourceIT {

    private static final Segment DEFAULT_SEGMENT = Segment.A;
    private static final Segment UPDATED_SEGMENT = Segment.B;

    private static final Long DEFAULT_SCORE = 1L;
    private static final Long UPDATED_SCORE = 2L;

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

    private static final String ENTITY_API_URL = "/api/affinities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AffinityRepository affinityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAffinityMockMvc;

    private Affinity affinity;

    private Affinity insertedAffinity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Affinity createEntity() {
        return new Affinity()
            .segment(DEFAULT_SEGMENT)
            .score(DEFAULT_SCORE)
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
    public static Affinity createUpdatedEntity() {
        return new Affinity()
            .segment(UPDATED_SEGMENT)
            .score(UPDATED_SCORE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        affinity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAffinity != null) {
            affinityRepository.delete(insertedAffinity);
            insertedAffinity = null;
        }
    }

    @Test
    @Transactional
    void createAffinity() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Affinity
        var returnedAffinity = om.readValue(
            restAffinityMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(affinity)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Affinity.class
        );

        // Validate the Affinity in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAffinityUpdatableFieldsEquals(returnedAffinity, getPersistedAffinity(returnedAffinity));

        insertedAffinity = returnedAffinity;
    }

    @Test
    @Transactional
    void createAffinityWithExistingId() throws Exception {
        // Create the Affinity with an existing ID
        affinity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAffinityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(affinity)))
            .andExpect(status().isBadRequest());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAffinities() throws Exception {
        // Initialize the database
        insertedAffinity = affinityRepository.saveAndFlush(affinity);

        // Get all the affinityList
        restAffinityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(affinity.getId().intValue())))
            .andExpect(jsonPath("$.[*].segment").value(hasItem(DEFAULT_SEGMENT.toString())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getAffinity() throws Exception {
        // Initialize the database
        insertedAffinity = affinityRepository.saveAndFlush(affinity);

        // Get the affinity
        restAffinityMockMvc
            .perform(get(ENTITY_API_URL_ID, affinity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(affinity.getId().intValue()))
            .andExpect(jsonPath("$.segment").value(DEFAULT_SEGMENT.toString()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAffinity() throws Exception {
        // Get the affinity
        restAffinityMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAffinity() throws Exception {
        // Initialize the database
        insertedAffinity = affinityRepository.saveAndFlush(affinity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the affinity
        Affinity updatedAffinity = affinityRepository.findById(affinity.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAffinity are not directly saved in db
        em.detach(updatedAffinity);
        updatedAffinity
            .segment(UPDATED_SEGMENT)
            .score(UPDATED_SCORE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAffinityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAffinity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAffinity))
            )
            .andExpect(status().isOk());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAffinityToMatchAllProperties(updatedAffinity);
    }

    @Test
    @Transactional
    void putNonExistingAffinity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        affinity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffinityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, affinity.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(affinity))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAffinity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        affinity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffinityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(affinity))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAffinity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        affinity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffinityMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(affinity)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAffinityWithPatch() throws Exception {
        // Initialize the database
        insertedAffinity = affinityRepository.saveAndFlush(affinity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the affinity using partial update
        Affinity partialUpdatedAffinity = new Affinity();
        partialUpdatedAffinity.setId(affinity.getId());

        partialUpdatedAffinity.score(UPDATED_SCORE);

        restAffinityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAffinity.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAffinity))
            )
            .andExpect(status().isOk());

        // Validate the Affinity in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAffinityUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedAffinity, affinity), getPersistedAffinity(affinity));
    }

    @Test
    @Transactional
    void fullUpdateAffinityWithPatch() throws Exception {
        // Initialize the database
        insertedAffinity = affinityRepository.saveAndFlush(affinity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the affinity using partial update
        Affinity partialUpdatedAffinity = new Affinity();
        partialUpdatedAffinity.setId(affinity.getId());

        partialUpdatedAffinity
            .segment(UPDATED_SEGMENT)
            .score(UPDATED_SCORE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAffinityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAffinity.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAffinity))
            )
            .andExpect(status().isOk());

        // Validate the Affinity in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAffinityUpdatableFieldsEquals(partialUpdatedAffinity, getPersistedAffinity(partialUpdatedAffinity));
    }

    @Test
    @Transactional
    void patchNonExistingAffinity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        affinity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffinityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, affinity.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(affinity))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAffinity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        affinity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffinityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(affinity))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAffinity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        affinity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffinityMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(affinity)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Affinity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAffinity() throws Exception {
        // Initialize the database
        insertedAffinity = affinityRepository.saveAndFlush(affinity);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the affinity
        restAffinityMockMvc
            .perform(delete(ENTITY_API_URL_ID, affinity.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return affinityRepository.count();
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

    protected Affinity getPersistedAffinity(Affinity affinity) {
        return affinityRepository.findById(affinity.getId()).orElseThrow();
    }

    protected void assertPersistedAffinityToMatchAllProperties(Affinity expectedAffinity) {
        assertAffinityAllPropertiesEquals(expectedAffinity, getPersistedAffinity(expectedAffinity));
    }

    protected void assertPersistedAffinityToMatchUpdatableProperties(Affinity expectedAffinity) {
        assertAffinityAllUpdatablePropertiesEquals(expectedAffinity, getPersistedAffinity(expectedAffinity));
    }
}
