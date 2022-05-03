package net.anotheria.changelog.biz.changelog;

import net.anotheria.anoprise.metafactory.Service;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogBO;
import net.anotheria.changelog.biz.changelog.exception.ChangeLogServiceException;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogSearchCriteria;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogSortProperty;
import net.anotheria.changelog.biz.shared.filter.SearchResult;
import org.distributeme.annotation.DistributeMe;
import org.distributeme.annotation.FailBy;
import org.distributeme.core.failing.RetryCallOnce;

import java.util.List;

@DistributeMe(initcode = {
        "net.anotheria.changelog.biz.changelog.ChangeLogServiceSpringConfigurator.configure();",
})
@FailBy(strategyClass = RetryCallOnce.class)
public interface ChangeLogService extends Service {
    int save(ChangeLogBO changeLogBO) throws ChangeLogServiceException;

    ChangeLogBO get(int id) throws ChangeLogServiceException;

    void delete(int id) throws ChangeLogServiceException;

    SearchResult<ChangeLogBO, ChangeLogSortProperty> list(ChangeLogSearchCriteria criteria) throws ChangeLogServiceException;

    List<String> getTags() throws ChangeLogServiceException;
}
