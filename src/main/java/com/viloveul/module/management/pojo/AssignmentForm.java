package com.viloveul.module.management.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class AssignmentForm implements Serializable {

    @NotNull
    @NotEmpty
    private String delegator;

    @NotNull
    @NotEmpty
    private String group;

    @NotNull
    @NotEmpty
    private String user;

    @NotNull
    @NotEmpty
    private String privilege;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    public AssignmentForm(String delegator, String group, String privilege, String user) {
        this.delegator = delegator;
        this.group = group;
        this.privilege = privilege;
        this.user = user;
    }
}
