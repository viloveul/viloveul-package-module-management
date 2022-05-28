package com.viloveul.module.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viloveul.context.base.AbstractController;
import com.viloveul.context.event.GenericTransform;
import com.viloveul.context.type.SecureType;
import com.viloveul.context.util.encryption.Tokenizer;
import com.viloveul.context.util.misc.StringObjectMapper;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.AuthForm;
import com.viloveul.context.util.misc.ActivityRecord;
import com.viloveul.module.management.pojo.CredentialForm;
import com.viloveul.module.management.service.CredentialService;
import com.viloveul.module.management.service.UserService;
import lombok.SneakyThrows;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Collections;

@RestController
@RequestMapping(path = "${viloveul.controller.authentication:/authentication}")
public class AuthController extends AbstractController {

    @Autowired(required = false)
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private Tokenizer tokenizer;

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @PostMapping(
        path = "/generate",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ActivityRecord(payload = false, action = "LOGIN")
    public Object generate(@RequestBody AuthForm form) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
            )
        );
        return this.tokenizer.generate(authentication.getDetails(), Tokenizer.USE_JWT);
    }

    @Transactional
    @SneakyThrows
    @PostMapping(path = "/forgot")
    public Object forgot(@RequestParam("username") String username) {
        String chrs = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()";
        String password = SecureRandom.getInstanceStrong().ints(9, 0, chrs.length()).mapToObj(chrs::charAt).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        User user = this.userService.find(username);
        CredentialForm form = new CredentialForm(user.getId(), SecureType.REQUESTED, password, password);
        this.credentialService.create(form);
        this.applicationEventPublisher.publishEvent(
            new GenericTransform(
                "MAIL",
                new StringObjectMapper(
                    new String[]{"subject", "body", "recipients"},
                    new Object[]{
                        "Password",
                        String.format("Your requested password for %s is %s", username, password),
                        Collections.singletonList(
                            new StringObjectMapper(
                                new String[]{"email", "type"},
                                new Object[]{user.getEmail(), "TO"}
                            )
                        )
                    }
                )
            )
        );
        return "ok";
    }
}
