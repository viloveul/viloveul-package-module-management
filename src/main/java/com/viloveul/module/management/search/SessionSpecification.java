package com.viloveul.module.management.search;

import com.viloveul.context.type.SignerType;
import com.viloveul.module.management.data.entity.Session;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SessionSpecification extends SearchSpecification<Session> {

    @SearchTarget(
        path = "user.username",
        condition = SearchTarget.Condition.EQUAL
    )
    protected String username;

    @SearchTarget(
        path = "user.type",
        condition = SearchTarget.Condition.EQUAL
    )
    protected SignerType type;

    @SearchTarget(
        path = "user.group.identity",
        condition = SearchTarget.Condition.EQUAL
    )
    protected String group;

    @SearchTarget(
        path = {"user.username", "user.fullname", "user.email", "user.group.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;
}
