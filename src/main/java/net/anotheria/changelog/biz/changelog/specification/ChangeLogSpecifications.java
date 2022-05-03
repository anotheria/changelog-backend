package net.anotheria.changelog.biz.changelog.specification;

import net.anotheria.changelog.biz.changelog.bean.ChangeLogType;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogEntity;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogTagEntity;
import net.anotheria.changelog.biz.shared.filter.util.SpecificationsFactory;
import net.anotheria.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

public final class ChangeLogSpecifications {

    public static Specification<ChangeLogEntity> searchTerm(String searchTerm) {
        if (StringUtils.isEmpty(searchTerm)) {
            return null;
        }

        String[] searchFields = {"author", "message", "reason"};
        return SpecificationsFactory.searchTerm(searchTerm, searchFields);
    }

    public static Specification<ChangeLogEntity> accessType(ChangeLogType type) {
        return (root, query, cb) -> cb.equal(root.get("type"), type);
    }

    public static Specification<ChangeLogEntity> author(String value) {
        return (root, query, cb) -> cb.equal(root.get("author"), value);
    }

    public static Specification<ChangeLogEntity> tag(String tag) {
        return (root, query, cb) -> {
            // result of subquery are tag changelogIds
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<ChangeLogTagEntity> rootTag = subquery.from(ChangeLogTagEntity.class);

            // subquery select from rootTag where tag == given tag
            Predicate tagPredicate = cb.equal(rootTag.get("id").get("tag"), tag);
            subquery.select(rootTag.get("id").get("changelogId")).where(tagPredicate);

            // find ChangeLog with ids in subquery result (rootTag changelogIds)
            return cb.in(root.get("id")).value(subquery);
        };
    }

    private static Specification<ChangeLogEntity> searchByAuthor(String searchTerm) {
        return SpecificationsFactory.searchTerm(searchTerm, "author");
    }

    private static Specification<ChangeLogEntity> searchByMessage(String searchTerm) {
        return SpecificationsFactory.searchTerm(searchTerm, "message");
    }

    private static Specification<ChangeLogEntity> searchByReason(String searchTerm) {
        return SpecificationsFactory.searchTerm(searchTerm, "reason");
    }

}
