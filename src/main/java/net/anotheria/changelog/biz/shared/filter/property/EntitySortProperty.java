package net.anotheria.changelog.biz.shared.filter.property;

/**
 * Marks class as filter sort property.
 *
 * ynikonchuk
 */
public interface EntitySortProperty extends SortProperty {

    /**
     * Returns entity property name.
     * For example: createdTimestamp
     *
     * @return {@link String} property name
     */
    String getEntityPropertyName();
}
