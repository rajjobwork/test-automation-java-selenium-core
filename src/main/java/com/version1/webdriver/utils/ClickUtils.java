package com.version1.webdriver.utils;

import com.codahale.metrics.Timer;
import com.version1.metrics.MetricRegistryHelper;
import com.version1.webdriver.configuration.TestConfigHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Only for us in the situations outlined for the provided methods.
 * There's nothing wrong with WebDrivers normal click method, if you don't need this, steer well clear.
 */
public final class ClickUtils extends TolerantInteraction {

    private static final Timer tolerantClickAction = MetricRegistryHelper.get().timer(name("ClickUtils.tolerantClick"));

    /**
     *
     * @param webElement active WebElements, already located
     * @param timeout time in seconds to keep trying
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static void tolerantClick(WebElement webElement, int timeout) throws Throwable {
        try(final Timer.Context ignored = tolerantClickAction.time()) {
            new ClickUtils().tolerantInteraction(webElement, Optional.empty(), timeout);
        }
    }

    public static void tolerantClick(WebElement webElement) throws Throwable {
        try(final Timer.Context ignored = tolerantClickAction.time()) {
            new ClickUtils().tolerantInteraction(webElement, Optional.empty(),
                    TestConfigHelper.get().getTolerantActionWaitTimeoutInSeconds());
        }
    }

    public static void tolerantClick(WebDriver webDriver, By locator) throws Throwable {
        try(final Timer.Context ignored = tolerantClickAction.time()) {
            new ClickUtils().tolerantInteraction(webDriver, locator, Optional.empty(),
                    TestConfigHelper.get().getTolerantActionWaitTimeoutInSeconds());
        }
    }
}
