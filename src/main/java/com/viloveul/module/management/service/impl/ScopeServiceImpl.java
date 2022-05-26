package com.viloveul.module.management.service.impl;

import com.viloveul.module.management.data.entity.Scope;
import com.viloveul.module.management.data.repository.ScopeRepository;
import com.viloveul.module.management.data.repository.OperationRepository;
import com.viloveul.module.management.data.repository.PrivilegeRepository;
import com.viloveul.module.management.data.repository.ResourceRepository;
import com.viloveul.module.management.service.ScopeService;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.module.management.data.entity.Operation;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.data.entity.Resource;
import com.viloveul.module.management.pojo.ScopeForm;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class ScopeServiceImpl implements ScopeService {

    @Autowired
    private ScopeRepository scopeRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Scope create(Scope scope) {
        return this.scopeRepository.save(scope);
    }

    @Override
    public Scope create(@Valid ScopeForm form) {
        Privilege privilege = this.privilegeRepository.getOne(form.getPrivilege());
        Resource resource = this.resourceRepository.getOne(form.getResource());
        Operation operation = this.operationRepository.getOne(form.getOperation());
        return this.create(new Scope(privilege, resource, operation));
    }

    @Override
    public void delete(String id) {
        Optional<Scope> scope = this.scopeRepository.findById(id);
        scope.ifPresent(this::delete);
    }

    @Override
    public void delete(Scope scope) {
        this.scopeRepository.delete(scope);
    }

    @Override
    public void activation(String id, Boolean status) {
        Scope scope = this.scopeRepository.getOne(id);
        scope.setStatus(status);
        this.scopeRepository.save(scope);
    }

    @Override
    public Scope detail(String id) {
        Optional<Scope> result = this.scopeRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public Page<Scope> search(Specification<Scope> filter, Pageable pageable) {
        return this.scopeRepository.findAll(filter, pageable);
    }
}
