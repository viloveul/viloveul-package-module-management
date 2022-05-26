package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Notification;
import com.viloveul.module.management.pojo.NotificationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public interface NotificationService {

    void send(@Valid NotificationForm form);

    Notification read(String id);

    Notification detail(String id);

    Page<Notification> search(Specification<Notification> filter, Pageable pageable);
}
