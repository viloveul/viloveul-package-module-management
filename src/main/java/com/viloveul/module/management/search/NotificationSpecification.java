package com.viloveul.module.management.search;

import com.viloveul.module.management.data.entity.Notification;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class NotificationSpecification extends SearchSpecification<Notification> {

    @SearchTarget(
        path = "sender.id",
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected String sender;

    @SearchTarget(
        path = "reader.id",
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected String reader;

    @SearchTarget(
        path = {"operation"},
        condition = SearchTarget.Condition.EQUAL
    )
    protected String operation;

    @SearchTarget(
        path = {"resource"},
        condition = SearchTarget.Condition.EQUAL
    )
    protected String resource;

    @SearchTarget(
        path = {"subject"},
        condition = SearchTarget.Condition.EQUAL
    )
    protected String subject;

    @SearchTarget(
        path = {"body"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String body;

    @SearchTarget(
        path = {"object"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String object;

    @SearchTarget(
        path = {"status"},
        condition = SearchTarget.Condition.EQUAL
    )
    protected Boolean status;

    @SearchTarget(
        path = {"operation","resource","subject","body","object"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;
}
