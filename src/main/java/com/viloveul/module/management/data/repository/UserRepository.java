package com.viloveul.module.management.data.repository;

import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.data.repository.advanced.UserRepositoryAdvanced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
    extends JpaRepository<User, String>, JpaSpecificationExecutor<User>, UserRepositoryAdvanced {

    User findByUsername(String username);
}
