package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.AiToolPayment;
import com.marketplace.affliate.video.repository.AiToolPaymentRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.AiToolPayment}.
 */
@RestController
@RequestMapping("/api/ai-tool-payments")
@Transactional
public class AiToolPaymentResource {

    private static final Logger LOG = LoggerFactory.getLogger(AiToolPaymentResource.class);

    private static final String ENTITY_NAME = "aiToolPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AiToolPaymentRepository aiToolPaymentRepository;

    public AiToolPaymentResource(AiToolPaymentRepository aiToolPaymentRepository) {
        this.aiToolPaymentRepository = aiToolPaymentRepository;
    }

    /**
     * {@code POST  /ai-tool-payments} : Create a new aiToolPayment.
     *
     * @param aiToolPayment the aiToolPayment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aiToolPayment, or with status {@code 400 (Bad Request)} if the aiToolPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AiToolPayment> createAiToolPayment(@RequestBody AiToolPayment aiToolPayment) throws URISyntaxException {
        LOG.debug("REST request to save AiToolPayment : {}", aiToolPayment);
        if (aiToolPayment.getId() != null) {
            throw new BadRequestAlertException("A new aiToolPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        aiToolPayment = aiToolPaymentRepository.save(aiToolPayment);
        return ResponseEntity.created(new URI("/api/ai-tool-payments/" + aiToolPayment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, aiToolPayment.getId().toString()))
            .body(aiToolPayment);
    }

    /**
     * {@code PUT  /ai-tool-payments/:id} : Updates an existing aiToolPayment.
     *
     * @param id the id of the aiToolPayment to save.
     * @param aiToolPayment the aiToolPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiToolPayment,
     * or with status {@code 400 (Bad Request)} if the aiToolPayment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aiToolPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AiToolPayment> updateAiToolPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AiToolPayment aiToolPayment
    ) throws URISyntaxException {
        LOG.debug("REST request to update AiToolPayment : {}, {}", id, aiToolPayment);
        if (aiToolPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiToolPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        aiToolPayment = aiToolPaymentRepository.save(aiToolPayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiToolPayment.getId().toString()))
            .body(aiToolPayment);
    }

    /**
     * {@code PATCH  /ai-tool-payments/:id} : Partial updates given fields of an existing aiToolPayment, field will ignore if it is null
     *
     * @param id the id of the aiToolPayment to save.
     * @param aiToolPayment the aiToolPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aiToolPayment,
     * or with status {@code 400 (Bad Request)} if the aiToolPayment is not valid,
     * or with status {@code 404 (Not Found)} if the aiToolPayment is not found,
     * or with status {@code 500 (Internal Server Error)} if the aiToolPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AiToolPayment> partialUpdateAiToolPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AiToolPayment aiToolPayment
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update AiToolPayment partially : {}, {}", id, aiToolPayment);
        if (aiToolPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, aiToolPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!aiToolPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AiToolPayment> result = aiToolPaymentRepository
            .findById(aiToolPayment.getId())
            .map(existingAiToolPayment -> {
                if (aiToolPayment.getAmount() != null) {
                    existingAiToolPayment.setAmount(aiToolPayment.getAmount());
                }
                if (aiToolPayment.getStatus() != null) {
                    existingAiToolPayment.setStatus(aiToolPayment.getStatus());
                }
                if (aiToolPayment.getPaymentLinkUrl() != null) {
                    existingAiToolPayment.setPaymentLinkUrl(aiToolPayment.getPaymentLinkUrl());
                }
                if (aiToolPayment.getPgType() != null) {
                    existingAiToolPayment.setPgType(aiToolPayment.getPgType());
                }
                if (aiToolPayment.getPgId() != null) {
                    existingAiToolPayment.setPgId(aiToolPayment.getPgId());
                }
                if (aiToolPayment.getPgStatus() != null) {
                    existingAiToolPayment.setPgStatus(aiToolPayment.getPgStatus());
                }
                if (aiToolPayment.getPgRequestJson() != null) {
                    existingAiToolPayment.setPgRequestJson(aiToolPayment.getPgRequestJson());
                }
                if (aiToolPayment.getPgResponseJson() != null) {
                    existingAiToolPayment.setPgResponseJson(aiToolPayment.getPgResponseJson());
                }
                if (aiToolPayment.getPgRequestTimeStamp() != null) {
                    existingAiToolPayment.setPgRequestTimeStamp(aiToolPayment.getPgRequestTimeStamp());
                }
                if (aiToolPayment.getPgResponseTimeStamp() != null) {
                    existingAiToolPayment.setPgResponseTimeStamp(aiToolPayment.getPgResponseTimeStamp());
                }
                if (aiToolPayment.getIsActive() != null) {
                    existingAiToolPayment.setIsActive(aiToolPayment.getIsActive());
                }
                if (aiToolPayment.getCreatedBy() != null) {
                    existingAiToolPayment.setCreatedBy(aiToolPayment.getCreatedBy());
                }
                if (aiToolPayment.getCreatedOn() != null) {
                    existingAiToolPayment.setCreatedOn(aiToolPayment.getCreatedOn());
                }
                if (aiToolPayment.getUpdatedBy() != null) {
                    existingAiToolPayment.setUpdatedBy(aiToolPayment.getUpdatedBy());
                }
                if (aiToolPayment.getUpdatedOn() != null) {
                    existingAiToolPayment.setUpdatedOn(aiToolPayment.getUpdatedOn());
                }

                return existingAiToolPayment;
            })
            .map(aiToolPaymentRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aiToolPayment.getId().toString())
        );
    }

    /**
     * {@code GET  /ai-tool-payments} : get all the aiToolPayments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aiToolPayments in body.
     */
    @GetMapping("")
    public List<AiToolPayment> getAllAiToolPayments() {
        LOG.debug("REST request to get all AiToolPayments");
        return aiToolPaymentRepository.findAll();
    }

    /**
     * {@code GET  /ai-tool-payments/:id} : get the "id" aiToolPayment.
     *
     * @param id the id of the aiToolPayment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aiToolPayment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AiToolPayment> getAiToolPayment(@PathVariable("id") Long id) {
        LOG.debug("REST request to get AiToolPayment : {}", id);
        Optional<AiToolPayment> aiToolPayment = aiToolPaymentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aiToolPayment);
    }

    /**
     * {@code DELETE  /ai-tool-payments/:id} : delete the "id" aiToolPayment.
     *
     * @param id the id of the aiToolPayment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAiToolPayment(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete AiToolPayment : {}", id);
        aiToolPaymentRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
