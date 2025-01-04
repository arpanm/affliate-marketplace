package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsor;
import com.marketplace.affliate.video.repository.CompetitionPaymentFromSponsorRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsor}.
 */
@RestController
@RequestMapping("/api/competition-payment-from-sponsors")
@Transactional
public class CompetitionPaymentFromSponsorResource {

    private static final Logger LOG = LoggerFactory.getLogger(CompetitionPaymentFromSponsorResource.class);

    private static final String ENTITY_NAME = "competitionPaymentFromSponsor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompetitionPaymentFromSponsorRepository competitionPaymentFromSponsorRepository;

    public CompetitionPaymentFromSponsorResource(CompetitionPaymentFromSponsorRepository competitionPaymentFromSponsorRepository) {
        this.competitionPaymentFromSponsorRepository = competitionPaymentFromSponsorRepository;
    }

    /**
     * {@code POST  /competition-payment-from-sponsors} : Create a new competitionPaymentFromSponsor.
     *
     * @param competitionPaymentFromSponsor the competitionPaymentFromSponsor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new competitionPaymentFromSponsor, or with status {@code 400 (Bad Request)} if the competitionPaymentFromSponsor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CompetitionPaymentFromSponsor> createCompetitionPaymentFromSponsor(
        @RequestBody CompetitionPaymentFromSponsor competitionPaymentFromSponsor
    ) throws URISyntaxException {
        LOG.debug("REST request to save CompetitionPaymentFromSponsor : {}", competitionPaymentFromSponsor);
        if (competitionPaymentFromSponsor.getId() != null) {
            throw new BadRequestAlertException("A new competitionPaymentFromSponsor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        competitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.save(competitionPaymentFromSponsor);
        return ResponseEntity.created(new URI("/api/competition-payment-from-sponsors/" + competitionPaymentFromSponsor.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, competitionPaymentFromSponsor.getId().toString())
            )
            .body(competitionPaymentFromSponsor);
    }

    /**
     * {@code PUT  /competition-payment-from-sponsors/:id} : Updates an existing competitionPaymentFromSponsor.
     *
     * @param id the id of the competitionPaymentFromSponsor to save.
     * @param competitionPaymentFromSponsor the competitionPaymentFromSponsor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competitionPaymentFromSponsor,
     * or with status {@code 400 (Bad Request)} if the competitionPaymentFromSponsor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the competitionPaymentFromSponsor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompetitionPaymentFromSponsor> updateCompetitionPaymentFromSponsor(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CompetitionPaymentFromSponsor competitionPaymentFromSponsor
    ) throws URISyntaxException {
        LOG.debug("REST request to update CompetitionPaymentFromSponsor : {}, {}", id, competitionPaymentFromSponsor);
        if (competitionPaymentFromSponsor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competitionPaymentFromSponsor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionPaymentFromSponsorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        competitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.save(competitionPaymentFromSponsor);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competitionPaymentFromSponsor.getId().toString())
            )
            .body(competitionPaymentFromSponsor);
    }

    /**
     * {@code PATCH  /competition-payment-from-sponsors/:id} : Partial updates given fields of an existing competitionPaymentFromSponsor, field will ignore if it is null
     *
     * @param id the id of the competitionPaymentFromSponsor to save.
     * @param competitionPaymentFromSponsor the competitionPaymentFromSponsor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competitionPaymentFromSponsor,
     * or with status {@code 400 (Bad Request)} if the competitionPaymentFromSponsor is not valid,
     * or with status {@code 404 (Not Found)} if the competitionPaymentFromSponsor is not found,
     * or with status {@code 500 (Internal Server Error)} if the competitionPaymentFromSponsor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CompetitionPaymentFromSponsor> partialUpdateCompetitionPaymentFromSponsor(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CompetitionPaymentFromSponsor competitionPaymentFromSponsor
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CompetitionPaymentFromSponsor partially : {}, {}", id, competitionPaymentFromSponsor);
        if (competitionPaymentFromSponsor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competitionPaymentFromSponsor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionPaymentFromSponsorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CompetitionPaymentFromSponsor> result = competitionPaymentFromSponsorRepository
            .findById(competitionPaymentFromSponsor.getId())
            .map(existingCompetitionPaymentFromSponsor -> {
                if (competitionPaymentFromSponsor.getAmount() != null) {
                    existingCompetitionPaymentFromSponsor.setAmount(competitionPaymentFromSponsor.getAmount());
                }
                if (competitionPaymentFromSponsor.getTransactionId() != null) {
                    existingCompetitionPaymentFromSponsor.setTransactionId(competitionPaymentFromSponsor.getTransactionId());
                }
                if (competitionPaymentFromSponsor.getTransactionScreenshotUrl() != null) {
                    existingCompetitionPaymentFromSponsor.setTransactionScreenshotUrl(
                        competitionPaymentFromSponsor.getTransactionScreenshotUrl()
                    );
                }
                if (competitionPaymentFromSponsor.getTransactionDate() != null) {
                    existingCompetitionPaymentFromSponsor.setTransactionDate(competitionPaymentFromSponsor.getTransactionDate());
                }
                if (competitionPaymentFromSponsor.getTransactionStatus() != null) {
                    existingCompetitionPaymentFromSponsor.setTransactionStatus(competitionPaymentFromSponsor.getTransactionStatus());
                }
                if (competitionPaymentFromSponsor.getPaidBy() != null) {
                    existingCompetitionPaymentFromSponsor.setPaidBy(competitionPaymentFromSponsor.getPaidBy());
                }
                if (competitionPaymentFromSponsor.getPaymentReceiptUrl() != null) {
                    existingCompetitionPaymentFromSponsor.setPaymentReceiptUrl(competitionPaymentFromSponsor.getPaymentReceiptUrl());
                }
                if (competitionPaymentFromSponsor.getIsActive() != null) {
                    existingCompetitionPaymentFromSponsor.setIsActive(competitionPaymentFromSponsor.getIsActive());
                }
                if (competitionPaymentFromSponsor.getCreatedBy() != null) {
                    existingCompetitionPaymentFromSponsor.setCreatedBy(competitionPaymentFromSponsor.getCreatedBy());
                }
                if (competitionPaymentFromSponsor.getCreatedOn() != null) {
                    existingCompetitionPaymentFromSponsor.setCreatedOn(competitionPaymentFromSponsor.getCreatedOn());
                }
                if (competitionPaymentFromSponsor.getUpdatedBy() != null) {
                    existingCompetitionPaymentFromSponsor.setUpdatedBy(competitionPaymentFromSponsor.getUpdatedBy());
                }
                if (competitionPaymentFromSponsor.getUpdatedOn() != null) {
                    existingCompetitionPaymentFromSponsor.setUpdatedOn(competitionPaymentFromSponsor.getUpdatedOn());
                }

                return existingCompetitionPaymentFromSponsor;
            })
            .map(competitionPaymentFromSponsorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competitionPaymentFromSponsor.getId().toString())
        );
    }

    /**
     * {@code GET  /competition-payment-from-sponsors} : get all the competitionPaymentFromSponsors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of competitionPaymentFromSponsors in body.
     */
    @GetMapping("")
    public List<CompetitionPaymentFromSponsor> getAllCompetitionPaymentFromSponsors() {
        LOG.debug("REST request to get all CompetitionPaymentFromSponsors");
        return competitionPaymentFromSponsorRepository.findAll();
    }

    /**
     * {@code GET  /competition-payment-from-sponsors/:id} : get the "id" competitionPaymentFromSponsor.
     *
     * @param id the id of the competitionPaymentFromSponsor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the competitionPaymentFromSponsor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompetitionPaymentFromSponsor> getCompetitionPaymentFromSponsor(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CompetitionPaymentFromSponsor : {}", id);
        Optional<CompetitionPaymentFromSponsor> competitionPaymentFromSponsor = competitionPaymentFromSponsorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(competitionPaymentFromSponsor);
    }

    /**
     * {@code DELETE  /competition-payment-from-sponsors/:id} : delete the "id" competitionPaymentFromSponsor.
     *
     * @param id the id of the competitionPaymentFromSponsor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetitionPaymentFromSponsor(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CompetitionPaymentFromSponsor : {}", id);
        competitionPaymentFromSponsorRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
