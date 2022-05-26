package com.viloveul.module.management.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.viloveul.context.type.SignerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class UserForm implements Serializable {

    @NotNull
    @NotEmpty
    @Size(min = 6)
    @Pattern(regexp = "(?U)^\\p{Lower}[\\p{Lower}\\p{Digit}._-]{4,}[\\p{Lower}\\p{Digit}]")
    private String username;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String fullname;

    @NotNull
    @NotEmpty
    private String group;

    @NotNull
    private Boolean status;

    @NotNull
    @NotEmpty
    private SignerType type = SignerType.USER;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    @JsonProperty("privileges")
    private Collection<String> privileges = new ArrayList<>();

    public UserForm(Map<String, Object> map) {
        this.username = (String) map.get("username");
        this.email = (String) map.get("email");
        this.group = (String) map.get("group");
        this.fullname = (String) map.get("fullname");
        this.status = (Boolean) map.get("status");
        this.startDate = map.containsKey("startDate") ? (Date) map.get("startDate") : null;
        this.endDate = map.containsKey("endDate") ? (Date) map.get("endDate") : null;
        this.type = SignerType.valueOf((String) map.get("type"));
    }
}
