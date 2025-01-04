package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CompetitionWinner.
 */
@Entity
@Table(name = "competition_winner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CompetitionWinner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prize_title")
    private String prizeTitle;

    @Column(name = "citation")
    private String citation;

    @Column(name = "certificate_url")
    private String certificateUrl;

    @Column(name = "selected_by")
    private String selectedBy;

    @Column(name = "selection_reason")
    private String selectionReason;

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

    @JsonIgnoreProperties(
        value = { "reviews", "changesHistories", "competition", "tags", "affinityVectors", "competitionWinner", "creator" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private VideoPost winningPost;

    @JsonIgnoreProperties(value = { "winner" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private CompetitionPaymentToWinner paymentToWinner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "winners", "competition" }, allowSetters = true)
    private Prize competitionPrize;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CompetitionWinner id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrizeTitle() {
        return this.prizeTitle;
    }

    public CompetitionWinner prizeTitle(String prizeTitle) {
        this.setPrizeTitle(prizeTitle);
        return this;
    }

    public void setPrizeTitle(String prizeTitle) {
        this.prizeTitle = prizeTitle;
    }

    public String getCitation() {
        return this.citation;
    }

    public CompetitionWinner citation(String citation) {
        this.setCitation(citation);
        return this;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getCertificateUrl() {
        return this.certificateUrl;
    }

    public CompetitionWinner certificateUrl(String certificateUrl) {
        this.setCertificateUrl(certificateUrl);
        return this;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public String getSelectedBy() {
        return this.selectedBy;
    }

    public CompetitionWinner selectedBy(String selectedBy) {
        this.setSelectedBy(selectedBy);
        return this;
    }

    public void setSelectedBy(String selectedBy) {
        this.selectedBy = selectedBy;
    }

    public String getSelectionReason() {
        return this.selectionReason;
    }

    public CompetitionWinner selectionReason(String selectionReason) {
        this.setSelectionReason(selectionReason);
        return this;
    }

    public void setSelectionReason(String selectionReason) {
        this.selectionReason = selectionReason;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public CompetitionWinner isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public CompetitionWinner createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public CompetitionWinner createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public CompetitionWinner updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public CompetitionWinner updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public VideoPost getWinningPost() {
        return this.winningPost;
    }

    public void setWinningPost(VideoPost videoPost) {
        this.winningPost = videoPost;
    }

    public CompetitionWinner winningPost(VideoPost videoPost) {
        this.setWinningPost(videoPost);
        return this;
    }

    public CompetitionPaymentToWinner getPaymentToWinner() {
        return this.paymentToWinner;
    }

    public void setPaymentToWinner(CompetitionPaymentToWinner competitionPaymentToWinner) {
        this.paymentToWinner = competitionPaymentToWinner;
    }

    public CompetitionWinner paymentToWinner(CompetitionPaymentToWinner competitionPaymentToWinner) {
        this.setPaymentToWinner(competitionPaymentToWinner);
        return this;
    }

    public Prize getCompetitionPrize() {
        return this.competitionPrize;
    }

    public void setCompetitionPrize(Prize prize) {
        this.competitionPrize = prize;
    }

    public CompetitionWinner competitionPrize(Prize prize) {
        this.setCompetitionPrize(prize);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompetitionWinner)) {
            return false;
        }
        return getId() != null && getId().equals(((CompetitionWinner) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompetitionWinner{" +
            "id=" + getId() +
            ", prizeTitle='" + getPrizeTitle() + "'" +
            ", citation='" + getCitation() + "'" +
            ", certificateUrl='" + getCertificateUrl() + "'" +
            ", selectedBy='" + getSelectedBy() + "'" +
            ", selectionReason='" + getSelectionReason() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
