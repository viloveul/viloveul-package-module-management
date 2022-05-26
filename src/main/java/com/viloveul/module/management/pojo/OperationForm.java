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
public class OperationForm implements Serializable {

    @NotNull
    @NotEmpty
    private String identity;

    @NotNull
    @NotEmpty
    private String name;

    public OperationForm(String identity, String name) {
        this.identity = identity;
        this.name = name;
    }

    public OperationForm(String name) {
        this.identity = name;
        this.name = name;
    }
}
