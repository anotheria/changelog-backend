package net.anotheria.changelog.listener;

import net.anotheria.anoplass.api.APIFinder;
import net.anotheria.changelog.api.ChangeLogAPI;
import net.anotheria.changelog.api.ChangeLogAPIFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 14:53
 */
public class StartListener implements ServletContextListener {

	/**
	 * Logger.
	 */
	private static Logger log = LoggerFactory.getLogger(StartListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("Changelog API starting");

		APIFinder.addAPIFactory(ChangeLogAPI.class, new ChangeLogAPIFactory());
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}
}
