package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, String>, JpaSpecificationExecutor<Scope> {

    @Query("SELECT x FROM Scope x WHERE x.privilege.id = ?1")
    List<Scope> findAllByPrivilege(String privilege);

}
