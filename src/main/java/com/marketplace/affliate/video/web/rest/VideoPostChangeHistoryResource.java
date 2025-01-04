package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.VideoPostChangeHistory;
import com.marketplace.affliate.video.repository.VideoPostChangeHistoryRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.VideoPostChangeHistory}.
 */
@RestController
@RequestMapping("/api/video-post-change-histories")
@Transactional
public class VideoPostChangeHistoryResource {

    private static final Logger LOG = LoggerFactory.getLogger(VideoPostChangeHistoryResource.class);

    private static final String ENTITY_NAME = "videoPostChangeHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoPostChangeHistoryRepository videoPostChangeHistoryRepository;

    public VideoPostChangeHistoryResource(VideoPostChangeHistoryRepository videoPostChangeHistoryRepository) {
        this.videoPostChangeHistoryRepository = videoPostChangeHistoryRepository;
    }

    /**
     * {@code POST  /video-post-change-histories} : Create a new videoPostChangeHistory.
     *
     * @param videoPostChangeHistory the videoPostChangeHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoPostChangeHistory, or with status {@code 400 (Bad Request)} if the videoPostChangeHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VideoPostChangeHistory> createVideoPostChangeHistory(@RequestBody VideoPostChangeHistory videoPostChangeHistory)
        throws URISyntaxException {
        LOG.debug("REST request to save VideoPostChangeHistory : {}", videoPostChangeHistory);
        if (videoPostChangeHistory.getId() != null) {
            throw new BadRequestAlertException("A new videoPostChangeHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        videoPostChangeHistory = videoPostChangeHistoryRepository.save(videoPostChangeHistory);
        return ResponseEntity.created(new URI("/api/video-post-change-histories/" + videoPostChangeHistory.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, videoPostChangeHistory.getId().toString()))
            .body(videoPostChangeHistory);
    }

    /**
     * {@code PUT  /video-post-change-histories/:id} : Updates an existing videoPostChangeHistory.
     *
     * @param id the id of the videoPostChangeHistory to save.
     * @param videoPostChangeHistory the videoPostChangeHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoPostChangeHistory,
     * or with status {@code 400 (Bad Request)} if the videoPostChangeHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the videoPostChangeHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VideoPostChangeHistory> updateVideoPostChangeHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoPostChangeHistory videoPostChangeHistory
    ) throws URISyntaxException {
        LOG.debug("REST request to update VideoPostChangeHistory : {}, {}", id, videoPostChangeHistory);
        if (videoPostChangeHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoPostChangeHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoPostChangeHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        videoPostChangeHistory = videoPostChangeHistoryRepository.save(videoPostChangeHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoPostChangeHistory.getId().toString()))
            .body(videoPostChangeHistory);
    }

    /**
     * {@code PATCH  /video-post-change-histories/:id} : Partial updates given fields of an existing videoPostChangeHistory, field will ignore if it is null
     *
     * @param id the id of the videoPostChangeHistory to save.
     * @param videoPostChangeHistory the videoPostChangeHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoPostChangeHistory,
     * or with status {@code 400 (Bad Request)} if the videoPostChangeHistory is not valid,
     * or with status {@code 404 (Not Found)} if the videoPostChangeHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the videoPostChangeHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VideoPostChangeHistory> partialUpdateVideoPostChangeHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoPostChangeHistory videoPostChangeHistory
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update VideoPostChangeHistory partially : {}, {}", id, videoPostChangeHistory);
        if (videoPostChangeHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoPostChangeHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoPostChangeHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VideoPostChangeHistory> result = videoPostChangeHistoryRepository
            .findById(videoPostChangeHistory.getId())
            .map(existingVideoPostChangeHistory -> {
                if (videoPostChangeHistory.getChangeType() != null) {
                    existingVideoPostChangeHistory.setChangeType(videoPostChangeHistory.getChangeType());
                }
                if (videoPostChangeHistory.getOldtitle() != null) {
                    existingVideoPostChangeHistory.setOldtitle(videoPostChangeHistory.getOldtitle());
                }
                if (videoPostChangeHistory.getOldDescription() != null) {
                    existingVideoPostChangeHistory.setOldDescription(videoPostChangeHistory.getOldDescription());
                }
                if (videoPostChangeHistory.getOldUrl() != null) {
                    existingVideoPostChangeHistory.setOldUrl(videoPostChangeHistory.getOldUrl());
                }
                if (videoPostChangeHistory.getOldUrlType() != null) {
                    existingVideoPostChangeHistory.setOldUrlType(videoPostChangeHistory.getOldUrlType());
                }
                if (videoPostChangeHistory.getOldIsAIGenerated() != null) {
                    existingVideoPostChangeHistory.setOldIsAIGenerated(videoPostChangeHistory.getOldIsAIGenerated());
                }
                if (videoPostChangeHistory.getOldIsPremium() != null) {
                    existingVideoPostChangeHistory.setOldIsPremium(videoPostChangeHistory.getOldIsPremium());
                }
                if (videoPostChangeHistory.getOldIsBlocked() != null) {
                    existingVideoPostChangeHistory.setOldIsBlocked(videoPostChangeHistory.getOldIsBlocked());
                }
                if (videoPostChangeHistory.getOldIsModerated() != null) {
                    existingVideoPostChangeHistory.setOldIsModerated(videoPostChangeHistory.getOldIsModerated());
                }
                if (videoPostChangeHistory.getOldIsActive() != null) {
                    existingVideoPostChangeHistory.setOldIsActive(videoPostChangeHistory.getOldIsActive());
                }
                if (videoPostChangeHistory.getOldCreatedBy() != null) {
                    existingVideoPostChangeHistory.setOldCreatedBy(videoPostChangeHistory.getOldCreatedBy());
                }
                if (videoPostChangeHistory.getOldCreatedOn() != null) {
                    existingVideoPostChangeHistory.setOldCreatedOn(videoPostChangeHistory.getOldCreatedOn());
                }
                if (videoPostChangeHistory.getOldUpdatedBy() != null) {
                    existingVideoPostChangeHistory.setOldUpdatedBy(videoPostChangeHistory.getOldUpdatedBy());
                }
                if (videoPostChangeHistory.getOldUpdatedOn() != null) {
                    existingVideoPostChangeHistory.setOldUpdatedOn(videoPostChangeHistory.getOldUpdatedOn());
                }

                return existingVideoPostChangeHistory;
            })
            .map(videoPostChangeHistoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoPostChangeHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /video-post-change-histories} : get all the videoPostChangeHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoPostChangeHistories in body.
     */
    @GetMapping("")
    public List<VideoPostChangeHistory> getAllVideoPostChangeHistories() {
        LOG.debug("REST request to get all VideoPostChangeHistories");
        return videoPostChangeHistoryRepository.findAll();
    }

    /**
     * {@code GET  /video-post-change-histories/:id} : get the "id" videoPostChangeHistory.
     *
     * @param id the id of the videoPostChangeHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoPostChangeHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VideoPostChangeHistory> getVideoPostChangeHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to get VideoPostChangeHistory : {}", id);
        Optional<VideoPostChangeHistory> videoPostChangeHistory = videoPostChangeHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(videoPostChangeHistory);
    }

    /**
     * {@code DELETE  /video-post-change-histories/:id} : delete the "id" videoPostChangeHistory.
     *
     * @param id the id of the videoPostChangeHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoPostChangeHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete VideoPostChangeHistory : {}", id);
        videoPostChangeHistoryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
