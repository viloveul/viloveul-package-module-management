package com.viloveul.module.management.service.impl;

import com.viloveul.module.management.service.ResourceService;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.module.management.data.entity.Resource;
import com.viloveul.module.management.pojo.ResourceForm;
import com.viloveul.module.management.data.repository.ResourceRepository;
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
public class ResourceServiceImpl extends AbstractComponent implements ResourceService {

    private static final Locale LOCALE = Locale.getDefault();

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Resource create(@Valid ResourceForm form) {
        Resource resource = new Resource(form.getIdentity().toUpperCase(LOCALE), form.getName());
        resource.setDescription(form.getDescription());
        return this.resourceRepository.save(resource);
    }

    @Override
    public Resource update(String id, @Valid ResourceForm form) {
        Resource resource = this.resourceRepository.getOne(id);
        resource.setIdentity(form.getIdentity().toUpperCase(LOCALE));
        resource.setName(form.getName());
        resource.setDescription(form.getDescription());
        return this.resourceRepository.save(resource);
    }

    @Override
    public Resource detail(String id) {
        Optional<Resource> result = this.resourceRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public Resource find(String identity) {
        return this.resourceRepository.findByIdentity(identity);
    }

    @Override
    public void activation(String id, Boolean status) {
        Resource resource = this.resourceRepository.getOne(id);
        resource.setStatus(status);
        this.resourceRepository.save(resource);
    }

    @Override
    public Page<Resource> search(Specification<Resource> filter, Pageable pageable) {
        return this.resourceRepository.findAll(filter, pageable);
    }
}
