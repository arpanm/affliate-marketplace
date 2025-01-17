package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.VideoUserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VideoUser.
 */
@Entity
@Table(name = "video_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VideoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "name")
    private String name;

    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    @Column(name = "phone")
    private Long phone;

    @Pattern(regexp = "^(.+)@(.+)$")
    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private VideoUserType userType;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "blocked_till")
    private LocalDate blockedTill;

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

    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private BankDetails bank;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "reviews", "changesHistories", "competition", "tags", "affinityVectors", "competitionWinner", "creator" },
        allowSetters = true
    )
    private Set<VideoPost> posts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "changesHistories", "reviewer", "post" }, allowSetters = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "chats", "payments", "user", "tool" }, allowSetters = true)
    private Set<AiToolSession> aiSessions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_video_user__affinity_vectors",
        joinColumns = @JoinColumn(name = "video_user_id"),
        inverseJoinColumns = @JoinColumn(name = "affinity_vectors_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "posts", "users" }, allowSetters = true)
    private Set<Affinity> affinityVectors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "adminUsers", "sponsoredCompetitions" }, allowSetters = true)
    private Sponsor company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sender", "receiver" }, allowSetters = true)
    private Set<Contact> contactsMades = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiver")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sender", "receiver" }, allowSetters = true)
    private Set<Contact> contactsReceiveds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VideoUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public VideoUser userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public VideoUser userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return this.name;
    }

    public VideoUser name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return this.phone;
    }

    public VideoUser phone(Long phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public VideoUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return this.description;
    }

    public VideoUser description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public VideoUser imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public VideoUserType getUserType() {
        return this.userType;
    }

    public VideoUser userType(VideoUserType userType) {
        this.setUserType(userType);
        return this;
    }

    public void setUserType(VideoUserType userType) {
        this.userType = userType;
    }

    public Boolean getIsBlocked() {
        return this.isBlocked;
    }

    public VideoUser isBlocked(Boolean isBlocked) {
        this.setIsBlocked(isBlocked);
        return this;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public LocalDate getBlockedTill() {
        return this.blockedTill;
    }

    public VideoUser blockedTill(LocalDate blockedTill) {
        this.setBlockedTill(blockedTill);
        return this;
    }

    public void setBlockedTill(LocalDate blockedTill) {
        this.blockedTill = blockedTill;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public VideoUser isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public VideoUser createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public VideoUser createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public VideoUser updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public VideoUser updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public BankDetails getBank() {
        return this.bank;
    }

    public void setBank(BankDetails bankDetails) {
        this.bank = bankDetails;
    }

    public VideoUser bank(BankDetails bankDetails) {
        this.setBank(bankDetails);
        return this;
    }

    public Set<VideoPost> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<VideoPost> videoPosts) {
        if (this.posts != null) {
            this.posts.forEach(i -> i.setCreator(null));
        }
        if (videoPosts != null) {
            videoPosts.forEach(i -> i.setCreator(this));
        }
        this.posts = videoPosts;
    }

    public VideoUser posts(Set<VideoPost> videoPosts) {
        this.setPosts(videoPosts);
        return this;
    }

    public VideoUser addPosts(VideoPost videoPost) {
        this.posts.add(videoPost);
        videoPost.setCreator(this);
        return this;
    }

    public VideoUser removePosts(VideoPost videoPost) {
        this.posts.remove(videoPost);
        videoPost.setCreator(null);
        return this;
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<Review> reviews) {
        if (this.reviews != null) {
            this.reviews.forEach(i -> i.setReviewer(null));
        }
        if (reviews != null) {
            reviews.forEach(i -> i.setReviewer(this));
        }
        this.reviews = reviews;
    }

    public VideoUser reviews(Set<Review> reviews) {
        this.setReviews(reviews);
        return this;
    }

    public VideoUser addReviews(Review review) {
        this.reviews.add(review);
        review.setReviewer(this);
        return this;
    }

    public VideoUser removeReviews(Review review) {
        this.reviews.remove(review);
        review.setReviewer(null);
        return this;
    }

    public Set<AiToolSession> getAiSessions() {
        return this.aiSessions;
    }

    public void setAiSessions(Set<AiToolSession> aiToolSessions) {
        if (this.aiSessions != null) {
            this.aiSessions.forEach(i -> i.setUser(null));
        }
        if (aiToolSessions != null) {
            aiToolSessions.forEach(i -> i.setUser(this));
        }
        this.aiSessions = aiToolSessions;
    }

    public VideoUser aiSessions(Set<AiToolSession> aiToolSessions) {
        this.setAiSessions(aiToolSessions);
        return this;
    }

    public VideoUser addAiSessions(AiToolSession aiToolSession) {
        this.aiSessions.add(aiToolSession);
        aiToolSession.setUser(this);
        return this;
    }

    public VideoUser removeAiSessions(AiToolSession aiToolSession) {
        this.aiSessions.remove(aiToolSession);
        aiToolSession.setUser(null);
        return this;
    }

    public Set<Affinity> getAffinityVectors() {
        return this.affinityVectors;
    }

    public void setAffinityVectors(Set<Affinity> affinities) {
        this.affinityVectors = affinities;
    }

    public VideoUser affinityVectors(Set<Affinity> affinities) {
        this.setAffinityVectors(affinities);
        return this;
    }

    public VideoUser addAffinityVectors(Affinity affinity) {
        this.affinityVectors.add(affinity);
        return this;
    }

    public VideoUser removeAffinityVectors(Affinity affinity) {
        this.affinityVectors.remove(affinity);
        return this;
    }

    public Sponsor getCompany() {
        return this.company;
    }

    public void setCompany(Sponsor sponsor) {
        this.company = sponsor;
    }

    public VideoUser company(Sponsor sponsor) {
        this.setCompany(sponsor);
        return this;
    }

    public Set<Contact> getContactsMades() {
        return this.contactsMades;
    }

    public void setContactsMades(Set<Contact> contacts) {
        if (this.contactsMades != null) {
            this.contactsMades.forEach(i -> i.setSender(null));
        }
        if (contacts != null) {
            contacts.forEach(i -> i.setSender(this));
        }
        this.contactsMades = contacts;
    }

    public VideoUser contactsMades(Set<Contact> contacts) {
        this.setContactsMades(contacts);
        return this;
    }

    public VideoUser addContactsMade(Contact contact) {
        this.contactsMades.add(contact);
        contact.setSender(this);
        return this;
    }

    public VideoUser removeContactsMade(Contact contact) {
        this.contactsMades.remove(contact);
        contact.setSender(null);
        return this;
    }

    public Set<Contact> getContactsReceiveds() {
        return this.contactsReceiveds;
    }

    public void setContactsReceiveds(Set<Contact> contacts) {
        if (this.contactsReceiveds != null) {
            this.contactsReceiveds.forEach(i -> i.setReceiver(null));
        }
        if (contacts != null) {
            contacts.forEach(i -> i.setReceiver(this));
        }
        this.contactsReceiveds = contacts;
    }

    public VideoUser contactsReceiveds(Set<Contact> contacts) {
        this.setContactsReceiveds(contacts);
        return this;
    }

    public VideoUser addContactsReceived(Contact contact) {
        this.contactsReceiveds.add(contact);
        contact.setReceiver(this);
        return this;
    }

    public VideoUser removeContactsReceived(Contact contact) {
        this.contactsReceiveds.remove(contact);
        contact.setReceiver(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoUser)) {
            return false;
        }
        return getId() != null && getId().equals(((VideoUser) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VideoUser{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", name='" + getName() + "'" +
            ", phone=" + getPhone() +
            ", email='" + getEmail() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", userType='" + getUserType() + "'" +
            ", isBlocked='" + getIsBlocked() + "'" +
            ", blockedTill='" + getBlockedTill() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
