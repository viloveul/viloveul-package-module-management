package com.viloveul.module.management.search;

import com.viloveul.module.management.data.entity.Group;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class GroupSpecification extends SearchSpecification<Group> {

    @SearchTarget(
        path = "name",
        condition = SearchTarget.Condition.LIKE
    )
    protected String name;

    @SearchTarget(
        path = "identity",
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected String identity;

    @SearchTarget(
        path = {"name", "identity"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;

}
