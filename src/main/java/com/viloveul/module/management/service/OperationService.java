package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Operation;
import com.viloveul.module.management.pojo.OperationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface OperationService {

    Operation create(OperationForm form);

    Operation update(String id, OperationForm form);

    Operation detail(String id);

    Operation find(String identity);

    void activation(String id, Boolean status);

    Page<Operation> search(Specification<Operation> filter, Pageable pageable);

}
