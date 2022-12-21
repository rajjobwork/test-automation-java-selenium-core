package com.version1.webdriver.configuration.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.version1.webdriver.configuration.TestConfigHelper;

public class ConfiguredInternetExplorerDriver implements ConfiguredDriver {

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
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver(this.getOptions());
    }

    /**
     *
     * @return configured options object for target browser driver
     */
    public InternetExplorerOptions getOptions() {
        return new InternetExplorerOptions();
    }
}
