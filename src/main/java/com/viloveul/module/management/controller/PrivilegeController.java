package com.viloveul.module.management.controller;

import com.viloveul.context.auth.dto.DetailAuthentication;
import com.viloveul.context.type.SignerType;
import com.viloveul.context.filter.SpecBuilder;
import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.pojo.PrivilegeForm;
import com.viloveul.module.management.search.PrivilegeSpecification;
import com.viloveul.module.management.service.PrivilegeService;
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
@RequestMapping(path = "${viloveul.controller.privilege:/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('PRIVILEGE', 'SEARCH') or hasPermission('PRIVILEGE', 'ACTIVATION') or hasPermission('PRIVILEGE', 'CREATE') or hasPermission('PRIVILEGE', 'DETAIL') or hasPermission('PRIVILEGE', 'DELETE') or hasPermission('PRIVILEGE', 'UPDATE')")
    public PageableResult<Privilege> search(Authentication authentication, Pageable pageable, PrivilegeSpecification filter) {
        DetailAuthentication authDetail = (DetailAuthentication) authentication.getDetails();
        if (authDetail.getType() == SignerType.USER) {
            Collection<Object> includes = authDetail.getPrivileges(x -> filter.getGroup() == null || x.getGroups().contains(filter.getGroup()));
            return new PageableResult<>(
                this.privilegeService.search(
                    filter.and(SpecBuilder.inValue("identity", includes)),
                    pageable
                )
            );
        }
        return new PageableResult<>(this.privilegeService.search(filter, pageable));
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @PreAuthorize("hasPermission(#id, 'PRIVILEGE', 'ACTIVATION')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.privilegeService.activation(id, status);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'PRIVILEGE', 'DETAIL') or hasPermission(#id, 'PRIVILEGE', 'ACTIVATION') or hasPermission(#id, 'PRIVILEGE', 'DELETE') or hasPermission(#id, 'PRIVILEGE', 'UPDATE')")
    public ResponseEntity<Privilege> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.privilegeService.detail(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = "/{id}")
    @PreAuthorize("hasPermission(#id, 'PRIVILEGE', 'UPDATE')")
    public ResponseEntity<Privilege> update(@PathVariable("id") String id, @RequestBody @Valid PrivilegeForm form) {
        return new ResponseEntity<>(this.privilegeService.update(id, form), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'PRIVILEGE', 'DELETE')")
    public void delete(@PathVariable("id") String id) {
        this.privilegeService.delete(id);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('PRIVILEGE', 'CREATE')")
    public ResponseEntity<Privilege> create(@RequestBody @Valid PrivilegeForm form) {
        return new ResponseEntity<>(this.privilegeService.create(form), HttpStatus.CREATED);
    }
}
