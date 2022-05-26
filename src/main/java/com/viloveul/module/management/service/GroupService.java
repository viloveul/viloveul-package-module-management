package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.Group;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.GroupForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    Group create(Group group);

    Group create(GroupForm form);

    Group update(String id, GroupForm form);

    Boolean delete(Group group);

    Boolean delete(String id);

    Group detail(String id);

    Group find(String identity);

    List<Group> loadAll(User user);

    void activation(String id, Boolean status);

    Page<Group> search(Specification<Group> filter, Pageable pageable);

}
