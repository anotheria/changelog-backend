package net.anotheria.changelog.biz.changelog.specification;

import net.anotheria.changelog.biz.shared.filter.property.EntitySortProperty;

/**
 * Stream sort properties enumeration.
 *
 */
public enum ChangeLogSortProperty implements EntitySortProperty {
    AUTHOR("author"),

    TIMESTAMP("timestamp"),

    TYPE("type"),

    ;

    private String entityPropertyName;

    ChangeLogSortProperty(String entityPropertyName) {
        this.entityPropertyName = entityPropertyName;
    }

    @Override
    public String getEntityPropertyName() {
        return entityPropertyName;
    }
}
