package com.viloveul.module;

import com.viloveul.context.type.SecureType;
import com.viloveul.module.initial.TestConfiguration;
import com.viloveul.module.initial.TestConstant;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.CredentialForm;
import com.viloveul.module.management.service.CredentialService;
import com.viloveul.module.management.service.UserService;
import com.viloveul.context.exception.GeneralFailureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
        TestConfiguration.class
    }
)
@Transactional
class CredentialTests {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Test
    void testCanCreateCredential() {
        Assertions.assertDoesNotThrow(() -> {
            User user = this.userService.find(TestConstant.USERNAME);
            this.credentialService.create(TestConstant.CREDENTIAL);
        });
    }

    @Test
    void testCantCreateCredential() {
        User user = this.userService.find(TestConstant.USERNAME);
        this.credentialService.create(TestConstant.CREDENTIAL);
        Assertions.assertThrows(GeneralFailureException.class, () -> {
            credentialService.create(TestConstant.CREDENTIAL);
        });
    }

    @Test
    void testCanDeleteCredential() {
        User user = this.userService.find(TestConstant.USERNAME);
        this.credentialService.create(TestConstant.CREDENTIAL);
        Assertions.assertTrue(this.credentialService.delete(TestConstant.CREDENTIAL));
    }

    @Test
    void testCantDeleteCredential() {
        CredentialForm credential = new CredentialForm(
            TestConstant.CREDENTIAL.getUser(),
            SecureType.PASSWORD,
            TestConstant.PASSWORD,
            TestConstant.PASSWORD
        );
        User user = this.userService.find(TestConstant.USERNAME);
        this.credentialService.create(TestConstant.CREDENTIAL);
        Assertions.assertFalse(this.credentialService.delete(credential));
    }

}
