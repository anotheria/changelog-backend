package net.anotheria.changelog.common.config;

import net.anotheria.changelog.common.shared.SubProject;
import net.anotheria.util.StringUtils;
import org.configureme.ConfigurationManager;
import org.configureme.annotations.AfterConfiguration;
import org.configureme.annotations.AfterReConfiguration;
import org.configureme.annotations.Configure;
import org.configureme.annotations.ConfigureMe;
import org.configureme.annotations.DontConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class should contain all business parameters that can be configured in the Application.
 *
 * @author lrosenberg
 * @since 22.08.14 00:11
 */
@ConfigureMe(name = "application", allfields = true)
public class ApplicationConfig {
    /**
     * {@link Logger} instance.
     */
    private static Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    /**
     * Async executor thread sleep time (seconds).
     */
    private long executorThreadsSleepTime = 10;

    /**
     * Async executor thread count.
     */
    private int executorThreadsCount = 10;

    /**
     * Adds to rest header Access-Control-Allow-Origin, with this value.
     * Allows cross domain request. for web app for example.
     */
    @Configure
    private String[] restAccessControlAllowOrigin;

    @Configure
    private String appName;

    @DontConfigure
    private SubProject subProject;

    public static ApplicationConfig get() {
        return ApplicationConfigHolder.instance;
    }

    public int getExecutorThreadsCount() {
        return executorThreadsCount;
    }

    public void setExecutorThreadsCount(int executorThreadsCount) {
        this.executorThreadsCount = executorThreadsCount;
    }

    public long getExecutorThreadsSleepTime() {
        return executorThreadsSleepTime;
    }

    public void setExecutorThreadsSleepTime(long executorThreadsSleepTime) {
        this.executorThreadsSleepTime = executorThreadsSleepTime;
    }

    public String[] getRestAccessControlAllowOrigin() {
        return restAccessControlAllowOrigin;
    }

    public void setRestAccessControlAllowOrigin(String[] restAccessControlAllowOrigin) {
        this.restAccessControlAllowOrigin = restAccessControlAllowOrigin;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    static class ApplicationConfigHolder {
        private static ApplicationConfig instance;

        static {
            instance = new ApplicationConfig();
            try {
                ConfigurationManager.INSTANCE.configure(instance);
            } catch (IllegalArgumentException e) {
                log.warn("No application.json config file found. Continuing with defaults.");
            }
            log.info("Application configured: " + instance);
        }
    }

    @AfterConfiguration
    @AfterReConfiguration
    public void setSubProject() {
        if (StringUtils.isEmpty(appName)) {
            return;
        }

        subProject = SubProject.valueOf(appName);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApplicationConfig{");
        sb.append("executorThreadsSleepTime=").append(executorThreadsSleepTime);
        sb.append(", executorThreadsCount=").append(executorThreadsCount);
        sb.append('}');
        return sb.toString();
    }
}

