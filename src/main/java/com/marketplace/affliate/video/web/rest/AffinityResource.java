package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.Affinity;
import com.marketplace.affliate.video.repository.AffinityRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.Affinity}.
 */
@RestController
@RequestMapping("/api/affinities")
@Transactional
public class AffinityResource {

    private static final Logger LOG = LoggerFactory.getLogger(AffinityResource.class);

    private static final String ENTITY_NAME = "affinity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AffinityRepository affinityRepository;

    public AffinityResource(AffinityRepository affinityRepository) {
        this.affinityRepository = affinityRepository;
    }

    /**
     * {@code POST  /affinities} : Create a new affinity.
     *
     * @param affinity the affinity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new affinity, or with status {@code 400 (Bad Request)} if the affinity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Affinity> createAffinity(@RequestBody Affinity affinity) throws URISyntaxException {
        LOG.debug("REST request to save Affinity : {}", affinity);
        if (affinity.getId() != null) {
            throw new BadRequestAlertException("A new affinity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        affinity = affinityRepository.save(affinity);
        return ResponseEntity.created(new URI("/api/affinities/" + affinity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, affinity.getId().toString()))
            .body(affinity);
    }

    /**
     * {@code PUT  /affinities/:id} : Updates an existing affinity.
     *
     * @param id the id of the affinity to save.
     * @param affinity the affinity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affinity,
     * or with status {@code 400 (Bad Request)} if the affinity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the affinity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Affinity> updateAffinity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Affinity affinity
    ) throws URISyntaxException {
        LOG.debug("REST request to update Affinity : {}, {}", id, affinity);
        if (affinity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, affinity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!affinityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        affinity = affinityRepository.save(affinity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affinity.getId().toString()))
            .body(affinity);
    }

    /**
     * {@code PATCH  /affinities/:id} : Partial updates given fields of an existing affinity, field will ignore if it is null
     *
     * @param id the id of the affinity to save.
     * @param affinity the affinity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affinity,
     * or with status {@code 400 (Bad Request)} if the affinity is not valid,
     * or with status {@code 404 (Not Found)} if the affinity is not found,
     * or with status {@code 500 (Internal Server Error)} if the affinity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Affinity> partialUpdateAffinity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Affinity affinity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Affinity partially : {}, {}", id, affinity);
        if (affinity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, affinity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!affinityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Affinity> result = affinityRepository
            .findById(affinity.getId())
            .map(existingAffinity -> {
                if (affinity.getSegment() != null) {
                    existingAffinity.setSegment(affinity.getSegment());
                }
                if (affinity.getScore() != null) {
                    existingAffinity.setScore(affinity.getScore());
                }
                if (affinity.getIsActive() != null) {
                    existingAffinity.setIsActive(affinity.getIsActive());
                }
                if (affinity.getCreatedBy() != null) {
                    existingAffinity.setCreatedBy(affinity.getCreatedBy());
                }
                if (affinity.getCreatedOn() != null) {
                    existingAffinity.setCreatedOn(affinity.getCreatedOn());
                }
                if (affinity.getUpdatedBy() != null) {
                    existingAffinity.setUpdatedBy(affinity.getUpdatedBy());
                }
                if (affinity.getUpdatedOn() != null) {
                    existingAffinity.setUpdatedOn(affinity.getUpdatedOn());
                }

                return existingAffinity;
            })
            .map(affinityRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affinity.getId().toString())
        );
    }

    /**
     * {@code GET  /affinities} : get all the affinities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of affinities in body.
     */
    @GetMapping("")
    public List<Affinity> getAllAffinities() {
        LOG.debug("REST request to get all Affinities");
        return affinityRepository.findAll();
    }

    /**
     * {@code GET  /affinities/:id} : get the "id" affinity.
     *
     * @param id the id of the affinity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the affinity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Affinity> getAffinity(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Affinity : {}", id);
        Optional<Affinity> affinity = affinityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(affinity);
    }

    /**
     * {@code DELETE  /affinities/:id} : delete the "id" affinity.
     *
     * @param id the id of the affinity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffinity(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Affinity : {}", id);
        affinityRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
