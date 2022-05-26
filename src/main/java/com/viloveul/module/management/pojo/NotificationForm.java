package com.viloveul.module.management.pojo;

import com.viloveul.context.type.PrincipalType;
import com.viloveul.context.util.validation.CaseWhenNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@CaseWhenNull(caseField = "type", whenMatch = "USER", thenUse = "user", orElse = "group")
public class NotificationForm implements Serializable {

    @NotNull
    @NotEmpty
    private String sender;

    @NotNull
    @NotEmpty
    private String resource;

    @NotNull
    @NotEmpty
    private String operation;

    @NotNull
    @NotEmpty
    private String subject;

    @NotNull
    @NotEmpty
    private String body;

    @NotNull
    @NotEmpty
    private String object;

    @NotNull
    @NotEmpty
    private PrincipalType type = PrincipalType.USER;

    @NotNull
    @NotEmpty
    private String target;
}
