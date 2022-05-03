package net.anotheria.changelog.biz.util.persistence;

import net.anotheria.changelog.biz.shared.filter.Order;
import net.anotheria.changelog.biz.shared.filter.criteria.SearchCriteria;
import net.anotheria.changelog.biz.shared.filter.criteria.SortCriteria;
import net.anotheria.changelog.biz.shared.filter.property.EntitySortProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Util that helps with {@link PageRequest}.
 *
 *
 */
public class PageRequestHelper {

    public static PageRequest getPageRequest(SearchCriteria criteria) {
        return getPageRequest(criteria.getPaging().getPage(), criteria.getPaging().getItemsOnPage(), criteria.getSort());
    }

    public static PageRequest getPageRequest(int page, int size, List<SortCriteria<EntitySortProperty>> sort) {
        List<Sort.Order> sortOrders = sort.stream().map(PageRequestHelper::mapSort).collect(Collectors.toList());
        return new PageRequest(page, size, new Sort(sortOrders));
    }

    private static Sort.Order mapSort(SortCriteria<EntitySortProperty> sortable) {
        Sort.Direction direction = sortable.getOrder() == Order.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        return new Sort.Order(direction, sortable.getField().getEntityPropertyName()).ignoreCase();
    }
}
