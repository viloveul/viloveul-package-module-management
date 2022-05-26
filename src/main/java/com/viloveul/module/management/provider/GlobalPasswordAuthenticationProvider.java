package com.viloveul.module.management.provider;

import com.viloveul.module.management.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GlobalPasswordAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalPasswordAuthenticationProvider.class);

    private final JdbcTemplate jdbcTemplate;

    private final Environment environment;

    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    public GlobalPasswordAuthenticationProvider(
        JdbcTemplate jdbcTemplate,
        Environment environment,
        AuthService authService,
        PasswordEncoder passwordEncoder
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.environment = environment;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String sql = this.environment.getProperty("viloveul.auth.global-password", String.class, "");
        if (!sql.isEmpty()) {
            try {
                String gpasswd = this.jdbcTemplate.queryForObject(sql, String.class);
                String pass = authentication.getCredentials().toString();
                if (gpasswd != null && this.passwordEncoder.matches(pass, gpasswd)) {
                    UserDetails details = this.authService.loadUserByUsername(authentication.getName());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        details.getUsername(), null, details.getAuthorities()
                    );
                    auth.setDetails(details);
                    return auth;
                }
            } catch (DataAccessException dae) {
                LOGGER.info(dae.getMessage(), dae.getCause());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
