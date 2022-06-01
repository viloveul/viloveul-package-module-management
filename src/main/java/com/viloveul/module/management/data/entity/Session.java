package com.viloveul.module.management.data.entity;

import com.viloveul.context.base.AbstractFullEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viloveul.context.auth.AccessControl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_session")
@EqualsAndHashCode(callSuper = true)
@AccessControl(resource = "SESSION")
public class Session extends AbstractFullEntity {

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties({"group"})
    private User user;

    public Session(User user) {
        this.user = user;
    }
}
