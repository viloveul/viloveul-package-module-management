package com.viloveul.module.management.controller;

import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Operation;
import com.viloveul.module.management.pojo.OperationForm;
import com.viloveul.module.management.search.OperationSpecification;
import com.viloveul.module.management.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${viloveul.controller.operation:/operation}")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission('OPERATION', 'SEARCH') or hasPermission('OPERATION', 'ACTIVATION') or hasPermission('OPERATION', 'CREATE') or hasPermission('OPERATION', 'DETAIL') or hasPermission('OPERATION', 'DELETE') or hasPermission('OPERATION', 'UPDATE')")
    public PageableResult<Operation> search(Pageable pageable, OperationSpecification filter) {
        return new PageableResult<>(this.operationService.search(filter, pageable));
    }

    @PostMapping(path = "/{id}/activation/{status}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'OPERATION', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.operationService.activation(id, status);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'OPERATION', 'DETAIL') or hasPermission(#id, 'OPERATION', 'ACTIVATION') or hasPermission(#id, 'OPERATION', 'DELETE') or hasPermission(#id, 'OPERATION', 'UPDATE')")
    public ResponseEntity<Operation> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.operationService.detail(id), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}")
    @Transactional
    @PreAuthorize("hasPermission(#id, 'OPERATION', 'UPDATE')")
    public ResponseEntity<Operation> update(@PathVariable("id") String id, @RequestBody @Valid OperationForm form) {
        return new ResponseEntity<>(this.operationService.update(id, form), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('OPERATION', 'CREATE')")
    public ResponseEntity<Operation> create(@RequestBody @Valid OperationForm form) {
        return new ResponseEntity<>(this.operationService.create(form), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'OPERATION', 'DELETE')")
    public void delete(@PathVariable("id") String id) {
        this.operationService.delete(id);
    }
}
