package com.viloveul.module.management.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viloveul.context.event.GenericTransform;
import com.viloveul.module.management.pojo.NotificationForm;
import com.viloveul.module.management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NotificationService notificationService;

    @EventListener(condition = "#event.target == 'NOTIFICATION'")
    public void send(GenericTransform event) {
        this.notificationService.send(
            this.objectMapper.convertValue(
                event.getPayload(),
                NotificationForm.class
            )
        );
    }

}
