package com.viloveul.module.management.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viloveul.context.filter.SearchPropertyAuthorization;
import com.viloveul.module.management.data.entity.Group;
import com.viloveul.module.management.pojo.GroupForm;
import com.viloveul.module.management.search.GroupSpecification;
import com.viloveul.module.management.service.GroupService;
import com.viloveul.module.management.service.OperationService;
import com.viloveul.module.management.service.PrivilegeService;
import com.viloveul.module.management.service.ResourceService;
import com.viloveul.module.management.service.UserService;
import com.viloveul.context.util.misc.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RequestMapping(path = "${viloveul.controller.group:/group}")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('GROUP', 'SEARCH') or hasPermission('GROUP', 'CREATE') or hasPermission('GROUP', 'DETAIL') or hasPermission('GROUP', 'DELETE') or hasPermission('GROUP', 'ACTIVATION') or hasPermission('GROUP', 'UPDATE')")
    @SearchPropertyAuthorization(
        resource = "GROUP",
        operation = "SEARCH"
    )
    public PageableResult<Group> search(Pageable pageable, GroupSpecification filter, Authentication authentication) {
        return new PageableResult<Group>(this.groupService.search(filter, pageable)) {
            @JsonIgnoreProperties({"childs"})
            @Override
            public Collection<Group> getItems() {
                return this.items;
            }
        };
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'GROUP', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.groupService.activation(id, status);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'GROUP', 'DETAIL') or hasPermission(#id, 'GROUP', 'ACTIVATION') or hasPermission(#id, 'GROUP', 'DELETE') or hasPermission(#id, 'GROUP', 'UPDATE')")
    public ResponseEntity<Group> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.groupService.detail(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = "/{id}")
    @PreAuthorize("hasPermission(#id, 'GROUP', 'UPDATE')")
    public ResponseEntity<Group> update(@PathVariable("id") String id, @RequestBody @Valid GroupForm form) {
        return new ResponseEntity<>(this.groupService.update(id, form), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'GROUP', 'DELETE')")
    public void delete(@PathVariable("id") String id) {
        this.groupService.delete(id);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('GROUP', 'CREATE')")
    public ResponseEntity<Group> create(@RequestBody @Valid GroupForm form) {
        Group group = this.groupService.create(form);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }
}
