package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.VideoUserAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.VideoUser;
import com.marketplace.affliate.video.domain.enumeration.VideoUserType;
import com.marketplace.affliate.video.repository.VideoUserRepository;
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
 * Integration tests for the {@link VideoUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VideoUserResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE = 1000000000L;
    private static final Long UPDATED_PHONE = 1000000001L;

    private static final String DEFAULT_EMAIL = "hC!>HT@0Y.I";
    private static final String UPDATED_EMAIL = "Rr&+o@@.";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final VideoUserType DEFAULT_USER_TYPE = VideoUserType.SuperAdmin;
    private static final VideoUserType UPDATED_USER_TYPE = VideoUserType.Moderator;

    private static final Boolean DEFAULT_IS_BLOCKED = false;
    private static final Boolean UPDATED_IS_BLOCKED = true;

    private static final LocalDate DEFAULT_BLOCKED_TILL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BLOCKED_TILL = LocalDate.now(ZoneId.systemDefault());

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

    private static final String ENTITY_API_URL = "/api/video-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VideoUserRepository videoUserRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVideoUserMockMvc;

    private VideoUser videoUser;

    private VideoUser insertedVideoUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoUser createEntity() {
        return new VideoUser()
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .description(DEFAULT_DESCRIPTION)
            .imageUrl(DEFAULT_IMAGE_URL)
            .userType(DEFAULT_USER_TYPE)
            .isBlocked(DEFAULT_IS_BLOCKED)
            .blockedTill(DEFAULT_BLOCKED_TILL)
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
    public static VideoUser createUpdatedEntity() {
        return new VideoUser()
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .imageUrl(UPDATED_IMAGE_URL)
            .userType(UPDATED_USER_TYPE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .blockedTill(UPDATED_BLOCKED_TILL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        videoUser = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedVideoUser != null) {
            videoUserRepository.delete(insertedVideoUser);
            insertedVideoUser = null;
        }
    }

    @Test
    @Transactional
    void createVideoUser() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the VideoUser
        var returnedVideoUser = om.readValue(
            restVideoUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoUser)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            VideoUser.class
        );

        // Validate the VideoUser in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVideoUserUpdatableFieldsEquals(returnedVideoUser, getPersistedVideoUser(returnedVideoUser));

        insertedVideoUser = returnedVideoUser;
    }

    @Test
    @Transactional
    void createVideoUserWithExistingId() throws Exception {
        // Create the VideoUser with an existing ID
        videoUser.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVideoUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoUser)))
            .andExpect(status().isBadRequest());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVideoUsers() throws Exception {
        // Initialize the database
        insertedVideoUser = videoUserRepository.saveAndFlush(videoUser);

        // Get all the videoUserList
        restVideoUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(videoUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].userType").value(hasItem(DEFAULT_USER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isBlocked").value(hasItem(DEFAULT_IS_BLOCKED)))
            .andExpect(jsonPath("$.[*].blockedTill").value(hasItem(DEFAULT_BLOCKED_TILL.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getVideoUser() throws Exception {
        // Initialize the database
        insertedVideoUser = videoUserRepository.saveAndFlush(videoUser);

        // Get the videoUser
        restVideoUserMockMvc
            .perform(get(ENTITY_API_URL_ID, videoUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(videoUser.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL))
            .andExpect(jsonPath("$.userType").value(DEFAULT_USER_TYPE.toString()))
            .andExpect(jsonPath("$.isBlocked").value(DEFAULT_IS_BLOCKED))
            .andExpect(jsonPath("$.blockedTill").value(DEFAULT_BLOCKED_TILL.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVideoUser() throws Exception {
        // Get the videoUser
        restVideoUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVideoUser() throws Exception {
        // Initialize the database
        insertedVideoUser = videoUserRepository.saveAndFlush(videoUser);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoUser
        VideoUser updatedVideoUser = videoUserRepository.findById(videoUser.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVideoUser are not directly saved in db
        em.detach(updatedVideoUser);
        updatedVideoUser
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .imageUrl(UPDATED_IMAGE_URL)
            .userType(UPDATED_USER_TYPE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .blockedTill(UPDATED_BLOCKED_TILL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVideoUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVideoUser))
            )
            .andExpect(status().isOk());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVideoUserToMatchAllProperties(updatedVideoUser);
    }

    @Test
    @Transactional
    void putNonExistingVideoUser() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoUser.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, videoUser.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVideoUser() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoUser.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(videoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVideoUser() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoUser.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoUser)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVideoUserWithPatch() throws Exception {
        // Initialize the database
        insertedVideoUser = videoUserRepository.saveAndFlush(videoUser);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoUser using partial update
        VideoUser partialUpdatedVideoUser = new VideoUser();
        partialUpdatedVideoUser.setId(videoUser.getId());

        partialUpdatedVideoUser
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .email(UPDATED_EMAIL)
            .imageUrl(UPDATED_IMAGE_URL)
            .userType(UPDATED_USER_TYPE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoUser))
            )
            .andExpect(status().isOk());

        // Validate the VideoUser in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoUserUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedVideoUser, videoUser),
            getPersistedVideoUser(videoUser)
        );
    }

    @Test
    @Transactional
    void fullUpdateVideoUserWithPatch() throws Exception {
        // Initialize the database
        insertedVideoUser = videoUserRepository.saveAndFlush(videoUser);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoUser using partial update
        VideoUser partialUpdatedVideoUser = new VideoUser();
        partialUpdatedVideoUser.setId(videoUser.getId());

        partialUpdatedVideoUser
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .imageUrl(UPDATED_IMAGE_URL)
            .userType(UPDATED_USER_TYPE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .blockedTill(UPDATED_BLOCKED_TILL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoUser))
            )
            .andExpect(status().isOk());

        // Validate the VideoUser in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoUserUpdatableFieldsEquals(partialUpdatedVideoUser, getPersistedVideoUser(partialUpdatedVideoUser));
    }

    @Test
    @Transactional
    void patchNonExistingVideoUser() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoUser.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, videoUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVideoUser() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoUser.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVideoUser() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoUser.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoUserMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(videoUser)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoUser in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVideoUser() throws Exception {
        // Initialize the database
        insertedVideoUser = videoUserRepository.saveAndFlush(videoUser);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the videoUser
        restVideoUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, videoUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return videoUserRepository.count();
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

    protected VideoUser getPersistedVideoUser(VideoUser videoUser) {
        return videoUserRepository.findById(videoUser.getId()).orElseThrow();
    }

    protected void assertPersistedVideoUserToMatchAllProperties(VideoUser expectedVideoUser) {
        assertVideoUserAllPropertiesEquals(expectedVideoUser, getPersistedVideoUser(expectedVideoUser));
    }

    protected void assertPersistedVideoUserToMatchUpdatableProperties(VideoUser expectedVideoUser) {
        assertVideoUserAllUpdatablePropertiesEquals(expectedVideoUser, getPersistedVideoUser(expectedVideoUser));
    }
}
