package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, String>, JpaSpecificationExecutor<Notification> {

}
