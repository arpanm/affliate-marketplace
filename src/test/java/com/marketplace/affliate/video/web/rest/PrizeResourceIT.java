package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.PrizeAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.Prize;
import com.marketplace.affliate.video.domain.enumeration.PrizeType;
import com.marketplace.affliate.video.domain.enumeration.PrizeValueType;
import com.marketplace.affliate.video.repository.PrizeRepository;
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
 * Integration tests for the {@link PrizeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PrizeResourceIT {

    private static final PrizeType DEFAULT_PRIZE_TYPE = PrizeType.Individual;
    private static final PrizeType UPDATED_PRIZE_TYPE = PrizeType.Group;

    private static final String DEFAULT_PRIZE_TAG = "AAAAAAAAAA";
    private static final String UPDATED_PRIZE_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_PRIZE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_PRIZE_DETAILS = "BBBBBBBBBB";

    private static final PrizeValueType DEFAULT_PRIZE_VALUE_TYPE = PrizeValueType.Money;
    private static final PrizeValueType UPDATED_PRIZE_VALUE_TYPE = PrizeValueType.Other;

    private static final Double DEFAULT_PRIZE_VALUE = 1D;
    private static final Double UPDATED_PRIZE_VALUE = 2D;

    private static final Long DEFAULT_COUNT_OF_POSSIBLE_WINNERS = 1L;
    private static final Long UPDATED_COUNT_OF_POSSIBLE_WINNERS = 2L;

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

    private static final String ENTITY_API_URL = "/api/prizes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PrizeRepository prizeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrizeMockMvc;

    private Prize prize;

    private Prize insertedPrize;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prize createEntity() {
        return new Prize()
            .prizeType(DEFAULT_PRIZE_TYPE)
            .prizeTag(DEFAULT_PRIZE_TAG)
            .prizeDetails(DEFAULT_PRIZE_DETAILS)
            .prizeValueType(DEFAULT_PRIZE_VALUE_TYPE)
            .prizeValue(DEFAULT_PRIZE_VALUE)
            .countOfPossibleWinners(DEFAULT_COUNT_OF_POSSIBLE_WINNERS)
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
    public static Prize createUpdatedEntity() {
        return new Prize()
            .prizeType(UPDATED_PRIZE_TYPE)
            .prizeTag(UPDATED_PRIZE_TAG)
            .prizeDetails(UPDATED_PRIZE_DETAILS)
            .prizeValueType(UPDATED_PRIZE_VALUE_TYPE)
            .prizeValue(UPDATED_PRIZE_VALUE)
            .countOfPossibleWinners(UPDATED_COUNT_OF_POSSIBLE_WINNERS)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        prize = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedPrize != null) {
            prizeRepository.delete(insertedPrize);
            insertedPrize = null;
        }
    }

    @Test
    @Transactional
    void createPrize() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Prize
        var returnedPrize = om.readValue(
            restPrizeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(prize)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Prize.class
        );

        // Validate the Prize in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPrizeUpdatableFieldsEquals(returnedPrize, getPersistedPrize(returnedPrize));

        insertedPrize = returnedPrize;
    }

    @Test
    @Transactional
    void createPrizeWithExistingId() throws Exception {
        // Create the Prize with an existing ID
        prize.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrizeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(prize)))
            .andExpect(status().isBadRequest());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPrizes() throws Exception {
        // Initialize the database
        insertedPrize = prizeRepository.saveAndFlush(prize);

        // Get all the prizeList
        restPrizeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prize.getId().intValue())))
            .andExpect(jsonPath("$.[*].prizeType").value(hasItem(DEFAULT_PRIZE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].prizeTag").value(hasItem(DEFAULT_PRIZE_TAG)))
            .andExpect(jsonPath("$.[*].prizeDetails").value(hasItem(DEFAULT_PRIZE_DETAILS)))
            .andExpect(jsonPath("$.[*].prizeValueType").value(hasItem(DEFAULT_PRIZE_VALUE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].prizeValue").value(hasItem(DEFAULT_PRIZE_VALUE)))
            .andExpect(jsonPath("$.[*].countOfPossibleWinners").value(hasItem(DEFAULT_COUNT_OF_POSSIBLE_WINNERS.intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getPrize() throws Exception {
        // Initialize the database
        insertedPrize = prizeRepository.saveAndFlush(prize);

        // Get the prize
        restPrizeMockMvc
            .perform(get(ENTITY_API_URL_ID, prize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prize.getId().intValue()))
            .andExpect(jsonPath("$.prizeType").value(DEFAULT_PRIZE_TYPE.toString()))
            .andExpect(jsonPath("$.prizeTag").value(DEFAULT_PRIZE_TAG))
            .andExpect(jsonPath("$.prizeDetails").value(DEFAULT_PRIZE_DETAILS))
            .andExpect(jsonPath("$.prizeValueType").value(DEFAULT_PRIZE_VALUE_TYPE.toString()))
            .andExpect(jsonPath("$.prizeValue").value(DEFAULT_PRIZE_VALUE))
            .andExpect(jsonPath("$.countOfPossibleWinners").value(DEFAULT_COUNT_OF_POSSIBLE_WINNERS.intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPrize() throws Exception {
        // Get the prize
        restPrizeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPrize() throws Exception {
        // Initialize the database
        insertedPrize = prizeRepository.saveAndFlush(prize);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the prize
        Prize updatedPrize = prizeRepository.findById(prize.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPrize are not directly saved in db
        em.detach(updatedPrize);
        updatedPrize
            .prizeType(UPDATED_PRIZE_TYPE)
            .prizeTag(UPDATED_PRIZE_TAG)
            .prizeDetails(UPDATED_PRIZE_DETAILS)
            .prizeValueType(UPDATED_PRIZE_VALUE_TYPE)
            .prizeValue(UPDATED_PRIZE_VALUE)
            .countOfPossibleWinners(UPDATED_COUNT_OF_POSSIBLE_WINNERS)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restPrizeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPrize.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPrize))
            )
            .andExpect(status().isOk());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPrizeToMatchAllProperties(updatedPrize);
    }

    @Test
    @Transactional
    void putNonExistingPrize() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        prize.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrizeMockMvc
            .perform(put(ENTITY_API_URL_ID, prize.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(prize)))
            .andExpect(status().isBadRequest());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPrize() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        prize.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrizeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(prize))
            )
            .andExpect(status().isBadRequest());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPrize() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        prize.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrizeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(prize)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePrizeWithPatch() throws Exception {
        // Initialize the database
        insertedPrize = prizeRepository.saveAndFlush(prize);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the prize using partial update
        Prize partialUpdatedPrize = new Prize();
        partialUpdatedPrize.setId(prize.getId());

        partialUpdatedPrize.prizeValueType(UPDATED_PRIZE_VALUE_TYPE).isActive(UPDATED_IS_ACTIVE).updatedBy(UPDATED_UPDATED_BY);

        restPrizeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrize.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPrize))
            )
            .andExpect(status().isOk());

        // Validate the Prize in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPrizeUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedPrize, prize), getPersistedPrize(prize));
    }

    @Test
    @Transactional
    void fullUpdatePrizeWithPatch() throws Exception {
        // Initialize the database
        insertedPrize = prizeRepository.saveAndFlush(prize);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the prize using partial update
        Prize partialUpdatedPrize = new Prize();
        partialUpdatedPrize.setId(prize.getId());

        partialUpdatedPrize
            .prizeType(UPDATED_PRIZE_TYPE)
            .prizeTag(UPDATED_PRIZE_TAG)
            .prizeDetails(UPDATED_PRIZE_DETAILS)
            .prizeValueType(UPDATED_PRIZE_VALUE_TYPE)
            .prizeValue(UPDATED_PRIZE_VALUE)
            .countOfPossibleWinners(UPDATED_COUNT_OF_POSSIBLE_WINNERS)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restPrizeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrize.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPrize))
            )
            .andExpect(status().isOk());

        // Validate the Prize in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPrizeUpdatableFieldsEquals(partialUpdatedPrize, getPersistedPrize(partialUpdatedPrize));
    }

    @Test
    @Transactional
    void patchNonExistingPrize() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        prize.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrizeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, prize.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(prize))
            )
            .andExpect(status().isBadRequest());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPrize() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        prize.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrizeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(prize))
            )
            .andExpect(status().isBadRequest());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPrize() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        prize.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrizeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(prize)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Prize in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePrize() throws Exception {
        // Initialize the database
        insertedPrize = prizeRepository.saveAndFlush(prize);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the prize
        restPrizeMockMvc
            .perform(delete(ENTITY_API_URL_ID, prize.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return prizeRepository.count();
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

    protected Prize getPersistedPrize(Prize prize) {
        return prizeRepository.findById(prize.getId()).orElseThrow();
    }

    protected void assertPersistedPrizeToMatchAllProperties(Prize expectedPrize) {
        assertPrizeAllPropertiesEquals(expectedPrize, getPersistedPrize(expectedPrize));
    }

    protected void assertPersistedPrizeToMatchUpdatableProperties(Prize expectedPrize) {
        assertPrizeAllUpdatablePropertiesEquals(expectedPrize, getPersistedPrize(expectedPrize));
    }
}
