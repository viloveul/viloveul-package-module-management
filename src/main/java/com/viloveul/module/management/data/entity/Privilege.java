package com.viloveul.module.management.data.entity;

import com.viloveul.context.base.AbstractFullEntity;
import com.viloveul.context.type.AuthorityType;
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_privilege", schema = "schema")
@EqualsAndHashCode(callSuper = true, exclude = {"agregates", "scopes"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "PRIVILEGE")
public class Privilege extends AbstractFullEntity implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @Column(name = "identity", updatable = false)
    private String identity;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AuthorityType type;

    @Transient
    private transient String authority;

    @Valid
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "tbl_privilege_agregate",
        joinColumns = @JoinColumn(name = "id_privilege", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_agregate", referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"agregates", "scopes"})
    private Set<Privilege> agregates = new HashSet<>();

    @OneToMany(mappedBy = "privilege")
    private Set<Scope> scopes = new HashSet<>();

    public Privilege(String identity, AuthorityType type, String name) {
        this.setIdentity(identity);
        this.setName(name);
        this.setType(type);
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        if (this.authority == null) {
            this.authority = (this.type.name().concat("_").concat(this.identity).concat("_PRIVILEGE"));
        }
        return this.authority;
    }
}
