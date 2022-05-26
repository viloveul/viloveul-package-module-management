package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, String>, JpaSpecificationExecutor<Operation> {

    Operation findByIdentity(String identity);

}
