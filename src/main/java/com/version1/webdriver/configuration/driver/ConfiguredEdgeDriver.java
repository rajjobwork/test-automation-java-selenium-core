package com.version1.webdriver.configuration.driver;

import static org.openqa.selenium.remote.Browser.EDGE;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.version1.webdriver.configuration.TestConfigHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConfiguredEdgeDriver implements ConfiguredDriver {

    /**
     *
     * @return WebDriver representing RemoteWebDriver grid
     */
    public WebDriver getRemoteDriver() {
        return new RemoteWebDriver(
                TestConfigHelper.get().getGridConfig().getGridUrl(), this.getOptions());
    }

    /**
     *
     * @return WebDriver representing RemoteWebDriver grid
     */
    public WebDriver getLocalDriver() {
    	WebDriverManager.edgedriver().setup();
        return new EdgeDriver(this.getOptions());
    }

    /**
     *
     * @return configured options object for target browser driver
     */
    public ChromiumOptions getOptions() {
    	ChromiumOptions test =new ChromiumOptions(CapabilityType.BROWSER_NAME, EDGE.browserName(), "ms:edgeOptions");
    	test.setHeadless(TestConfigHelper.get().isHeadless());
        return test;
    }
}
