package com.viloveul.module.management.search;

import com.viloveul.context.type.SignerType;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSpecification extends SearchSpecification<User> {

    @SearchTarget(
        path = "username",
        condition = SearchTarget.Condition.EQUAL
    )
    protected String username;

    @SearchTarget(
        path = "type",
        condition = SearchTarget.Condition.EQUAL
    )
    protected SignerType type;

    @SearchTarget(
        path = "group.identity",
        condition = SearchTarget.Condition.EQUAL
    )
    protected String group;

    @SearchTarget(
        path = {"username", "fullname", "email", "group.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;
}
