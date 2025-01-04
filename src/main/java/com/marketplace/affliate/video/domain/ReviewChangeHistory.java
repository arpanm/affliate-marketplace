package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.ChangeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReviewChangeHistory.
 */
@Entity
@Table(name = "review_change_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReviewChangeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "change_type")
    private ChangeType changeType;

    @Column(name = "old_is_liked")
    private Boolean oldIsLiked;

    @Column(name = "old_is_skipped")
    private Boolean oldIsSkipped;

    @Column(name = "old_is_disliked")
    private Boolean oldIsDisliked;

    @Column(name = "old_is_watched")
    private Boolean oldIsWatched;

    @Column(name = "old_is_fully_watched")
    private Boolean oldIsFullyWatched;

    @Column(name = "old_is_reported")
    private Boolean oldIsReported;

    @Min(value = 1L)
    @Max(value = 5L)
    @Column(name = "old_rating")
    private Long oldRating;

    @Column(name = "old_comment")
    private String oldComment;

    @Column(name = "old_report_reason")
    private String oldReportReason;

    @Column(name = "old_is_blocked")
    private Boolean oldIsBlocked;

    @Column(name = "old_is_moderated")
    private Boolean oldIsModerated;

    @Column(name = "old_is_active")
    private Boolean oldIsActive;

    @Column(name = "old_created_by")
    private String oldCreatedBy;

    @Column(name = "old_created_on")
    private LocalDate oldCreatedOn;

    @Column(name = "old_updated_by")
    private String oldUpdatedBy;

    @Column(name = "old_updated_on")
    private LocalDate oldUpdatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "changesHistories", "reviewer", "post" }, allowSetters = true)
    private Review review;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReviewChangeHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChangeType getChangeType() {
        return this.changeType;
    }

    public ReviewChangeHistory changeType(ChangeType changeType) {
        this.setChangeType(changeType);
        return this;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public Boolean getOldIsLiked() {
        return this.oldIsLiked;
    }

    public ReviewChangeHistory oldIsLiked(Boolean oldIsLiked) {
        this.setOldIsLiked(oldIsLiked);
        return this;
    }

    public void setOldIsLiked(Boolean oldIsLiked) {
        this.oldIsLiked = oldIsLiked;
    }

    public Boolean getOldIsSkipped() {
        return this.oldIsSkipped;
    }

    public ReviewChangeHistory oldIsSkipped(Boolean oldIsSkipped) {
        this.setOldIsSkipped(oldIsSkipped);
        return this;
    }

    public void setOldIsSkipped(Boolean oldIsSkipped) {
        this.oldIsSkipped = oldIsSkipped;
    }

    public Boolean getOldIsDisliked() {
        return this.oldIsDisliked;
    }

    public ReviewChangeHistory oldIsDisliked(Boolean oldIsDisliked) {
        this.setOldIsDisliked(oldIsDisliked);
        return this;
    }

    public void setOldIsDisliked(Boolean oldIsDisliked) {
        this.oldIsDisliked = oldIsDisliked;
    }

    public Boolean getOldIsWatched() {
        return this.oldIsWatched;
    }

    public ReviewChangeHistory oldIsWatched(Boolean oldIsWatched) {
        this.setOldIsWatched(oldIsWatched);
        return this;
    }

    public void setOldIsWatched(Boolean oldIsWatched) {
        this.oldIsWatched = oldIsWatched;
    }

    public Boolean getOldIsFullyWatched() {
        return this.oldIsFullyWatched;
    }

    public ReviewChangeHistory oldIsFullyWatched(Boolean oldIsFullyWatched) {
        this.setOldIsFullyWatched(oldIsFullyWatched);
        return this;
    }

    public void setOldIsFullyWatched(Boolean oldIsFullyWatched) {
        this.oldIsFullyWatched = oldIsFullyWatched;
    }

    public Boolean getOldIsReported() {
        return this.oldIsReported;
    }

    public ReviewChangeHistory oldIsReported(Boolean oldIsReported) {
        this.setOldIsReported(oldIsReported);
        return this;
    }

    public void setOldIsReported(Boolean oldIsReported) {
        this.oldIsReported = oldIsReported;
    }

    public Long getOldRating() {
        return this.oldRating;
    }

    public ReviewChangeHistory oldRating(Long oldRating) {
        this.setOldRating(oldRating);
        return this;
    }

    public void setOldRating(Long oldRating) {
        this.oldRating = oldRating;
    }

    public String getOldComment() {
        return this.oldComment;
    }

    public ReviewChangeHistory oldComment(String oldComment) {
        this.setOldComment(oldComment);
        return this;
    }

    public void setOldComment(String oldComment) {
        this.oldComment = oldComment;
    }

    public String getOldReportReason() {
        return this.oldReportReason;
    }

    public ReviewChangeHistory oldReportReason(String oldReportReason) {
        this.setOldReportReason(oldReportReason);
        return this;
    }

    public void setOldReportReason(String oldReportReason) {
        this.oldReportReason = oldReportReason;
    }

    public Boolean getOldIsBlocked() {
        return this.oldIsBlocked;
    }

    public ReviewChangeHistory oldIsBlocked(Boolean oldIsBlocked) {
        this.setOldIsBlocked(oldIsBlocked);
        return this;
    }

    public void setOldIsBlocked(Boolean oldIsBlocked) {
        this.oldIsBlocked = oldIsBlocked;
    }

    public Boolean getOldIsModerated() {
        return this.oldIsModerated;
    }

    public ReviewChangeHistory oldIsModerated(Boolean oldIsModerated) {
        this.setOldIsModerated(oldIsModerated);
        return this;
    }

    public void setOldIsModerated(Boolean oldIsModerated) {
        this.oldIsModerated = oldIsModerated;
    }

    public Boolean getOldIsActive() {
        return this.oldIsActive;
    }

    public ReviewChangeHistory oldIsActive(Boolean oldIsActive) {
        this.setOldIsActive(oldIsActive);
        return this;
    }

    public void setOldIsActive(Boolean oldIsActive) {
        this.oldIsActive = oldIsActive;
    }

    public String getOldCreatedBy() {
        return this.oldCreatedBy;
    }

    public ReviewChangeHistory oldCreatedBy(String oldCreatedBy) {
        this.setOldCreatedBy(oldCreatedBy);
        return this;
    }

    public void setOldCreatedBy(String oldCreatedBy) {
        this.oldCreatedBy = oldCreatedBy;
    }

    public LocalDate getOldCreatedOn() {
        return this.oldCreatedOn;
    }

    public ReviewChangeHistory oldCreatedOn(LocalDate oldCreatedOn) {
        this.setOldCreatedOn(oldCreatedOn);
        return this;
    }

    public void setOldCreatedOn(LocalDate oldCreatedOn) {
        this.oldCreatedOn = oldCreatedOn;
    }

    public String getOldUpdatedBy() {
        return this.oldUpdatedBy;
    }

    public ReviewChangeHistory oldUpdatedBy(String oldUpdatedBy) {
        this.setOldUpdatedBy(oldUpdatedBy);
        return this;
    }

    public void setOldUpdatedBy(String oldUpdatedBy) {
        this.oldUpdatedBy = oldUpdatedBy;
    }

    public LocalDate getOldUpdatedOn() {
        return this.oldUpdatedOn;
    }

    public ReviewChangeHistory oldUpdatedOn(LocalDate oldUpdatedOn) {
        this.setOldUpdatedOn(oldUpdatedOn);
        return this;
    }

    public void setOldUpdatedOn(LocalDate oldUpdatedOn) {
        this.oldUpdatedOn = oldUpdatedOn;
    }

    public Review getReview() {
        return this.review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public ReviewChangeHistory review(Review review) {
        this.setReview(review);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReviewChangeHistory)) {
            return false;
        }
        return getId() != null && getId().equals(((ReviewChangeHistory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReviewChangeHistory{" +
            "id=" + getId() +
            ", changeType='" + getChangeType() + "'" +
            ", oldIsLiked='" + getOldIsLiked() + "'" +
            ", oldIsSkipped='" + getOldIsSkipped() + "'" +
            ", oldIsDisliked='" + getOldIsDisliked() + "'" +
            ", oldIsWatched='" + getOldIsWatched() + "'" +
            ", oldIsFullyWatched='" + getOldIsFullyWatched() + "'" +
            ", oldIsReported='" + getOldIsReported() + "'" +
            ", oldRating=" + getOldRating() +
            ", oldComment='" + getOldComment() + "'" +
            ", oldReportReason='" + getOldReportReason() + "'" +
            ", oldIsBlocked='" + getOldIsBlocked() + "'" +
            ", oldIsModerated='" + getOldIsModerated() + "'" +
            ", oldIsActive='" + getOldIsActive() + "'" +
            ", oldCreatedBy='" + getOldCreatedBy() + "'" +
            ", oldCreatedOn='" + getOldCreatedOn() + "'" +
            ", oldUpdatedBy='" + getOldUpdatedBy() + "'" +
            ", oldUpdatedOn='" + getOldUpdatedOn() + "'" +
            "}";
    }
}
