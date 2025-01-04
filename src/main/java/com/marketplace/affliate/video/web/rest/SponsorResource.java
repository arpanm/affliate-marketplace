package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.Sponsor;
import com.marketplace.affliate.video.repository.SponsorRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.Sponsor}.
 */
@RestController
@RequestMapping("/api/sponsors")
@Transactional
public class SponsorResource {

    private static final Logger LOG = LoggerFactory.getLogger(SponsorResource.class);

    private static final String ENTITY_NAME = "sponsor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SponsorRepository sponsorRepository;

    public SponsorResource(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    /**
     * {@code POST  /sponsors} : Create a new sponsor.
     *
     * @param sponsor the sponsor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sponsor, or with status {@code 400 (Bad Request)} if the sponsor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Sponsor> createSponsor(@RequestBody Sponsor sponsor) throws URISyntaxException {
        LOG.debug("REST request to save Sponsor : {}", sponsor);
        if (sponsor.getId() != null) {
            throw new BadRequestAlertException("A new sponsor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        sponsor = sponsorRepository.save(sponsor);
        return ResponseEntity.created(new URI("/api/sponsors/" + sponsor.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, sponsor.getId().toString()))
            .body(sponsor);
    }

    /**
     * {@code PUT  /sponsors/:id} : Updates an existing sponsor.
     *
     * @param id the id of the sponsor to save.
     * @param sponsor the sponsor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sponsor,
     * or with status {@code 400 (Bad Request)} if the sponsor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sponsor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable(value = "id", required = false) final Long id, @RequestBody Sponsor sponsor)
        throws URISyntaxException {
        LOG.debug("REST request to update Sponsor : {}, {}", id, sponsor);
        if (sponsor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sponsor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sponsorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        sponsor = sponsorRepository.save(sponsor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sponsor.getId().toString()))
            .body(sponsor);
    }

    /**
     * {@code PATCH  /sponsors/:id} : Partial updates given fields of an existing sponsor, field will ignore if it is null
     *
     * @param id the id of the sponsor to save.
     * @param sponsor the sponsor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sponsor,
     * or with status {@code 400 (Bad Request)} if the sponsor is not valid,
     * or with status {@code 404 (Not Found)} if the sponsor is not found,
     * or with status {@code 500 (Internal Server Error)} if the sponsor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Sponsor> partialUpdateSponsor(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Sponsor sponsor
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Sponsor partially : {}, {}", id, sponsor);
        if (sponsor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sponsor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sponsorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Sponsor> result = sponsorRepository
            .findById(sponsor.getId())
            .map(existingSponsor -> {
                if (sponsor.getSponsorName() != null) {
                    existingSponsor.setSponsorName(sponsor.getSponsorName());
                }
                if (sponsor.getSponsorDescription() != null) {
                    existingSponsor.setSponsorDescription(sponsor.getSponsorDescription());
                }
                if (sponsor.getSponsorBanner1Url() != null) {
                    existingSponsor.setSponsorBanner1Url(sponsor.getSponsorBanner1Url());
                }
                if (sponsor.getSponsorBanner2Url() != null) {
                    existingSponsor.setSponsorBanner2Url(sponsor.getSponsorBanner2Url());
                }
                if (sponsor.getSponsorBanner3Url() != null) {
                    existingSponsor.setSponsorBanner3Url(sponsor.getSponsorBanner3Url());
                }
                if (sponsor.getSponsorExternalUrl() != null) {
                    existingSponsor.setSponsorExternalUrl(sponsor.getSponsorExternalUrl());
                }
                if (sponsor.getSponsorLogoUrl() != null) {
                    existingSponsor.setSponsorLogoUrl(sponsor.getSponsorLogoUrl());
                }
                if (sponsor.getIsActive() != null) {
                    existingSponsor.setIsActive(sponsor.getIsActive());
                }
                if (sponsor.getCreatedBy() != null) {
                    existingSponsor.setCreatedBy(sponsor.getCreatedBy());
                }
                if (sponsor.getCreatedOn() != null) {
                    existingSponsor.setCreatedOn(sponsor.getCreatedOn());
                }
                if (sponsor.getUpdatedBy() != null) {
                    existingSponsor.setUpdatedBy(sponsor.getUpdatedBy());
                }
                if (sponsor.getUpdatedOn() != null) {
                    existingSponsor.setUpdatedOn(sponsor.getUpdatedOn());
                }

                return existingSponsor;
            })
            .map(sponsorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sponsor.getId().toString())
        );
    }

    /**
     * {@code GET  /sponsors} : get all the sponsors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sponsors in body.
     */
    @GetMapping("")
    public List<Sponsor> getAllSponsors() {
        LOG.debug("REST request to get all Sponsors");
        return sponsorRepository.findAll();
    }

    /**
     * {@code GET  /sponsors/:id} : get the "id" sponsor.
     *
     * @param id the id of the sponsor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sponsor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsor(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Sponsor : {}", id);
        Optional<Sponsor> sponsor = sponsorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sponsor);
    }

    /**
     * {@code DELETE  /sponsors/:id} : delete the "id" sponsor.
     *
     * @param id the id of the sponsor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Sponsor : {}", id);
        sponsorRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
