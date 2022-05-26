package com.viloveul.module.management.service.impl;

import com.viloveul.context.auth.AccessControlCustomizer;
import com.viloveul.context.auth.dto.DetailAuthentication;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.context.exception.GeneralFailureException;
import com.viloveul.module.management.data.entity.Assignment;
import com.viloveul.module.management.data.entity.Group;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.data.repository.AssignmentRepository;
import com.viloveul.module.management.data.repository.GroupRepository;
import com.viloveul.module.management.pojo.GroupForm;
import com.viloveul.module.management.service.GroupService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl extends AbstractComponent implements GroupService, AccessControlCustomizer {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Group create(Group group) {
        group.setPath(Group.SEPARATOR.concat(group.getIdentity()));
        if (group.getParent() != null) {
            Group parent = group.getParent();
            if (Boolean.FALSE.equals(parent.getStatus())) {
                throw new GeneralFailureException(DomainErrorCollection.PARENT_INACTIVE);
            }
            group.setPath(parent.getPath().concat(Group.SEPARATOR).concat(group.getIdentity()));
        }
        return this.groupRepository.save(group);
    }

    @Override
    public Group create(@Valid GroupForm form) {
        Group group = new Group(form.getIdentity(), form.getName());
        if (form.getParent() != null) {
            group.setParent(this.groupRepository.getOne(form.getParent()));
        }
        return this.create(group);
    }

    @Override
    public Group update(String id, @Valid GroupForm form) {
        Group group = this.groupRepository.getOne(id);
        group.setName(form.getName());
        group.setParent(null);
        group.setPath(Group.SEPARATOR.concat(group.getIdentity()));
        if (form.getParent() != null) {
            Group parent = this.groupRepository.getOne(form.getParent());
            if (Boolean.FALSE.equals(parent.getStatus())) {
                throw new GeneralFailureException(DomainErrorCollection.PARENT_INACTIVE);
            }
            this.validateRecursive(
                parent.getId(),
                group.getChilds()
            );
            group.setPath(parent.getPath().concat(Group.SEPARATOR).concat(group.getIdentity()));
            group.setParent(parent);
        }
        List<Group> groups = new ArrayList<>();
        if (!group.getChilds().isEmpty()) {
            groups.addAll(this.prepareChilds(group, group.getChilds()));
        }
        groups.add(group);
        this.groupRepository.saveAll(groups);
        return Hibernate.unproxy(group, Group.class);
    }

    @Override
    public Boolean delete(Group group) {
        group.setDeletedAt(new Date());
        group.setStatus(Boolean.FALSE);
        List<Group> groups = new ArrayList<>();
        Collection<Group> childs = this.groupRepository.findAllByIdParent(group.getId());
        if (!childs.isEmpty()) {
            groups.addAll(
                this.prepareChilds(
                    group.getParent(),
                    childs
                )
            );
        }
        groups.add(group);
        this.groupRepository.saveAll(groups);
        return true;
    }

    @Override
    public Boolean delete(String id) {
        Optional<Group> result = this.groupRepository.findById(id);
        if (result.isPresent()) {
            return this.delete(result.get());
        }
        return false;
    }

    @Override
    public Group detail(String id) {
        Optional<Group> result = this.groupRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public Group find(String identity) {
        return this.groupRepository.findByIdentity(identity);
    }

    @Override
    public List<Group> loadAll(User user) {
        List<Group> groups = new ArrayList<>();
        groups.add(user.getGroup());
        List<Assignment> assignments = this.assignmentRepository.findAllActiveByUser(user.getId(), new Date());
        for (Assignment assignment : assignments) {
            Group group = assignment.getGroup();
            if (!groups.contains(group)) {
                groups.add(group);
            }
        }
        return this.extract(groups);
    }

    @Override
    public void activation(String id, Boolean status) {
        Group group = this.groupRepository.getOne(id);
        List<Group> groups = new ArrayList<>();
        group.setStatus(status);
        if (!group.getChilds().isEmpty()) {
            groups.addAll(this.prepareChilds(group, group.getChilds()));
        }
        groups.add(group);
        this.groupRepository.saveAll(groups);
    }

    @Override
    public Page<Group> search(Specification<Group> filter, Pageable pageable) {
        return this.groupRepository.findAll(filter, pageable);
    }

    private List<Group> extract(Collection<Group> groups) {
        List<Group> tmps = new ArrayList<>(groups);
        for (Group group : groups) {
            if (tmps.contains(group)) {
                tmps.add(group);
            }
            tmps.addAll(group.getChilds());
        }
        return tmps;
    }

    private Collection<Group> prepareChilds(Group group, Collection<Group> childs) {
        List<Group> groups = new ArrayList<>();
        for (Group child : childs) {
            if (group != null) {
                child.setParent(group);
                child.setPath(group.getPath().concat(Group.SEPARATOR).concat(child.getIdentity()));
                if (Boolean.FALSE.equals(group.getStatus())) {
                    child.setStatus(false);
                }
            } else {
                child.setParent(null);
                child.setPath(Group.SEPARATOR.concat(child.getIdentity()));
            }
            groups.add(child);
            if (!child.getChilds().isEmpty()) {
                groups.addAll(this.prepareChilds(child, child.getChilds()));
            }
        }
        return groups;
    }

    private void validateRecursive(String parent, Collection<Group> childs) {
        for (Group child : childs) {
            if (child.getId().equals(parent)) {
                throw new GeneralFailureException(
                    DomainErrorCollection.INFINITELY_RECURSIVE,
                    new RuntimeException("Group " + parent)
                );
            }
            this.validateRecursive(parent, child.getChilds());
        }
    }

    @Override
    public Access registerAccessCustomizer() {
        return new DefaultAccess<Group>("GROUP",
            (authentication, operation) -> (root, criteriaQuery, criteriaBuilder) -> {
                Collection<Predicate> predicates = new ArrayList<>();
                for (DetailAuthentication.GroupMapper group : authentication.getAbilities()) {
                    predicates.add(
                        root.get("id").in(group.getGroups())
                    );
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            },
            handler -> 0 < this.groupRepository.count(handler.specification().and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), handler.evaluator().getObject())))
        );
    }
}
