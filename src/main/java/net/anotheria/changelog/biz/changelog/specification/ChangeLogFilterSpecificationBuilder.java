package net.anotheria.changelog.biz.changelog.specification;

import net.anotheria.changelog.biz.changelog.bean.ChangeLogType;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogEntity;
import net.anotheria.changelog.biz.shared.filter.util.BaseFilterSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

public class ChangeLogFilterSpecificationBuilder extends BaseFilterSpecificationBuilder<ChangeLogFilterProperty, ChangeLogEntity> {

    public Specification<ChangeLogEntity> getSpecification(ChangeLogFilterProperty filterProperty, String value) {
        switch (filterProperty) {
            case TYPE:
                ChangeLogType type = ChangeLogType.valueOf(value);
                return ChangeLogSpecifications.accessType(type);
            default:
                return null;
        }
    }

}
