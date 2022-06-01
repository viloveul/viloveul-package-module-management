package com.viloveul.module.management.data.entity;

import com.viloveul.context.base.AbstractFullEntity;
import com.viloveul.context.type.SecureType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_credential", schema = "schema")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "CREDENTIAL")
public class Credential extends AbstractFullEntity {

    @Column(name = "type", updatable = false)
    @Enumerated(EnumType.STRING)
    private SecureType type;

    @Column(name = "start_date", updatable = false)
    private Date startDate;

    @Column(name = "end_date", updatable = false)
    private Date endDate;

    @JsonIgnore
    @Column(name = "value", updatable = false)
    private String value;

    @Column(name = "description", updatable = false)
    private String description;

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonIgnoreProperties({"group", "privileges"})
    private User user;

    public Credential(User user, SecureType type, String value) {
        this.user = user;
        this.type = type;
        this.value = value;
    }

    public Credential(User user, String value) {
        this.user = user;
        this.type = SecureType.PASSWORD;
        this.value = value;
    }

    public Credential(User user, String value, Date endDate) {
        this.user = user;
        this.type = SecureType.REQUESTED;
        this.value = value;
        this.setEndDate(endDate);
    }
}
