package net.anotheria.changelog.biz.shared.filter.property;

/**
 *
 */
public interface EntityFilterProperty extends FilterProperty {

    /**
     * Returns entity property name.
     * For example: createdTimestamp
     *
     * @return {@link String} property name
     */
    String getEntityPropertyName();
}
