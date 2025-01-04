package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.TransactionStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CompetitionPaymentToWinner.
 */
@Entity
@Table(name = "competition_payment_to_winner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CompetitionPaymentToWinner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prize_title")
    private String prizeTitle;

    @Column(name = "prize_amount")
    private Double prizeAmount;

    @Column(name = "tds_amount")
    private Double tdsAmount;

    @Column(name = "tds_certificate_url")
    private Double tdsCertificateUrl;

    @Column(name = "other_deduction_amount")
    private Double otherDeductionAmount;

    @Column(name = "deduction_reason")
    private String deductionReason;

    @Column(name = "deduction_json_data")
    private String deductionJsonData;

    @Column(name = "deduction_certificate_url")
    private Double deductionCertificateUrl;

    @Column(name = "paid_amount")
    private Double paidAmount;

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

    @JsonIgnoreProperties(value = { "winningPost", "paymentToWinner", "competitionPrize" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "paymentToWinner")
    private CompetitionWinner winner;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CompetitionPaymentToWinner id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrizeTitle() {
        return this.prizeTitle;
    }

    public CompetitionPaymentToWinner prizeTitle(String prizeTitle) {
        this.setPrizeTitle(prizeTitle);
        return this;
    }

    public void setPrizeTitle(String prizeTitle) {
        this.prizeTitle = prizeTitle;
    }

    public Double getPrizeAmount() {
        return this.prizeAmount;
    }

    public CompetitionPaymentToWinner prizeAmount(Double prizeAmount) {
        this.setPrizeAmount(prizeAmount);
        return this;
    }

    public void setPrizeAmount(Double prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public Double getTdsAmount() {
        return this.tdsAmount;
    }

    public CompetitionPaymentToWinner tdsAmount(Double tdsAmount) {
        this.setTdsAmount(tdsAmount);
        return this;
    }

    public void setTdsAmount(Double tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public Double getTdsCertificateUrl() {
        return this.tdsCertificateUrl;
    }

    public CompetitionPaymentToWinner tdsCertificateUrl(Double tdsCertificateUrl) {
        this.setTdsCertificateUrl(tdsCertificateUrl);
        return this;
    }

    public void setTdsCertificateUrl(Double tdsCertificateUrl) {
        this.tdsCertificateUrl = tdsCertificateUrl;
    }

    public Double getOtherDeductionAmount() {
        return this.otherDeductionAmount;
    }

    public CompetitionPaymentToWinner otherDeductionAmount(Double otherDeductionAmount) {
        this.setOtherDeductionAmount(otherDeductionAmount);
        return this;
    }

    public void setOtherDeductionAmount(Double otherDeductionAmount) {
        this.otherDeductionAmount = otherDeductionAmount;
    }

    public String getDeductionReason() {
        return this.deductionReason;
    }

    public CompetitionPaymentToWinner deductionReason(String deductionReason) {
        this.setDeductionReason(deductionReason);
        return this;
    }

    public void setDeductionReason(String deductionReason) {
        this.deductionReason = deductionReason;
    }

    public String getDeductionJsonData() {
        return this.deductionJsonData;
    }

    public CompetitionPaymentToWinner deductionJsonData(String deductionJsonData) {
        this.setDeductionJsonData(deductionJsonData);
        return this;
    }

    public void setDeductionJsonData(String deductionJsonData) {
        this.deductionJsonData = deductionJsonData;
    }

    public Double getDeductionCertificateUrl() {
        return this.deductionCertificateUrl;
    }

    public CompetitionPaymentToWinner deductionCertificateUrl(Double deductionCertificateUrl) {
        this.setDeductionCertificateUrl(deductionCertificateUrl);
        return this;
    }

    public void setDeductionCertificateUrl(Double deductionCertificateUrl) {
        this.deductionCertificateUrl = deductionCertificateUrl;
    }

    public Double getPaidAmount() {
        return this.paidAmount;
    }

    public CompetitionPaymentToWinner paidAmount(Double paidAmount) {
        this.setPaidAmount(paidAmount);
        return this;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public CompetitionPaymentToWinner transactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionScreenshotUrl() {
        return this.transactionScreenshotUrl;
    }

    public CompetitionPaymentToWinner transactionScreenshotUrl(String transactionScreenshotUrl) {
        this.setTransactionScreenshotUrl(transactionScreenshotUrl);
        return this;
    }

    public void setTransactionScreenshotUrl(String transactionScreenshotUrl) {
        this.transactionScreenshotUrl = transactionScreenshotUrl;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public CompetitionPaymentToWinner transactionDate(LocalDate transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    public CompetitionPaymentToWinner transactionStatus(TransactionStatus transactionStatus) {
        this.setTransactionStatus(transactionStatus);
        return this;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPaidBy() {
        return this.paidBy;
    }

    public CompetitionPaymentToWinner paidBy(String paidBy) {
        this.setPaidBy(paidBy);
        return this;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public CompetitionPaymentToWinner isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public CompetitionPaymentToWinner createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public CompetitionPaymentToWinner createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public CompetitionPaymentToWinner updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public CompetitionPaymentToWinner updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CompetitionWinner getWinner() {
        return this.winner;
    }

    public void setWinner(CompetitionWinner competitionWinner) {
        if (this.winner != null) {
            this.winner.setPaymentToWinner(null);
        }
        if (competitionWinner != null) {
            competitionWinner.setPaymentToWinner(this);
        }
        this.winner = competitionWinner;
    }

    public CompetitionPaymentToWinner winner(CompetitionWinner competitionWinner) {
        this.setWinner(competitionWinner);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompetitionPaymentToWinner)) {
            return false;
        }
        return getId() != null && getId().equals(((CompetitionPaymentToWinner) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompetitionPaymentToWinner{" +
            "id=" + getId() +
            ", prizeTitle='" + getPrizeTitle() + "'" +
            ", prizeAmount=" + getPrizeAmount() +
            ", tdsAmount=" + getTdsAmount() +
            ", tdsCertificateUrl=" + getTdsCertificateUrl() +
            ", otherDeductionAmount=" + getOtherDeductionAmount() +
            ", deductionReason='" + getDeductionReason() + "'" +
            ", deductionJsonData='" + getDeductionJsonData() + "'" +
            ", deductionCertificateUrl=" + getDeductionCertificateUrl() +
            ", paidAmount=" + getPaidAmount() +
            ", transactionId='" + getTransactionId() + "'" +
            ", transactionScreenshotUrl='" + getTransactionScreenshotUrl() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", transactionStatus='" + getTransactionStatus() + "'" +
            ", paidBy='" + getPaidBy() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
