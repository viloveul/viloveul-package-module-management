package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String>, JpaSpecificationExecutor<Resource> {

    Resource findByIdentity(String identity);

}
