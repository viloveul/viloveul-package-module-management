package com.viloveul.module.management.service.impl;

import com.viloveul.context.auth.AccessControlCustomizer;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.context.exception.GeneralFailureException;
import com.viloveul.context.type.AuthorityType;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.data.repository.AssignmentRepository;
import com.viloveul.module.management.data.repository.PrivilegeRepository;
import com.viloveul.module.management.pojo.PrivilegeForm;
import com.viloveul.module.management.service.PrivilegeService;
import com.viloveul.context.util.helper.GeneralHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class PrivilegeServiceImpl extends AbstractComponent implements PrivilegeService, AccessControlCustomizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

    private static final Locale LOCALE = Locale.getDefault();

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Privilege create(@Valid Privilege privilege) {
        try {
            if (privilege.getType() == null) {
                privilege.setType(AuthorityType.TASK);
            }
            return this.privilegeRepository.save(privilege);
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        return null;
    }

    @Override
    public Privilege create(@Valid PrivilegeForm form) {
        Privilege privilege = new Privilege(form.getIdentity().toUpperCase(LOCALE), form.getType(), form.getName());
        Collection<String> agregates = GeneralHelper.mergeCollection(
            form.getAgregates(),
            form.getChilds(),
            form.getTasks()
        );
        if (!agregates.isEmpty()) {
            privilege.setAgregates(new HashSet<>(this.privilegeRepository.findAllById(agregates)));
        }
        return this.create(privilege);
    }

    @Override
    public Privilege update(String id, @Valid PrivilegeForm form) {
        Privilege privilege = this.privilegeRepository.getOne(id);
        privilege.getScopes().clear();
        privilege.getAgregates().clear();
        Collection<String> agregates = GeneralHelper.mergeCollection(
            form.getAgregates(),
            form.getChilds(),
            form.getTasks()
        );
        if (!agregates.isEmpty()) {
            List<Privilege> privileges = this.privilegeRepository.findAllById(agregates);
            if (!privileges.isEmpty()) {
                this.validateRecursive(privilege.getId(), privileges);
                privilege.getAgregates().addAll(privileges);
            }
        }
        privilege.setIdentity(form.getIdentity().toUpperCase(LOCALE));
        privilege.setName(form.getName());
        return this.privilegeRepository.save(privilege);
    }

    @Override
    public Boolean delete(@Valid Privilege privilege) {
        try {
            privilege.setDeletedAt(new Date());
            this.privilegeRepository.save(privilege);
            return true;
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        return false;
    }

    @Override
    public Boolean delete(String id) {
        Optional<Privilege> result = this.privilegeRepository.findById(id);
        if (result.isPresent()) {
            return this.delete(result.get());
        }
        return false;
    }

    @Override
    public Privilege detail(String id) {
        Optional<Privilege> result = this.privilegeRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public Privilege find(String identity) {
        return this.privilegeRepository.findByIdentity(identity.toUpperCase(LOCALE));
    }

    @Override
    public void activation(String id, Boolean status) {
        Privilege privilege = this.privilegeRepository.getOne(id);
        privilege.setStatus(status);
        this.privilegeRepository.save(privilege);
    }

    private List<Privilege> extract(Collection<Privilege> privileges) {
        List<Privilege> results = new ArrayList<>();
        for (Privilege x : privileges) {
            if (!results.contains(x)) {
                results.add(x);
            }

            List<Privilege> tmps = this.extract(x.getAgregates());
            for (Privilege y : tmps) {
                if (!privileges.contains(y)) {
                    privileges.add(y);
                }
            }
        }
        return results;
    }

    @Override
    public Page<Privilege> search(Specification<Privilege> filter, Pageable pageable) {
        return this.privilegeRepository.findAll(filter, pageable);
    }

    private void validateRecursive(String id, Collection<Privilege> privileges) {
        for (Privilege privilege : privileges) {
            if (privilege.getId().equals(id)) {
                throw new GeneralFailureException(
                    DomainErrorCollection.INFINITELY_RECURSIVE,
                    new RuntimeException("Privilege " + id)
                );
            }
            this.validateRecursive(id, privilege.getAgregates());
        }
    }

    @Override
    public Access registerAccessCustomizer() {
        return new DefaultAccess<Privilege>("PRIVILEGE",
            (authentication, operation) -> (root, criteriaQuery, criteriaBuilder) -> root.get("identity").in(authentication.getPrivileges(null)),
            handler -> 0 < this.privilegeRepository.count(handler.specification().and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), handler.evaluator().getObject())))
        );
    }
}
