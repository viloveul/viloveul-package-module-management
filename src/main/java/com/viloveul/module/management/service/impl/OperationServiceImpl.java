package com.viloveul.module.management.service.impl;

import com.viloveul.module.management.service.OperationService;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.module.management.data.entity.Operation;
import com.viloveul.module.management.pojo.OperationForm;
import com.viloveul.module.management.data.repository.OperationRepository;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@Service
public class OperationServiceImpl extends AbstractComponent implements OperationService {

    private static final Locale LOCALE = Locale.getDefault();

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Operation create(@Valid OperationForm form) {
        Operation operation = new Operation(form.getIdentity().toUpperCase(LOCALE), form.getName());
        return this.operationRepository.save(operation);
    }

    @Override
    public Operation update(String id, @Valid OperationForm form) {
        Operation operation = this.operationRepository.getOne(id);
        operation.setIdentity(form.getIdentity().toUpperCase(LOCALE));
        operation.setName(form.getName());
        return this.operationRepository.save(operation);
    }

    @Override
    public Operation detail(String id) {
        Optional<Operation> result = this.operationRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public Operation find(String identity) {
        return this.operationRepository.findByIdentity(identity);
    }

    @Override
    public void activation(String id, Boolean status) {
        Operation operation = this.operationRepository.getOne(id);
        operation.setStatus(status);
        this.operationRepository.save(operation);
    }

    @Override
    public Page<Operation> search(Specification<Operation> filter, Pageable pageable) {
        return this.operationRepository.findAll(filter, pageable);
    }
}
