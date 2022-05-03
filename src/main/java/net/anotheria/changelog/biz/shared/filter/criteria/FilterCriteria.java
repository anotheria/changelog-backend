package net.anotheria.changelog.biz.shared.filter.criteria;

import net.anotheria.changelog.biz.shared.filter.property.FilterProperty;

import java.io.Serializable;

/**
 *
 */
public class FilterCriteria<S extends FilterProperty> implements Serializable {

    private static final long serialVersionUID = -3048828775078902525L;

    private String value;
    private S field;


    public FilterCriteria() {
    }

    public FilterCriteria(String value, S field) {
        this.value = value;
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public S getField() {
        return field;
    }

    public void setField(S field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "FilterCriteria{" +
                "value='" + value + '\'' +
                ", field=" + field +
                '}';
    }
}
