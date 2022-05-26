package com.viloveul.module.management.controller;

import com.viloveul.context.filter.SearchProperties;
import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Assignment;
import com.viloveul.module.management.pojo.AssignmentForm;
import com.viloveul.module.management.search.AssignmentSpecification;
import com.viloveul.module.management.service.AssignmentService;
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

@RestController
@RequestMapping(path = "/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('ASSIGNMENT', 'SEARCH')")
    @SearchProperties(
        resource = "ASSIGNMENT",
        operation = "SEARCH",
        allows = @SearchProperties.Allow(field = "delegator.id", option = SearchProperties.Option.USER)
    )
    public PageableResult<Assignment> search(Pageable pageable, AssignmentSpecification filter) {
        return new PageableResult<>(
            this.assignmentService.search(filter, pageable)
        );
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'ASSIGNMENT', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.assignmentService.activation(id, status);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'ASSIGNMENT', 'DELETE')")
    public void delete(@PathVariable("id") String id) {
        this.assignmentService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'ASSIGNMENT', 'DETAIL')")
    public ResponseEntity<Assignment> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.assignmentService.detail(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('ASSIGNMENT', 'CREATE')")
    public ResponseEntity<Assignment> create(@RequestBody @Valid AssignmentForm form) {
        return new ResponseEntity<>(this.assignmentService.create(form), HttpStatus.CREATED);
    }
}
