package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.UrlType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VideoPost.
 */
@Entity
@Table(name = "video_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VideoPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "url_type")
    private UrlType urlType;

    @Column(name = "is_ai_generated")
    private Boolean isAIGenerated;

    @Column(name = "is_premium")
    private Boolean isPremium;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_video_post__tags",
        joinColumns = @JoinColumn(name = "video_post_id"),
        inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "posts" }, allowSetters = true)
    private Set<VideoTag> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "bank", "posts", "company", "contactsMades", "contactsReceiveds" }, allowSetters = true)
    private VideoUser creator;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VideoPost id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public VideoPost title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public VideoPost description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public VideoPost url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UrlType getUrlType() {
        return this.urlType;
    }

    public VideoPost urlType(UrlType urlType) {
        this.setUrlType(urlType);
        return this;
    }

    public void setUrlType(UrlType urlType) {
        this.urlType = urlType;
    }

    public Boolean getIsAIGenerated() {
        return this.isAIGenerated;
    }

    public VideoPost isAIGenerated(Boolean isAIGenerated) {
        this.setIsAIGenerated(isAIGenerated);
        return this;
    }

    public void setIsAIGenerated(Boolean isAIGenerated) {
        this.isAIGenerated = isAIGenerated;
    }

    public Boolean getIsPremium() {
        return this.isPremium;
    }

    public VideoPost isPremium(Boolean isPremium) {
        this.setIsPremium(isPremium);
        return this;
    }

    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public Boolean getIsBlocked() {
        return this.isBlocked;
    }

    public VideoPost isBlocked(Boolean isBlocked) {
        this.setIsBlocked(isBlocked);
        return this;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Boolean getIsModerated() {
        return this.isModerated;
    }

    public VideoPost isModerated(Boolean isModerated) {
        this.setIsModerated(isModerated);
        return this;
    }

    public void setIsModerated(Boolean isModerated) {
        this.isModerated = isModerated;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public VideoPost isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public VideoPost createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public VideoPost createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public VideoPost updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public VideoPost updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<VideoTag> getTags() {
        return this.tags;
    }

    public void setTags(Set<VideoTag> videoTags) {
        this.tags = videoTags;
    }

    public VideoPost tags(Set<VideoTag> videoTags) {
        this.setTags(videoTags);
        return this;
    }

    public VideoPost addTags(VideoTag videoTag) {
        this.tags.add(videoTag);
        return this;
    }

    public VideoPost removeTags(VideoTag videoTag) {
        this.tags.remove(videoTag);
        return this;
    }

    public VideoUser getCreator() {
        return this.creator;
    }

    public void setCreator(VideoUser videoUser) {
        this.creator = videoUser;
    }

    public VideoPost creator(VideoUser videoUser) {
        this.setCreator(videoUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoPost)) {
            return false;
        }
        return getId() != null && getId().equals(((VideoPost) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VideoPost{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", url='" + getUrl() + "'" +
            ", urlType='" + getUrlType() + "'" +
            ", isAIGenerated='" + getIsAIGenerated() + "'" +
            ", isPremium='" + getIsPremium() + "'" +
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
