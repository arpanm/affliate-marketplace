package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VideoTag.
 */
@Entity
@Table(name = "video_tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VideoTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "is_moderated")
    private Boolean isModerated;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deletion_reason")
    private Boolean deletionReason;

    @Column(name = "merged_with_tag_name")
    private String mergedWithTagName;

    @Column(name = "merged_with_tag_code")
    private String mergedWithTagCode;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "reviews", "changesHistories", "competition", "tags", "affinityVectors", "competitionWinner", "creator" },
        allowSetters = true
    )
    private Set<VideoPost> posts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VideoTag id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public VideoTag name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public VideoTag code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsModerated() {
        return this.isModerated;
    }

    public VideoTag isModerated(Boolean isModerated) {
        this.setIsModerated(isModerated);
        return this;
    }

    public void setIsModerated(Boolean isModerated) {
        this.isModerated = isModerated;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public VideoTag isDeleted(Boolean isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getDeletionReason() {
        return this.deletionReason;
    }

    public VideoTag deletionReason(Boolean deletionReason) {
        this.setDeletionReason(deletionReason);
        return this;
    }

    public void setDeletionReason(Boolean deletionReason) {
        this.deletionReason = deletionReason;
    }

    public String getMergedWithTagName() {
        return this.mergedWithTagName;
    }

    public VideoTag mergedWithTagName(String mergedWithTagName) {
        this.setMergedWithTagName(mergedWithTagName);
        return this;
    }

    public void setMergedWithTagName(String mergedWithTagName) {
        this.mergedWithTagName = mergedWithTagName;
    }

    public String getMergedWithTagCode() {
        return this.mergedWithTagCode;
    }

    public VideoTag mergedWithTagCode(String mergedWithTagCode) {
        this.setMergedWithTagCode(mergedWithTagCode);
        return this;
    }

    public void setMergedWithTagCode(String mergedWithTagCode) {
        this.mergedWithTagCode = mergedWithTagCode;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public VideoTag isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public VideoTag createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public VideoTag createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public VideoTag updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public VideoTag updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<VideoPost> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<VideoPost> videoPosts) {
        if (this.posts != null) {
            this.posts.forEach(i -> i.removeTags(this));
        }
        if (videoPosts != null) {
            videoPosts.forEach(i -> i.addTags(this));
        }
        this.posts = videoPosts;
    }

    public VideoTag posts(Set<VideoPost> videoPosts) {
        this.setPosts(videoPosts);
        return this;
    }

    public VideoTag addPosts(VideoPost videoPost) {
        this.posts.add(videoPost);
        videoPost.getTags().add(this);
        return this;
    }

    public VideoTag removePosts(VideoPost videoPost) {
        this.posts.remove(videoPost);
        videoPost.getTags().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoTag)) {
            return false;
        }
        return getId() != null && getId().equals(((VideoTag) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VideoTag{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", isModerated='" + getIsModerated() + "'" +
            ", isDeleted='" + getIsDeleted() + "'" +
            ", deletionReason='" + getDeletionReason() + "'" +
            ", mergedWithTagName='" + getMergedWithTagName() + "'" +
            ", mergedWithTagCode='" + getMergedWithTagCode() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
