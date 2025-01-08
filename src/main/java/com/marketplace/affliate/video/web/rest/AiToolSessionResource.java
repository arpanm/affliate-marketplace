package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.AiToolSession;
import com.marketplace.affliate.video.repository.AiToolSessionRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.AiToolSession}.
 */
@RestController
@RequestMapping("/api/ai-tool-sessions")
@Transactional
public class AiToolSessionResource {

    private static final Logger LOG = LoggerFactory.getLogger(AiToolSessionResource.class);

    private static final String ENTITY_NAME = "aiToolSession";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AiToolSessionRepository aiToolSessionRepository;

    public AiToolSessionResource(AiToolSessionRepository aiToolSessionRepository) {
        this.aiToolSessionRepository = aiToolSessionRepository;
    }

    /**
     * {@code POST  /ai-tool-sessions} : Create a new aiToolSession.
     *
     * @param aiToolSession the aiToolSession to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aiToolSession, or with status {@code 400 (Bad Request)} if the aiToolSession has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AiToolSession> createAiToolSession(@RequestBody AiToolSession aiToolSession) throws URISyntaxException {
        LOG.debug("REST request to save AiToolSession : {}", aiToolSession);
        if (aiToolSession.getId() != null) {
            throw new BadRequestAlertException("A new aiToolSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        aiToolSession = aiToolSessionRepository.save(aiToolSession);
        return ResponseEntity.created(new URI("/api/ai-tool-sessions/" + aiToolSession.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, aiToolSession.getId().toString()))
            .body(aiToolSession);
    }

    /**
     * {@code PUT  /ai-tool-sessions/:id} : Updates an existing aiToolSession.
     *
     * @param id the id of the aiToolSession to save.
     * @param aiToolSession the aiToolSession to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiToolSession,
     * or with status {@code 400 (Bad Request)} if the aiToolSession is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aiToolSession couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AiToolSession> updateAiToolSession(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AiToolSession aiToolSession
    ) throws URISyntaxException {
        LOG.debug("REST request to update AiToolSession : {}, {}", id, aiToolSession);
        if (aiToolSession.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiToolSession.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolSessionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        aiToolSession = aiToolSessionRepository.save(aiToolSession);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiToolSession.getId().toString()))
            .body(aiToolSession);
    }

    /**
     * {@code PATCH  /ai-tool-sessions/:id} : Partial updates given fields of an existing aiToolSession, field will ignore if it is null
     *
     * @param id the id of the aiToolSession to save.
     * @param aiToolSession the aiToolSession to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiToolSession,
     * or with status {@code 400 (Bad Request)} if the aiToolSession is not valid,
     * or with status {@code 404 (Not Found)} if the aiToolSession is not found,
     * or with status {@code 500 (Internal Server Error)} if the aiToolSession couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AiToolSession> partialUpdateAiToolSession(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AiToolSession aiToolSession
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update AiToolSession partially : {}, {}", id, aiToolSession);
        if (aiToolSession.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiToolSession.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolSessionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AiToolSession> result = aiToolSessionRepository
            .findById(aiToolSession.getId())
            .map(existingAiToolSession -> {
                if (aiToolSession.getIsPaymentLinkGenerated() != null) {
                    existingAiToolSession.setIsPaymentLinkGenerated(aiToolSession.getIsPaymentLinkGenerated());
                }
                if (aiToolSession.getIsPaid() != null) {
                    existingAiToolSession.setIsPaid(aiToolSession.getIsPaid());
                }
                if (aiToolSession.getIsVideoGenerated() != null) {
                    existingAiToolSession.setIsVideoGenerated(aiToolSession.getIsVideoGenerated());
                }
                if (aiToolSession.getIsVideoDownloaded() != null) {
                    existingAiToolSession.setIsVideoDownloaded(aiToolSession.getIsVideoDownloaded());
                }
                if (aiToolSession.getIsActive() != null) {
                    existingAiToolSession.setIsActive(aiToolSession.getIsActive());
                }
                if (aiToolSession.getCreatedBy() != null) {
                    existingAiToolSession.setCreatedBy(aiToolSession.getCreatedBy());
                }
                if (aiToolSession.getCreatedOn() != null) {
                    existingAiToolSession.setCreatedOn(aiToolSession.getCreatedOn());
                }
                if (aiToolSession.getUpdatedBy() != null) {
                    existingAiToolSession.setUpdatedBy(aiToolSession.getUpdatedBy());
                }
                if (aiToolSession.getUpdatedOn() != null) {
                    existingAiToolSession.setUpdatedOn(aiToolSession.getUpdatedOn());
                }

                return existingAiToolSession;
            })
            .map(aiToolSessionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiToolSession.getId().toString())
        );
    }

    /**
     * {@code GET  /ai-tool-sessions} : get all the aiToolSessions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aiToolSessions in body.
     */
    @GetMapping("")
    public List<AiToolSession> getAllAiToolSessions() {
        LOG.debug("REST request to get all AiToolSessions");
        return aiToolSessionRepository.findAll();
    }

    /**
     * {@code GET  /ai-tool-sessions/:id} : get the "id" aiToolSession.
     *
     * @param id the id of the aiToolSession to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aiToolSession, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AiToolSession> getAiToolSession(@PathVariable("id") Long id) {
        LOG.debug("REST request to get AiToolSession : {}", id);
        Optional<AiToolSession> aiToolSession = aiToolSessionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aiToolSession);
    }

    /**
     * {@code DELETE  /ai-tool-sessions/:id} : delete the "id" aiToolSession.
     *
     * @param id the id of the aiToolSession to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAiToolSession(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete AiToolSession : {}", id);
        aiToolSessionRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
