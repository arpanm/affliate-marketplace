package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.SponsorAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.Sponsor;
import com.marketplace.affliate.video.repository.SponsorRepository;
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
 * Integration tests for the {@link SponsorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SponsorResourceIT {

    private static final String DEFAULT_SPONSOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_BANNER_1_URL = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_BANNER_1_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_BANNER_2_URL = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_BANNER_2_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_BANNER_3_URL = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_BANNER_3_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_EXTERNAL_URL = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_EXTERNAL_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_LOGO_URL = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_LOGO_URL = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/sponsors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSponsorMockMvc;

    private Sponsor sponsor;

    private Sponsor insertedSponsor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sponsor createEntity() {
        return new Sponsor()
            .sponsorName(DEFAULT_SPONSOR_NAME)
            .sponsorDescription(DEFAULT_SPONSOR_DESCRIPTION)
            .sponsorBanner1Url(DEFAULT_SPONSOR_BANNER_1_URL)
            .sponsorBanner2Url(DEFAULT_SPONSOR_BANNER_2_URL)
            .sponsorBanner3Url(DEFAULT_SPONSOR_BANNER_3_URL)
            .sponsorExternalUrl(DEFAULT_SPONSOR_EXTERNAL_URL)
            .sponsorLogoUrl(DEFAULT_SPONSOR_LOGO_URL)
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
    public static Sponsor createUpdatedEntity() {
        return new Sponsor()
            .sponsorName(UPDATED_SPONSOR_NAME)
            .sponsorDescription(UPDATED_SPONSOR_DESCRIPTION)
            .sponsorBanner1Url(UPDATED_SPONSOR_BANNER_1_URL)
            .sponsorBanner2Url(UPDATED_SPONSOR_BANNER_2_URL)
            .sponsorBanner3Url(UPDATED_SPONSOR_BANNER_3_URL)
            .sponsorExternalUrl(UPDATED_SPONSOR_EXTERNAL_URL)
            .sponsorLogoUrl(UPDATED_SPONSOR_LOGO_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        sponsor = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedSponsor != null) {
            sponsorRepository.delete(insertedSponsor);
            insertedSponsor = null;
        }
    }

    @Test
    @Transactional
    void createSponsor() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Sponsor
        var returnedSponsor = om.readValue(
            restSponsorMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sponsor)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Sponsor.class
        );

        // Validate the Sponsor in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSponsorUpdatableFieldsEquals(returnedSponsor, getPersistedSponsor(returnedSponsor));

        insertedSponsor = returnedSponsor;
    }

    @Test
    @Transactional
    void createSponsorWithExistingId() throws Exception {
        // Create the Sponsor with an existing ID
        sponsor.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSponsorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sponsor)))
            .andExpect(status().isBadRequest());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSponsors() throws Exception {
        // Initialize the database
        insertedSponsor = sponsorRepository.saveAndFlush(sponsor);

        // Get all the sponsorList
        restSponsorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sponsor.getId().intValue())))
            .andExpect(jsonPath("$.[*].sponsorName").value(hasItem(DEFAULT_SPONSOR_NAME)))
            .andExpect(jsonPath("$.[*].sponsorDescription").value(hasItem(DEFAULT_SPONSOR_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].sponsorBanner1Url").value(hasItem(DEFAULT_SPONSOR_BANNER_1_URL)))
            .andExpect(jsonPath("$.[*].sponsorBanner2Url").value(hasItem(DEFAULT_SPONSOR_BANNER_2_URL)))
            .andExpect(jsonPath("$.[*].sponsorBanner3Url").value(hasItem(DEFAULT_SPONSOR_BANNER_3_URL)))
            .andExpect(jsonPath("$.[*].sponsorExternalUrl").value(hasItem(DEFAULT_SPONSOR_EXTERNAL_URL)))
            .andExpect(jsonPath("$.[*].sponsorLogoUrl").value(hasItem(DEFAULT_SPONSOR_LOGO_URL)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getSponsor() throws Exception {
        // Initialize the database
        insertedSponsor = sponsorRepository.saveAndFlush(sponsor);

        // Get the sponsor
        restSponsorMockMvc
            .perform(get(ENTITY_API_URL_ID, sponsor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sponsor.getId().intValue()))
            .andExpect(jsonPath("$.sponsorName").value(DEFAULT_SPONSOR_NAME))
            .andExpect(jsonPath("$.sponsorDescription").value(DEFAULT_SPONSOR_DESCRIPTION))
            .andExpect(jsonPath("$.sponsorBanner1Url").value(DEFAULT_SPONSOR_BANNER_1_URL))
            .andExpect(jsonPath("$.sponsorBanner2Url").value(DEFAULT_SPONSOR_BANNER_2_URL))
            .andExpect(jsonPath("$.sponsorBanner3Url").value(DEFAULT_SPONSOR_BANNER_3_URL))
            .andExpect(jsonPath("$.sponsorExternalUrl").value(DEFAULT_SPONSOR_EXTERNAL_URL))
            .andExpect(jsonPath("$.sponsorLogoUrl").value(DEFAULT_SPONSOR_LOGO_URL))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSponsor() throws Exception {
        // Get the sponsor
        restSponsorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSponsor() throws Exception {
        // Initialize the database
        insertedSponsor = sponsorRepository.saveAndFlush(sponsor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sponsor
        Sponsor updatedSponsor = sponsorRepository.findById(sponsor.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSponsor are not directly saved in db
        em.detach(updatedSponsor);
        updatedSponsor
            .sponsorName(UPDATED_SPONSOR_NAME)
            .sponsorDescription(UPDATED_SPONSOR_DESCRIPTION)
            .sponsorBanner1Url(UPDATED_SPONSOR_BANNER_1_URL)
            .sponsorBanner2Url(UPDATED_SPONSOR_BANNER_2_URL)
            .sponsorBanner3Url(UPDATED_SPONSOR_BANNER_3_URL)
            .sponsorExternalUrl(UPDATED_SPONSOR_EXTERNAL_URL)
            .sponsorLogoUrl(UPDATED_SPONSOR_LOGO_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restSponsorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSponsor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSponsor))
            )
            .andExpect(status().isOk());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSponsorToMatchAllProperties(updatedSponsor);
    }

    @Test
    @Transactional
    void putNonExistingSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sponsor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSponsorMockMvc
            .perform(put(ENTITY_API_URL_ID, sponsor.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sponsor)))
            .andExpect(status().isBadRequest());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSponsorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(sponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSponsorMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sponsor)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSponsorWithPatch() throws Exception {
        // Initialize the database
        insertedSponsor = sponsorRepository.saveAndFlush(sponsor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sponsor using partial update
        Sponsor partialUpdatedSponsor = new Sponsor();
        partialUpdatedSponsor.setId(sponsor.getId());

        partialUpdatedSponsor
            .sponsorBanner1Url(UPDATED_SPONSOR_BANNER_1_URL)
            .sponsorBanner2Url(UPDATED_SPONSOR_BANNER_2_URL)
            .sponsorBanner3Url(UPDATED_SPONSOR_BANNER_3_URL)
            .sponsorExternalUrl(UPDATED_SPONSOR_EXTERNAL_URL)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSponsor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSponsor))
            )
            .andExpect(status().isOk());

        // Validate the Sponsor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSponsorUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedSponsor, sponsor), getPersistedSponsor(sponsor));
    }

    @Test
    @Transactional
    void fullUpdateSponsorWithPatch() throws Exception {
        // Initialize the database
        insertedSponsor = sponsorRepository.saveAndFlush(sponsor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sponsor using partial update
        Sponsor partialUpdatedSponsor = new Sponsor();
        partialUpdatedSponsor.setId(sponsor.getId());

        partialUpdatedSponsor
            .sponsorName(UPDATED_SPONSOR_NAME)
            .sponsorDescription(UPDATED_SPONSOR_DESCRIPTION)
            .sponsorBanner1Url(UPDATED_SPONSOR_BANNER_1_URL)
            .sponsorBanner2Url(UPDATED_SPONSOR_BANNER_2_URL)
            .sponsorBanner3Url(UPDATED_SPONSOR_BANNER_3_URL)
            .sponsorExternalUrl(UPDATED_SPONSOR_EXTERNAL_URL)
            .sponsorLogoUrl(UPDATED_SPONSOR_LOGO_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSponsor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSponsor))
            )
            .andExpect(status().isOk());

        // Validate the Sponsor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSponsorUpdatableFieldsEquals(partialUpdatedSponsor, getPersistedSponsor(partialUpdatedSponsor));
    }

    @Test
    @Transactional
    void patchNonExistingSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sponsor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sponsor.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(sponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSponsorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(sponsor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSponsor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sponsor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSponsorMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(sponsor)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sponsor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSponsor() throws Exception {
        // Initialize the database
        insertedSponsor = sponsorRepository.saveAndFlush(sponsor);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the sponsor
        restSponsorMockMvc
            .perform(delete(ENTITY_API_URL_ID, sponsor.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return sponsorRepository.count();
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

    protected Sponsor getPersistedSponsor(Sponsor sponsor) {
        return sponsorRepository.findById(sponsor.getId()).orElseThrow();
    }

    protected void assertPersistedSponsorToMatchAllProperties(Sponsor expectedSponsor) {
        assertSponsorAllPropertiesEquals(expectedSponsor, getPersistedSponsor(expectedSponsor));
    }

    protected void assertPersistedSponsorToMatchUpdatableProperties(Sponsor expectedSponsor) {
        assertSponsorAllUpdatablePropertiesEquals(expectedSponsor, getPersistedSponsor(expectedSponsor));
    }
}
