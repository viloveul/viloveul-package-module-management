package com.viloveul.module.management.data.entity;

import com.viloveul.context.base.AbstractFullEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tprefix_assignment", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AccessControl(resource = "ASSIGNMENT")
public class Assignment extends AbstractFullEntity {

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_group", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties({"childs", "users", "parent"})
    private Group group;

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_privilege", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties({"scopes", "agregates"})
    private Privilege privilege;

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties({"group"})
    private User user;

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_delegator", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties({"group"})
    private User delegator;

    public Assignment(User delegator, Group group, Privilege privilege, User user) {
        this.delegator = delegator;
        this.group = group;
        this.privilege = privilege;
        this.user = user;
    }
}
