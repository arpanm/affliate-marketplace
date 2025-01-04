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
 * A Sponsor.
 */
@Entity
@Table(name = "sponsor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sponsor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sponsor_name")
    private String sponsorName;

    @Column(name = "sponsor_description")
    private String sponsorDescription;

    @Column(name = "sponsor_banner_1_url")
    private String sponsorBanner1Url;

    @Column(name = "sponsor_banner_2_url")
    private String sponsorBanner2Url;

    @Column(name = "sponsor_banner_3_url")
    private String sponsorBanner3Url;

    @Column(name = "sponsor_external_url")
    private String sponsorExternalUrl;

    @Column(name = "sponsor_logo_url")
    private String sponsorLogoUrl;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "bank", "posts", "reviews", "aiSessions", "affinityVectors", "company", "contactsMades", "contactsReceiveds" },
        allowSetters = true
    )
    private Set<VideoUser> adminUsers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sponsor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "prizes", "paymentsFromSponsors", "sponsor", "competitionPosts" }, allowSetters = true)
    private Set<Competition> sponsoredCompetitions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Sponsor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSponsorName() {
        return this.sponsorName;
    }

    public Sponsor sponsorName(String sponsorName) {
        this.setSponsorName(sponsorName);
        return this;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorDescription() {
        return this.sponsorDescription;
    }

    public Sponsor sponsorDescription(String sponsorDescription) {
        this.setSponsorDescription(sponsorDescription);
        return this;
    }

    public void setSponsorDescription(String sponsorDescription) {
        this.sponsorDescription = sponsorDescription;
    }

    public String getSponsorBanner1Url() {
        return this.sponsorBanner1Url;
    }

    public Sponsor sponsorBanner1Url(String sponsorBanner1Url) {
        this.setSponsorBanner1Url(sponsorBanner1Url);
        return this;
    }

    public void setSponsorBanner1Url(String sponsorBanner1Url) {
        this.sponsorBanner1Url = sponsorBanner1Url;
    }

    public String getSponsorBanner2Url() {
        return this.sponsorBanner2Url;
    }

    public Sponsor sponsorBanner2Url(String sponsorBanner2Url) {
        this.setSponsorBanner2Url(sponsorBanner2Url);
        return this;
    }

    public void setSponsorBanner2Url(String sponsorBanner2Url) {
        this.sponsorBanner2Url = sponsorBanner2Url;
    }

    public String getSponsorBanner3Url() {
        return this.sponsorBanner3Url;
    }

    public Sponsor sponsorBanner3Url(String sponsorBanner3Url) {
        this.setSponsorBanner3Url(sponsorBanner3Url);
        return this;
    }

    public void setSponsorBanner3Url(String sponsorBanner3Url) {
        this.sponsorBanner3Url = sponsorBanner3Url;
    }

    public String getSponsorExternalUrl() {
        return this.sponsorExternalUrl;
    }

    public Sponsor sponsorExternalUrl(String sponsorExternalUrl) {
        this.setSponsorExternalUrl(sponsorExternalUrl);
        return this;
    }

    public void setSponsorExternalUrl(String sponsorExternalUrl) {
        this.sponsorExternalUrl = sponsorExternalUrl;
    }

    public String getSponsorLogoUrl() {
        return this.sponsorLogoUrl;
    }

    public Sponsor sponsorLogoUrl(String sponsorLogoUrl) {
        this.setSponsorLogoUrl(sponsorLogoUrl);
        return this;
    }

    public void setSponsorLogoUrl(String sponsorLogoUrl) {
        this.sponsorLogoUrl = sponsorLogoUrl;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Sponsor isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Sponsor createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public Sponsor createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Sponsor updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public Sponsor updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<VideoUser> getAdminUsers() {
        return this.adminUsers;
    }

    public void setAdminUsers(Set<VideoUser> videoUsers) {
        if (this.adminUsers != null) {
            this.adminUsers.forEach(i -> i.setCompany(null));
        }
        if (videoUsers != null) {
            videoUsers.forEach(i -> i.setCompany(this));
        }
        this.adminUsers = videoUsers;
    }

    public Sponsor adminUsers(Set<VideoUser> videoUsers) {
        this.setAdminUsers(videoUsers);
        return this;
    }

    public Sponsor addAdminUsers(VideoUser videoUser) {
        this.adminUsers.add(videoUser);
        videoUser.setCompany(this);
        return this;
    }

    public Sponsor removeAdminUsers(VideoUser videoUser) {
        this.adminUsers.remove(videoUser);
        videoUser.setCompany(null);
        return this;
    }

    public Set<Competition> getSponsoredCompetitions() {
        return this.sponsoredCompetitions;
    }

    public void setSponsoredCompetitions(Set<Competition> competitions) {
        if (this.sponsoredCompetitions != null) {
            this.sponsoredCompetitions.forEach(i -> i.setSponsor(null));
        }
        if (competitions != null) {
            competitions.forEach(i -> i.setSponsor(this));
        }
        this.sponsoredCompetitions = competitions;
    }

    public Sponsor sponsoredCompetitions(Set<Competition> competitions) {
        this.setSponsoredCompetitions(competitions);
        return this;
    }

    public Sponsor addSponsoredCompetitions(Competition competition) {
        this.sponsoredCompetitions.add(competition);
        competition.setSponsor(this);
        return this;
    }

    public Sponsor removeSponsoredCompetitions(Competition competition) {
        this.sponsoredCompetitions.remove(competition);
        competition.setSponsor(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sponsor)) {
            return false;
        }
        return getId() != null && getId().equals(((Sponsor) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sponsor{" +
            "id=" + getId() +
            ", sponsorName='" + getSponsorName() + "'" +
            ", sponsorDescription='" + getSponsorDescription() + "'" +
            ", sponsorBanner1Url='" + getSponsorBanner1Url() + "'" +
            ", sponsorBanner2Url='" + getSponsorBanner2Url() + "'" +
            ", sponsorBanner3Url='" + getSponsorBanner3Url() + "'" +
            ", sponsorExternalUrl='" + getSponsorExternalUrl() + "'" +
            ", sponsorLogoUrl='" + getSponsorLogoUrl() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
