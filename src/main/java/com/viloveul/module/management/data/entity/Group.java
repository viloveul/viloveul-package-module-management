package com.viloveul.module.management.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.base.AbstractFullEntity;
import com.viloveul.context.auth.AccessControl;
import com.viloveul.context.auth.model.GroupModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tprefix_group", schema = "schema")
@EqualsAndHashCode(of = {"identity"}, callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AccessControl(resource = "GROUP")
public class Group extends AbstractFullEntity implements GroupModel {

    public static final String SEPARATOR = "/";

    @Column(name = "name")
    private String name;

    @Column(name = "identity", updatable = false, unique = true)
    private String identity;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "id_parent", referencedColumnName = "id")
    @JsonIgnoreProperties({"privileges", "childs", "parent"})
    @NotFound(action = NotFoundAction.IGNORE)
    private Group parent;

    @OneToMany(mappedBy = "parent")
    // ignore property childs & assignments & permissions on child
    @JsonIgnoreProperties({"privileges", "parent"})
    private Set<Group> childs = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private Set<User> users = new HashSet<>();

    public Group(String identity) {
        this(identity, identity);
    }

    public Group(String identity, String name) {
        this.setIdentity(identity);
        this.setName(name);
    }

}
