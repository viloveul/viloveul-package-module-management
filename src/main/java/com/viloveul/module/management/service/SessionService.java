package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Session;
import com.viloveul.module.management.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {

    Session create(User user);

    Boolean delete(Session session);

    Boolean delete(String id);

    Boolean purge(String id);

    Boolean purge(User user);

    void activation(String id, Boolean status);

    Page<Session> search(Specification<Session> filter, Pageable pageable);
}
