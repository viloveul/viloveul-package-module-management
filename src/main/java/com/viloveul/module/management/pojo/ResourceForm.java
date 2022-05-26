package com.viloveul.module.management.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ResourceForm implements Serializable {

    @NotNull
    @NotEmpty
    private String identity;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    public ResourceForm(String identity, String name, String description) {
        this.identity = identity;
        this.name = name;
        this.description = description;
    }

    public ResourceForm(String identity, String name) {
        this.identity = identity;
        this.name = name;
    }

    public ResourceForm(String name) {
        this.identity = name;
        this.name = name;
    }
}
