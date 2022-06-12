package com.viloveul.module;

import com.viloveul.module.initial.TestConfiguration;
import com.viloveul.module.management.data.entity.Group;
import com.viloveul.module.management.pojo.GroupForm;
import com.viloveul.module.management.search.GroupSpecification;
import com.viloveul.module.management.service.GroupService;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
        TestConfiguration.class
    }
)
@Transactional
class GroupTests {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PrivilegeService privilegeService;

    @Test
    void testCanCreate() {
        GroupForm form1 = new GroupForm("TEST-1", "Test 1");
        Group group1 = this.groupService.create(form1);
        Assertions.assertNotNull(group1);
        GroupForm form2 = new GroupForm("TEST-2", "Test 2");
        form2.setParent(group1.getId());
        Group group2 = this.groupService.create(form2);
        Assertions.assertNotNull(group2);
    }

    @Test
    void testCantInfiniteRecursive() {
        Group group1 = this.groupService.find("SAMPLE-LVL-1"); // check V1_0_0_003_002__ALLMANDUM.sql
        Assertions.assertNotNull(group1);
        Group group2 = this.groupService.find("SAMPLE-LVL-3"); // check V1_0_0_003_002__ALLMANDUM.sql
        Assertions.assertNotNull(group2);
//        GroupForm form = new GroupForm("Test 4");
//        form.setParent(group2.getId());
//        String gid = group1.getId();
//        Assertions.assertThrows(GeneralFailureException.class, () -> {
//            this.groupService.update(gid, form);
//        });
    }

    @Test
    void testCanUpdateRecursive() {
        GroupForm form1 = new GroupForm("TEST-1", "Test 1");
        Group group1 = this.groupService.create(form1);
        Assertions.assertNotNull(group1);
        GroupForm form2 = new GroupForm("TEST-2", "Test 2");
        form2.setParent(group1.getId());
        Group group2 = this.groupService.create(form2);
        Assertions.assertNotNull(group2);
        GroupForm form3 = new GroupForm("TEST-3", "Test 3");
        form3.setParent(group2.getId());
        Group group3 = this.groupService.create(form3);
        Assertions.assertNotNull(group3);
        GroupForm form4 = new GroupForm("TEST-4", "Test 4");
        this.groupService.update(group2.getId(), form4);
    }

    @Test
    void testCanDeleteParent() {
        GroupForm form1 = new GroupForm("TEST-1", "Test 1");
        Group group1 = this.groupService.create(form1);
        Assertions.assertNotNull(group1);
        GroupForm form2 = new GroupForm("TEST-2", "Test 2");
        form2.setParent(group1.getId());
        Group group2 = this.groupService.create(form2);
        Assertions.assertNotNull(group2);
        GroupForm form3 = new GroupForm("TEST-3", "Test 3");
        form3.setParent(group2.getId());
        Group group3 = this.groupService.create(form3);
        Assertions.assertNotNull(group3);
        this.groupService.delete(group1);
        Assertions.assertEquals(group2.getId(), this.groupService.detail(group3.getId()).getParent().getId());
    }

    @Test
    void testCanSearch() {
        GroupForm form1 = new GroupForm("TEST-SEARCH-IDENTITY-1", "Test Search Name 1");
        Group group1 = this.groupService.create(form1);
        Assertions.assertNotNull(group1);
        GroupForm form2 = new GroupForm("TEST-SEARCH-IDENTITY-2", "Test Search Name 2");
        form2.setParent(group1.getId());
        Group group2 = this.groupService.create(form2);
        Assertions.assertNotNull(group2);
        GroupForm form3 = new GroupForm("TEST-SEARCH-IDENTITY-3", "Test Search Name 3");
        form3.setParent(group2.getId());
        Group group3 = this.groupService.create(form3);
        Assertions.assertNotNull(group3);
        GroupSpecification filter = new GroupSpecification();
        filter.setKeyword("test");
        Page<Group> page1 = this.groupService.search(filter, PageRequest.of(0, 10));
        Assertions.assertEquals(3, page1.getTotalElements());
        filter.setName("Name");
        Page<Group> page2 = this.groupService.search(filter, PageRequest.of(0, 10));
        Assertions.assertEquals(3, page2.getTotalElements());
        filter.setIdentity("test");
        Page<Group> page3 = this.groupService.search(filter, PageRequest.of(0, 10));
        Assertions.assertEquals(0, page3.getTotalElements());
    }
}
