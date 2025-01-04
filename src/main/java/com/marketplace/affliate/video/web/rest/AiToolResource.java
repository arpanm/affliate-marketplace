package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.AiTool;
import com.marketplace.affliate.video.repository.AiToolRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.AiTool}.
 */
@RestController
@RequestMapping("/api/ai-tools")
@Transactional
public class AiToolResource {

    private static final Logger LOG = LoggerFactory.getLogger(AiToolResource.class);

    private static final String ENTITY_NAME = "aiTool";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AiToolRepository aiToolRepository;

    public AiToolResource(AiToolRepository aiToolRepository) {
        this.aiToolRepository = aiToolRepository;
    }

    /**
     * {@code POST  /ai-tools} : Create a new aiTool.
     *
     * @param aiTool the aiTool to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aiTool, or with status {@code 400 (Bad Request)} if the aiTool has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AiTool> createAiTool(@RequestBody AiTool aiTool) throws URISyntaxException {
        LOG.debug("REST request to save AiTool : {}", aiTool);
        if (aiTool.getId() != null) {
            throw new BadRequestAlertException("A new aiTool cannot already have an ID", ENTITY_NAME, "idexists");
        }
        aiTool = aiToolRepository.save(aiTool);
        return ResponseEntity.created(new URI("/api/ai-tools/" + aiTool.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, aiTool.getId().toString()))
            .body(aiTool);
    }

    /**
     * {@code PUT  /ai-tools/:id} : Updates an existing aiTool.
     *
     * @param id the id of the aiTool to save.
     * @param aiTool the aiTool to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiTool,
     * or with status {@code 400 (Bad Request)} if the aiTool is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aiTool couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AiTool> updateAiTool(@PathVariable(value = "id", required = false) final Long id, @RequestBody AiTool aiTool)
        throws URISyntaxException {
        LOG.debug("REST request to update AiTool : {}, {}", id, aiTool);
        if (aiTool.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiTool.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        aiTool = aiToolRepository.save(aiTool);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiTool.getId().toString()))
            .body(aiTool);
    }

    /**
     * {@code PATCH  /ai-tools/:id} : Partial updates given fields of an existing aiTool, field will ignore if it is null
     *
     * @param id the id of the aiTool to save.
     * @param aiTool the aiTool to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiTool,
     * or with status {@code 400 (Bad Request)} if the aiTool is not valid,
     * or with status {@code 404 (Not Found)} if the aiTool is not found,
     * or with status {@code 500 (Internal Server Error)} if the aiTool couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AiTool> partialUpdateAiTool(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AiTool aiTool
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update AiTool partially : {}, {}", id, aiTool);
        if (aiTool.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiTool.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AiTool> result = aiToolRepository
            .findById(aiTool.getId())
            .map(existingAiTool -> {
                if (aiTool.getName() != null) {
                    existingAiTool.setName(aiTool.getName());
                }
                if (aiTool.getDescription() != null) {
                    existingAiTool.setDescription(aiTool.getDescription());
                }
                if (aiTool.getVendor() != null) {
                    existingAiTool.setVendor(aiTool.getVendor());
                }
                if (aiTool.getLinkUrl() != null) {
                    existingAiTool.setLinkUrl(aiTool.getLinkUrl());
                }
                if (aiTool.getIsActive() != null) {
                    existingAiTool.setIsActive(aiTool.getIsActive());
                }
                if (aiTool.getCreatedBy() != null) {
                    existingAiTool.setCreatedBy(aiTool.getCreatedBy());
                }
                if (aiTool.getCreatedOn() != null) {
                    existingAiTool.setCreatedOn(aiTool.getCreatedOn());
                }
                if (aiTool.getUpdatedBy() != null) {
                    existingAiTool.setUpdatedBy(aiTool.getUpdatedBy());
                }
                if (aiTool.getUpdatedOn() != null) {
                    existingAiTool.setUpdatedOn(aiTool.getUpdatedOn());
                }

                return existingAiTool;
            })
            .map(aiToolRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiTool.getId().toString())
        );
    }

    /**
     * {@code GET  /ai-tools} : get all the aiTools.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aiTools in body.
     */
    @GetMapping("")
    public List<AiTool> getAllAiTools() {
        LOG.debug("REST request to get all AiTools");
        return aiToolRepository.findAll();
    }

    /**
     * {@code GET  /ai-tools/:id} : get the "id" aiTool.
     *
     * @param id the id of the aiTool to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aiTool, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AiTool> getAiTool(@PathVariable("id") Long id) {
        LOG.debug("REST request to get AiTool : {}", id);
        Optional<AiTool> aiTool = aiToolRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aiTool);
    }

    /**
     * {@code DELETE  /ai-tools/:id} : delete the "id" aiTool.
     *
     * @param id the id of the aiTool to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAiTool(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete AiTool : {}", id);
        aiToolRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
