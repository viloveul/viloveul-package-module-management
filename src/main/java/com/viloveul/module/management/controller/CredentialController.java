package com.viloveul.module.management.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viloveul.context.filter.SearchProperties;
import com.viloveul.module.management.service.ResourceService;
import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Credential;
import com.viloveul.module.management.pojo.CredentialForm;
import com.viloveul.module.management.search.CredentialSpecification;
import com.viloveul.module.management.service.CredentialService;
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
@RequestMapping(path = "/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission('CREDENTIAL', 'SEARCH')")
    @SearchProperties(
        resource = "CREDENTIAL",
        operation = "SEARCH",
        allows = {
            @SearchProperties.Allow(field = "user.id", option = SearchProperties.Option.USER),
            @SearchProperties.Allow(field = "user.group.id", option = SearchProperties.Option.GROUP)
        }
    )
    public PageableResult<Credential> search(
        Pageable pageable,
        CredentialSpecification filter
    ) {
        return new PageableResult<Credential>(this.credentialService.search(filter, pageable)) {
            @Override
            @JsonIgnoreProperties({"user.privileges", "user.group"})
            public Collection<Credential> getItems() {
                return this.items;
            }
        };
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'CREDENTIAL', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.credentialService.activation(id, status);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'CREDENTIAL', 'DELETE')")
    public void delete(@PathVariable("id") String id) {
        this.credentialService.delete(id);
    }

    @Transactional(readOnly = true)
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasPermission(#id, 'CREDENTIAL', 'DETAIL')")
    public ResponseEntity<Credential> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.credentialService.detail(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('CREDENTIAL', 'CREATE')")
    public ResponseEntity<Credential> create(@RequestBody @Valid CredentialForm form) {
        return new ResponseEntity<>(this.credentialService.create(form), HttpStatus.CREATED);
    }
}
