package com.viloveul.module.management.search;

import com.viloveul.module.management.data.entity.Scope;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ScopeSpecification extends SearchSpecification<Scope> {

    @SearchTarget(
        path = {"privilege.identity","privilege.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String privilege;

    @SearchTarget(
        path = {"resource.identity","resource.name","resource.description"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String resource;

    @SearchTarget(
        path = {"operation.identity","operation.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String operation;

    @SearchTarget(
        path = {"privilege.name","resource.name","operation.name"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;

}
