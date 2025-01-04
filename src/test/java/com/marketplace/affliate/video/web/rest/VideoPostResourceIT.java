package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.VideoPostAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.VideoPost;
import com.marketplace.affliate.video.domain.enumeration.UrlType;
import com.marketplace.affliate.video.repository.VideoPostRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VideoPostResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class VideoPostResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final UrlType DEFAULT_URL_TYPE = UrlType.YouTube;
    private static final UrlType UPDATED_URL_TYPE = UrlType.Instagram;

    private static final Boolean DEFAULT_IS_AI_GENERATED = false;
    private static final Boolean UPDATED_IS_AI_GENERATED = true;

    private static final Boolean DEFAULT_IS_PREMIUM = false;
    private static final Boolean UPDATED_IS_PREMIUM = true;

    private static final Boolean DEFAULT_IS_BLOCKED = false;
    private static final Boolean UPDATED_IS_BLOCKED = true;

    private static final Boolean DEFAULT_IS_MODERATED = false;
    private static final Boolean UPDATED_IS_MODERATED = true;

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

    private static final String ENTITY_API_URL = "/api/video-posts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VideoPostRepository videoPostRepository;

    @Mock
    private VideoPostRepository videoPostRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVideoPostMockMvc;

    private VideoPost videoPost;

    private VideoPost insertedVideoPost;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoPost createEntity() {
        return new VideoPost()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .url(DEFAULT_URL)
            .urlType(DEFAULT_URL_TYPE)
            .isAIGenerated(DEFAULT_IS_AI_GENERATED)
            .isPremium(DEFAULT_IS_PREMIUM)
            .isBlocked(DEFAULT_IS_BLOCKED)
            .isModerated(DEFAULT_IS_MODERATED)
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
    public static VideoPost createUpdatedEntity() {
        return new VideoPost()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .url(UPDATED_URL)
            .urlType(UPDATED_URL_TYPE)
            .isAIGenerated(UPDATED_IS_AI_GENERATED)
            .isPremium(UPDATED_IS_PREMIUM)
            .isBlocked(UPDATED_IS_BLOCKED)
            .isModerated(UPDATED_IS_MODERATED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        videoPost = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedVideoPost != null) {
            videoPostRepository.delete(insertedVideoPost);
            insertedVideoPost = null;
        }
    }

    @Test
    @Transactional
    void createVideoPost() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the VideoPost
        var returnedVideoPost = om.readValue(
            restVideoPostMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoPost)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            VideoPost.class
        );

        // Validate the VideoPost in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVideoPostUpdatableFieldsEquals(returnedVideoPost, getPersistedVideoPost(returnedVideoPost));

        insertedVideoPost = returnedVideoPost;
    }

    @Test
    @Transactional
    void createVideoPostWithExistingId() throws Exception {
        // Create the VideoPost with an existing ID
        videoPost.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVideoPostMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoPost)))
            .andExpect(status().isBadRequest());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVideoPosts() throws Exception {
        // Initialize the database
        insertedVideoPost = videoPostRepository.saveAndFlush(videoPost);

        // Get all the videoPostList
        restVideoPostMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(videoPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].urlType").value(hasItem(DEFAULT_URL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isAIGenerated").value(hasItem(DEFAULT_IS_AI_GENERATED)))
            .andExpect(jsonPath("$.[*].isPremium").value(hasItem(DEFAULT_IS_PREMIUM)))
            .andExpect(jsonPath("$.[*].isBlocked").value(hasItem(DEFAULT_IS_BLOCKED)))
            .andExpect(jsonPath("$.[*].isModerated").value(hasItem(DEFAULT_IS_MODERATED)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllVideoPostsWithEagerRelationshipsIsEnabled() throws Exception {
        when(videoPostRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restVideoPostMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(videoPostRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllVideoPostsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(videoPostRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restVideoPostMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(videoPostRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getVideoPost() throws Exception {
        // Initialize the database
        insertedVideoPost = videoPostRepository.saveAndFlush(videoPost);

        // Get the videoPost
        restVideoPostMockMvc
            .perform(get(ENTITY_API_URL_ID, videoPost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(videoPost.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.urlType").value(DEFAULT_URL_TYPE.toString()))
            .andExpect(jsonPath("$.isAIGenerated").value(DEFAULT_IS_AI_GENERATED))
            .andExpect(jsonPath("$.isPremium").value(DEFAULT_IS_PREMIUM))
            .andExpect(jsonPath("$.isBlocked").value(DEFAULT_IS_BLOCKED))
            .andExpect(jsonPath("$.isModerated").value(DEFAULT_IS_MODERATED))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVideoPost() throws Exception {
        // Get the videoPost
        restVideoPostMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVideoPost() throws Exception {
        // Initialize the database
        insertedVideoPost = videoPostRepository.saveAndFlush(videoPost);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoPost
        VideoPost updatedVideoPost = videoPostRepository.findById(videoPost.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVideoPost are not directly saved in db
        em.detach(updatedVideoPost);
        updatedVideoPost
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .url(UPDATED_URL)
            .urlType(UPDATED_URL_TYPE)
            .isAIGenerated(UPDATED_IS_AI_GENERATED)
            .isPremium(UPDATED_IS_PREMIUM)
            .isBlocked(UPDATED_IS_BLOCKED)
            .isModerated(UPDATED_IS_MODERATED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoPostMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVideoPost.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVideoPost))
            )
            .andExpect(status().isOk());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVideoPostToMatchAllProperties(updatedVideoPost);
    }

    @Test
    @Transactional
    void putNonExistingVideoPost() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPost.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoPostMockMvc
            .perform(
                put(ENTITY_API_URL_ID, videoPost.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoPost))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVideoPost() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPost.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(videoPost))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVideoPost() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPost.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(videoPost)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVideoPostWithPatch() throws Exception {
        // Initialize the database
        insertedVideoPost = videoPostRepository.saveAndFlush(videoPost);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoPost using partial update
        VideoPost partialUpdatedVideoPost = new VideoPost();
        partialUpdatedVideoPost.setId(videoPost.getId());

        partialUpdatedVideoPost
            .title(UPDATED_TITLE)
            .urlType(UPDATED_URL_TYPE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoPostMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoPost.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoPost))
            )
            .andExpect(status().isOk());

        // Validate the VideoPost in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoPostUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedVideoPost, videoPost),
            getPersistedVideoPost(videoPost)
        );
    }

    @Test
    @Transactional
    void fullUpdateVideoPostWithPatch() throws Exception {
        // Initialize the database
        insertedVideoPost = videoPostRepository.saveAndFlush(videoPost);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the videoPost using partial update
        VideoPost partialUpdatedVideoPost = new VideoPost();
        partialUpdatedVideoPost.setId(videoPost.getId());

        partialUpdatedVideoPost
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .url(UPDATED_URL)
            .urlType(UPDATED_URL_TYPE)
            .isAIGenerated(UPDATED_IS_AI_GENERATED)
            .isPremium(UPDATED_IS_PREMIUM)
            .isBlocked(UPDATED_IS_BLOCKED)
            .isModerated(UPDATED_IS_MODERATED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restVideoPostMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideoPost.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVideoPost))
            )
            .andExpect(status().isOk());

        // Validate the VideoPost in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVideoPostUpdatableFieldsEquals(partialUpdatedVideoPost, getPersistedVideoPost(partialUpdatedVideoPost));
    }

    @Test
    @Transactional
    void patchNonExistingVideoPost() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPost.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoPostMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, videoPost.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoPost))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVideoPost() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPost.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(videoPost))
            )
            .andExpect(status().isBadRequest());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVideoPost() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        videoPost.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoPostMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(videoPost)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the VideoPost in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVideoPost() throws Exception {
        // Initialize the database
        insertedVideoPost = videoPostRepository.saveAndFlush(videoPost);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the videoPost
        restVideoPostMockMvc
            .perform(delete(ENTITY_API_URL_ID, videoPost.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return videoPostRepository.count();
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

    protected VideoPost getPersistedVideoPost(VideoPost videoPost) {
        return videoPostRepository.findById(videoPost.getId()).orElseThrow();
    }

    protected void assertPersistedVideoPostToMatchAllProperties(VideoPost expectedVideoPost) {
        assertVideoPostAllPropertiesEquals(expectedVideoPost, getPersistedVideoPost(expectedVideoPost));
    }

    protected void assertPersistedVideoPostToMatchUpdatableProperties(VideoPost expectedVideoPost) {
        assertVideoPostAllUpdatablePropertiesEquals(expectedVideoPost, getPersistedVideoPost(expectedVideoPost));
    }
}
