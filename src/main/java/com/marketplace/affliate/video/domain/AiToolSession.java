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
 * A AiToolSession.
 */
@Entity
@Table(name = "ai_tool_session")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AiToolSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_payment_link_generated")
    private Boolean isPaymentLinkGenerated;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "is_video_generated")
    private Boolean isVideoGenerated;

    @Column(name = "is_video_downloaded")
    private Boolean isVideoDownloaded;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "session" }, allowSetters = true)
    private Set<AiToolChat> chats = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "session" }, allowSetters = true)
    private Set<AiToolPayment> payments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "bank", "posts", "reviews", "aiSessions", "affinityVectors", "company", "contactsMades", "contactsReceiveds" },
        allowSetters = true
    )
    private VideoUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "aiSessions" }, allowSetters = true)
    private AiTool tool;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AiToolSession id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsPaymentLinkGenerated() {
        return this.isPaymentLinkGenerated;
    }

    public AiToolSession isPaymentLinkGenerated(Boolean isPaymentLinkGenerated) {
        this.setIsPaymentLinkGenerated(isPaymentLinkGenerated);
        return this;
    }

    public void setIsPaymentLinkGenerated(Boolean isPaymentLinkGenerated) {
        this.isPaymentLinkGenerated = isPaymentLinkGenerated;
    }

    public Boolean getIsPaid() {
        return this.isPaid;
    }

    public AiToolSession isPaid(Boolean isPaid) {
        this.setIsPaid(isPaid);
        return this;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Boolean getIsVideoGenerated() {
        return this.isVideoGenerated;
    }

    public AiToolSession isVideoGenerated(Boolean isVideoGenerated) {
        this.setIsVideoGenerated(isVideoGenerated);
        return this;
    }

    public void setIsVideoGenerated(Boolean isVideoGenerated) {
        this.isVideoGenerated = isVideoGenerated;
    }

    public Boolean getIsVideoDownloaded() {
        return this.isVideoDownloaded;
    }

    public AiToolSession isVideoDownloaded(Boolean isVideoDownloaded) {
        this.setIsVideoDownloaded(isVideoDownloaded);
        return this;
    }

    public void setIsVideoDownloaded(Boolean isVideoDownloaded) {
        this.isVideoDownloaded = isVideoDownloaded;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public AiToolSession isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AiToolSession createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public AiToolSession createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AiToolSession updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public AiToolSession updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<AiToolChat> getChats() {
        return this.chats;
    }

    public void setChats(Set<AiToolChat> aiToolChats) {
        if (this.chats != null) {
            this.chats.forEach(i -> i.setSession(null));
        }
        if (aiToolChats != null) {
            aiToolChats.forEach(i -> i.setSession(this));
        }
        this.chats = aiToolChats;
    }

    public AiToolSession chats(Set<AiToolChat> aiToolChats) {
        this.setChats(aiToolChats);
        return this;
    }

    public AiToolSession addChats(AiToolChat aiToolChat) {
        this.chats.add(aiToolChat);
        aiToolChat.setSession(this);
        return this;
    }

    public AiToolSession removeChats(AiToolChat aiToolChat) {
        this.chats.remove(aiToolChat);
        aiToolChat.setSession(null);
        return this;
    }

    public Set<AiToolPayment> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<AiToolPayment> aiToolPayments) {
        if (this.payments != null) {
            this.payments.forEach(i -> i.setSession(null));
        }
        if (aiToolPayments != null) {
            aiToolPayments.forEach(i -> i.setSession(this));
        }
        this.payments = aiToolPayments;
    }

    public AiToolSession payments(Set<AiToolPayment> aiToolPayments) {
        this.setPayments(aiToolPayments);
        return this;
    }

    public AiToolSession addPayments(AiToolPayment aiToolPayment) {
        this.payments.add(aiToolPayment);
        aiToolPayment.setSession(this);
        return this;
    }

    public AiToolSession removePayments(AiToolPayment aiToolPayment) {
        this.payments.remove(aiToolPayment);
        aiToolPayment.setSession(null);
        return this;
    }

    public VideoUser getUser() {
        return this.user;
    }

    public void setUser(VideoUser videoUser) {
        this.user = videoUser;
    }

    public AiToolSession user(VideoUser videoUser) {
        this.setUser(videoUser);
        return this;
    }

    public AiTool getTool() {
        return this.tool;
    }

    public void setTool(AiTool aiTool) {
        this.tool = aiTool;
    }

    public AiToolSession tool(AiTool aiTool) {
        this.setTool(aiTool);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AiToolSession)) {
            return false;
        }
        return getId() != null && getId().equals(((AiToolSession) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AiToolSession{" +
            "id=" + getId() +
            ", isPaymentLinkGenerated='" + getIsPaymentLinkGenerated() + "'" +
            ", isPaid='" + getIsPaid() + "'" +
            ", isVideoGenerated='" + getIsVideoGenerated() + "'" +
            ", isVideoDownloaded='" + getIsVideoDownloaded() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
