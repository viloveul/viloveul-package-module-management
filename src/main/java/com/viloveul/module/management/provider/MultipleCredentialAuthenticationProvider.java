package com.viloveul.module.management.provider;

import com.viloveul.context.auth.dto.DetailAuthentication;
import com.viloveul.module.management.service.AuthService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MultipleCredentialAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final AuthService authService;

    public MultipleCredentialAuthenticationProvider(AuthService authService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (Boolean.TRUE.equals(this.authService.authenticate(authentication, this.passwordEncoder))) {
            DetailAuthentication user = (DetailAuthentication) this.authService.loadUserByUsername(authentication.getName());
            return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities()) {
                @Override
                public Object getDetails() {
                    return user;
                }
            };
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
