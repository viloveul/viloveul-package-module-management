package com.viloveul.module.management.config;

import com.viloveul.module.management.provider.MultipleCredentialAuthenticationProvider;
import com.viloveul.module.management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration(
    proxyBeanMethods = false
)
public class TokenLoginSetupDefault {

    @Autowired
    public void setup(AuthenticationManagerBuilder authenticationManagerBuilder, AuthService authService, PasswordEncoder passwordEncoder) {
        authenticationManagerBuilder.authenticationProvider(
            new MultipleCredentialAuthenticationProvider(authService, passwordEncoder)
        );
    }
}
