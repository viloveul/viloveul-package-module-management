package com.viloveul.module.management.pojo;

import com.viloveul.context.type.AuthorityType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class PrivilegeForm implements Serializable {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String identity;

    @NotNull
    @NotEmpty
    private AuthorityType type;

    private Collection<String> agregates = new ArrayList<>();

    private Collection<String> childs = new ArrayList<>();

    private Collection<String> tasks = new ArrayList<>();

    public PrivilegeForm(String name) {
        this.name = name;
        this.identity = name;
    }

    public PrivilegeForm(String name, String identity) {
        this.name = name;
        this.identity = identity;
    }
}
