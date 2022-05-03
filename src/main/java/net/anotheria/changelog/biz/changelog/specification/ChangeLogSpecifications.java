package net.anotheria.changelog.biz.changelog.specification;

import net.anotheria.changelog.biz.changelog.bean.ChangeLogType;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogEntity;
import net.anotheria.changelog.biz.shared.filter.util.SpecificationsFactory;
import net.anotheria.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public final class ChangeLogSpecifications {

    public static Specification<ChangeLogEntity> searchTerm(String searchTerm) {
        if (StringUtils.isEmpty(searchTerm)) {
            return null;
        }
        return Specifications.where(searchByAuthor(searchTerm))
                .or(searchByMessage(searchTerm))
                .or(searchByReason(searchTerm));
    }

    public static Specification<ChangeLogEntity> accessType(ChangeLogType type) {
        return (root, query, cb) -> cb.equal(root.get("type"), type.name());
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
