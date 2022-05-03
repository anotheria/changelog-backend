package net.anotheria.changelog.biz.shared.filter.util;

import net.anotheria.changelog.biz.shared.filter.criteria.FilterCriteria;
import net.anotheria.changelog.biz.shared.filter.criteria.FilterListCriteria;
import net.anotheria.changelog.biz.shared.filter.criteria.SearchCriteria;
import net.anotheria.changelog.biz.shared.filter.property.EntitySortProperty;
import net.anotheria.changelog.biz.shared.filter.property.FilterProperty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFilterSpecificationBuilder<F extends FilterProperty, E> {

    private List<FilterCriteria<F>> criteriaFilters = new ArrayList<>();
    private List<FilterListCriteria<F>> criteriaGroupFilters = new ArrayList<>();

    public BaseFilterSpecificationBuilder() {
    }

    public BaseFilterSpecificationBuilder<F, E> withCriteriaFilters(List<FilterCriteria<F>> filters) {
        if (filters != null) {
            this.criteriaFilters = filters;
        }
        return this;
    }

    public BaseFilterSpecificationBuilder<F, E> withGroupFilters(List<FilterListCriteria<F>> filters) {
        if (filters != null) {
            this.criteriaGroupFilters = filters;
        }
        return this;
    }

    public Specifications<E> build(SearchCriteria<? extends EntitySortProperty, F> criteria) {
        withCriteriaFilters(criteria.getFilters());
        withGroupFilters(criteria.getGroupFilters());
        return build();
    }

    public Specifications<E> build() {

        Specifications<E> searchSpec = Specifications.where(null);
        for (FilterCriteria<F> filter : criteriaFilters) {
            searchSpec = searchSpec.and(getSpecification(filter.getField(), filter.getValue()));
        }

        for (FilterListCriteria<F> filter : criteriaGroupFilters) {
            searchSpec = searchSpec.and(getSpecification(filter.getField(), filter.getValue()));
        }

        return searchSpec;
    }

    private Specification<E> getSpecification(F filterProperty, List<String> values) {
        Specifications<E> groupSpecification = null;
        for (String value : values) {
            groupSpecification = groupSpecification == null ?
                    Specifications.where(getSpecification(filterProperty, value)) :
                    groupSpecification.or(getSpecification(filterProperty, value));
        }
        return groupSpecification;
    }

    protected abstract Specification<E> getSpecification(F filterProperty, String value);
}
