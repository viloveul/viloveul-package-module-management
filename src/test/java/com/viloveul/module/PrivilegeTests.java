package com.viloveul.module;

import com.viloveul.context.type.AuthorityType;
import com.viloveul.module.initial.TestConfiguration;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.pojo.PrivilegeForm;
import com.viloveul.module.management.search.PrivilegeSpecification;
import com.viloveul.module.management.service.PrivilegeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
        TestConfiguration.class
    }
)
@Transactional
class PrivilegeTests {

    @Autowired
    private PrivilegeService privilegeService;

    @Test
    void testCanCrud() {
        PrivilegeForm formCreate = new PrivilegeForm("Test Create");
        Privilege privilege2 = this.privilegeService.create(formCreate);
        Assertions.assertNotNull(privilege2);
        PrivilegeForm formUpdate = new PrivilegeForm("Test Update");
        Privilege privilege3 = this.privilegeService.update(privilege2.getId(), formUpdate);
        Assertions.assertEquals(formUpdate.getName(), privilege3.getName());
        this.privilegeService.delete(privilege3.getId());
    }

    @Test
    void testCanCreateAgregate() {
        Privilege privilege1 = this.privilegeService.find("user-administrating"); // 05-dummy.sql
        Assertions.assertNotNull(privilege1);
        PrivilegeForm formCreate = new PrivilegeForm("Test Create");
        formCreate.setAgregates(Collections.singleton(privilege1.getId()));
        Privilege privilege2 = this.privilegeService.create(formCreate);
        Assertions.assertNotNull(privilege2);
        Assertions.assertFalse(privilege2.getAgregates().isEmpty());
    }

    @Test
    void testCanSearch() {
        PrivilegeForm formCreate = new PrivilegeForm("Test Create");
        Privilege privilege = this.privilegeService.create(formCreate);
        PrivilegeSpecification filter = new PrivilegeSpecification();
        filter.setKeyword("test");
        filter.setType(AuthorityType.TASK);
        Page<Privilege> page1 = this.privilegeService.search(filter, PageRequest.of(0, 10));
        Assertions.assertNotEquals(0, page1.getTotalElements());
    }
}