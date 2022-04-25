package net.anotheria.changelog.biz.shared;

import net.anotheria.anoprise.metafactory.Extension;
import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.changelog.biz.changelog.ChangeLogService;
import net.anotheria.changelog.biz.changelog.ChangeLogServiceSpringConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Business tier configurator.
 */
public final class BusinessTierConfigurator {

    /**
     * {@link Logger} instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessTierConfigurator.class);

    /**
     * Default constructor.
     */
    private BusinessTierConfigurator() {
        throw new IllegalAccessError();
    }

    /**
     * Configure Business tier.
     */
    public static void configure() {
        LOGGER.info("configure() Configuring Business tier: STARTED");

        configureLocalFactories();
        configureLocalAliases();

        LOGGER.info("configure() Configuring Business tier: FINISHED");
    }

    private static void configureRemoteFactories() {

    }

    private static void configureLocalFactories() {
        ChangeLogServiceSpringConfigurator.configure();
    }

    private static void configureRemoteAliases() {
        configureAliases(Extension.REMOTE);
    }

    private static void configureLocalAliases() {
        configureAliases(Extension.LOCAL);
    }

    private static void configureAliases(Extension extension) {
        MetaFactory.addAlias(ChangeLogService.class, extension);
    }
}
