package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.AiToolChat;
import com.marketplace.affliate.video.repository.AiToolChatRepository;
import com.marketplace.affliate.video.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.marketplace.affliate.video.domain.AiToolChat}.
 */
@RestController
@RequestMapping("/api/ai-tool-chats")
@Transactional
public class AiToolChatResource {

    private static final Logger LOG = LoggerFactory.getLogger(AiToolChatResource.class);

    private static final String ENTITY_NAME = "aiToolChat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AiToolChatRepository aiToolChatRepository;

    public AiToolChatResource(AiToolChatRepository aiToolChatRepository) {
        this.aiToolChatRepository = aiToolChatRepository;
    }

    /**
     * {@code POST  /ai-tool-chats} : Create a new aiToolChat.
     *
     * @param aiToolChat the aiToolChat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aiToolChat, or with status {@code 400 (Bad Request)} if the aiToolChat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AiToolChat> createAiToolChat(@RequestBody AiToolChat aiToolChat) throws URISyntaxException {
        LOG.debug("REST request to save AiToolChat : {}", aiToolChat);
        if (aiToolChat.getId() != null) {
            throw new BadRequestAlertException("A new aiToolChat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        aiToolChat = aiToolChatRepository.save(aiToolChat);
        return ResponseEntity.created(new URI("/api/ai-tool-chats/" + aiToolChat.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, aiToolChat.getId().toString()))
            .body(aiToolChat);
    }

    /**
     * {@code PUT  /ai-tool-chats/:id} : Updates an existing aiToolChat.
     *
     * @param id the id of the aiToolChat to save.
     * @param aiToolChat the aiToolChat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiToolChat,
     * or with status {@code 400 (Bad Request)} if the aiToolChat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aiToolChat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AiToolChat> updateAiToolChat(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AiToolChat aiToolChat
    ) throws URISyntaxException {
        LOG.debug("REST request to update AiToolChat : {}, {}", id, aiToolChat);
        if (aiToolChat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiToolChat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolChatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        aiToolChat = aiToolChatRepository.save(aiToolChat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiToolChat.getId().toString()))
            .body(aiToolChat);
    }

    /**
     * {@code PATCH  /ai-tool-chats/:id} : Partial updates given fields of an existing aiToolChat, field will ignore if it is null
     *
     * @param id the id of the aiToolChat to save.
     * @param aiToolChat the aiToolChat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiToolChat,
     * or with status {@code 400 (Bad Request)} if the aiToolChat is not valid,
     * or with status {@code 404 (Not Found)} if the aiToolChat is not found,
     * or with status {@code 500 (Internal Server Error)} if the aiToolChat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AiToolChat> partialUpdateAiToolChat(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AiToolChat aiToolChat
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update AiToolChat partially : {}, {}", id, aiToolChat);
        if (aiToolChat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiToolChat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolChatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AiToolChat> result = aiToolChatRepository
            .findById(aiToolChat.getId())
            .map(existingAiToolChat -> {
                if (aiToolChat.getMessage() != null) {
                    existingAiToolChat.setMessage(aiToolChat.getMessage());
                }
                if (aiToolChat.getVideoUrl() != null) {
                    existingAiToolChat.setVideoUrl(aiToolChat.getVideoUrl());
                }
                if (aiToolChat.getPaymentUrl() != null) {
                    existingAiToolChat.setPaymentUrl(aiToolChat.getPaymentUrl());
                }
                if (aiToolChat.getType() != null) {
                    existingAiToolChat.setType(aiToolChat.getType());
                }
                if (aiToolChat.getIsFinalVideo() != null) {
                    existingAiToolChat.setIsFinalVideo(aiToolChat.getIsFinalVideo());
                }
                if (aiToolChat.getIsDownloaded() != null) {
                    existingAiToolChat.setIsDownloaded(aiToolChat.getIsDownloaded());
                }
                if (aiToolChat.getIsActive() != null) {
                    existingAiToolChat.setIsActive(aiToolChat.getIsActive());
                }
                if (aiToolChat.getCreatedBy() != null) {
                    existingAiToolChat.setCreatedBy(aiToolChat.getCreatedBy());
                }
                if (aiToolChat.getCreatedOn() != null) {
                    existingAiToolChat.setCreatedOn(aiToolChat.getCreatedOn());
                }
                if (aiToolChat.getUpdatedBy() != null) {
                    existingAiToolChat.setUpdatedBy(aiToolChat.getUpdatedBy());
                }
                if (aiToolChat.getUpdatedOn() != null) {
                    existingAiToolChat.setUpdatedOn(aiToolChat.getUpdatedOn());
                }

                return existingAiToolChat;
            })
            .map(aiToolChatRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiToolChat.getId().toString())
        );
    }

    /**
     * {@code GET  /ai-tool-chats} : get all the aiToolChats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aiToolChats in body.
     */
    @GetMapping("")
    public List<AiToolChat> getAllAiToolChats() {
        LOG.debug("REST request to get all AiToolChats");
        return aiToolChatRepository.findAll();
    }

    /**
     * {@code GET  /ai-tool-chats/:id} : get the "id" aiToolChat.
     *
     * @param id the id of the aiToolChat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aiToolChat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AiToolChat> getAiToolChat(@PathVariable("id") Long id) {
        LOG.debug("REST request to get AiToolChat : {}", id);
        Optional<AiToolChat> aiToolChat = aiToolChatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aiToolChat);
    }

    /**
     * {@code DELETE  /ai-tool-chats/:id} : delete the "id" aiToolChat.
     *
     * @param id the id of the aiToolChat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAiToolChat(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete AiToolChat : {}", id);
        aiToolChatRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
