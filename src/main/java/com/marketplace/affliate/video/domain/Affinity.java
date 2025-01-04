package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.Segment;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Affinity.
 */
@Entity
@Table(name = "affinity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Affinity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "segment")
    private Segment segment;

    @Column(name = "score")
    private Long score;

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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "affinityVectors")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "reviews", "changesHistories", "competition", "tags", "affinityVectors", "competitionWinner", "creator" },
        allowSetters = true
    )
    private Set<VideoPost> posts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "affinityVectors")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "bank", "posts", "reviews", "aiSessions", "affinityVectors", "company", "contactsMades", "contactsReceiveds" },
        allowSetters = true
    )
    private Set<VideoUser> users = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Affinity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Segment getSegment() {
        return this.segment;
    }

    public Affinity segment(Segment segment) {
        this.setSegment(segment);
        return this;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Long getScore() {
        return this.score;
    }

    public Affinity score(Long score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Affinity isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Affinity createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public Affinity createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Affinity updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public Affinity updatedOn(LocalDate updatedOn) {
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
            this.posts.forEach(i -> i.removeAffinityVectors(this));
        }
        if (videoPosts != null) {
            videoPosts.forEach(i -> i.addAffinityVectors(this));
        }
        this.posts = videoPosts;
    }

    public Affinity posts(Set<VideoPost> videoPosts) {
        this.setPosts(videoPosts);
        return this;
    }

    public Affinity addPosts(VideoPost videoPost) {
        this.posts.add(videoPost);
        videoPost.getAffinityVectors().add(this);
        return this;
    }

    public Affinity removePosts(VideoPost videoPost) {
        this.posts.remove(videoPost);
        videoPost.getAffinityVectors().remove(this);
        return this;
    }

    public Set<VideoUser> getUsers() {
        return this.users;
    }

    public void setUsers(Set<VideoUser> videoUsers) {
        if (this.users != null) {
            this.users.forEach(i -> i.removeAffinityVectors(this));
        }
        if (videoUsers != null) {
            videoUsers.forEach(i -> i.addAffinityVectors(this));
        }
        this.users = videoUsers;
    }

    public Affinity users(Set<VideoUser> videoUsers) {
        this.setUsers(videoUsers);
        return this;
    }

    public Affinity addUsers(VideoUser videoUser) {
        this.users.add(videoUser);
        videoUser.getAffinityVectors().add(this);
        return this;
    }

    public Affinity removeUsers(VideoUser videoUser) {
        this.users.remove(videoUser);
        videoUser.getAffinityVectors().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Affinity)) {
            return false;
        }
        return getId() != null && getId().equals(((Affinity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Affinity{" +
            "id=" + getId() +
            ", segment='" + getSegment() + "'" +
            ", score=" + getScore() +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
