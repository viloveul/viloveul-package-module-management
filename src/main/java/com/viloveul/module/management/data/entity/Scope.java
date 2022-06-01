package com.viloveul.module.management.data.entity;

import com.viloveul.context.base.AbstractMidEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_scope", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "SCOPE")
public class Scope extends AbstractMidEntity implements GrantedAuthority {

    @ManyToOne
    @JsonIgnoreProperties({"scopes", "agregates"})
    @JoinColumn(name = "id_privilege", referencedColumnName = "id", updatable = false)
    private Privilege privilege;

    @ManyToOne
    @JoinColumn(name = "id_resource", referencedColumnName = "id", updatable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "id_operation", referencedColumnName = "id", updatable = false)
    private Operation operation;

    @Transient
    @JsonIgnore
    private String authority;

    public Scope(Privilege privilege, Resource resource, Operation operation) {
        this.setPrivilege(privilege);
        this.setResource(resource);
        this.setOperation(operation);
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        if (this.authority == null) {
            this.authority = this.operation.getIdentity().concat("-").concat(this.resource.getIdentity());
        }
        return this.authority;
    }
}
