package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.PaymentStatus;
import com.marketplace.affliate.video.domain.enumeration.PgType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AiToolPayment.
 */
@Entity
@Table(name = "ai_tool_payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AiToolPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "payment_link_url")
    private String paymentLinkUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "pg_type")
    private PgType pgType;

    @Column(name = "pg_id")
    private String pgId;

    @Column(name = "pg_status")
    private String pgStatus;

    @Column(name = "pg_request_json")
    private String pgRequestJson;

    @Column(name = "pg_response_json")
    private String pgResponseJson;

    @Column(name = "pg_request_time_stamp")
    private String pgRequestTimeStamp;

    @Column(name = "pg_response_time_stamp")
    private String pgResponseTimeStamp;

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
    @JsonIgnoreProperties(value = { "chats", "payments", "user", "tool" }, allowSetters = true)
    private AiToolSession session;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AiToolPayment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return this.amount;
    }

    public AiToolPayment amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public AiToolPayment status(PaymentStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getPaymentLinkUrl() {
        return this.paymentLinkUrl;
    }

    public AiToolPayment paymentLinkUrl(String paymentLinkUrl) {
        this.setPaymentLinkUrl(paymentLinkUrl);
        return this;
    }

    public void setPaymentLinkUrl(String paymentLinkUrl) {
        this.paymentLinkUrl = paymentLinkUrl;
    }

    public PgType getPgType() {
        return this.pgType;
    }

    public AiToolPayment pgType(PgType pgType) {
        this.setPgType(pgType);
        return this;
    }

    public void setPgType(PgType pgType) {
        this.pgType = pgType;
    }

    public String getPgId() {
        return this.pgId;
    }

    public AiToolPayment pgId(String pgId) {
        this.setPgId(pgId);
        return this;
    }

    public void setPgId(String pgId) {
        this.pgId = pgId;
    }

    public String getPgStatus() {
        return this.pgStatus;
    }

    public AiToolPayment pgStatus(String pgStatus) {
        this.setPgStatus(pgStatus);
        return this;
    }

    public void setPgStatus(String pgStatus) {
        this.pgStatus = pgStatus;
    }

    public String getPgRequestJson() {
        return this.pgRequestJson;
    }

    public AiToolPayment pgRequestJson(String pgRequestJson) {
        this.setPgRequestJson(pgRequestJson);
        return this;
    }

    public void setPgRequestJson(String pgRequestJson) {
        this.pgRequestJson = pgRequestJson;
    }

    public String getPgResponseJson() {
        return this.pgResponseJson;
    }

    public AiToolPayment pgResponseJson(String pgResponseJson) {
        this.setPgResponseJson(pgResponseJson);
        return this;
    }

    public void setPgResponseJson(String pgResponseJson) {
        this.pgResponseJson = pgResponseJson;
    }

    public String getPgRequestTimeStamp() {
        return this.pgRequestTimeStamp;
    }

    public AiToolPayment pgRequestTimeStamp(String pgRequestTimeStamp) {
        this.setPgRequestTimeStamp(pgRequestTimeStamp);
        return this;
    }

    public void setPgRequestTimeStamp(String pgRequestTimeStamp) {
        this.pgRequestTimeStamp = pgRequestTimeStamp;
    }

    public String getPgResponseTimeStamp() {
        return this.pgResponseTimeStamp;
    }

    public AiToolPayment pgResponseTimeStamp(String pgResponseTimeStamp) {
        this.setPgResponseTimeStamp(pgResponseTimeStamp);
        return this;
    }

    public void setPgResponseTimeStamp(String pgResponseTimeStamp) {
        this.pgResponseTimeStamp = pgResponseTimeStamp;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public AiToolPayment isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AiToolPayment createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public AiToolPayment createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AiToolPayment updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public AiToolPayment updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public AiToolSession getSession() {
        return this.session;
    }

    public void setSession(AiToolSession aiToolSession) {
        this.session = aiToolSession;
    }

    public AiToolPayment session(AiToolSession aiToolSession) {
        this.setSession(aiToolSession);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AiToolPayment)) {
            return false;
        }
        return getId() != null && getId().equals(((AiToolPayment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AiToolPayment{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", status='" + getStatus() + "'" +
            ", paymentLinkUrl='" + getPaymentLinkUrl() + "'" +
            ", pgType='" + getPgType() + "'" +
            ", pgId='" + getPgId() + "'" +
            ", pgStatus='" + getPgStatus() + "'" +
            ", pgRequestJson='" + getPgRequestJson() + "'" +
            ", pgResponseJson='" + getPgResponseJson() + "'" +
            ", pgRequestTimeStamp='" + getPgRequestTimeStamp() + "'" +
            ", pgResponseTimeStamp='" + getPgResponseTimeStamp() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
