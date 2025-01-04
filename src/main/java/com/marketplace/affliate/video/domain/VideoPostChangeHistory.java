package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.ChangeType;
import com.marketplace.affliate.video.domain.enumeration.UrlType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VideoPostChangeHistory.
 */
@Entity
@Table(name = "video_post_change_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VideoPostChangeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "change_type")
    private ChangeType changeType;

    @Column(name = "oldtitle")
    private String oldtitle;

    @Column(name = "old_description")
    private String oldDescription;

    @Column(name = "old_url")
    private String oldUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_url_type")
    private UrlType oldUrlType;

    @Column(name = "old_is_ai_generated")
    private Boolean oldIsAIGenerated;

    @Column(name = "old_is_premium")
    private Boolean oldIsPremium;

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
    @JsonIgnoreProperties(
        value = { "reviews", "changesHistories", "competition", "tags", "affinityVectors", "competitionWinner", "creator" },
        allowSetters = true
    )
    private VideoPost post;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VideoPostChangeHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChangeType getChangeType() {
        return this.changeType;
    }

    public VideoPostChangeHistory changeType(ChangeType changeType) {
        this.setChangeType(changeType);
        return this;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public String getOldtitle() {
        return this.oldtitle;
    }

    public VideoPostChangeHistory oldtitle(String oldtitle) {
        this.setOldtitle(oldtitle);
        return this;
    }

    public void setOldtitle(String oldtitle) {
        this.oldtitle = oldtitle;
    }

    public String getOldDescription() {
        return this.oldDescription;
    }

    public VideoPostChangeHistory oldDescription(String oldDescription) {
        this.setOldDescription(oldDescription);
        return this;
    }

    public void setOldDescription(String oldDescription) {
        this.oldDescription = oldDescription;
    }

    public String getOldUrl() {
        return this.oldUrl;
    }

    public VideoPostChangeHistory oldUrl(String oldUrl) {
        this.setOldUrl(oldUrl);
        return this;
    }

    public void setOldUrl(String oldUrl) {
        this.oldUrl = oldUrl;
    }

    public UrlType getOldUrlType() {
        return this.oldUrlType;
    }

    public VideoPostChangeHistory oldUrlType(UrlType oldUrlType) {
        this.setOldUrlType(oldUrlType);
        return this;
    }

    public void setOldUrlType(UrlType oldUrlType) {
        this.oldUrlType = oldUrlType;
    }

    public Boolean getOldIsAIGenerated() {
        return this.oldIsAIGenerated;
    }

    public VideoPostChangeHistory oldIsAIGenerated(Boolean oldIsAIGenerated) {
        this.setOldIsAIGenerated(oldIsAIGenerated);
        return this;
    }

    public void setOldIsAIGenerated(Boolean oldIsAIGenerated) {
        this.oldIsAIGenerated = oldIsAIGenerated;
    }

    public Boolean getOldIsPremium() {
        return this.oldIsPremium;
    }

    public VideoPostChangeHistory oldIsPremium(Boolean oldIsPremium) {
        this.setOldIsPremium(oldIsPremium);
        return this;
    }

    public void setOldIsPremium(Boolean oldIsPremium) {
        this.oldIsPremium = oldIsPremium;
    }

    public Boolean getOldIsBlocked() {
        return this.oldIsBlocked;
    }

    public VideoPostChangeHistory oldIsBlocked(Boolean oldIsBlocked) {
        this.setOldIsBlocked(oldIsBlocked);
        return this;
    }

    public void setOldIsBlocked(Boolean oldIsBlocked) {
        this.oldIsBlocked = oldIsBlocked;
    }

    public Boolean getOldIsModerated() {
        return this.oldIsModerated;
    }

    public VideoPostChangeHistory oldIsModerated(Boolean oldIsModerated) {
        this.setOldIsModerated(oldIsModerated);
        return this;
    }

    public void setOldIsModerated(Boolean oldIsModerated) {
        this.oldIsModerated = oldIsModerated;
    }

    public Boolean getOldIsActive() {
        return this.oldIsActive;
    }

    public VideoPostChangeHistory oldIsActive(Boolean oldIsActive) {
        this.setOldIsActive(oldIsActive);
        return this;
    }

    public void setOldIsActive(Boolean oldIsActive) {
        this.oldIsActive = oldIsActive;
    }

    public String getOldCreatedBy() {
        return this.oldCreatedBy;
    }

    public VideoPostChangeHistory oldCreatedBy(String oldCreatedBy) {
        this.setOldCreatedBy(oldCreatedBy);
        return this;
    }

    public void setOldCreatedBy(String oldCreatedBy) {
        this.oldCreatedBy = oldCreatedBy;
    }

    public LocalDate getOldCreatedOn() {
        return this.oldCreatedOn;
    }

    public VideoPostChangeHistory oldCreatedOn(LocalDate oldCreatedOn) {
        this.setOldCreatedOn(oldCreatedOn);
        return this;
    }

    public void setOldCreatedOn(LocalDate oldCreatedOn) {
        this.oldCreatedOn = oldCreatedOn;
    }

    public String getOldUpdatedBy() {
        return this.oldUpdatedBy;
    }

    public VideoPostChangeHistory oldUpdatedBy(String oldUpdatedBy) {
        this.setOldUpdatedBy(oldUpdatedBy);
        return this;
    }

    public void setOldUpdatedBy(String oldUpdatedBy) {
        this.oldUpdatedBy = oldUpdatedBy;
    }

    public LocalDate getOldUpdatedOn() {
        return this.oldUpdatedOn;
    }

    public VideoPostChangeHistory oldUpdatedOn(LocalDate oldUpdatedOn) {
        this.setOldUpdatedOn(oldUpdatedOn);
        return this;
    }

    public void setOldUpdatedOn(LocalDate oldUpdatedOn) {
        this.oldUpdatedOn = oldUpdatedOn;
    }

    public VideoPost getPost() {
        return this.post;
    }

    public void setPost(VideoPost videoPost) {
        this.post = videoPost;
    }

    public VideoPostChangeHistory post(VideoPost videoPost) {
        this.setPost(videoPost);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoPostChangeHistory)) {
            return false;
        }
        return getId() != null && getId().equals(((VideoPostChangeHistory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VideoPostChangeHistory{" +
            "id=" + getId() +
            ", changeType='" + getChangeType() + "'" +
            ", oldtitle='" + getOldtitle() + "'" +
            ", oldDescription='" + getOldDescription() + "'" +
            ", oldUrl='" + getOldUrl() + "'" +
            ", oldUrlType='" + getOldUrlType() + "'" +
            ", oldIsAIGenerated='" + getOldIsAIGenerated() + "'" +
            ", oldIsPremium='" + getOldIsPremium() + "'" +
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
