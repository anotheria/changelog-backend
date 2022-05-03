package net.anotheria.changelog.biz.shared.filter.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SpecificationsFactory {

    public static <E, V> Specification<E> equals(String fieldName, V value) {
        return (root, query, cb) -> {
            Path<V> fieldPath = getFieldPath(root, fieldName);
            return cb.equal(fieldPath, value);
        };
    }

    public static <E, V> Specification<E> notEquals(String fieldName, V value) {
        return (root, query, cb) -> {
            Path<V> fieldPath = getFieldPath(root, fieldName);
            return cb.notEqual(fieldPath, value);
        };
    }

    public static <E> Specification<E> like(String fieldName, String value) {
        return (root, query, cb) -> like(cb, getFieldPath(root, fieldName), value);
    }

    public static Predicate like(CriteriaBuilder cb, Expression<String> expression, String value) {
        return cb.like(cb.lower(expression), "%" + value.toLowerCase() + "%");
    }

    public static <E> Specification<E> searchTerm(final String searchTerm, String... fieldNames) {
        Specifications<E> specifications = null;
        if (StringUtils.isEmpty(searchTerm)) {
            return null;
        }

        for (String fieldName : fieldNames) {
            if (specifications == null) {
                specifications = Specifications.where(SpecificationsFactory.like(fieldName, searchTerm));
            } else {
                specifications = specifications.or(SpecificationsFactory.like(fieldName, searchTerm));
            }
        }
        return specifications;
    }

    public static <E> Specification<E> in(String fieldName, List<String> values) {
        String[] valuesArr = new String[values.size()];
        valuesArr = values.toArray(valuesArr);
        return in(fieldName, valuesArr);
    }

    public static <E> Specification<E> in(String fieldName, String... values) {
        if (values == null || values.length == 0) {
            return null;
        }

        return (root, query, cb) -> {
            Path<String> fieldPath = getFieldPath(root, fieldName);
            CriteriaBuilder.In<String> in = cb.in(fieldPath);
            for (String value : values) {
                in = in.value(value);
            }
            return in;
        };
    }

    public static <E, V extends Comparable<? super V>> Specification<E> between(String fieldName, V start, V end) {
        return (root, query, cb) -> {
            Path<V> fieldPath = getFieldPath(root, fieldName);
            if (start != null && end != null)
                return cb.between(fieldPath, start, end);
            if (start != null) {
                return cb.greaterThan(fieldPath, start);
            }
            if (end != null) {
                return cb.lessThan(fieldPath, end);
            }
            return null;
        };
    }

    public static <E, V extends Comparable<? super V>> Specification<E> greaterThan(String fieldName, V value) {
        return (root, query, cb) -> {
            Path<V> fieldPath = getFieldPath(root, fieldName);
            return cb.greaterThan(fieldPath, value);
        };
    }

    public static <E, V extends Comparable<? super V>> Specification<E> lessThan(String fieldName, V value) {
        return (root, query, cb) -> {
            Path<V> fieldPath = getFieldPath(root, fieldName);
            return cb.lessThan(fieldPath, value);
        };
    }

    public static <E> Specification<E> isTrue(String fieldName) {
        return (root, query, cb) -> {
            Path<Boolean> fieldPath = getFieldPath(root, fieldName);
            return cb.isTrue(fieldPath);
        };
    }

    public static <E> Specification<E> isFalse(String fieldName) {
        return (root, query, cb) -> {
            Path<Boolean> fieldPath = getFieldPath(root, fieldName);
            return cb.isFalse(fieldPath);
        };
    }

    private static <E, V> Path<V> getFieldPath(Root<E> root, String fieldPath) {
        Path<V> result = null;
        if (fieldPath.contains(".")) {
            String[] embeddedFieldNames = fieldPath.split("\\.");
            for (String fieldName: embeddedFieldNames) {
                result = result == null ? root.get(fieldName) : result.get(fieldName);
            }
        } else {
            result = root.get(fieldPath);
        }
        return result;
    }
}
