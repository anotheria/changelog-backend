package net.anotheria.changelog.biz.changelog;

import net.anotheria.anoprise.metafactory.ServiceFactory;
import net.anotheria.changelog.biz.changelog.ChangeLogService;
import net.anotheria.portalkit.services.common.spring.SpringHolder;

/**
 * {@link ChangeLogService} factory.
 *
 */
public class ChangeLogServiceFactory implements ServiceFactory<ChangeLogService> {

    @Override
    public ChangeLogService create() {
        return SpringHolder.get(ChangeLogService.class);
    }
}
