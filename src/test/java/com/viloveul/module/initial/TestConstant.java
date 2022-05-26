package com.viloveul.module.initial;

import com.viloveul.context.type.SecureType;
import com.viloveul.module.management.pojo.CredentialForm;

public class TestConstant {

    private TestConstant() {
        // not for initialize
    }

    public static final int PAGE_SIZE = 1;

    public static final int ACTIVE_PAGE = 1;

    public static final String EMAIL = "fajrulaz@gmail.com";

    public static final String USERNAME = "admin";

    public static final String FULLNAME = "Fajrul Akbar Zuhdi";

    public static final String PASSWORD = "ADMIN123";

    public static final CredentialForm CREDENTIAL = new CredentialForm(
        "user0000-0000-0000-adm-d90b38dummy1",
        SecureType.PASSWORD,
        "PASSWORD",
        "PASSWORD"
    );

}
