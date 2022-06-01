package com.viloveul.module.management.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viloveul.context.filter.SearchPropertyAuthorization;
import com.viloveul.context.util.misc.PageableResult;
import com.viloveul.module.management.data.entity.Notification;
import com.viloveul.module.management.search.NotificationSpecification;
import com.viloveul.module.management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "${viloveul.controller.notification:/notification}")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @SearchPropertyAuthorization(
        resource = "NOTIFICATION",
        operation = "SEARCH"
    )
    @PreAuthorize("hasPermission('NOTIFICATION', 'SEARCH') or hasPermission('NOTIFICATION', 'DETAIL') or hasPermission('NOTIFICATION', 'READ')")
    public PageableResult<Notification> search(
        Pageable pageable,
        NotificationSpecification filter
    ) {
        return new PageableResult<Notification>(this.notificationService.search(filter, pageable)) {
            @Override
            @JsonIgnoreProperties({"sender.privileges", "sender.group"})
            public Collection<Notification> getItems() {
                return this.items;
            }
        };
    }

    @Transactional
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(#id, 'NOTIFICATION', 'DETAIL') or hasPermission(#id, 'NOTIFICATION', 'READ')")
    public ResponseEntity<Notification> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.notificationService.read(id), HttpStatus.OK);
    }

}
