package net.anotheria.changelog.biz.shared.filter.criteria;

import net.anotheria.changelog.biz.shared.filter.Paging;
import net.anotheria.changelog.biz.shared.filter.property.EntitySortProperty;
import net.anotheria.changelog.biz.shared.filter.property.FilterProperty;
import net.anotheria.changelog.biz.shared.time.TimeRange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Common search criteria for filtering results.
 *
 */
public class SearchCriteria<S extends EntitySortProperty, F extends FilterProperty> implements Serializable {

    private static final long serialVersionUID = 1619021430480763910L;

    /**
     * Sort/Paging
     */
    private List<SortCriteria<S>> sort;
    private Paging paging;

    /**
     * Search term to criteria
     */
    private String searchTerm;

    /**
     * Time range to search
     */
    private TimeRange timeRange;

    /**
     * Filters by entity's fields
     */
    private List<FilterCriteria<F>> filters;
    private List<FilterListCriteria<F>> groupFilters;

    public SearchCriteria() {
        sort = new ArrayList<>();
        paging = new Paging();
        filters = new ArrayList<>();
        groupFilters = new ArrayList<>();
    }

    public List<SortCriteria<S>> getSort() {
        return sort;
    }

    public void setSort(List<SortCriteria<S>> sort) {
        this.sort = sort;
    }

    public void addSortable(SortCriteria<S> criteria) {
        sort.add(criteria);
    }

    public void addSortable(Collection<SortCriteria<S>> criteriaList) {
        sort.addAll(criteriaList);
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    public List<FilterCriteria<F>> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterCriteria<F>> filters) {
        this.filters = filters;
    }

    public void addFilter(FilterCriteria<F> filter) {
        this.filters.add(filter);
    }

    public List<FilterListCriteria<F>> getGroupFilters() {
        return groupFilters;
    }

    public void setGroupFilters(List<FilterListCriteria<F>> groupFilters) {
        this.groupFilters = groupFilters;
    }

    public void addGroupFilter(FilterListCriteria<F> filter) {
        this.groupFilters.add(filter);
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "sort=" + sort +
                ", paging=" + paging +
                ", searchTerm='" + searchTerm + '\'' +
                ", timeRange=" + timeRange +
                ", filters=" + filters +
                ", groupFilters=" + groupFilters +
                '}';
    }
}
