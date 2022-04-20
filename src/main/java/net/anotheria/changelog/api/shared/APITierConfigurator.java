package net.anotheria.changelog.api.shared;

import net.anotheria.anoplass.api.APIFinder;
import net.anotheria.changelog.api.changelog.ChangeLogAPI;
import net.anotheria.changelog.api.changelog.ChangeLogAPIFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API tier configurator.
 */
public final class APITierConfigurator {

    /**
     * {@link Logger} instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(APITierConfigurator.class);

    /**
     * Default constructor.
     */
    private APITierConfigurator() {
        throw new IllegalAccessError();
    }

    public static void configure() {
        configureCommonParts();
    }

    /**
     * Configure API tier.
     */
    private static void configureCommonParts() {
        LOGGER.debug("configure() Configuring API tier: STARTED");

        APIFinder.addAPIFactory(ChangeLogAPI.class, new ChangeLogAPIFactory());

        LOGGER.debug("configure() Configuring API tier: FINISHED");
    }


}
