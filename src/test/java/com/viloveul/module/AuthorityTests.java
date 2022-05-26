package com.viloveul.module;

import com.viloveul.module.initial.TestConfiguration;
import com.viloveul.module.management.data.entity.Operation;
import com.viloveul.module.management.data.entity.Scope;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.data.entity.Resource;
import com.viloveul.module.management.pojo.OperationForm;
import com.viloveul.module.management.pojo.ScopeForm;
import com.viloveul.module.management.pojo.PrivilegeForm;
import com.viloveul.module.management.pojo.ResourceForm;
import com.viloveul.module.management.service.OperationService;
import com.viloveul.module.management.service.ScopeService;
import com.viloveul.module.management.service.PrivilegeService;
import com.viloveul.module.management.service.ResourceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
        TestConfiguration.class
    }
)
@Transactional
class AuthorityTests {

    @Autowired
    private ScopeService scopeService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private OperationService operationService;

    @Test
    void testCanCreate() {
        Privilege privilege = this.privilegeService.create(
            new PrivilegeForm("Test Privilege", "test-privileges")
        );
        Assertions.assertNotNull(privilege);
        Resource resource = this.resourceService.create(
            new ResourceForm("test-resources", "Test Resource")
        );
        Assertions.assertNotNull(resource);
        Operation operation = this.operationService.create(
            new OperationForm("test-operations", "Test Access")
        );
        Assertions.assertNotNull(operation);
        Scope scope = this.scopeService.create(
            new ScopeForm(privilege.getId(), resource.getId(), operation.getId())
        );
        Assertions.assertNotNull(scope);
    }

}
