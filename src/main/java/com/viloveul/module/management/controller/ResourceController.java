package com.viloveul.module.management.controller;

import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Resource;
import com.viloveul.module.management.pojo.ResourceForm;
import com.viloveul.module.management.search.ResourceSpecification;
import com.viloveul.module.management.service.ResourceService;
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
@RequestMapping(path = "/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('RESOURCE', 'SEARCH')")
    public PageableResult<Resource> search(Pageable pageable, ResourceSpecification filter) {
        return new PageableResult<>(this.resourceService.search(filter, pageable));
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'RESOURCE', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.resourceService.activation(id, status);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'RESOURCE', 'DETAIL')")
    public ResponseEntity<Resource> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.resourceService.detail(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = "/{id}")
    @PreAuthorize("hasPermission(#id, 'RESOURCE', 'UPDATE')")
    public ResponseEntity<Resource> update(@PathVariable("id") String id, @RequestBody @Valid ResourceForm form) {
        return new ResponseEntity<>(this.resourceService.update(id, form), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('RESOURCE', 'CREATE')")
    public ResponseEntity<Resource> create(@RequestBody @Valid ResourceForm form) {
        return new ResponseEntity<>(this.resourceService.create(form), HttpStatus.CREATED);
    }
}
