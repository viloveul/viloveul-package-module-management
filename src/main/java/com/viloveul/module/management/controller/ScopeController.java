package com.viloveul.module.management.controller;

import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Scope;
import com.viloveul.module.management.pojo.ScopeForm;
import com.viloveul.module.management.search.ScopeSpecification;
import com.viloveul.module.management.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${viloveul.controller.scope:/scope}")
public class ScopeController {

    @Autowired
    private ScopeService scopeService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('SCOPE', 'SEARCH')")
    public PageableResult<Scope> search(Pageable pageable, ScopeSpecification filter) {
        return new PageableResult<>(this.scopeService.search(filter, pageable));
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'SCOPE', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.scopeService.activation(id, status);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'SCOPE', 'DETAIL')")
    public ResponseEntity<Scope> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.scopeService.detail(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('SCOPE', 'CREATE')")
    public ResponseEntity<Scope> create(@RequestBody @Valid ScopeForm form) {
        return new ResponseEntity<>(this.scopeService.create(form), HttpStatus.CREATED);
    }
}
