package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.mycompany.myapp.domain.enumeration.ChangeType;

/**
 * A AudUserChange.
 */
@Entity
@Table(name = "aud_user_change")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AudUserChange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "auc_id")
    private Long aucId;

    @Enumerated(EnumType.STRING)
    @Column(name = "auc_change_type")
    private ChangeType aucChangeType;

    @Column(name = "auc_change_date")
    private Instant aucChangeDate;

    @Column(name = "auc_user_id")
    private String aucUserId;

    @Column(name = "auc_department_id")
    private String aucDepartmentId;

    @Column(name = "auc_modified_user_id")
    private String aucModifiedUserId;

    @Column(name = "auc_permissions")
    private String aucPermissions;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAucId() {
        return aucId;
    }

    public AudUserChange aucId(Long aucId) {
        this.aucId = aucId;
        return this;
    }

    public void setAucId(Long aucId) {
        this.aucId = aucId;
    }

    public ChangeType getAucChangeType() {
        return aucChangeType;
    }

    public AudUserChange aucChangeType(ChangeType aucChangeType) {
        this.aucChangeType = aucChangeType;
        return this;
    }

    public void setAucChangeType(ChangeType aucChangeType) {
        this.aucChangeType = aucChangeType;
    }

    public Instant getAucChangeDate() {
        return aucChangeDate;
    }

    public AudUserChange aucChangeDate(Instant aucChangeDate) {
        this.aucChangeDate = aucChangeDate;
        return this;
    }

    public void setAucChangeDate(Instant aucChangeDate) {
        this.aucChangeDate = aucChangeDate;
    }

    public String getAucUserId() {
        return aucUserId;
    }

    public AudUserChange aucUserId(String aucUserId) {
        this.aucUserId = aucUserId;
        return this;
    }

    public void setAucUserId(String aucUserId) {
        this.aucUserId = aucUserId;
    }

    public String getAucDepartmentId() {
        return aucDepartmentId;
    }

    public AudUserChange aucDepartmentId(String aucDepartmentId) {
        this.aucDepartmentId = aucDepartmentId;
        return this;
    }

    public void setAucDepartmentId(String aucDepartmentId) {
        this.aucDepartmentId = aucDepartmentId;
    }

    public String getAucModifiedUserId() {
        return aucModifiedUserId;
    }

    public AudUserChange aucModifiedUserId(String aucModifiedUserId) {
        this.aucModifiedUserId = aucModifiedUserId;
        return this;
    }

    public void setAucModifiedUserId(String aucModifiedUserId) {
        this.aucModifiedUserId = aucModifiedUserId;
    }

    public String getAucPermissions() {
        return aucPermissions;
    }

    public AudUserChange aucPermissions(String aucPermissions) {
        this.aucPermissions = aucPermissions;
        return this;
    }

    public void setAucPermissions(String aucPermissions) {
        this.aucPermissions = aucPermissions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AudUserChange)) {
            return false;
        }
        return id != null && id.equals(((AudUserChange) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AudUserChange{" +
            "id=" + getId() +
            ", aucId=" + getAucId() +
            ", aucChangeType='" + getAucChangeType() + "'" +
            ", aucChangeDate='" + getAucChangeDate() + "'" +
            ", aucUserId='" + getAucUserId() + "'" +
            ", aucDepartmentId='" + getAucDepartmentId() + "'" +
            ", aucModifiedUserId='" + getAucModifiedUserId() + "'" +
            ", aucPermissions='" + getAucPermissions() + "'" +
            "}";
    }
}
