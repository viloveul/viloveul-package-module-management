package com.viloveul.module.management.data.repository;

import com.viloveul.context.type.SecureType;
import com.viloveul.module.management.data.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, String>, JpaSpecificationExecutor<Credential> {

    @Query(
        "SELECT X " +
        "FROM Credential X " +
        "WHERE X.user.username = :username " +
            "AND X.status IN (:status) " +
            "AND (X.endDate IS NULL OR X.endDate < CURRENT_DATE) " +
            "AND (X.startDate IS NULL OR X.startDate >= CURRENT_DATE) " +
            "AND X.deletedAt IS NULL " +
            "AND X.type IN (:type)"
    )
    List<Credential> findAll(
            @Param("username") String username,
            @NotEmpty @Param("status") List<Boolean> status,
            @NotEmpty @Param("type") List<SecureType> type
    );

    @Query(
        "SELECT X " +
        "FROM Credential X " +
        "WHERE X.user.username = :username " +
            "AND X.status IN (:status) " +
            "AND (X.endDate IS NULL OR X.endDate < CURRENT_DATE) " +
            "AND (X.startDate IS NULL OR X.startDate >= CURRENT_DATE) " +
            "AND X.deletedAt IS NULL " +
            "AND X.type NOT IN (:type)"
    )
    List<Credential> findAllTypeNotIn(
            @Param("username") String username,
            @NotEmpty @Param("status") List<Boolean> status,
            @NotEmpty @Param("type") List<SecureType> type
    );

}
