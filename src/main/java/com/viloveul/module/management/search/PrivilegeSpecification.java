package com.viloveul.module.management.search;

import com.viloveul.context.type.AuthorityType;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PrivilegeSpecification extends SearchSpecification<Privilege> {

    protected String group;

    @SearchTarget(
        path = "name",
        condition = SearchTarget.Condition.LIKE
    )
    protected String name;

    @SearchTarget(
        path = "identity",
        condition = SearchTarget.Condition.EQUAL
    )
    protected String identity;

    @SearchTarget(
        path = "type",
        condition = SearchTarget.Condition.EQUAL
    )
    protected AuthorityType type;

    @SearchTarget(
        path = {"name", "identity", "type"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;

}
