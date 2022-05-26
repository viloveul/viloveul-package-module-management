package com.viloveul.module.management.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public interface AuthService extends UserDetailsService {

    Boolean authenticate(Authentication authentication, PasswordEncoder passwordEncoder);

    Boolean validate(Authentication authentication);
}
