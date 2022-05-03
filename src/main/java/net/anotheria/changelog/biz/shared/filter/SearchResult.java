package net.anotheria.changelog.biz.shared.filter;

import net.anotheria.changelog.biz.shared.filter.criteria.SortCriteria;
import net.anotheria.changelog.biz.shared.filter.property.EntitySortProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class SearchResult<T, S extends EntitySortProperty> implements Serializable {

    private static final long serialVersionUID = 1674632611282191288L;

    private List<SortCriteria<S>> sort;
    private PagingResult paging;
    private List<T> items;

    public SearchResult() {
        this(Collections.emptyList(), new PagingResult(new Paging(), 0), Collections.emptyList());
    }

    public SearchResult(List<SortCriteria<S>> sort, PagingResult paging, List<T> items) {
        this.sort = sort;
        this.paging = paging;
        this.items = items;
    }

    public List<SortCriteria<S>> getSort() {
        return sort;
    }

    public void setSort(List<SortCriteria<S>> sort) {
        this.sort = sort;
    }

    public PagingResult getPaging() {
        return paging;
    }

    public void setPaging(PagingResult paging) {
        this.paging = paging;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
