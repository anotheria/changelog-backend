package net.anotheria.changelog.biz.shared.filter.criteria;

import net.anotheria.changelog.biz.shared.filter.property.FilterProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterListCriteria<S extends FilterProperty> implements Serializable {

    private static final long serialVersionUID = 3806826423686753842L;

    private List<String> value;
    private S field;

    public FilterListCriteria() {
    }

    public FilterListCriteria(S field) {
        this(new ArrayList<>(), field);
    }

    public FilterListCriteria(List<String> value, S field) {
        this.value = value;
        this.field = field;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public S getField() {
        return field;
    }

    public void setField(S field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilterListCriteria<?> that = (FilterListCriteria<?>) o;
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return "FilterListCriteria{" +
                "value=" + value +
                ", field=" + field +
                '}';
    }
}
