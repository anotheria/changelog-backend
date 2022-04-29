package net.anotheria.changelog.biz.changelog;

import net.anotheria.changelog.biz.changelog.ChangeLogService;
import net.anotheria.changelog.biz.shared.JpaSpringConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring configuration for {@link ChangeLogService}.
 */
@Import(JpaSpringConfiguration.class)
@EnableJpaRepositories
@ComponentScan("net.anotheria.changelog.biz.changelog")
@Configuration
public class ChangeLogServiceConfiguration extends JpaSpringConfiguration {

    @Override
    protected String getServiceName() {
        return "changelog";
    }

    @Override
    protected String[] getBasePackages() {
        return new String[] { ChangeLogService.class.getPackage().getName() };
    }

    @Override
    protected String[] getEntityPackagesToScan() {
        return getBasePackages();
    }

    @Override
    protected String getDbConfigurationName() {
        return "pk-jdbc-" + getServiceName();
    }
}
