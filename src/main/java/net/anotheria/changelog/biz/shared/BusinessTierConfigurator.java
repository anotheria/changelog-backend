package net.anotheria.changelog.biz.shared;

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


        LOGGER.info("configure() Configuring Business tier: FINISHED");
    }

}
