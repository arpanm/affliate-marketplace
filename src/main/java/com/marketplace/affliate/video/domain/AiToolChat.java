package com.marketplace.affliate.video.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.affliate.video.domain.enumeration.ChatType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AiToolChat.
 */
@Entity
@Table(name = "ai_tool_chat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AiToolChat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "payment_url")
    private String paymentUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ChatType type;

    @Column(name = "is_final_video")
    private Boolean isFinalVideo;

    @Column(name = "is_downloaded")
    private Boolean isDownloaded;

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

    public AiToolChat id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public AiToolChat message(String message) {
        this.setMessage(message);
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public AiToolChat videoUrl(String videoUrl) {
        this.setVideoUrl(videoUrl);
        return this;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPaymentUrl() {
        return this.paymentUrl;
    }

    public AiToolChat paymentUrl(String paymentUrl) {
        this.setPaymentUrl(paymentUrl);
        return this;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public ChatType getType() {
        return this.type;
    }

    public AiToolChat type(ChatType type) {
        this.setType(type);
        return this;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

    public Boolean getIsFinalVideo() {
        return this.isFinalVideo;
    }

    public AiToolChat isFinalVideo(Boolean isFinalVideo) {
        this.setIsFinalVideo(isFinalVideo);
        return this;
    }

    public void setIsFinalVideo(Boolean isFinalVideo) {
        this.isFinalVideo = isFinalVideo;
    }

    public Boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    public AiToolChat isDownloaded(Boolean isDownloaded) {
        this.setIsDownloaded(isDownloaded);
        return this;
    }

    public void setIsDownloaded(Boolean isDownloaded) {
        this.isDownloaded = isDownloaded;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public AiToolChat isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AiToolChat createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public AiToolChat createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AiToolChat updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public AiToolChat updatedOn(LocalDate updatedOn) {
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

    public AiToolChat session(AiToolSession aiToolSession) {
        this.setSession(aiToolSession);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AiToolChat)) {
            return false;
        }
        return getId() != null && getId().equals(((AiToolChat) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AiToolChat{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", videoUrl='" + getVideoUrl() + "'" +
            ", paymentUrl='" + getPaymentUrl() + "'" +
            ", type='" + getType() + "'" +
            ", isFinalVideo='" + getIsFinalVideo() + "'" +
            ", isDownloaded='" + getIsDownloaded() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
