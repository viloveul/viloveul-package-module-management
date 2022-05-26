package com.viloveul.module.management.pojo;

import com.viloveul.context.type.SecureType;
import com.viloveul.context.util.validation.FieldMatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch(field = "value", match = "confirm")
public class CredentialForm implements Serializable {

    @NotNull
    @NotEmpty
    private String user;

    @NotNull
    @NotEmpty
    private SecureType type;

    @NotNull
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = "(?U)((?=.*\\d)(?=.*[\\p{Lower}])(?=.*[\\p{Upper}])(?=.*[^\\p{Alnum}]).{8,})")
    private String value;

    @NotNull
    @NotEmpty
    private String confirm;

    private String description;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    public CredentialForm(String user, SecureType type, String value, String confirm) {
        this.user = user;
        this.type = type;
        this.value = value;
        this.confirm = confirm;
    }
}
