package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.CompetitionPaymentToWinner;
import com.marketplace.affliate.video.repository.CompetitionPaymentToWinnerRepository;
import com.marketplace.affliate.video.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.marketplace.affliate.video.domain.CompetitionPaymentToWinner}.
 */
@RestController
@RequestMapping("/api/competition-payment-to-winners")
@Transactional
public class CompetitionPaymentToWinnerResource {

    private static final Logger LOG = LoggerFactory.getLogger(CompetitionPaymentToWinnerResource.class);

    private static final String ENTITY_NAME = "competitionPaymentToWinner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompetitionPaymentToWinnerRepository competitionPaymentToWinnerRepository;

    public CompetitionPaymentToWinnerResource(CompetitionPaymentToWinnerRepository competitionPaymentToWinnerRepository) {
        this.competitionPaymentToWinnerRepository = competitionPaymentToWinnerRepository;
    }

    /**
     * {@code POST  /competition-payment-to-winners} : Create a new competitionPaymentToWinner.
     *
     * @param competitionPaymentToWinner the competitionPaymentToWinner to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new competitionPaymentToWinner, or with status {@code 400 (Bad Request)} if the competitionPaymentToWinner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CompetitionPaymentToWinner> createCompetitionPaymentToWinner(
        @RequestBody CompetitionPaymentToWinner competitionPaymentToWinner
    ) throws URISyntaxException {
        LOG.debug("REST request to save CompetitionPaymentToWinner : {}", competitionPaymentToWinner);
        if (competitionPaymentToWinner.getId() != null) {
            throw new BadRequestAlertException("A new competitionPaymentToWinner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        competitionPaymentToWinner = competitionPaymentToWinnerRepository.save(competitionPaymentToWinner);
        return ResponseEntity.created(new URI("/api/competition-payment-to-winners/" + competitionPaymentToWinner.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, competitionPaymentToWinner.getId().toString())
            )
            .body(competitionPaymentToWinner);
    }

    /**
     * {@code PUT  /competition-payment-to-winners/:id} : Updates an existing competitionPaymentToWinner.
     *
     * @param id the id of the competitionPaymentToWinner to save.
     * @param competitionPaymentToWinner the competitionPaymentToWinner to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competitionPaymentToWinner,
     * or with status {@code 400 (Bad Request)} if the competitionPaymentToWinner is not valid,
     * or with status {@code 500 (Internal Server Error)} if the competitionPaymentToWinner couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompetitionPaymentToWinner> updateCompetitionPaymentToWinner(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CompetitionPaymentToWinner competitionPaymentToWinner
    ) throws URISyntaxException {
        LOG.debug("REST request to update CompetitionPaymentToWinner : {}, {}", id, competitionPaymentToWinner);
        if (competitionPaymentToWinner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competitionPaymentToWinner.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionPaymentToWinnerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        competitionPaymentToWinner = competitionPaymentToWinnerRepository.save(competitionPaymentToWinner);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competitionPaymentToWinner.getId().toString()))
            .body(competitionPaymentToWinner);
    }

    /**
     * {@code PATCH  /competition-payment-to-winners/:id} : Partial updates given fields of an existing competitionPaymentToWinner, field will ignore if it is null
     *
     * @param id the id of the competitionPaymentToWinner to save.
     * @param competitionPaymentToWinner the competitionPaymentToWinner to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competitionPaymentToWinner,
     * or with status {@code 400 (Bad Request)} if the competitionPaymentToWinner is not valid,
     * or with status {@code 404 (Not Found)} if the competitionPaymentToWinner is not found,
     * or with status {@code 500 (Internal Server Error)} if the competitionPaymentToWinner couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CompetitionPaymentToWinner> partialUpdateCompetitionPaymentToWinner(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CompetitionPaymentToWinner competitionPaymentToWinner
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CompetitionPaymentToWinner partially : {}, {}", id, competitionPaymentToWinner);
        if (competitionPaymentToWinner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competitionPaymentToWinner.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionPaymentToWinnerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CompetitionPaymentToWinner> result = competitionPaymentToWinnerRepository
            .findById(competitionPaymentToWinner.getId())
            .map(existingCompetitionPaymentToWinner -> {
                if (competitionPaymentToWinner.getPrizeTitle() != null) {
                    existingCompetitionPaymentToWinner.setPrizeTitle(competitionPaymentToWinner.getPrizeTitle());
                }
                if (competitionPaymentToWinner.getPrizeAmount() != null) {
                    existingCompetitionPaymentToWinner.setPrizeAmount(competitionPaymentToWinner.getPrizeAmount());
                }
                if (competitionPaymentToWinner.getTdsAmount() != null) {
                    existingCompetitionPaymentToWinner.setTdsAmount(competitionPaymentToWinner.getTdsAmount());
                }
                if (competitionPaymentToWinner.getTdsCertificateUrl() != null) {
                    existingCompetitionPaymentToWinner.setTdsCertificateUrl(competitionPaymentToWinner.getTdsCertificateUrl());
                }
                if (competitionPaymentToWinner.getOtherDeductionAmount() != null) {
                    existingCompetitionPaymentToWinner.setOtherDeductionAmount(competitionPaymentToWinner.getOtherDeductionAmount());
                }
                if (competitionPaymentToWinner.getDeductionReason() != null) {
                    existingCompetitionPaymentToWinner.setDeductionReason(competitionPaymentToWinner.getDeductionReason());
                }
                if (competitionPaymentToWinner.getDeductionJsonData() != null) {
                    existingCompetitionPaymentToWinner.setDeductionJsonData(competitionPaymentToWinner.getDeductionJsonData());
                }
                if (competitionPaymentToWinner.getDeductionCertificateUrl() != null) {
                    existingCompetitionPaymentToWinner.setDeductionCertificateUrl(competitionPaymentToWinner.getDeductionCertificateUrl());
                }
                if (competitionPaymentToWinner.getPaidAmount() != null) {
                    existingCompetitionPaymentToWinner.setPaidAmount(competitionPaymentToWinner.getPaidAmount());
                }
                if (competitionPaymentToWinner.getTransactionId() != null) {
                    existingCompetitionPaymentToWinner.setTransactionId(competitionPaymentToWinner.getTransactionId());
                }
                if (competitionPaymentToWinner.getTransactionScreenshotUrl() != null) {
                    existingCompetitionPaymentToWinner.setTransactionScreenshotUrl(
                        competitionPaymentToWinner.getTransactionScreenshotUrl()
                    );
                }
                if (competitionPaymentToWinner.getTransactionDate() != null) {
                    existingCompetitionPaymentToWinner.setTransactionDate(competitionPaymentToWinner.getTransactionDate());
                }
                if (competitionPaymentToWinner.getTransactionStatus() != null) {
                    existingCompetitionPaymentToWinner.setTransactionStatus(competitionPaymentToWinner.getTransactionStatus());
                }
                if (competitionPaymentToWinner.getPaidBy() != null) {
                    existingCompetitionPaymentToWinner.setPaidBy(competitionPaymentToWinner.getPaidBy());
                }
                if (competitionPaymentToWinner.getIsActive() != null) {
                    existingCompetitionPaymentToWinner.setIsActive(competitionPaymentToWinner.getIsActive());
                }
                if (competitionPaymentToWinner.getCreatedBy() != null) {
                    existingCompetitionPaymentToWinner.setCreatedBy(competitionPaymentToWinner.getCreatedBy());
                }
                if (competitionPaymentToWinner.getCreatedOn() != null) {
                    existingCompetitionPaymentToWinner.setCreatedOn(competitionPaymentToWinner.getCreatedOn());
                }
                if (competitionPaymentToWinner.getUpdatedBy() != null) {
                    existingCompetitionPaymentToWinner.setUpdatedBy(competitionPaymentToWinner.getUpdatedBy());
                }
                if (competitionPaymentToWinner.getUpdatedOn() != null) {
                    existingCompetitionPaymentToWinner.setUpdatedOn(competitionPaymentToWinner.getUpdatedOn());
                }

                return existingCompetitionPaymentToWinner;
            })
            .map(competitionPaymentToWinnerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competitionPaymentToWinner.getId().toString())
        );
    }

    /**
     * {@code GET  /competition-payment-to-winners} : get all the competitionPaymentToWinners.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of competitionPaymentToWinners in body.
     */
    @GetMapping("")
    public List<CompetitionPaymentToWinner> getAllCompetitionPaymentToWinners(
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("winner-is-null".equals(filter)) {
            LOG.debug("REST request to get all CompetitionPaymentToWinners where winner is null");
            return StreamSupport.stream(competitionPaymentToWinnerRepository.findAll().spliterator(), false)
                .filter(competitionPaymentToWinner -> competitionPaymentToWinner.getWinner() == null)
                .toList();
        }
        LOG.debug("REST request to get all CompetitionPaymentToWinners");
        return competitionPaymentToWinnerRepository.findAll();
    }

    /**
     * {@code GET  /competition-payment-to-winners/:id} : get the "id" competitionPaymentToWinner.
     *
     * @param id the id of the competitionPaymentToWinner to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the competitionPaymentToWinner, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompetitionPaymentToWinner> getCompetitionPaymentToWinner(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CompetitionPaymentToWinner : {}", id);
        Optional<CompetitionPaymentToWinner> competitionPaymentToWinner = competitionPaymentToWinnerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(competitionPaymentToWinner);
    }

    /**
     * {@code DELETE  /competition-payment-to-winners/:id} : delete the "id" competitionPaymentToWinner.
     *
     * @param id the id of the competitionPaymentToWinner to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetitionPaymentToWinner(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CompetitionPaymentToWinner : {}", id);
        competitionPaymentToWinnerRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
