package net.anotheria.changelog.listener;

import net.anotheria.anoplass.api.APIFinder;
import net.anotheria.changelog.api.changelog.ChangeLogAPI;
import net.anotheria.changelog.api.changelog.ChangeLogAPIFactory;
import net.anotheria.changelog.api.shared.APITierConfigurator;
import net.anotheria.changelog.biz.shared.BusinessTierConfigurator;
import org.configureme.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application context initializer.
 *
 * @author lrosenberg
 * @since 13.04.22 14:53
 */
public class ContextInitializer implements ServletContextListener {

	/**
	 * Application name.
	 */
	public static final String APPLICATION_NAME = "<[ CHANGELOG ]>";

	/**
	 * Logger.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(ContextInitializer.class);

	/**
	 * Context initialization actions.
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOGGER.info("--- " + APPLICATION_NAME + " --- APPLICATION INITIALIZATION: STARTED --- ");

		configureBusinessTier();
		configureAPITier();

		LOGGER.info("--- " + APPLICATION_NAME + " --- APPLICATION INITIALIZATION: FINISHED --- ");
		LOGGER.info("--- " + APPLICATION_NAME + " --- CURRENT CONFIGURATION ENVIRONMENT: " + ConfigurationManager.INSTANCE.getDefaultEnvironment().expandedStringForm() + " --- ");
	}

	/**
	 * Context tear down actions.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		LOGGER.info("--- " + APPLICATION_NAME + " --- APPLICATION DESTROYED --- ");
	}

	/**
	 * Configuring Business Tier.
	 */
	private void configureBusinessTier() {
		// custom project services
		BusinessTierConfigurator.configure();
	}

	/**
	 * Configuring API Tier.
	 */
	private void configureAPITier() {
		APITierConfigurator.configure();
	}
}
