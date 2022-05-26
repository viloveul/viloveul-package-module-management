package com.viloveul.module.management.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viloveul.context.filter.SearchProperties;
import com.viloveul.module.management.service.ResourceService;
import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Session;
import com.viloveul.module.management.search.SessionSpecification;
import com.viloveul.module.management.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "${viloveul.controller.session:/session}")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission('SESSION', 'SEARCH')")
    @SearchProperties(
        resource = "SESSION",
        operation = "SEARCH",
        allows = @SearchProperties.Allow(field = "user.id", option = SearchProperties.Option.USER)
    )
    public PageableResult<Session> search(
        Pageable pageable,
        SessionSpecification filter
    ) {
        return new PageableResult<Session>(this.sessionService.search(filter, pageable)) {
            @Override
            @JsonIgnoreProperties({"user.privileges", "user.group"})
            public Collection<Session> getItems() {
                return this.items;
            }
        };
    }
}
