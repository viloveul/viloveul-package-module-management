package com.viloveul.module.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthForm implements Serializable {

    @NotNull
    @NotEmpty
    @Size(min = 6)
    @Pattern(regexp = "(?U)^\\p{Lower}[\\p{Lower}\\p{Digit}._-]{4,}[\\p{Lower}\\p{Digit}]")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = "(?U)((?=.*\\d)(?=.*[\\p{Lower}])(?=.*[\\p{Upper}])(?=.*[^\\p{Alnum}]).{8,})")
    private String password;

}
