package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Credential;
import com.viloveul.module.management.pojo.CredentialForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface CredentialService {

    Credential create(CredentialForm form);

    void activation(String id, Boolean status);

    Credential detail(String id);

    Boolean delete(String id);

    Boolean delete(Credential credential);

    Boolean delete(CredentialForm form);

    Page<Credential> search(Specification<Credential> filter, Pageable pageable);

}
