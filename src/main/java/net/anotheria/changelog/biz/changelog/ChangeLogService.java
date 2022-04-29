package net.anotheria.changelog.biz.changelog;

import net.anotheria.anoprise.metafactory.Service;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogBO;
import net.anotheria.changelog.biz.changelog.exception.ChangeLogServiceException;
import org.distributeme.annotation.DistributeMe;
import org.distributeme.annotation.FailBy;
import org.distributeme.core.failing.RetryCallOnce;

@DistributeMe(initcode = {
        "net.anotheria.changelog.biz.changelog.ChangeLogServiceSpringConfigurator.configure();",
})
@FailBy(strategyClass = RetryCallOnce.class)
public interface ChangeLogService extends Service {
    int save(ChangeLogBO changeLogBO) throws ChangeLogServiceException;

    ChangeLogBO get(int id) throws ChangeLogServiceException;

    void delete(int id) throws ChangeLogServiceException;

}
