package com.viloveul.module.management.service.impl;

import com.viloveul.context.ApplicationContainer;
import com.viloveul.context.auth.AccessControlCustomizer;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.auth.dto.DetailAuthentication;
import com.viloveul.context.type.PrincipalType;
import com.viloveul.module.management.data.entity.Notification;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.NotificationForm;
import com.viloveul.module.management.data.repository.AssignmentRepository;
import com.viloveul.module.management.data.repository.GroupRepository;
import com.viloveul.module.management.data.repository.NotificationRepository;
import com.viloveul.module.management.data.repository.OperationRepository;
import com.viloveul.module.management.data.repository.ResourceRepository;
import com.viloveul.module.management.data.repository.UserRepository;
import com.viloveul.module.management.service.NotificationService;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class NotificationServiceImpl extends AbstractComponent implements NotificationService, AccessControlCustomizer {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Async
    @Override
    public void send(@Valid NotificationForm form) {
        User sender = this.userRepository.getOne(form.getSender());
        Notification notification = new Notification(sender, form.getType(), form.getTarget());
        notification.setResource(form.getResource());
        notification.setOperation(form.getOperation());
        notification.setBody(form.getBody());
        notification.setObject(form.getObject());
        notification.setSubject(form.getSubject());
        this.notificationRepository.save(notification);
    }

    @Override
    public Notification read(String id) {
        Optional<Notification> result = this.notificationRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        Notification notification = result.get();
        if (!Boolean.FALSE.equals(notification.getStatus())) {
            notification.setStatus(Boolean.FALSE);
            DetailAuthentication authentication = ApplicationContainer.getDetailAuthentication(DetailAuthentication.class);
            if (authentication != null) {
                notification.setReader(this.userRepository.getOne(authentication.getId()));
            }
            this.notificationRepository.save(notification);
        }
        return notification;
    }

    @Override
    public Notification detail(String id) {
        Optional<Notification> result = this.notificationRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public Page<Notification> search(Specification<Notification> filter, Pageable pageable) {
        return this.notificationRepository.findAll(filter, pageable);
    }

    @Override
    public Access registerAccessCustomizer() {
        return new DefaultAccess<Notification>("NOTIFICATION",
            authentication -> (root, criteriaQuery, criteriaBuilder) -> {
                String id = authentication.getId();
                Predicate userPredicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("principal"), id),
                    criteriaBuilder.equal(root.get("type"), PrincipalType.USER)
                );
                Collection<Predicate> predicates = new ArrayList<>();
                for (DetailAuthentication.GroupMapper group : authentication.getAbilities()) {
                    for (String authority : group.getAuthorities()) {
                        if (!authority.endsWith("_PRIVILEGE")) {
                            String[] parts = authority.split("-");
                            predicates.add(
                                criteriaBuilder.and(
                                    criteriaBuilder.equal(root.get("type"), PrincipalType.GROUP),
                                    root.get("principal").in(group.getGroups()),
                                    criteriaBuilder.equal(root.get("resource"), parts[1]),
                                    criteriaBuilder.equal(root.get("operation"), parts[0])
                                )
                            );
                        }
                    }
                }
                return predicates.isEmpty() ? userPredicate : criteriaBuilder.or(userPredicate, criteriaBuilder.or(predicates.toArray(new Predicate[0])));
            },
            handler -> 0 < this.notificationRepository.count(handler.specification().and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), handler.object())))
        );
    }
}
