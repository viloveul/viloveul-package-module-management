package com.viloveul.module.management.service.impl;

import com.viloveul.context.ApplicationContainer;
import com.viloveul.context.auth.AccessControlCustomizer;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.context.exception.GeneralFailureException;
import com.viloveul.context.type.SignerType;
import com.viloveul.context.util.helper.FieldHelper;
import com.viloveul.module.management.data.entity.Group;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.data.repository.GroupRepository;
import com.viloveul.module.management.data.repository.PrivilegeRepository;
import com.viloveul.module.management.data.repository.UserRepository;
import com.viloveul.module.management.pojo.UserForm;
import com.viloveul.module.management.service.UserService;
import com.viloveul.context.auth.dto.DetailAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class UserServiceImpl extends AbstractComponent implements UserService, AccessControlCustomizer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public User create(User user) {
        try {
            return this.userRepository.save(user);
        } catch (RuntimeException e) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_CANT_BE_CREATED, e);
        }
    }

    @Override
    public User create(UserForm form) {
        Group group = this.groupRepository.getOne(form.getGroup());
        User user = new User(form.getUsername(), form.getFullname());
        user.setEmail(form.getEmail());
        user.setStartDate(form.getStartDate());
        user.setEndDate(form.getEndDate());
        user.setGroup(group);
        user.setType(form.getType());
        this.checkInternal(user, form, "CREATE");
        if (!form.getPrivileges().isEmpty()) {
            List<Privilege> privileges = this.privilegeRepository.findAllById(form.getPrivileges());
            if (!privileges.isEmpty()) {
                user.setPrivileges(new HashSet<>(privileges));
            }
        }
        return this.create(user);
    }

    @Override
    public User update(String id, UserForm form) {
        User user = this.userRepository.getOne(id);
        try {
            this.checkInternal(user, form, "UPDATE");
            user.setEmail(Optional.ofNullable(form.getEmail()).orElse(user.getEmail()));
            user.setFullname(Optional.ofNullable(form.getFullname()).orElse(user.getFullname()));
            user.setEndDate(form.getEndDate());
            user.setType(form.getType() == null ? user.getType() : form.getType());
            Set<Privilege> privileges = new HashSet<>();
            if (!form.getPrivileges().isEmpty()) {
                privileges.addAll(this.privilegeRepository.findAllById(form.getPrivileges()));
            }
            user.setPrivileges(privileges);
            if (form.getGroup() != null) {
                user.setGroup(this.groupRepository.getOne(form.getGroup()));
            }
            return this.userRepository.save(user);
        } catch (RuntimeException e) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_CANT_BE_UPDATED, e);
        }
    }

    @Override
    public Boolean delete(String id) {
        Optional<User> result = this.userRepository.findById(id);
        if (result.isPresent()) {
            return this.delete(result.get());
        }
        return true;
    }

    @Override
    public Boolean delete(User user) {
        try {
            user.setDeletedAt(new Date());
            user.setStatus(Boolean.FALSE);
            this.userRepository.save(user);
        } catch (RuntimeException e) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_CANT_BE_DELETED, e);
        }
        return true;
    }

    @Override
    public User detail(String id) {
        Optional<User> result = this.userRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public User find(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void activation(String id, Boolean status) {
        User user = this.userRepository.getOne(id);
        user.setStatus(status);
        this.userRepository.save(user);
    }

    @Override
    public Page<User> search(Specification<User> filter, Pageable pageable) {
        return this.userRepository.findAll(filter, pageable);
    }

    private boolean checkInternalExtended(User user, UserForm form, String event, Collection<DetailAuthentication.GroupMapper> abilities) {
        if (form.getGroup() != null && !user.getGroup().getId().equals(form.getGroup())) {
            String authority = event.concat("-USER");
            List<String> groups = new ArrayList<>();
            for (DetailAuthentication.GroupMapper group : abilities) {
                if (group.getAuthorities().contains(authority)) {
                    groups.addAll(group.getGroups());
                }
            }
            return !groups.contains(form.getGroup());
        }
        return false;
    }

    private void checkInternal(User user, UserForm form, String event) {
        DetailAuthentication auth = ApplicationContainer.getDetailAuthentication(DetailAuthentication.class);
        boolean failed = auth == null;
        if (!failed && auth.getType() == SignerType.USER) {
            if (form.getType() != SignerType.USER) {
                failed = true;
            }
            if (!failed) {
                List<String> privileges = user.getPrivileges().stream().map(Privilege::getId).collect(Collectors.toList());
                failed = privileges.stream().noneMatch(x -> form.getPrivileges().contains(x)) || form.getPrivileges().stream().noneMatch(privileges::contains);
            }
            if (!failed && this.checkInternalExtended(user, form, event, auth.getAbilities())) {
                failed = true;
            }
        }

        if (failed) {
            throw new IllegalArgumentException("Cannot create/update this user. You are not ADMIN");
        }
    }

    @Override
    public Access registerAccessCustomizer() {
        return new DefaultAccess<User>("USER",
            authentication -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(FieldHelper.fillFieldSelector(root, "group.id"), authentication.getGroup().getId()),
            handler -> 0 < this.userRepository.count(handler.specification().and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), handler.object())))
        );
    }
}
