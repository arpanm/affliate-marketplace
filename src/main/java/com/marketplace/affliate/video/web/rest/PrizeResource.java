package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.Prize;
import com.marketplace.affliate.video.repository.PrizeRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.Prize}.
 */
@RestController
@RequestMapping("/api/prizes")
@Transactional
public class PrizeResource {

    private static final Logger LOG = LoggerFactory.getLogger(PrizeResource.class);

    private static final String ENTITY_NAME = "prize";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrizeRepository prizeRepository;

    public PrizeResource(PrizeRepository prizeRepository) {
        this.prizeRepository = prizeRepository;
    }

    /**
     * {@code POST  /prizes} : Create a new prize.
     *
     * @param prize the prize to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prize, or with status {@code 400 (Bad Request)} if the prize has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Prize> createPrize(@RequestBody Prize prize) throws URISyntaxException {
        LOG.debug("REST request to save Prize : {}", prize);
        if (prize.getId() != null) {
            throw new BadRequestAlertException("A new prize cannot already have an ID", ENTITY_NAME, "idexists");
        }
        prize = prizeRepository.save(prize);
        return ResponseEntity.created(new URI("/api/prizes/" + prize.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, prize.getId().toString()))
            .body(prize);
    }

    /**
     * {@code PUT  /prizes/:id} : Updates an existing prize.
     *
     * @param id the id of the prize to save.
     * @param prize the prize to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prize,
     * or with status {@code 400 (Bad Request)} if the prize is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prize couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prize> updatePrize(@PathVariable(value = "id", required = false) final Long id, @RequestBody Prize prize)
        throws URISyntaxException {
        LOG.debug("REST request to update Prize : {}, {}", id, prize);
        if (prize.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prize.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prizeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        prize = prizeRepository.save(prize);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prize.getId().toString()))
            .body(prize);
    }

    /**
     * {@code PATCH  /prizes/:id} : Partial updates given fields of an existing prize, field will ignore if it is null
     *
     * @param id the id of the prize to save.
     * @param prize the prize to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prize,
     * or with status {@code 400 (Bad Request)} if the prize is not valid,
     * or with status {@code 404 (Not Found)} if the prize is not found,
     * or with status {@code 500 (Internal Server Error)} if the prize couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Prize> partialUpdatePrize(@PathVariable(value = "id", required = false) final Long id, @RequestBody Prize prize)
        throws URISyntaxException {
        LOG.debug("REST request to partial update Prize partially : {}, {}", id, prize);
        if (prize.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prize.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prizeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Prize> result = prizeRepository
            .findById(prize.getId())
            .map(existingPrize -> {
                if (prize.getPrizeType() != null) {
                    existingPrize.setPrizeType(prize.getPrizeType());
                }
                if (prize.getPrizeTag() != null) {
                    existingPrize.setPrizeTag(prize.getPrizeTag());
                }
                if (prize.getPrizeDetails() != null) {
                    existingPrize.setPrizeDetails(prize.getPrizeDetails());
                }
                if (prize.getPrizeValueType() != null) {
                    existingPrize.setPrizeValueType(prize.getPrizeValueType());
                }
                if (prize.getPrizeValue() != null) {
                    existingPrize.setPrizeValue(prize.getPrizeValue());
                }
                if (prize.getCountOfPossibleWinners() != null) {
                    existingPrize.setCountOfPossibleWinners(prize.getCountOfPossibleWinners());
                }
                if (prize.getIsActive() != null) {
                    existingPrize.setIsActive(prize.getIsActive());
                }
                if (prize.getCreatedBy() != null) {
                    existingPrize.setCreatedBy(prize.getCreatedBy());
                }
                if (prize.getCreatedOn() != null) {
                    existingPrize.setCreatedOn(prize.getCreatedOn());
                }
                if (prize.getUpdatedBy() != null) {
                    existingPrize.setUpdatedBy(prize.getUpdatedBy());
                }
                if (prize.getUpdatedOn() != null) {
                    existingPrize.setUpdatedOn(prize.getUpdatedOn());
                }

                return existingPrize;
            })
            .map(prizeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prize.getId().toString())
        );
    }

    /**
     * {@code GET  /prizes} : get all the prizes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prizes in body.
     */
    @GetMapping("")
    public List<Prize> getAllPrizes() {
        LOG.debug("REST request to get all Prizes");
        return prizeRepository.findAll();
    }

    /**
     * {@code GET  /prizes/:id} : get the "id" prize.
     *
     * @param id the id of the prize to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prize, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prize> getPrize(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Prize : {}", id);
        Optional<Prize> prize = prizeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(prize);
    }

    /**
     * {@code DELETE  /prizes/:id} : delete the "id" prize.
     *
     * @param id the id of the prize to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrize(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Prize : {}", id);
        prizeRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
