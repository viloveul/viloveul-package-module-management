package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, String>, JpaSpecificationExecutor<Session> {

    @Query("SELECT x FROM Session x WHERE x.user.id = :id AND x.status = true")
    List<Session> findAllByUser(@Param("id") String id);

}
