package com.version1.webdriver.utils;

import com.codahale.metrics.Timer;
import com.version1.metrics.MetricRegistryHelper;
import com.version1.webdriver.configuration.TestConfigHelper;

import org.openqa.selenium.WebElement;

import static com.codahale.metrics.MetricRegistry.name;

public class ClearUtils extends TolerantInteraction {

    private static final Timer tolerantClearAction = MetricRegistryHelper.get().timer(name("ClearUtils.tolerantClear"));

    /**
     *
     * @param webElement active WebElement, already located
     * @param timeout time in seconds to keep trying
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static void tolerantClear(WebElement webElement, int timeout) throws Throwable {
        try(final Timer.Context ignored = tolerantClearAction.time()) {
            new ClearUtils().tolerantInteractionToClear(webElement, timeout);
        }
    }

    /**
     *
     * @param webElement active WebElement, already located
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static void tolerantClear(WebElement webElement) throws Throwable {
        try(final Timer.Context ignored = tolerantClearAction.time()) {
            new ClearUtils().tolerantInteractionToClear(webElement,
                    TestConfigHelper.get().getTolerantActionWaitTimeoutInSeconds());
        }
    }
}
