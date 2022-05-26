package com.viloveul.module.management.service.impl;

import com.viloveul.context.auth.AccessControlCustomizer;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.type.SecureType;
import com.viloveul.context.util.helper.FieldHelper;
import com.viloveul.module.management.service.CredentialService;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.module.management.data.entity.Credential;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.CredentialForm;
import com.viloveul.module.management.data.repository.CredentialRepository;
import com.viloveul.module.management.data.repository.UserRepository;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CredentialServiceImpl extends AbstractComponent implements CredentialService, AccessControlCustomizer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Credential create(@Valid CredentialForm form) {
        User user = this.userRepository.getOne(form.getUser());
        if (form.getType() == SecureType.PASSWORD) {
            List<Credential> credentials = this.credentialRepository.findAll(
                user.getUsername(),
                Arrays.asList(Boolean.FALSE, Boolean.TRUE),
                Collections.singletonList(SecureType.PASSWORD)
            );
            for (Credential current : credentials) {
                if (this.passwordEncoder.matches(form.getValue(), current.getValue())) {
                    throw new GeneralFailureException(DomainErrorCollection.DATA_ALREADY_EXISTS);
                }
            }
        }
        Credential credential = new Credential(
            user,
            form.getType(),
            this.passwordEncoder.encode(form.getValue())
        );
        credential.setDescription(form.getDescription());
        credential.setStartDate(form.getStartDate());
        credential.setEndDate(form.getEndDate());
        return this.credentialRepository.save(credential);
    }

    @Override
    public void activation(String id, Boolean status) {
        Credential credential = this.credentialRepository.getOne(id);
        credential.setStatus(status);
        this.credentialRepository.save(credential);
    }

    @Override
    public Credential detail(String id) {
        Optional<Credential> result = this.credentialRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public Boolean delete(String id) {
        Optional<Credential> credential = this.credentialRepository.findById(id);
        return credential.isPresent() && this.delete(credential.get());
    }

    @Override
    public Boolean delete(@Valid CredentialForm form) {
        User user = this.userRepository.getOne(form.getUser());
        List<Credential> toDeletes = new ArrayList<>();
        List<Credential> credentials = this.credentialRepository.findAllTypeNotIn(
            user.getUsername(),
            Collections.singletonList(Boolean.TRUE),
            Collections.singletonList(SecureType.REQUESTED)
        );
        for (Credential current : credentials) {
            if (this.passwordEncoder.matches(form.getValue(), current.getValue())) {
                current.setDeletedAt(new Date());
                toDeletes.add(current);
            }
        }
        if (!toDeletes.isEmpty()) {
            this.credentialRepository.saveAll(toDeletes);
        }
        return true;
    }

    @Override
    public Boolean delete(Credential credential) {
        credential.setDeletedAt(new Date());
        credential.setStatus(false);
        this.credentialRepository.save(credential);
        return true;
    }

    @Override
    public Page<Credential> search(Specification<Credential> filter, Pageable pageable) {
        return this.credentialRepository.findAll(filter, pageable);
    }

    @Override
    public Access registerAccessCustomizer() {
        return new DefaultAccess<Credential>("CREDENTIAL",
            (authentication, operation) -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(FieldHelper.fillFieldSelector(root, "user.id"), authentication.getId()),
            handler -> 0 < this.credentialRepository.count(handler.specification().and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), handler.evaluator().getObject())))
        );
    }
}
