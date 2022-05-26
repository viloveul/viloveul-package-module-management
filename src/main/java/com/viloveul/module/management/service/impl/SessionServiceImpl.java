package com.viloveul.module.management.service.impl;

import com.viloveul.module.management.data.entity.Session;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.data.repository.SessionRepository;
import com.viloveul.module.management.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session create(User user) {
        return this.sessionRepository.save(new Session(user));
    }

    @Override
    public Boolean delete(Session session) {
        session.setDeletedAt(new Date());
        session.setStatus(false);
        this.sessionRepository.save(session);
        return true;
    }

    @Override
    public Boolean delete(String id) {
        Optional<Session> result = this.sessionRepository.findById(id);
        result.ifPresent(this::delete);
        return true;
    }

    @Override
    public Boolean purge(String id) {
        List<Session> sessions = this.sessionRepository.findAllByUser(id);
        sessions.forEach((Session x) -> {
            x.setStatus(false);
            x.setDeletedAt(new Date());
        });
        if (!sessions.isEmpty()) {
            this.sessionRepository.saveAll(sessions);
        }
        return true;
    }

    @Override
    public Boolean purge(User user) {
        List<Session> sessions = this.sessionRepository.findAllByUser(user.getId());
        sessions.forEach((Session x) -> {
            x.setStatus(false);
            x.setDeletedAt(new Date());
        });
        if (!sessions.isEmpty()) {
            this.sessionRepository.saveAll(sessions);
        }
        return true;
    }

    @Override
    public void activation(String id, Boolean status) {
        Optional<Session> result = this.sessionRepository.findById(id);
        if (result.isPresent()) {
            Session session = result.get();
            session.setStatus(status);
            this.sessionRepository.save(session);
        }
    }

    @Override
    public Page<Session> search(Specification<Session> filter, Pageable pageable) {
        return null;
    }
}
