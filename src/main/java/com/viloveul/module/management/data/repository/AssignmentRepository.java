package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String>, JpaSpecificationExecutor<Assignment> {

    @Query(
        "SELECT x " +
        "FROM Assignment x " +
        "WHERE x.user.id = :user " +
        "AND x.status = true " +
        "AND x.user.status = true " +
        "AND (x.endDate IS NULL OR cast(:date as date) IS NULL OR x.endDate < cast(:date as date)) " +
        "AND (x.startDate IS NULL OR cast(:date as date) IS NULL OR x.startDate >= cast(:date as date)) " +
        "AND x.deletedAt IS NULL "
    )
    List<Assignment> findAllActiveByUser(@Param("user") String user, @Param("date") Date date);

    @Query(
        "SELECT x " +
        "FROM Assignment x " +
        "WHERE x.status = true " +
        "AND x.user.status = true " +
        "AND x.privilege.status = true " +
        "AND x.group.id = :group " +
        "AND (x.endDate IS NULL OR cast(:date as date) IS NULL OR x.endDate < cast(:date as date)) " +
        "AND (x.startDate IS NULL OR cast(:date as date) IS NULL OR x.startDate >= cast(:date as date)) "
    )
    List<Assignment> findAllActiveByGroup(@Param("group") String group, @Param("date") Date date);

}
