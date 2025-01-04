package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.PrizeType;
import com.marketplace.affliate.video.domain.enumeration.PrizeValueType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Prize.
 */
@Entity
@Table(name = "prize")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Prize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "prize_type")
    private PrizeType prizeType;

    @Column(name = "prize_tag")
    private String prizeTag;

    @Column(name = "prize_details")
    private String prizeDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "prize_value_type")
    private PrizeValueType prizeValueType;

    @Column(name = "prize_value")
    private Double prizeValue;

    @Column(name = "count_of_possible_winners")
    private Long countOfPossibleWinners;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competitionPrize")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "winningPost", "paymentToWinner", "competitionPrize" }, allowSetters = true)
    private Set<CompetitionWinner> winners = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "prizes", "paymentsFromSponsors", "sponsor", "competitionPosts" }, allowSetters = true)
    private Competition competition;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Prize id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrizeType getPrizeType() {
        return this.prizeType;
    }

    public Prize prizeType(PrizeType prizeType) {
        this.setPrizeType(prizeType);
        return this;
    }

    public void setPrizeType(PrizeType prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizeTag() {
        return this.prizeTag;
    }

    public Prize prizeTag(String prizeTag) {
        this.setPrizeTag(prizeTag);
        return this;
    }

    public void setPrizeTag(String prizeTag) {
        this.prizeTag = prizeTag;
    }

    public String getPrizeDetails() {
        return this.prizeDetails;
    }

    public Prize prizeDetails(String prizeDetails) {
        this.setPrizeDetails(prizeDetails);
        return this;
    }

    public void setPrizeDetails(String prizeDetails) {
        this.prizeDetails = prizeDetails;
    }

    public PrizeValueType getPrizeValueType() {
        return this.prizeValueType;
    }

    public Prize prizeValueType(PrizeValueType prizeValueType) {
        this.setPrizeValueType(prizeValueType);
        return this;
    }

    public void setPrizeValueType(PrizeValueType prizeValueType) {
        this.prizeValueType = prizeValueType;
    }

    public Double getPrizeValue() {
        return this.prizeValue;
    }

    public Prize prizeValue(Double prizeValue) {
        this.setPrizeValue(prizeValue);
        return this;
    }

    public void setPrizeValue(Double prizeValue) {
        this.prizeValue = prizeValue;
    }

    public Long getCountOfPossibleWinners() {
        return this.countOfPossibleWinners;
    }

    public Prize countOfPossibleWinners(Long countOfPossibleWinners) {
        this.setCountOfPossibleWinners(countOfPossibleWinners);
        return this;
    }

    public void setCountOfPossibleWinners(Long countOfPossibleWinners) {
        this.countOfPossibleWinners = countOfPossibleWinners;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Prize isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Prize createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public Prize createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Prize updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public Prize updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<CompetitionWinner> getWinners() {
        return this.winners;
    }

    public void setWinners(Set<CompetitionWinner> competitionWinners) {
        if (this.winners != null) {
            this.winners.forEach(i -> i.setCompetitionPrize(null));
        }
        if (competitionWinners != null) {
            competitionWinners.forEach(i -> i.setCompetitionPrize(this));
        }
        this.winners = competitionWinners;
    }

    public Prize winners(Set<CompetitionWinner> competitionWinners) {
        this.setWinners(competitionWinners);
        return this;
    }

    public Prize addWinners(CompetitionWinner competitionWinner) {
        this.winners.add(competitionWinner);
        competitionWinner.setCompetitionPrize(this);
        return this;
    }

    public Prize removeWinners(CompetitionWinner competitionWinner) {
        this.winners.remove(competitionWinner);
        competitionWinner.setCompetitionPrize(null);
        return this;
    }

    public Competition getCompetition() {
        return this.competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Prize competition(Competition competition) {
        this.setCompetition(competition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prize)) {
            return false;
        }
        return getId() != null && getId().equals(((Prize) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prize{" +
            "id=" + getId() +
            ", prizeType='" + getPrizeType() + "'" +
            ", prizeTag='" + getPrizeTag() + "'" +
            ", prizeDetails='" + getPrizeDetails() + "'" +
            ", prizeValueType='" + getPrizeValueType() + "'" +
            ", prizeValue=" + getPrizeValue() +
            ", countOfPossibleWinners=" + getCountOfPossibleWinners() +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
