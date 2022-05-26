package com.viloveul.module.management.controller;

import com.viloveul.context.util.encryption.Tokenizer;
import com.viloveul.module.management.pojo.AuthForm;
import com.viloveul.module.management.service.CredentialService;
import com.viloveul.module.management.service.UserService;
import com.viloveul.context.util.misc.ActivityRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired(required = false)
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private Tokenizer tokenizer;

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Transactional(readOnly = true)
    @PostMapping(
        path = "/generate",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ActivityRecord(payload = false, action = "LOGIN")
    public Object generate(@Valid @RequestBody AuthForm form) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
            )
        );
        return this.tokenizer.generate(authentication.getDetails(), Tokenizer.USE_JWT);
    }
}
