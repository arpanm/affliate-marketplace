package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.VideoTag;
import com.marketplace.affliate.video.repository.VideoTagRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.VideoTag}.
 */
@RestController
@RequestMapping("/api/video-tags")
@Transactional
public class VideoTagResource {

    private static final Logger LOG = LoggerFactory.getLogger(VideoTagResource.class);

    private static final String ENTITY_NAME = "videoTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoTagRepository videoTagRepository;

    public VideoTagResource(VideoTagRepository videoTagRepository) {
        this.videoTagRepository = videoTagRepository;
    }

    /**
     * {@code POST  /video-tags} : Create a new videoTag.
     *
     * @param videoTag the videoTag to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoTag, or with status {@code 400 (Bad Request)} if the videoTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VideoTag> createVideoTag(@RequestBody VideoTag videoTag) throws URISyntaxException {
        LOG.debug("REST request to save VideoTag : {}", videoTag);
        if (videoTag.getId() != null) {
            throw new BadRequestAlertException("A new videoTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        videoTag = videoTagRepository.save(videoTag);
        return ResponseEntity.created(new URI("/api/video-tags/" + videoTag.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, videoTag.getId().toString()))
            .body(videoTag);
    }

    /**
     * {@code PUT  /video-tags/:id} : Updates an existing videoTag.
     *
     * @param id the id of the videoTag to save.
     * @param videoTag the videoTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoTag,
     * or with status {@code 400 (Bad Request)} if the videoTag is not valid,
     * or with status {@code 500 (Internal Server Error)} if the videoTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VideoTag> updateVideoTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoTag videoTag
    ) throws URISyntaxException {
        LOG.debug("REST request to update VideoTag : {}, {}", id, videoTag);
        if (videoTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        videoTag = videoTagRepository.save(videoTag);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoTag.getId().toString()))
            .body(videoTag);
    }

    /**
     * {@code PATCH  /video-tags/:id} : Partial updates given fields of an existing videoTag, field will ignore if it is null
     *
     * @param id the id of the videoTag to save.
     * @param videoTag the videoTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoTag,
     * or with status {@code 400 (Bad Request)} if the videoTag is not valid,
     * or with status {@code 404 (Not Found)} if the videoTag is not found,
     * or with status {@code 500 (Internal Server Error)} if the videoTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VideoTag> partialUpdateVideoTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoTag videoTag
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update VideoTag partially : {}, {}", id, videoTag);
        if (videoTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VideoTag> result = videoTagRepository
            .findById(videoTag.getId())
            .map(existingVideoTag -> {
                if (videoTag.getName() != null) {
                    existingVideoTag.setName(videoTag.getName());
                }
                if (videoTag.getCode() != null) {
                    existingVideoTag.setCode(videoTag.getCode());
                }
                if (videoTag.getIsModerated() != null) {
                    existingVideoTag.setIsModerated(videoTag.getIsModerated());
                }
                if (videoTag.getIsDeleted() != null) {
                    existingVideoTag.setIsDeleted(videoTag.getIsDeleted());
                }
                if (videoTag.getDeletionReason() != null) {
                    existingVideoTag.setDeletionReason(videoTag.getDeletionReason());
                }
                if (videoTag.getMergedWithTagName() != null) {
                    existingVideoTag.setMergedWithTagName(videoTag.getMergedWithTagName());
                }
                if (videoTag.getMergedWithTagCode() != null) {
                    existingVideoTag.setMergedWithTagCode(videoTag.getMergedWithTagCode());
                }
                if (videoTag.getIsActive() != null) {
                    existingVideoTag.setIsActive(videoTag.getIsActive());
                }
                if (videoTag.getCreatedBy() != null) {
                    existingVideoTag.setCreatedBy(videoTag.getCreatedBy());
                }
                if (videoTag.getCreatedOn() != null) {
                    existingVideoTag.setCreatedOn(videoTag.getCreatedOn());
                }
                if (videoTag.getUpdatedBy() != null) {
                    existingVideoTag.setUpdatedBy(videoTag.getUpdatedBy());
                }
                if (videoTag.getUpdatedOn() != null) {
                    existingVideoTag.setUpdatedOn(videoTag.getUpdatedOn());
                }

                return existingVideoTag;
            })
            .map(videoTagRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoTag.getId().toString())
        );
    }

    /**
     * {@code GET  /video-tags} : get all the videoTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoTags in body.
     */
    @GetMapping("")
    public List<VideoTag> getAllVideoTags() {
        LOG.debug("REST request to get all VideoTags");
        return videoTagRepository.findAll();
    }

    /**
     * {@code GET  /video-tags/:id} : get the "id" videoTag.
     *
     * @param id the id of the videoTag to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoTag, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VideoTag> getVideoTag(@PathVariable("id") Long id) {
        LOG.debug("REST request to get VideoTag : {}", id);
        Optional<VideoTag> videoTag = videoTagRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(videoTag);
    }

    /**
     * {@code DELETE  /video-tags/:id} : delete the "id" videoTag.
     *
     * @param id the id of the videoTag to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoTag(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete VideoTag : {}", id);
        videoTagRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
