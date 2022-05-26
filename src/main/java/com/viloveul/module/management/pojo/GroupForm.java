package com.viloveul.module.management.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class GroupForm implements Serializable {

    @NotNull
    @NotEmpty
    private String identity;

    @NotNull
    @NotEmpty
    private String name;

    private String parent;

    public GroupForm(String identity, String name) {
        this.identity = identity;
        this.name = name;
    }

    public GroupForm(String name) {
        this.identity = name;
        this.name = name;
    }
}
