package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.CompetitionPaymentStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Competition.
 */
@Entity
@Table(name = "competition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Competition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompetitionPaymentStatus status;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "block_reason")
    private String blockReason;

    @Column(name = "blocked_by")
    private String blockedBy;

    @Column(name = "is_paused")
    private Boolean isPaused;

    @Column(name = "pause_reason")
    private String pauseReason;

    @Column(name = "paused_by")
    private String pausedBy;

    @Column(name = "banner_1_url")
    private String banner1Url;

    @Column(name = "banner_2_url")
    private String banner2Url;

    @Column(name = "banner_3_url")
    private String banner3Url;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "landing_url")
    private String landingUrl;

    @Column(name = "total_prize_value")
    private Double totalPrizeValue;

    @Column(name = "invoice_to_sponsor_url")
    private String invoiceToSponsorUrl;

    @Column(name = "tnc_url")
    private String tncUrl;

    @Column(name = "tnc")
    private String tnc;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competition")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "winners", "competition" }, allowSetters = true)
    private Set<Prize> prizes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competition")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "competition" }, allowSetters = true)
    private Set<CompetitionPaymentFromSponsor> paymentsFromSponsors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "adminUsers", "sponsoredCompetitions" }, allowSetters = true)
    private Sponsor sponsor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competition")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "reviews", "changesHistories", "competition", "tags", "affinityVectors", "competitionWinner", "creator" },
        allowSetters = true
    )
    private Set<VideoPost> competitionPosts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Competition id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Competition title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public Competition description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompetitionPaymentStatus getStatus() {
        return this.status;
    }

    public Competition status(CompetitionPaymentStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(CompetitionPaymentStatus status) {
        this.status = status;
    }

    public Boolean getIsBlocked() {
        return this.isBlocked;
    }

    public Competition isBlocked(Boolean isBlocked) {
        this.setIsBlocked(isBlocked);
        return this;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getBlockReason() {
        return this.blockReason;
    }

    public Competition blockReason(String blockReason) {
        this.setBlockReason(blockReason);
        return this;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public String getBlockedBy() {
        return this.blockedBy;
    }

    public Competition blockedBy(String blockedBy) {
        this.setBlockedBy(blockedBy);
        return this;
    }

    public void setBlockedBy(String blockedBy) {
        this.blockedBy = blockedBy;
    }

    public Boolean getIsPaused() {
        return this.isPaused;
    }

    public Competition isPaused(Boolean isPaused) {
        this.setIsPaused(isPaused);
        return this;
    }

    public void setIsPaused(Boolean isPaused) {
        this.isPaused = isPaused;
    }

    public String getPauseReason() {
        return this.pauseReason;
    }

    public Competition pauseReason(String pauseReason) {
        this.setPauseReason(pauseReason);
        return this;
    }

    public void setPauseReason(String pauseReason) {
        this.pauseReason = pauseReason;
    }

    public String getPausedBy() {
        return this.pausedBy;
    }

    public Competition pausedBy(String pausedBy) {
        this.setPausedBy(pausedBy);
        return this;
    }

    public void setPausedBy(String pausedBy) {
        this.pausedBy = pausedBy;
    }

    public String getBanner1Url() {
        return this.banner1Url;
    }

    public Competition banner1Url(String banner1Url) {
        this.setBanner1Url(banner1Url);
        return this;
    }

    public void setBanner1Url(String banner1Url) {
        this.banner1Url = banner1Url;
    }

    public String getBanner2Url() {
        return this.banner2Url;
    }

    public Competition banner2Url(String banner2Url) {
        this.setBanner2Url(banner2Url);
        return this;
    }

    public void setBanner2Url(String banner2Url) {
        this.banner2Url = banner2Url;
    }

    public String getBanner3Url() {
        return this.banner3Url;
    }

    public Competition banner3Url(String banner3Url) {
        this.setBanner3Url(banner3Url);
        return this;
    }

    public void setBanner3Url(String banner3Url) {
        this.banner3Url = banner3Url;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Competition startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public Competition endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLandingUrl() {
        return this.landingUrl;
    }

    public Competition landingUrl(String landingUrl) {
        this.setLandingUrl(landingUrl);
        return this;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    public Double getTotalPrizeValue() {
        return this.totalPrizeValue;
    }

    public Competition totalPrizeValue(Double totalPrizeValue) {
        this.setTotalPrizeValue(totalPrizeValue);
        return this;
    }

    public void setTotalPrizeValue(Double totalPrizeValue) {
        this.totalPrizeValue = totalPrizeValue;
    }

    public String getInvoiceToSponsorUrl() {
        return this.invoiceToSponsorUrl;
    }

    public Competition invoiceToSponsorUrl(String invoiceToSponsorUrl) {
        this.setInvoiceToSponsorUrl(invoiceToSponsorUrl);
        return this;
    }

    public void setInvoiceToSponsorUrl(String invoiceToSponsorUrl) {
        this.invoiceToSponsorUrl = invoiceToSponsorUrl;
    }

    public String getTncUrl() {
        return this.tncUrl;
    }

    public Competition tncUrl(String tncUrl) {
        this.setTncUrl(tncUrl);
        return this;
    }

    public void setTncUrl(String tncUrl) {
        this.tncUrl = tncUrl;
    }

    public String getTnc() {
        return this.tnc;
    }

    public Competition tnc(String tnc) {
        this.setTnc(tnc);
        return this;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Competition isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Competition createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public Competition createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Competition updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public Competition updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<Prize> getPrizes() {
        return this.prizes;
    }

    public void setPrizes(Set<Prize> prizes) {
        if (this.prizes != null) {
            this.prizes.forEach(i -> i.setCompetition(null));
        }
        if (prizes != null) {
            prizes.forEach(i -> i.setCompetition(this));
        }
        this.prizes = prizes;
    }

    public Competition prizes(Set<Prize> prizes) {
        this.setPrizes(prizes);
        return this;
    }

    public Competition addPrizes(Prize prize) {
        this.prizes.add(prize);
        prize.setCompetition(this);
        return this;
    }

    public Competition removePrizes(Prize prize) {
        this.prizes.remove(prize);
        prize.setCompetition(null);
        return this;
    }

    public Set<CompetitionPaymentFromSponsor> getPaymentsFromSponsors() {
        return this.paymentsFromSponsors;
    }

    public void setPaymentsFromSponsors(Set<CompetitionPaymentFromSponsor> competitionPaymentFromSponsors) {
        if (this.paymentsFromSponsors != null) {
            this.paymentsFromSponsors.forEach(i -> i.setCompetition(null));
        }
        if (competitionPaymentFromSponsors != null) {
            competitionPaymentFromSponsors.forEach(i -> i.setCompetition(this));
        }
        this.paymentsFromSponsors = competitionPaymentFromSponsors;
    }

    public Competition paymentsFromSponsors(Set<CompetitionPaymentFromSponsor> competitionPaymentFromSponsors) {
        this.setPaymentsFromSponsors(competitionPaymentFromSponsors);
        return this;
    }

    public Competition addPaymentsFromSponsor(CompetitionPaymentFromSponsor competitionPaymentFromSponsor) {
        this.paymentsFromSponsors.add(competitionPaymentFromSponsor);
        competitionPaymentFromSponsor.setCompetition(this);
        return this;
    }

    public Competition removePaymentsFromSponsor(CompetitionPaymentFromSponsor competitionPaymentFromSponsor) {
        this.paymentsFromSponsors.remove(competitionPaymentFromSponsor);
        competitionPaymentFromSponsor.setCompetition(null);
        return this;
    }

    public Sponsor getSponsor() {
        return this.sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Competition sponsor(Sponsor sponsor) {
        this.setSponsor(sponsor);
        return this;
    }

    public Set<VideoPost> getCompetitionPosts() {
        return this.competitionPosts;
    }

    public void setCompetitionPosts(Set<VideoPost> videoPosts) {
        if (this.competitionPosts != null) {
            this.competitionPosts.forEach(i -> i.setCompetition(null));
        }
        if (videoPosts != null) {
            videoPosts.forEach(i -> i.setCompetition(this));
        }
        this.competitionPosts = videoPosts;
    }

    public Competition competitionPosts(Set<VideoPost> videoPosts) {
        this.setCompetitionPosts(videoPosts);
        return this;
    }

    public Competition addCompetitionPosts(VideoPost videoPost) {
        this.competitionPosts.add(videoPost);
        videoPost.setCompetition(this);
        return this;
    }

    public Competition removeCompetitionPosts(VideoPost videoPost) {
        this.competitionPosts.remove(videoPost);
        videoPost.setCompetition(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Competition)) {
            return false;
        }
        return getId() != null && getId().equals(((Competition) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Competition{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", isBlocked='" + getIsBlocked() + "'" +
            ", blockReason='" + getBlockReason() + "'" +
            ", blockedBy='" + getBlockedBy() + "'" +
            ", isPaused='" + getIsPaused() + "'" +
            ", pauseReason='" + getPauseReason() + "'" +
            ", pausedBy='" + getPausedBy() + "'" +
            ", banner1Url='" + getBanner1Url() + "'" +
            ", banner2Url='" + getBanner2Url() + "'" +
            ", banner3Url='" + getBanner3Url() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", landingUrl='" + getLandingUrl() + "'" +
            ", totalPrizeValue=" + getTotalPrizeValue() +
            ", invoiceToSponsorUrl='" + getInvoiceToSponsorUrl() + "'" +
            ", tncUrl='" + getTncUrl() + "'" +
            ", tnc='" + getTnc() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
