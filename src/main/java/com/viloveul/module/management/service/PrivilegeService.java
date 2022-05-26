package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.pojo.PrivilegeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface PrivilegeService {

    Privilege create(Privilege privilege);

    Privilege create(PrivilegeForm form);

    Privilege update(String id, PrivilegeForm form);

    Boolean delete(Privilege privilege);

    Boolean delete(String id);

    Privilege detail(String id);

    Privilege find(String identity);

    void activation(String id, Boolean status);

    Page<Privilege> search(Specification<Privilege> filter, Pageable pageable);
}
