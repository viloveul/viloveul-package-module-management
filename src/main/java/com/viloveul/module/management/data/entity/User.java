package com.viloveul.module.management.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.base.AbstractFullEntity;
import com.viloveul.context.type.SignerType;
import com.viloveul.context.auth.AccessControl;
import com.viloveul.context.auth.model.UserModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_user", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "USER")
public class User extends AbstractFullEntity implements UserModel {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SignerType type;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "id_group", referencedColumnName = "id")
    @JsonIgnoreProperties({"childs", "parent"})
    private Group group;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "tbl_user_privilege",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_privilege", referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"agregates", "scopes"})
    private Set<Privilege> privileges = new HashSet<>();

    public User(String username) {
        this.setUsername(username);
        this.setType(SignerType.USER);
    }

    public User(String username, String fullname) {
        this.setUsername(username);
        this.setFullname(fullname);
        this.setType(SignerType.USER);
    }
}
