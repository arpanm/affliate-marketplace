package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.ReviewChangeHistory;
import com.marketplace.affliate.video.repository.ReviewChangeHistoryRepository;
import com.marketplace.affliate.video.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.ReviewChangeHistory}.
 */
@RestController
@RequestMapping("/api/review-change-histories")
@Transactional
public class ReviewChangeHistoryResource {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewChangeHistoryResource.class);

    private static final String ENTITY_NAME = "reviewChangeHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReviewChangeHistoryRepository reviewChangeHistoryRepository;

    public ReviewChangeHistoryResource(ReviewChangeHistoryRepository reviewChangeHistoryRepository) {
        this.reviewChangeHistoryRepository = reviewChangeHistoryRepository;
    }

    /**
     * {@code POST  /review-change-histories} : Create a new reviewChangeHistory.
     *
     * @param reviewChangeHistory the reviewChangeHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reviewChangeHistory, or with status {@code 400 (Bad Request)} if the reviewChangeHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReviewChangeHistory> createReviewChangeHistory(@Valid @RequestBody ReviewChangeHistory reviewChangeHistory)
        throws URISyntaxException {
        LOG.debug("REST request to save ReviewChangeHistory : {}", reviewChangeHistory);
        if (reviewChangeHistory.getId() != null) {
            throw new BadRequestAlertException("A new reviewChangeHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        reviewChangeHistory = reviewChangeHistoryRepository.save(reviewChangeHistory);
        return ResponseEntity.created(new URI("/api/review-change-histories/" + reviewChangeHistory.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, reviewChangeHistory.getId().toString()))
            .body(reviewChangeHistory);
    }

    /**
     * {@code PUT  /review-change-histories/:id} : Updates an existing reviewChangeHistory.
     *
     * @param id the id of the reviewChangeHistory to save.
     * @param reviewChangeHistory the reviewChangeHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reviewChangeHistory,
     * or with status {@code 400 (Bad Request)} if the reviewChangeHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reviewChangeHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReviewChangeHistory> updateReviewChangeHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReviewChangeHistory reviewChangeHistory
    ) throws URISyntaxException {
        LOG.debug("REST request to update ReviewChangeHistory : {}, {}", id, reviewChangeHistory);
        if (reviewChangeHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reviewChangeHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reviewChangeHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        reviewChangeHistory = reviewChangeHistoryRepository.save(reviewChangeHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reviewChangeHistory.getId().toString()))
            .body(reviewChangeHistory);
    }

    /**
     * {@code PATCH  /review-change-histories/:id} : Partial updates given fields of an existing reviewChangeHistory, field will ignore if it is null
     *
     * @param id the id of the reviewChangeHistory to save.
     * @param reviewChangeHistory the reviewChangeHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reviewChangeHistory,
     * or with status {@code 400 (Bad Request)} if the reviewChangeHistory is not valid,
     * or with status {@code 404 (Not Found)} if the reviewChangeHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the reviewChangeHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReviewChangeHistory> partialUpdateReviewChangeHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReviewChangeHistory reviewChangeHistory
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ReviewChangeHistory partially : {}, {}", id, reviewChangeHistory);
        if (reviewChangeHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reviewChangeHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reviewChangeHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReviewChangeHistory> result = reviewChangeHistoryRepository
            .findById(reviewChangeHistory.getId())
            .map(existingReviewChangeHistory -> {
                if (reviewChangeHistory.getChangeType() != null) {
                    existingReviewChangeHistory.setChangeType(reviewChangeHistory.getChangeType());
                }
                if (reviewChangeHistory.getOldIsLiked() != null) {
                    existingReviewChangeHistory.setOldIsLiked(reviewChangeHistory.getOldIsLiked());
                }
                if (reviewChangeHistory.getOldIsSkipped() != null) {
                    existingReviewChangeHistory.setOldIsSkipped(reviewChangeHistory.getOldIsSkipped());
                }
                if (reviewChangeHistory.getOldIsDisliked() != null) {
                    existingReviewChangeHistory.setOldIsDisliked(reviewChangeHistory.getOldIsDisliked());
                }
                if (reviewChangeHistory.getOldIsWatched() != null) {
                    existingReviewChangeHistory.setOldIsWatched(reviewChangeHistory.getOldIsWatched());
                }
                if (reviewChangeHistory.getOldIsFullyWatched() != null) {
                    existingReviewChangeHistory.setOldIsFullyWatched(reviewChangeHistory.getOldIsFullyWatched());
                }
                if (reviewChangeHistory.getOldIsReported() != null) {
                    existingReviewChangeHistory.setOldIsReported(reviewChangeHistory.getOldIsReported());
                }
                if (reviewChangeHistory.getOldRating() != null) {
                    existingReviewChangeHistory.setOldRating(reviewChangeHistory.getOldRating());
                }
                if (reviewChangeHistory.getOldComment() != null) {
                    existingReviewChangeHistory.setOldComment(reviewChangeHistory.getOldComment());
                }
                if (reviewChangeHistory.getOldReportReason() != null) {
                    existingReviewChangeHistory.setOldReportReason(reviewChangeHistory.getOldReportReason());
                }
                if (reviewChangeHistory.getOldIsBlocked() != null) {
                    existingReviewChangeHistory.setOldIsBlocked(reviewChangeHistory.getOldIsBlocked());
                }
                if (reviewChangeHistory.getOldIsModerated() != null) {
                    existingReviewChangeHistory.setOldIsModerated(reviewChangeHistory.getOldIsModerated());
                }
                if (reviewChangeHistory.getOldIsActive() != null) {
                    existingReviewChangeHistory.setOldIsActive(reviewChangeHistory.getOldIsActive());
                }
                if (reviewChangeHistory.getOldCreatedBy() != null) {
                    existingReviewChangeHistory.setOldCreatedBy(reviewChangeHistory.getOldCreatedBy());
                }
                if (reviewChangeHistory.getOldCreatedOn() != null) {
                    existingReviewChangeHistory.setOldCreatedOn(reviewChangeHistory.getOldCreatedOn());
                }
                if (reviewChangeHistory.getOldUpdatedBy() != null) {
                    existingReviewChangeHistory.setOldUpdatedBy(reviewChangeHistory.getOldUpdatedBy());
                }
                if (reviewChangeHistory.getOldUpdatedOn() != null) {
                    existingReviewChangeHistory.setOldUpdatedOn(reviewChangeHistory.getOldUpdatedOn());
                }

                return existingReviewChangeHistory;
            })
            .map(reviewChangeHistoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reviewChangeHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /review-change-histories} : get all the reviewChangeHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reviewChangeHistories in body.
     */
    @GetMapping("")
    public List<ReviewChangeHistory> getAllReviewChangeHistories() {
        LOG.debug("REST request to get all ReviewChangeHistories");
        return reviewChangeHistoryRepository.findAll();
    }

    /**
     * {@code GET  /review-change-histories/:id} : get the "id" reviewChangeHistory.
     *
     * @param id the id of the reviewChangeHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reviewChangeHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReviewChangeHistory> getReviewChangeHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ReviewChangeHistory : {}", id);
        Optional<ReviewChangeHistory> reviewChangeHistory = reviewChangeHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(reviewChangeHistory);
    }

    /**
     * {@code DELETE  /review-change-histories/:id} : delete the "id" reviewChangeHistory.
     *
     * @param id the id of the reviewChangeHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewChangeHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ReviewChangeHistory : {}", id);
        reviewChangeHistoryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
