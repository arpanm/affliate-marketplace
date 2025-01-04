package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.TransactionStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CompetitionPaymentFromSponsor.
 */
@Entity
@Table(name = "competition_payment_from_sponsor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CompetitionPaymentFromSponsor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "transaction_screenshot_url")
    private String transactionScreenshotUrl;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @Column(name = "paid_by")
    private String paidBy;

    @Column(name = "payment_receipt_url")
    private String paymentReceiptUrl;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "prizes", "paymentsFromSponsors", "sponsor", "competitionPosts" }, allowSetters = true)
    private Competition competition;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CompetitionPaymentFromSponsor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return this.amount;
    }

    public CompetitionPaymentFromSponsor amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public CompetitionPaymentFromSponsor transactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionScreenshotUrl() {
        return this.transactionScreenshotUrl;
    }

    public CompetitionPaymentFromSponsor transactionScreenshotUrl(String transactionScreenshotUrl) {
        this.setTransactionScreenshotUrl(transactionScreenshotUrl);
        return this;
    }

    public void setTransactionScreenshotUrl(String transactionScreenshotUrl) {
        this.transactionScreenshotUrl = transactionScreenshotUrl;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public CompetitionPaymentFromSponsor transactionDate(LocalDate transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    public CompetitionPaymentFromSponsor transactionStatus(TransactionStatus transactionStatus) {
        this.setTransactionStatus(transactionStatus);
        return this;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPaidBy() {
        return this.paidBy;
    }

    public CompetitionPaymentFromSponsor paidBy(String paidBy) {
        this.setPaidBy(paidBy);
        return this;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getPaymentReceiptUrl() {
        return this.paymentReceiptUrl;
    }

    public CompetitionPaymentFromSponsor paymentReceiptUrl(String paymentReceiptUrl) {
        this.setPaymentReceiptUrl(paymentReceiptUrl);
        return this;
    }

    public void setPaymentReceiptUrl(String paymentReceiptUrl) {
        this.paymentReceiptUrl = paymentReceiptUrl;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public CompetitionPaymentFromSponsor isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public CompetitionPaymentFromSponsor createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public CompetitionPaymentFromSponsor createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public CompetitionPaymentFromSponsor updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public CompetitionPaymentFromSponsor updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Competition getCompetition() {
        return this.competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public CompetitionPaymentFromSponsor competition(Competition competition) {
        this.setCompetition(competition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompetitionPaymentFromSponsor)) {
            return false;
        }
        return getId() != null && getId().equals(((CompetitionPaymentFromSponsor) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompetitionPaymentFromSponsor{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", transactionId='" + getTransactionId() + "'" +
            ", transactionScreenshotUrl='" + getTransactionScreenshotUrl() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", transactionStatus='" + getTransactionStatus() + "'" +
            ", paidBy='" + getPaidBy() + "'" +
            ", paymentReceiptUrl='" + getPaymentReceiptUrl() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
