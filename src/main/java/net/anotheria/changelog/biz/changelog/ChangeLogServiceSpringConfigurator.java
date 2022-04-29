package net.anotheria.changelog.biz.changelog;

import net.anotheria.anoprise.metafactory.Extension;
import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.portalkit.services.common.spring.SpringHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Configurator that allows distributeme to initialize the service.
 *
 */
public class ChangeLogServiceSpringConfigurator {

    public static void configure() {
        SpringHolder.register(ChangeLogService.class, new AnnotationConfigApplicationContext(ChangeLogServiceConfiguration.class));
        MetaFactory.addFactoryClass(ChangeLogService.class, Extension.LOCAL, ChangeLogServiceFactory.class);
    }
}
