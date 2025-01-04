package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.VideoUser;
import com.marketplace.affliate.video.repository.VideoUserRepository;
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
 * REST controller for managing {@link com.marketplace.affliate.video.domain.VideoUser}.
 */
@RestController
@RequestMapping("/api/video-users")
@Transactional
public class VideoUserResource {

    private static final Logger LOG = LoggerFactory.getLogger(VideoUserResource.class);

    private static final String ENTITY_NAME = "videoUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoUserRepository videoUserRepository;

    public VideoUserResource(VideoUserRepository videoUserRepository) {
        this.videoUserRepository = videoUserRepository;
    }

    /**
     * {@code POST  /video-users} : Create a new videoUser.
     *
     * @param videoUser the videoUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoUser, or with status {@code 400 (Bad Request)} if the videoUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VideoUser> createVideoUser(@Valid @RequestBody VideoUser videoUser) throws URISyntaxException {
        LOG.debug("REST request to save VideoUser : {}", videoUser);
        if (videoUser.getId() != null) {
            throw new BadRequestAlertException("A new videoUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        videoUser = videoUserRepository.save(videoUser);
        return ResponseEntity.created(new URI("/api/video-users/" + videoUser.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, videoUser.getId().toString()))
            .body(videoUser);
    }

    /**
     * {@code PUT  /video-users/:id} : Updates an existing videoUser.
     *
     * @param id the id of the videoUser to save.
     * @param videoUser the videoUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoUser,
     * or with status {@code 400 (Bad Request)} if the videoUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the videoUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VideoUser> updateVideoUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VideoUser videoUser
    ) throws URISyntaxException {
        LOG.debug("REST request to update VideoUser : {}, {}", id, videoUser);
        if (videoUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        videoUser = videoUserRepository.save(videoUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoUser.getId().toString()))
            .body(videoUser);
    }

    /**
     * {@code PATCH  /video-users/:id} : Partial updates given fields of an existing videoUser, field will ignore if it is null
     *
     * @param id the id of the videoUser to save.
     * @param videoUser the videoUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoUser,
     * or with status {@code 400 (Bad Request)} if the videoUser is not valid,
     * or with status {@code 404 (Not Found)} if the videoUser is not found,
     * or with status {@code 500 (Internal Server Error)} if the videoUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VideoUser> partialUpdateVideoUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VideoUser videoUser
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update VideoUser partially : {}, {}", id, videoUser);
        if (videoUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VideoUser> result = videoUserRepository
            .findById(videoUser.getId())
            .map(existingVideoUser -> {
                if (videoUser.getUserId() != null) {
                    existingVideoUser.setUserId(videoUser.getUserId());
                }
                if (videoUser.getUserName() != null) {
                    existingVideoUser.setUserName(videoUser.getUserName());
                }
                if (videoUser.getName() != null) {
                    existingVideoUser.setName(videoUser.getName());
                }
                if (videoUser.getPhone() != null) {
                    existingVideoUser.setPhone(videoUser.getPhone());
                }
                if (videoUser.getEmail() != null) {
                    existingVideoUser.setEmail(videoUser.getEmail());
                }
                if (videoUser.getDescription() != null) {
                    existingVideoUser.setDescription(videoUser.getDescription());
                }
                if (videoUser.getImageUrl() != null) {
                    existingVideoUser.setImageUrl(videoUser.getImageUrl());
                }
                if (videoUser.getUserType() != null) {
                    existingVideoUser.setUserType(videoUser.getUserType());
                }
                if (videoUser.getIsBlocked() != null) {
                    existingVideoUser.setIsBlocked(videoUser.getIsBlocked());
                }
                if (videoUser.getBlockedTill() != null) {
                    existingVideoUser.setBlockedTill(videoUser.getBlockedTill());
                }
                if (videoUser.getIsActive() != null) {
                    existingVideoUser.setIsActive(videoUser.getIsActive());
                }
                if (videoUser.getCreatedBy() != null) {
                    existingVideoUser.setCreatedBy(videoUser.getCreatedBy());
                }
                if (videoUser.getCreatedOn() != null) {
                    existingVideoUser.setCreatedOn(videoUser.getCreatedOn());
                }
                if (videoUser.getUpdatedBy() != null) {
                    existingVideoUser.setUpdatedBy(videoUser.getUpdatedBy());
                }
                if (videoUser.getUpdatedOn() != null) {
                    existingVideoUser.setUpdatedOn(videoUser.getUpdatedOn());
                }

                return existingVideoUser;
            })
            .map(videoUserRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoUser.getId().toString())
        );
    }

    /**
     * {@code GET  /video-users} : get all the videoUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoUsers in body.
     */
    @GetMapping("")
    public List<VideoUser> getAllVideoUsers() {
        LOG.debug("REST request to get all VideoUsers");
        return videoUserRepository.findAll();
    }

    /**
     * {@code GET  /video-users/:id} : get the "id" videoUser.
     *
     * @param id the id of the videoUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VideoUser> getVideoUser(@PathVariable("id") Long id) {
        LOG.debug("REST request to get VideoUser : {}", id);
        Optional<VideoUser> videoUser = videoUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(videoUser);
    }

    /**
     * {@code DELETE  /video-users/:id} : delete the "id" videoUser.
     *
     * @param id the id of the videoUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoUser(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete VideoUser : {}", id);
        videoUserRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
