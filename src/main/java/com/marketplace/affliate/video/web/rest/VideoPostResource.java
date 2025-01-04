package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.VideoPost;
import com.marketplace.affliate.video.repository.VideoPostRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.VideoPost}.
 */
@RestController
@RequestMapping("/api/video-posts")
@Transactional
public class VideoPostResource {

    private static final Logger LOG = LoggerFactory.getLogger(VideoPostResource.class);

    private static final String ENTITY_NAME = "videoPost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoPostRepository videoPostRepository;

    public VideoPostResource(VideoPostRepository videoPostRepository) {
        this.videoPostRepository = videoPostRepository;
    }

    /**
     * {@code POST  /video-posts} : Create a new videoPost.
     *
     * @param videoPost the videoPost to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoPost, or with status {@code 400 (Bad Request)} if the videoPost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VideoPost> createVideoPost(@RequestBody VideoPost videoPost) throws URISyntaxException {
        LOG.debug("REST request to save VideoPost : {}", videoPost);
        if (videoPost.getId() != null) {
            throw new BadRequestAlertException("A new videoPost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        videoPost = videoPostRepository.save(videoPost);
        return ResponseEntity.created(new URI("/api/video-posts/" + videoPost.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, videoPost.getId().toString()))
            .body(videoPost);
    }

    /**
     * {@code PUT  /video-posts/:id} : Updates an existing videoPost.
     *
     * @param id the id of the videoPost to save.
     * @param videoPost the videoPost to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoPost,
     * or with status {@code 400 (Bad Request)} if the videoPost is not valid,
     * or with status {@code 500 (Internal Server Error)} if the videoPost couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VideoPost> updateVideoPost(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoPost videoPost
    ) throws URISyntaxException {
        LOG.debug("REST request to update VideoPost : {}, {}", id, videoPost);
        if (videoPost.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoPost.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoPostRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        videoPost = videoPostRepository.save(videoPost);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoPost.getId().toString()))
            .body(videoPost);
    }

    /**
     * {@code PATCH  /video-posts/:id} : Partial updates given fields of an existing videoPost, field will ignore if it is null
     *
     * @param id the id of the videoPost to save.
     * @param videoPost the videoPost to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoPost,
     * or with status {@code 400 (Bad Request)} if the videoPost is not valid,
     * or with status {@code 404 (Not Found)} if the videoPost is not found,
     * or with status {@code 500 (Internal Server Error)} if the videoPost couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VideoPost> partialUpdateVideoPost(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoPost videoPost
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update VideoPost partially : {}, {}", id, videoPost);
        if (videoPost.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoPost.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoPostRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VideoPost> result = videoPostRepository
            .findById(videoPost.getId())
            .map(existingVideoPost -> {
                if (videoPost.getTitle() != null) {
                    existingVideoPost.setTitle(videoPost.getTitle());
                }
                if (videoPost.getDescription() != null) {
                    existingVideoPost.setDescription(videoPost.getDescription());
                }
                if (videoPost.getUrl() != null) {
                    existingVideoPost.setUrl(videoPost.getUrl());
                }
                if (videoPost.getUrlType() != null) {
                    existingVideoPost.setUrlType(videoPost.getUrlType());
                }
                if (videoPost.getIsAIGenerated() != null) {
                    existingVideoPost.setIsAIGenerated(videoPost.getIsAIGenerated());
                }
                if (videoPost.getIsPremium() != null) {
                    existingVideoPost.setIsPremium(videoPost.getIsPremium());
                }
                if (videoPost.getIsBlocked() != null) {
                    existingVideoPost.setIsBlocked(videoPost.getIsBlocked());
                }
                if (videoPost.getIsModerated() != null) {
                    existingVideoPost.setIsModerated(videoPost.getIsModerated());
                }
                if (videoPost.getIsActive() != null) {
                    existingVideoPost.setIsActive(videoPost.getIsActive());
                }
                if (videoPost.getCreatedBy() != null) {
                    existingVideoPost.setCreatedBy(videoPost.getCreatedBy());
                }
                if (videoPost.getCreatedOn() != null) {
                    existingVideoPost.setCreatedOn(videoPost.getCreatedOn());
                }
                if (videoPost.getUpdatedBy() != null) {
                    existingVideoPost.setUpdatedBy(videoPost.getUpdatedBy());
                }
                if (videoPost.getUpdatedOn() != null) {
                    existingVideoPost.setUpdatedOn(videoPost.getUpdatedOn());
                }

                return existingVideoPost;
            })
            .map(videoPostRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoPost.getId().toString())
        );
    }

    /**
     * {@code GET  /video-posts} : get all the videoPosts.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoPosts in body.
     */
    @GetMapping("")
    public List<VideoPost> getAllVideoPosts(@RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload) {
        LOG.debug("REST request to get all VideoPosts");
        if (eagerload) {
            return videoPostRepository.findAllWithEagerRelationships();
        } else {
            return videoPostRepository.findAll();
        }
    }

    /**
     * {@code GET  /video-posts/:id} : get the "id" videoPost.
     *
     * @param id the id of the videoPost to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoPost, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VideoPost> getVideoPost(@PathVariable("id") Long id) {
        LOG.debug("REST request to get VideoPost : {}", id);
        Optional<VideoPost> videoPost = videoPostRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(videoPost);
    }

    /**
     * {@code DELETE  /video-posts/:id} : delete the "id" videoPost.
     *
     * @param id the id of the videoPost to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoPost(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete VideoPost : {}", id);
        videoPostRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
