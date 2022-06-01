package com.viloveul.module.management.data.entity;

import com.viloveul.context.base.AbstractMidEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import com.viloveul.context.type.PrincipalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_notification", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "NOTIFICATION")
public class Notification extends AbstractMidEntity {

    @Column(name = "subject", updatable = false)
    private String subject;

    @Column(name = "body", updatable = false)
    private String body;

    @Column(name = "object", updatable = false)
    private String object;

    @Column(name = "resource", updatable = false)
    private String resource;

    @Column(name = "operation", updatable = false)
    private String operation;

    @Column(name = "principal", updatable = false)
    private String principal;

    @Column(name = "type", updatable = false)
    @Enumerated(EnumType.STRING)
    private PrincipalType type;

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_sender", nullable = false, updatable = false)
    private User sender;

    @Valid
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_reader")
    private User reader;

    public Notification(User sender, PrincipalType type, String principal) {
        this.principal = principal;
        this.sender = sender;
        this.type = type;
    }
}
