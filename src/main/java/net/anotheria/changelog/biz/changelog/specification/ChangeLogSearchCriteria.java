package net.anotheria.changelog.biz.changelog.specification;

import net.anotheria.changelog.biz.shared.filter.criteria.SearchCriteria;

public class ChangeLogSearchCriteria extends SearchCriteria<ChangeLogSortProperty, ChangeLogFilterProperty> {

    public ChangeLogSearchCriteria() {
    }

    public ChangeLogSearchCriteria(SearchCriteria<ChangeLogSortProperty, ChangeLogFilterProperty> criteria) {
        setSearchTerm(criteria.getSearchTerm());
        setPaging(criteria.getPaging());
        setTimeRange(criteria.getTimeRange());
        setSort(criteria.getSort());
        setFilters(criteria.getFilters());
        setGroupFilters(criteria.getGroupFilters());
    }

    @Override
    public String toString() {
        return "ChangeLogSearchCriteria{" +
                "searchTerm='" + getSearchTerm() + '\'' +
                ", paging=" + getPaging() +
                ", sort=" + getSort() +
                ", timeRange=" + getTimeRange() +
                ", filters=" + getFilters() +
                ", groupFilters=" + getGroupFilters() +
                '}';
    }

}
