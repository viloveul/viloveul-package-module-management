package com.viloveul.module.management.service;

import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.UserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User create(User user);

    User create(UserForm form);

    User update(String id, UserForm form);

    Boolean delete(User user);

    Boolean delete(String id);

    User detail(String id);

    User find(String username);

    void activation(String id, Boolean status);

    Page<User> search(Specification<User> filter, Pageable pageable);
}
