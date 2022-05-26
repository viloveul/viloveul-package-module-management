package com.viloveul.module.management.search;

import com.viloveul.module.management.data.entity.Resource;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResourceSpecification extends SearchSpecification<Resource> {

    @SearchTarget(
        path = "identity",
        condition = SearchTarget.Condition.EQUAL
    )
    protected String identity;

    @SearchTarget(
        path = "name",
        condition = SearchTarget.Condition.LIKE
    )
    protected String name;

    @SearchTarget(
        path = {"identity", "name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;
}
