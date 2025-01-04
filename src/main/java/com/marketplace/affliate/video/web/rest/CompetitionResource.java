package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.Competition;
import com.marketplace.affliate.video.repository.CompetitionRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.Competition}.
 */
@RestController
@RequestMapping("/api/competitions")
@Transactional
public class CompetitionResource {

    private static final Logger LOG = LoggerFactory.getLogger(CompetitionResource.class);

    private static final String ENTITY_NAME = "competition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompetitionRepository competitionRepository;

    public CompetitionResource(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    /**
     * {@code POST  /competitions} : Create a new competition.
     *
     * @param competition the competition to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new competition, or with status {@code 400 (Bad Request)} if the competition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Competition> createCompetition(@RequestBody Competition competition) throws URISyntaxException {
        LOG.debug("REST request to save Competition : {}", competition);
        if (competition.getId() != null) {
            throw new BadRequestAlertException("A new competition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        competition = competitionRepository.save(competition);
        return ResponseEntity.created(new URI("/api/competitions/" + competition.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, competition.getId().toString()))
            .body(competition);
    }

    /**
     * {@code PUT  /competitions/:id} : Updates an existing competition.
     *
     * @param id the id of the competition to save.
     * @param competition the competition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competition,
     * or with status {@code 400 (Bad Request)} if the competition is not valid,
     * or with status {@code 500 (Internal Server Error)} if the competition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Competition> updateCompetition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Competition competition
    ) throws URISyntaxException {
        LOG.debug("REST request to update Competition : {}, {}", id, competition);
        if (competition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competition.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        competition = competitionRepository.save(competition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competition.getId().toString()))
            .body(competition);
    }

    /**
     * {@code PATCH  /competitions/:id} : Partial updates given fields of an existing competition, field will ignore if it is null
     *
     * @param id the id of the competition to save.
     * @param competition the competition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competition,
     * or with status {@code 400 (Bad Request)} if the competition is not valid,
     * or with status {@code 404 (Not Found)} if the competition is not found,
     * or with status {@code 500 (Internal Server Error)} if the competition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Competition> partialUpdateCompetition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Competition competition
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Competition partially : {}, {}", id, competition);
        if (competition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, competition.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!competitionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Competition> result = competitionRepository
            .findById(competition.getId())
            .map(existingCompetition -> {
                if (competition.getTitle() != null) {
                    existingCompetition.setTitle(competition.getTitle());
                }
                if (competition.getDescription() != null) {
                    existingCompetition.setDescription(competition.getDescription());
                }
                if (competition.getStatus() != null) {
                    existingCompetition.setStatus(competition.getStatus());
                }
                if (competition.getIsBlocked() != null) {
                    existingCompetition.setIsBlocked(competition.getIsBlocked());
                }
                if (competition.getBlockReason() != null) {
                    existingCompetition.setBlockReason(competition.getBlockReason());
                }
                if (competition.getBlockedBy() != null) {
                    existingCompetition.setBlockedBy(competition.getBlockedBy());
                }
                if (competition.getIsPaused() != null) {
                    existingCompetition.setIsPaused(competition.getIsPaused());
                }
                if (competition.getPauseReason() != null) {
                    existingCompetition.setPauseReason(competition.getPauseReason());
                }
                if (competition.getPausedBy() != null) {
                    existingCompetition.setPausedBy(competition.getPausedBy());
                }
                if (competition.getBanner1Url() != null) {
                    existingCompetition.setBanner1Url(competition.getBanner1Url());
                }
                if (competition.getBanner2Url() != null) {
                    existingCompetition.setBanner2Url(competition.getBanner2Url());
                }
                if (competition.getBanner3Url() != null) {
                    existingCompetition.setBanner3Url(competition.getBanner3Url());
                }
                if (competition.getStartDate() != null) {
                    existingCompetition.setStartDate(competition.getStartDate());
                }
                if (competition.getEndDate() != null) {
                    existingCompetition.setEndDate(competition.getEndDate());
                }
                if (competition.getLandingUrl() != null) {
                    existingCompetition.setLandingUrl(competition.getLandingUrl());
                }
                if (competition.getTotalPrizeValue() != null) {
                    existingCompetition.setTotalPrizeValue(competition.getTotalPrizeValue());
                }
                if (competition.getInvoiceToSponsorUrl() != null) {
                    existingCompetition.setInvoiceToSponsorUrl(competition.getInvoiceToSponsorUrl());
                }
                if (competition.getTncUrl() != null) {
                    existingCompetition.setTncUrl(competition.getTncUrl());
                }
                if (competition.getTnc() != null) {
                    existingCompetition.setTnc(competition.getTnc());
                }
                if (competition.getIsActive() != null) {
                    existingCompetition.setIsActive(competition.getIsActive());
                }
                if (competition.getCreatedBy() != null) {
                    existingCompetition.setCreatedBy(competition.getCreatedBy());
                }
                if (competition.getCreatedOn() != null) {
                    existingCompetition.setCreatedOn(competition.getCreatedOn());
                }
                if (competition.getUpdatedBy() != null) {
                    existingCompetition.setUpdatedBy(competition.getUpdatedBy());
                }
                if (competition.getUpdatedOn() != null) {
                    existingCompetition.setUpdatedOn(competition.getUpdatedOn());
                }

                return existingCompetition;
            })
            .map(competitionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, competition.getId().toString())
        );
    }

    /**
     * {@code GET  /competitions} : get all the competitions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of competitions in body.
     */
    @GetMapping("")
    public List<Competition> getAllCompetitions() {
        LOG.debug("REST request to get all Competitions");
        return competitionRepository.findAll();
    }

    /**
     * {@code GET  /competitions/:id} : get the "id" competition.
     *
     * @param id the id of the competition to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the competition, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetition(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Competition : {}", id);
        Optional<Competition> competition = competitionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(competition);
    }

    /**
     * {@code DELETE  /competitions/:id} : delete the "id" competition.
     *
     * @param id the id of the competition to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Competition : {}", id);
        competitionRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
