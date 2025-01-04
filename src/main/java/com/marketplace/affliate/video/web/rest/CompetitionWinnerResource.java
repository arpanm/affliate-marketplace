package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.CompetitionWinner;
import com.marketplace.affliate.video.repository.CompetitionWinnerRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.CompetitionWinner}.
 */
@RestController
@RequestMapping("/api/competition-winners")
@Transactional
public class CompetitionWinnerResource {

    private static final Logger LOG = LoggerFactory.getLogger(CompetitionWinnerResource.class);

    private static final String ENTITY_NAME = "competitionWinner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompetitionWinnerRepository competitionWinnerRepository;

    public CompetitionWinnerResource(CompetitionWinnerRepository competitionWinnerRepository) {
        this.competitionWinnerRepository = competitionWinnerRepository;
    }

    /**
     * {@code POST  /competition-winners} : Create a new competitionWinner.
     *
     * @param competitionWinner the competitionWinner to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new competitionWinner, or with status {@code 400 (Bad Request)} if the competitionWinner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CompetitionWinner> createCompetitionWinner(@RequestBody CompetitionWinner competitionWinner)
        throws URISyntaxException {
        LOG.debug("REST request to save CompetitionWinner : {}", competitionWinner);
        if (competitionWinner.getId() != null) {
            throw new BadRequestAlertException("A new competitionWinner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        competitionWinner = competitionWinnerRepository.save(competitionWinner);
        return ResponseEntity.created(new URI("/api/competition-winners/" + competitionWinner.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, competitionWinner.getId().toString()))
            .body(competitionWinner);
    }

    /**
     * {@code PUT  /competition-winners/:id} : Updates an existing competitionWinner.
     *
     * @param id the id of the competitionWinner to save.
     * @param competitionWinner the competitionWinner to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competitionWinner,
     * or with status {@code 400 (Bad Request)} if the competitionWinner is not valid,
     * or with status {@code 500 (Internal Server Error)} if the competitionWinner couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompetitionWinner> updateCompetitionWinner(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CompetitionWinner competitionWinner
    ) throws URISyntaxException {
        LOG.debug("REST request to update CompetitionWinner : {}, {}", id, competitionWinner);
        if (competitionWinner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competitionWinner.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionWinnerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        competitionWinner = competitionWinnerRepository.save(competitionWinner);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competitionWinner.getId().toString()))
            .body(competitionWinner);
    }

    /**
     * {@code PATCH  /competition-winners/:id} : Partial updates given fields of an existing competitionWinner, field will ignore if it is null
     *
     * @param id the id of the competitionWinner to save.
     * @param competitionWinner the competitionWinner to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competitionWinner,
     * or with status {@code 400 (Bad Request)} if the competitionWinner is not valid,
     * or with status {@code 404 (Not Found)} if the competitionWinner is not found,
     * or with status {@code 500 (Internal Server Error)} if the competitionWinner couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CompetitionWinner> partialUpdateCompetitionWinner(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CompetitionWinner competitionWinner
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CompetitionWinner partially : {}, {}", id, competitionWinner);
        if (competitionWinner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competitionWinner.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionWinnerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CompetitionWinner> result = competitionWinnerRepository
            .findById(competitionWinner.getId())
            .map(existingCompetitionWinner -> {
                if (competitionWinner.getPrizeTitle() != null) {
                    existingCompetitionWinner.setPrizeTitle(competitionWinner.getPrizeTitle());
                }
                if (competitionWinner.getCitation() != null) {
                    existingCompetitionWinner.setCitation(competitionWinner.getCitation());
                }
                if (competitionWinner.getCertificateUrl() != null) {
                    existingCompetitionWinner.setCertificateUrl(competitionWinner.getCertificateUrl());
                }
                if (competitionWinner.getSelectedBy() != null) {
                    existingCompetitionWinner.setSelectedBy(competitionWinner.getSelectedBy());
                }
                if (competitionWinner.getSelectionReason() != null) {
                    existingCompetitionWinner.setSelectionReason(competitionWinner.getSelectionReason());
                }
                if (competitionWinner.getIsActive() != null) {
                    existingCompetitionWinner.setIsActive(competitionWinner.getIsActive());
                }
                if (competitionWinner.getCreatedBy() != null) {
                    existingCompetitionWinner.setCreatedBy(competitionWinner.getCreatedBy());
                }
                if (competitionWinner.getCreatedOn() != null) {
                    existingCompetitionWinner.setCreatedOn(competitionWinner.getCreatedOn());
                }
                if (competitionWinner.getUpdatedBy() != null) {
                    existingCompetitionWinner.setUpdatedBy(competitionWinner.getUpdatedBy());
                }
                if (competitionWinner.getUpdatedOn() != null) {
                    existingCompetitionWinner.setUpdatedOn(competitionWinner.getUpdatedOn());
                }

                return existingCompetitionWinner;
            })
            .map(competitionWinnerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competitionWinner.getId().toString())
        );
    }

    /**
     * {@code GET  /competition-winners} : get all the competitionWinners.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of competitionWinners in body.
     */
    @GetMapping("")
    public List<CompetitionWinner> getAllCompetitionWinners() {
        LOG.debug("REST request to get all CompetitionWinners");
        return competitionWinnerRepository.findAll();
    }

    /**
     * {@code GET  /competition-winners/:id} : get the "id" competitionWinner.
     *
     * @param id the id of the competitionWinner to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the competitionWinner, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompetitionWinner> getCompetitionWinner(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CompetitionWinner : {}", id);
        Optional<CompetitionWinner> competitionWinner = competitionWinnerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(competitionWinner);
    }

    /**
     * {@code DELETE  /competition-winners/:id} : delete the "id" competitionWinner.
     *
     * @param id the id of the competitionWinner to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetitionWinner(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CompetitionWinner : {}", id);
        competitionWinnerRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
