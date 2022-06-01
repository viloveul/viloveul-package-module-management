package com.viloveul.module.management.service.impl;

import com.viloveul.module.management.service.AssignmentService;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.module.management.data.entity.Assignment;
import com.viloveul.module.management.data.entity.Group;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.AssignmentForm;
import com.viloveul.module.management.data.repository.AssignmentRepository;
import com.viloveul.module.management.data.repository.GroupRepository;
import com.viloveul.module.management.data.repository.PrivilegeRepository;
import com.viloveul.module.management.data.repository.UserRepository;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public Assignment create(@Valid AssignmentForm form) {
        User delegator = this.userRepository.getOne(form.getDelegator());
        Privilege privilege = this.privilegeRepository.getOne(form.getPrivilege());
        User user = this.userRepository.getOne(form.getUser());
        Group group = user.getGroup();
        if (form.getGroup() != null) {
            Optional<Group> gres = this.groupRepository.findById(form.getGroup());
            if (gres.isPresent()) {
                group = gres.get();
            }
        }
        Assignment assignment = new Assignment(delegator, group, privilege, user);
        assignment.setStartDate(form.getStartDate());
        assignment.setEndDate(form.getEndDate());
        return this.assignmentRepository.save(assignment);
    }

    @Override
    public Assignment detail(String id) {
        Optional<Assignment> assignment = this.assignmentRepository.findById(id);
        if (!assignment.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return assignment.get();
    }

    @Override
    public void delete(String id) {
        this.assignmentRepository.deleteById(id);
    }

    @Override
    public void activation(String id, Boolean status) {
        Assignment assignment = this.assignmentRepository.getOne(id);
        assignment.setStatus(status);
        this.assignmentRepository.save(assignment);
    }

    @Override
    public Page<Assignment> search(Specification<Assignment> filter, Pageable pageable) {
        return this.assignmentRepository.findAll(filter, pageable);
    }
}
