package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Resource;
import com.viloveul.module.management.pojo.ResourceForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface ResourceService {

    Resource create(ResourceForm form);

    Resource update(String id, ResourceForm form);

    Resource detail(String id);

    Resource find(String identity);

    void activation(String id, Boolean status);

    Page<Resource> search(Specification<Resource> filter, Pageable pageable);

}
