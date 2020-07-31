package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.mycompany.myapp.domain.enumeration.ChangeType;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.AudUserChange} entity.
 */
public class AudUserChangeDTO implements Serializable {
    
    private Long id;

    private Long aucId;

    private ChangeType aucChangeType;

    private Instant aucChangeDate;

    private String aucUserId;

    private String aucDepartmentId;

    private String aucModifiedUserId;

    private String aucPermissions;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAucId() {
        return aucId;
    }

    public void setAucId(Long aucId) {
        this.aucId = aucId;
    }

    public ChangeType getAucChangeType() {
        return aucChangeType;
    }

    public void setAucChangeType(ChangeType aucChangeType) {
        this.aucChangeType = aucChangeType;
    }

    public Instant getAucChangeDate() {
        return aucChangeDate;
    }

    public void setAucChangeDate(Instant aucChangeDate) {
        this.aucChangeDate = aucChangeDate;
    }

    public String getAucUserId() {
        return aucUserId;
    }

    public void setAucUserId(String aucUserId) {
        this.aucUserId = aucUserId;
    }

    public String getAucDepartmentId() {
        return aucDepartmentId;
    }

    public void setAucDepartmentId(String aucDepartmentId) {
        this.aucDepartmentId = aucDepartmentId;
    }

    public String getAucModifiedUserId() {
        return aucModifiedUserId;
    }

    public void setAucModifiedUserId(String aucModifiedUserId) {
        this.aucModifiedUserId = aucModifiedUserId;
    }

    public String getAucPermissions() {
        return aucPermissions;
    }

    public void setAucPermissions(String aucPermissions) {
        this.aucPermissions = aucPermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AudUserChangeDTO)) {
            return false;
        }

        return id != null && id.equals(((AudUserChangeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AudUserChangeDTO{" +
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
