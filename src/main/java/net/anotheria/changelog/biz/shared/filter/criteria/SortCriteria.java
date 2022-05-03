package net.anotheria.changelog.biz.shared.filter.criteria;

import net.anotheria.changelog.biz.shared.filter.Order;
import net.anotheria.changelog.biz.shared.filter.property.SortProperty;

import java.io.Serializable;

/**
 *
 */
public class SortCriteria<S extends SortProperty> implements Serializable {

    private static final long serialVersionUID = 4039481480889913965L;

    private Order order;
    private S field;


    public SortCriteria() {
    }

    public SortCriteria(Order order, S field) {
        this.order = order;
        this.field = field;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public S getField() {
        return field;
    }

    public void setField(S field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "SortCriteria{" +
                "order=" + order +
                ", field=" + field +
                '}';
    }
}
