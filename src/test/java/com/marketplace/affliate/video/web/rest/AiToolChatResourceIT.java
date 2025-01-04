package com.marketplace.affliate.video.web.rest;

import static com.marketplace.affliate.video.domain.AiToolChatAsserts.*;
import static com.marketplace.affliate.video.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.affliate.video.IntegrationTest;
import com.marketplace.affliate.video.domain.AiToolChat;
import com.marketplace.affliate.video.domain.enumeration.ChatType;
import com.marketplace.affliate.video.repository.AiToolChatRepository;
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
 * Integration tests for the {@link AiToolChatResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AiToolChatResourceIT {

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_VIDEO_URL = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_URL = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_URL = "BBBBBBBBBB";

    private static final ChatType DEFAULT_TYPE = ChatType.UserMessage;
    private static final ChatType UPDATED_TYPE = ChatType.AiResponse;

    private static final Boolean DEFAULT_IS_FINAL_VIDEL = false;
    private static final Boolean UPDATED_IS_FINAL_VIDEL = true;

    private static final Boolean DEFAULT_IS_DOWNLOADED = false;
    private static final Boolean UPDATED_IS_DOWNLOADED = true;

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

    private static final String ENTITY_API_URL = "/api/ai-tool-chats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AiToolChatRepository aiToolChatRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAiToolChatMockMvc;

    private AiToolChat aiToolChat;

    private AiToolChat insertedAiToolChat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AiToolChat createEntity() {
        return new AiToolChat()
            .message(DEFAULT_MESSAGE)
            .videoUrl(DEFAULT_VIDEO_URL)
            .paymentUrl(DEFAULT_PAYMENT_URL)
            .type(DEFAULT_TYPE)
            .isFinalVidel(DEFAULT_IS_FINAL_VIDEL)
            .isDownloaded(DEFAULT_IS_DOWNLOADED)
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
    public static AiToolChat createUpdatedEntity() {
        return new AiToolChat()
            .message(UPDATED_MESSAGE)
            .videoUrl(UPDATED_VIDEO_URL)
            .paymentUrl(UPDATED_PAYMENT_URL)
            .type(UPDATED_TYPE)
            .isFinalVidel(UPDATED_IS_FINAL_VIDEL)
            .isDownloaded(UPDATED_IS_DOWNLOADED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
    }

    @BeforeEach
    public void initTest() {
        aiToolChat = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAiToolChat != null) {
            aiToolChatRepository.delete(insertedAiToolChat);
            insertedAiToolChat = null;
        }
    }

    @Test
    @Transactional
    void createAiToolChat() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AiToolChat
        var returnedAiToolChat = om.readValue(
            restAiToolChatMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolChat)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AiToolChat.class
        );

        // Validate the AiToolChat in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAiToolChatUpdatableFieldsEquals(returnedAiToolChat, getPersistedAiToolChat(returnedAiToolChat));

        insertedAiToolChat = returnedAiToolChat;
    }

    @Test
    @Transactional
    void createAiToolChatWithExistingId() throws Exception {
        // Create the AiToolChat with an existing ID
        aiToolChat.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAiToolChatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolChat)))
            .andExpect(status().isBadRequest());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAiToolChats() throws Exception {
        // Initialize the database
        insertedAiToolChat = aiToolChatRepository.saveAndFlush(aiToolChat);

        // Get all the aiToolChatList
        restAiToolChatMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aiToolChat.getId().intValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].videoUrl").value(hasItem(DEFAULT_VIDEO_URL)))
            .andExpect(jsonPath("$.[*].paymentUrl").value(hasItem(DEFAULT_PAYMENT_URL)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isFinalVidel").value(hasItem(DEFAULT_IS_FINAL_VIDEL)))
            .andExpect(jsonPath("$.[*].isDownloaded").value(hasItem(DEFAULT_IS_DOWNLOADED)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getAiToolChat() throws Exception {
        // Initialize the database
        insertedAiToolChat = aiToolChatRepository.saveAndFlush(aiToolChat);

        // Get the aiToolChat
        restAiToolChatMockMvc
            .perform(get(ENTITY_API_URL_ID, aiToolChat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aiToolChat.getId().intValue()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.videoUrl").value(DEFAULT_VIDEO_URL))
            .andExpect(jsonPath("$.paymentUrl").value(DEFAULT_PAYMENT_URL))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.isFinalVidel").value(DEFAULT_IS_FINAL_VIDEL))
            .andExpect(jsonPath("$.isDownloaded").value(DEFAULT_IS_DOWNLOADED))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAiToolChat() throws Exception {
        // Get the aiToolChat
        restAiToolChatMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAiToolChat() throws Exception {
        // Initialize the database
        insertedAiToolChat = aiToolChatRepository.saveAndFlush(aiToolChat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolChat
        AiToolChat updatedAiToolChat = aiToolChatRepository.findById(aiToolChat.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAiToolChat are not directly saved in db
        em.detach(updatedAiToolChat);
        updatedAiToolChat
            .message(UPDATED_MESSAGE)
            .videoUrl(UPDATED_VIDEO_URL)
            .paymentUrl(UPDATED_PAYMENT_URL)
            .type(UPDATED_TYPE)
            .isFinalVidel(UPDATED_IS_FINAL_VIDEL)
            .isDownloaded(UPDATED_IS_DOWNLOADED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolChatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAiToolChat.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAiToolChat))
            )
            .andExpect(status().isOk());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAiToolChatToMatchAllProperties(updatedAiToolChat);
    }

    @Test
    @Transactional
    void putNonExistingAiToolChat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolChat.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolChatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, aiToolChat.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolChat))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAiToolChat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolChat.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolChatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(aiToolChat))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAiToolChat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolChat.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolChatMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aiToolChat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAiToolChatWithPatch() throws Exception {
        // Initialize the database
        insertedAiToolChat = aiToolChatRepository.saveAndFlush(aiToolChat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolChat using partial update
        AiToolChat partialUpdatedAiToolChat = new AiToolChat();
        partialUpdatedAiToolChat.setId(aiToolChat.getId());

        partialUpdatedAiToolChat
            .message(UPDATED_MESSAGE)
            .videoUrl(UPDATED_VIDEO_URL)
            .paymentUrl(UPDATED_PAYMENT_URL)
            .type(UPDATED_TYPE)
            .isFinalVidel(UPDATED_IS_FINAL_VIDEL)
            .isDownloaded(UPDATED_IS_DOWNLOADED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolChatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiToolChat.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiToolChat))
            )
            .andExpect(status().isOk());

        // Validate the AiToolChat in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolChatUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAiToolChat, aiToolChat),
            getPersistedAiToolChat(aiToolChat)
        );
    }

    @Test
    @Transactional
    void fullUpdateAiToolChatWithPatch() throws Exception {
        // Initialize the database
        insertedAiToolChat = aiToolChatRepository.saveAndFlush(aiToolChat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aiToolChat using partial update
        AiToolChat partialUpdatedAiToolChat = new AiToolChat();
        partialUpdatedAiToolChat.setId(aiToolChat.getId());

        partialUpdatedAiToolChat
            .message(UPDATED_MESSAGE)
            .videoUrl(UPDATED_VIDEO_URL)
            .paymentUrl(UPDATED_PAYMENT_URL)
            .type(UPDATED_TYPE)
            .isFinalVidel(UPDATED_IS_FINAL_VIDEL)
            .isDownloaded(UPDATED_IS_DOWNLOADED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restAiToolChatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAiToolChat.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAiToolChat))
            )
            .andExpect(status().isOk());

        // Validate the AiToolChat in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAiToolChatUpdatableFieldsEquals(partialUpdatedAiToolChat, getPersistedAiToolChat(partialUpdatedAiToolChat));
    }

    @Test
    @Transactional
    void patchNonExistingAiToolChat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolChat.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAiToolChatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, aiToolChat.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aiToolChat))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAiToolChat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolChat.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolChatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aiToolChat))
            )
            .andExpect(status().isBadRequest());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAiToolChat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aiToolChat.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAiToolChatMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(aiToolChat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AiToolChat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAiToolChat() throws Exception {
        // Initialize the database
        insertedAiToolChat = aiToolChatRepository.saveAndFlush(aiToolChat);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the aiToolChat
        restAiToolChatMockMvc
            .perform(delete(ENTITY_API_URL_ID, aiToolChat.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return aiToolChatRepository.count();
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

    protected AiToolChat getPersistedAiToolChat(AiToolChat aiToolChat) {
        return aiToolChatRepository.findById(aiToolChat.getId()).orElseThrow();
    }

    protected void assertPersistedAiToolChatToMatchAllProperties(AiToolChat expectedAiToolChat) {
        assertAiToolChatAllPropertiesEquals(expectedAiToolChat, getPersistedAiToolChat(expectedAiToolChat));
    }

    protected void assertPersistedAiToolChatToMatchUpdatableProperties(AiToolChat expectedAiToolChat) {
        assertAiToolChatAllUpdatablePropertiesEquals(expectedAiToolChat, getPersistedAiToolChat(expectedAiToolChat));
    }
}
