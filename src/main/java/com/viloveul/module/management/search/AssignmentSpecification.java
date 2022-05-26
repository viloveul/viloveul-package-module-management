package com.viloveul.module.management.search;

import com.viloveul.module.management.data.entity.Assignment;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AssignmentSpecification extends SearchSpecification<Assignment> {

    @SearchTarget(
        path = {"privilege.identity","privilege.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String privilege;

    @SearchTarget(
        path = {"delegator.name","delegator.fullname"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String delegator;

    @SearchTarget(
        path = {"user.username"},
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected String user;

    @SearchTarget(
        path = {"group.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String group;

    @SearchTarget(
        path = {"privilege.name","resource.name","operation.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;

}
