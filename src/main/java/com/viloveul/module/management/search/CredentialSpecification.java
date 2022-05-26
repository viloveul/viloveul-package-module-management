package com.viloveul.module.management.search;

import com.viloveul.context.type.SecureType;
import com.viloveul.module.management.data.entity.Credential;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CredentialSpecification extends SearchSpecification<Credential> {

    @SearchTarget(
        path = "user.id",
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected String id;

    @SearchTarget(
        path = "user.username",
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected String username;

    @SearchTarget(
        path = "type",
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected SecureType type;

    @SearchTarget(
        path = "description",
        condition = SearchTarget.Condition.LIKE
    )
    protected String description;

    @SearchTarget(
        path = {"user.username", "user.fullname", "user.name", "description"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;
}
