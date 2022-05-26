package com.viloveul.module.management.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viloveul.context.filter.SearchProperties;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.pojo.UserForm;
import com.viloveul.module.management.search.UserSpecification;
import com.viloveul.module.management.service.AuthService;
import com.viloveul.module.management.service.GroupService;
import com.viloveul.module.management.service.PrivilegeService;
import com.viloveul.module.management.service.UserService;
import com.viloveul.context.util.misc.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "${viloveul.controller.user:/user}")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private GroupService groupService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('USER', 'SEARCH')")
    @SearchProperties(
        resource = "USER",
        operation = "SEARCH",
        allows = {
            @SearchProperties.Allow(field = "id", option = SearchProperties.Option.USER),
            @SearchProperties.Allow(field = "group.id", option = SearchProperties.Option.GROUP)
        }
    )
    public PageableResult<User> search(
        UserSpecification filter,
        Pageable pageable
    ) {
        return new PageableResult<User>(this.userService.search(filter, pageable)) {
            @Override
            @JsonIgnoreProperties("privileges")
            public Collection<User> getItems() {
                return this.items;
            }
        };
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'USER', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.userService.activation(id, status);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'USER', 'DETAIL')")
    public ResponseEntity<User> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.userService.detail(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = "/{id}")
    @PreAuthorize("hasPermission(#id, 'USER', 'UPDATE')")
    public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody @Valid UserForm form) {
        return new ResponseEntity<>(this.userService.update(id, form), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('USER', 'CREATE')")
    public ResponseEntity<User> create(
        @RequestBody @Valid UserForm form
    ) {
        return new ResponseEntity<>(this.userService.create(form), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'USER', 'DELETE')")
    public void delete(@PathVariable("id") String id) {
        this.userService.delete(id);
    }
}
