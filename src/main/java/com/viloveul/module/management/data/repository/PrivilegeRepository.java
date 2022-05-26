package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, String>, JpaSpecificationExecutor<Privilege> {

    Privilege findByIdentity(String identity);

}
