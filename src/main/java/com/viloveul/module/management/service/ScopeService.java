package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Scope;
import com.viloveul.module.management.pojo.ScopeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface ScopeService {

    Scope create(Scope scope);

    Scope create(ScopeForm form);

    void delete(String id);

    void delete(Scope scope);

    void activation(String id, Boolean status);

    Scope detail(String id);

    Page<Scope> search(Specification<Scope> filter, Pageable pageable);
}
