package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Assignment;
import com.viloveul.module.management.pojo.AssignmentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface AssignmentService {

    Assignment create(AssignmentForm form);

    Assignment detail(String id);

    void delete(String id);

    void activation(String id, Boolean status);

    Page<Assignment> search(Specification<Assignment> filter, Pageable pageable);

}
