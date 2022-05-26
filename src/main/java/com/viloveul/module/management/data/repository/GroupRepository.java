package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {

    Group findByIdentity(String identity);

    @Query("SELECT x FROM Group x WHERE x.parent.id = ?1")
    List<Group> findAllByIdParent(String parent);
}
