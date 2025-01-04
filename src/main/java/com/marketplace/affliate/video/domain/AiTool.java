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
 * A AiTool.
 */
@Entity
@Table(name = "ai_tool")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AiTool implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "link_url")
    private String linkUrl;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tool")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "chats", "payments", "user", "tool" }, allowSetters = true)
    private Set<AiToolSession> aiSessions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AiTool id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public AiTool name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public AiTool description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return this.vendor;
    }

    public AiTool vendor(String vendor) {
        this.setVendor(vendor);
        return this;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public AiTool linkUrl(String linkUrl) {
        this.setLinkUrl(linkUrl);
        return this;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public AiTool isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AiTool createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public AiTool createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AiTool updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public AiTool updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<AiToolSession> getAiSessions() {
        return this.aiSessions;
    }

    public void setAiSessions(Set<AiToolSession> aiToolSessions) {
        if (this.aiSessions != null) {
            this.aiSessions.forEach(i -> i.setTool(null));
        }
        if (aiToolSessions != null) {
            aiToolSessions.forEach(i -> i.setTool(this));
        }
        this.aiSessions = aiToolSessions;
    }

    public AiTool aiSessions(Set<AiToolSession> aiToolSessions) {
        this.setAiSessions(aiToolSessions);
        return this;
    }

    public AiTool addAiSessions(AiToolSession aiToolSession) {
        this.aiSessions.add(aiToolSession);
        aiToolSession.setTool(this);
        return this;
    }

    public AiTool removeAiSessions(AiToolSession aiToolSession) {
        this.aiSessions.remove(aiToolSession);
        aiToolSession.setTool(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AiTool)) {
            return false;
        }
        return getId() != null && getId().equals(((AiTool) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AiTool{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", vendor='" + getVendor() + "'" +
            ", linkUrl='" + getLinkUrl() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
