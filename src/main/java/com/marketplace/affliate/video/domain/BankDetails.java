package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BankDetails.
 */
@Entity
@Table(name = "bank_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "ifsc")
    private String ifsc;

    @Column(name = "proof_url")
    private String proofUrl;

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

    @JsonIgnoreProperties(value = { "bank", "contactsMades" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bank")
    private VideoUser user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BankDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public BankDetails accountName(String accountName) {
        this.setAccountName(accountName);
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public BankDetails accountNo(String accountNo) {
        this.setAccountNo(accountNo);
        return this;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return this.bankName;
    }

    public BankDetails bankName(String bankName) {
        this.setBankName(bankName);
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return this.ifsc;
    }

    public BankDetails ifsc(String ifsc) {
        this.setIfsc(ifsc);
        return this;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getProofUrl() {
        return this.proofUrl;
    }

    public BankDetails proofUrl(String proofUrl) {
        this.setProofUrl(proofUrl);
        return this;
    }

    public void setProofUrl(String proofUrl) {
        this.proofUrl = proofUrl;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public BankDetails isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BankDetails createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public BankDetails createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public BankDetails updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public BankDetails updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public VideoUser getUser() {
        return this.user;
    }

    public void setUser(VideoUser videoUser) {
        if (this.user != null) {
            this.user.setBank(null);
        }
        if (videoUser != null) {
            videoUser.setBank(this);
        }
        this.user = videoUser;
    }

    public BankDetails user(VideoUser videoUser) {
        this.setUser(videoUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((BankDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankDetails{" +
            "id=" + getId() +
            ", accountName='" + getAccountName() + "'" +
            ", accountNo='" + getAccountNo() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", ifsc='" + getIfsc() + "'" +
            ", proofUrl='" + getProofUrl() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
