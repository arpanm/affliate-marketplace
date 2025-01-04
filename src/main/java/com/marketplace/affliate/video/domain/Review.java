package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Review.
 */
@Entity
@Table(name = "review")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_liked")
    private Boolean isLiked;

    @Column(name = "is_skipped")
    private Boolean isSkipped;

    @Column(name = "is_disliked")
    private Boolean isDisliked;

    @Column(name = "is_watched")
    private Boolean isWatched;

    @Column(name = "is_fully_watched")
    private Boolean isFullyWatched;

    @Column(name = "is_reported")
    private Boolean isReported;

    @Min(value = 1L)
    @Max(value = 5L)
    @Column(name = "rating")
    private Long rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "report_reason")
    private String reportReason;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "is_moderated")
    private Boolean isModerated;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "review" }, allowSetters = true)
    private Set<ReviewChangeHistory> changesHistories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "bank", "posts", "reviews", "aiSessions", "affinityVectors", "company", "contactsMades", "contactsReceiveds" },
        allowSetters = true
    )
    private VideoUser reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "reviews", "changesHistories", "competition", "tags", "affinityVectors", "competitionWinner", "creator" },
        allowSetters = true
    )
    private VideoPost post;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Review id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsLiked() {
        return this.isLiked;
    }

    public Review isLiked(Boolean isLiked) {
        this.setIsLiked(isLiked);
        return this;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Boolean getIsSkipped() {
        return this.isSkipped;
    }

    public Review isSkipped(Boolean isSkipped) {
        this.setIsSkipped(isSkipped);
        return this;
    }

    public void setIsSkipped(Boolean isSkipped) {
        this.isSkipped = isSkipped;
    }

    public Boolean getIsDisliked() {
        return this.isDisliked;
    }

    public Review isDisliked(Boolean isDisliked) {
        this.setIsDisliked(isDisliked);
        return this;
    }

    public void setIsDisliked(Boolean isDisliked) {
        this.isDisliked = isDisliked;
    }

    public Boolean getIsWatched() {
        return this.isWatched;
    }

    public Review isWatched(Boolean isWatched) {
        this.setIsWatched(isWatched);
        return this;
    }

    public void setIsWatched(Boolean isWatched) {
        this.isWatched = isWatched;
    }

    public Boolean getIsFullyWatched() {
        return this.isFullyWatched;
    }

    public Review isFullyWatched(Boolean isFullyWatched) {
        this.setIsFullyWatched(isFullyWatched);
        return this;
    }

    public void setIsFullyWatched(Boolean isFullyWatched) {
        this.isFullyWatched = isFullyWatched;
    }

    public Boolean getIsReported() {
        return this.isReported;
    }

    public Review isReported(Boolean isReported) {
        this.setIsReported(isReported);
        return this;
    }

    public void setIsReported(Boolean isReported) {
        this.isReported = isReported;
    }

    public Long getRating() {
        return this.rating;
    }

    public Review rating(Long rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getComment() {
        return this.comment;
    }

    public Review comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReportReason() {
        return this.reportReason;
    }

    public Review reportReason(String reportReason) {
        this.setReportReason(reportReason);
        return this;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public Boolean getIsBlocked() {
        return this.isBlocked;
    }

    public Review isBlocked(Boolean isBlocked) {
        this.setIsBlocked(isBlocked);
        return this;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Boolean getIsModerated() {
        return this.isModerated;
    }

    public Review isModerated(Boolean isModerated) {
        this.setIsModerated(isModerated);
        return this;
    }

    public void setIsModerated(Boolean isModerated) {
        this.isModerated = isModerated;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Review isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Review createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public Review createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Review updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public Review updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<ReviewChangeHistory> getChangesHistories() {
        return this.changesHistories;
    }

    public void setChangesHistories(Set<ReviewChangeHistory> reviewChangeHistories) {
        if (this.changesHistories != null) {
            this.changesHistories.forEach(i -> i.setReview(null));
        }
        if (reviewChangeHistories != null) {
            reviewChangeHistories.forEach(i -> i.setReview(this));
        }
        this.changesHistories = reviewChangeHistories;
    }

    public Review changesHistories(Set<ReviewChangeHistory> reviewChangeHistories) {
        this.setChangesHistories(reviewChangeHistories);
        return this;
    }

    public Review addChangesHistory(ReviewChangeHistory reviewChangeHistory) {
        this.changesHistories.add(reviewChangeHistory);
        reviewChangeHistory.setReview(this);
        return this;
    }

    public Review removeChangesHistory(ReviewChangeHistory reviewChangeHistory) {
        this.changesHistories.remove(reviewChangeHistory);
        reviewChangeHistory.setReview(null);
        return this;
    }

    public VideoUser getReviewer() {
        return this.reviewer;
    }

    public void setReviewer(VideoUser videoUser) {
        this.reviewer = videoUser;
    }

    public Review reviewer(VideoUser videoUser) {
        this.setReviewer(videoUser);
        return this;
    }

    public VideoPost getPost() {
        return this.post;
    }

    public void setPost(VideoPost videoPost) {
        this.post = videoPost;
    }

    public Review post(VideoPost videoPost) {
        this.setPost(videoPost);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)) {
            return false;
        }
        return getId() != null && getId().equals(((Review) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Review{" +
            "id=" + getId() +
            ", isLiked='" + getIsLiked() + "'" +
            ", isSkipped='" + getIsSkipped() + "'" +
            ", isDisliked='" + getIsDisliked() + "'" +
            ", isWatched='" + getIsWatched() + "'" +
            ", isFullyWatched='" + getIsFullyWatched() + "'" +
            ", isReported='" + getIsReported() + "'" +
            ", rating=" + getRating() +
            ", comment='" + getComment() + "'" +
            ", reportReason='" + getReportReason() + "'" +
            ", isBlocked='" + getIsBlocked() + "'" +
            ", isModerated='" + getIsModerated() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
