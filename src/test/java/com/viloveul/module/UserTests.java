package com.viloveul.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viloveul.module.initial.TestConfiguration;
import com.viloveul.module.initial.TestConstant;
import com.viloveul.context.type.SignerType;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.UserForm;
import com.viloveul.module.management.search.UserSpecification;
import com.viloveul.module.management.service.AuthService;
import com.viloveul.module.management.service.UserService;
import com.viloveul.context.util.misc.StringObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
class UserTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            this.authService.loadUserByUsername("dor");
        });
    }

    @Test
    void testUserFound() {
        UserDetails user = this.authService.loadUserByUsername(TestConstant.USERNAME);
        Assertions.assertEquals(TestConstant.USERNAME, user.getUsername());
    }

    @Test
    void testCanFindUserByUsername() {
        User user = this.userService.find(TestConstant.USERNAME);
        Assertions.assertNotNull(user);
    }

    @Test
    void testCanCreateAndUpdateUser() {
        User user = this.userService.create(new User(RandomString.make(), TestConstant.FULLNAME));
        Assertions.assertNotNull(user.getId());
        UserForm form = new UserForm(
            this.objectMapper.convertValue(user, StringObjectMapper.class)
        );
        form.setFullname(RandomString.make());
        Assertions.assertNotEquals(TestConstant.FULLNAME, this.userService.update(user.getId(), form).getFullname());
    }

    @Test
    void testSearch() {
        User user1 = new User();
        user1.setEmail(TestConstant.EMAIL);
        user1.setFullname(TestConstant.FULLNAME);
        user1.setUsername(RandomString.make());
        user1.setType(SignerType.ADMIN);
        this.userService.create(user1);

        User user2 = new User();
        user2.setEmail(TestConstant.EMAIL);
        user2.setFullname(TestConstant.FULLNAME);
        user2.setUsername(RandomString.make());
        user2.setType(SignerType.ADMIN);
        this.userService.create(user2);

        UserSpecification search = new UserSpecification();
        Page<User> pages = this.userService.search(
            search,
            PageRequest.of(TestConstant.ACTIVE_PAGE, TestConstant.PAGE_SIZE, Sort.unsorted())
        );
        Assertions.assertEquals(TestConstant.PAGE_SIZE, pages.getContent().size());
        Assertions.assertNotEquals(pages.getContent().size(), pages.getTotalElements());
    }
}
