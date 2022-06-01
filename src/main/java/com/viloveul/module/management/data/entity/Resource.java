package com.viloveul.module.management.data.entity;

import com.viloveul.context.base.AbstractMidEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_resource", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "RESOURCE")
public class Resource extends AbstractMidEntity {

    @Column(name = "identity", updatable = false)
    private String identity;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Resource(String name) {
        this.name = name;
        this.identity = name;
    }

    public Resource(String identity, String name) {
        this.name = name;
        this.identity = identity;
    }

}
