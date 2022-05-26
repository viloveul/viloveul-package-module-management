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
public class ScopeForm implements Serializable {

    @NotNull
    @NotEmpty
    private String privilege;

    @NotNull
    @NotEmpty
    private String resource;

    @NotNull
    @NotEmpty
    private String operation;

    public ScopeForm(String privilege, String resource, String operation) {
        this.privilege = privilege;
        this.resource = resource;
        this.operation = operation;
    }
}
