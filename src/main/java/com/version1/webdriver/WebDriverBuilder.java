package com.version1.webdriver;

import com.codahale.metrics.Timer;
import com.version1.metrics.MetricRegistryHelper;
import com.version1.webdriver.configuration.BrowserType;
import com.version1.webdriver.configuration.TestConfigHelper;
import com.version1.webdriver.configuration.driver.ConfiguredChromeDriver;
import com.version1.webdriver.configuration.driver.ConfiguredEdgeDriver;
import com.version1.webdriver.configuration.driver.ConfiguredFirefoxDriver;
import com.version1.webdriver.configuration.driver.ConfiguredInternetExplorerDriver;
import com.version1.webdriver.configuration.driver.ConfiguredSafariDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * This class uses the Builder Pattern to construct its options.  Calling .build() will
 * result in all of the configuration options being assembled and a valid WebDriver object being
 * supplied to the caller
 */
public class WebDriverBuilder {
    
    private File screenshotDirectory;
    private static final Timer webDriverBuild = MetricRegistryHelper.get().timer(name("WebDriverBuilder.build"));

    /**
     * 
     * @param screenshotDirectory path for the directory storing screenshots
     * @return WebDriverBuilder as using builder pattern
     */
    public WebDriverBuilder setResultsDirectory(File screenshotDirectory) {
        this.screenshotDirectory = screenshotDirectory;
        return this;
    }

    /**
     * The build method pulls all of the configuration options together and uses them to
     * construct an EventFiringWebDriver with the correct configuration for the browser type
     * @return EventFiringWebDriver representing the configured driver
     * @throws IOException if log file for browser driver logs cannot be created
     */
    public EventFiringWebDriver build() throws IOException {
        try(final Timer.Context ignored = webDriverBuild.time()) {
            switch (TestConfigHelper.get().getBrowserType()) {
                case CHROME:
                    return new ConfiguredChromeDriver().getDriver(this.screenshotDirectory);
                case FIREFOX:
                    return new ConfiguredFirefoxDriver().getDriver(this.screenshotDirectory);
                case IE:
                    return new ConfiguredInternetExplorerDriver().getDriver(this.screenshotDirectory);
                case EDGE:
                    return new ConfiguredEdgeDriver().getDriver(this.screenshotDirectory);
                case SAFARI:
                    return new ConfiguredSafariDriver().getDriver(this.screenshotDirectory);
                default:
                    throw new RuntimeException("WebDriverBuilder has no valid target browser set in WebDriverConfig");
            }
        }
    }
    public EventFiringWebDriver build(String browser) throws IOException {
        try(final Timer.Context ignored = webDriverBuild.time()) {
            switch (BrowserType.valueOf(browser.toUpperCase())) {
                case CHROME:
                    return new ConfiguredChromeDriver().getDriver(this.screenshotDirectory);
                case FIREFOX:
                    return new ConfiguredFirefoxDriver().getDriver(this.screenshotDirectory);
                case IE:
                    return new ConfiguredInternetExplorerDriver().getDriver(this.screenshotDirectory);
                case EDGE:
                    return new ConfiguredEdgeDriver().getDriver(this.screenshotDirectory);
                case SAFARI:
                    return new ConfiguredSafariDriver().getDriver(this.screenshotDirectory);
                default:
                    throw new RuntimeException("WebDriverBuilder has no valid target browser set in WebDriverConfig");
            }
        }
    }
}
