package com.version1.webdriver.utils;

import com.codahale.metrics.Timer;
import com.version1.metrics.MetricRegistryHelper;
import com.version1.webdriver.configuration.TestConfigHelper;

import org.openqa.selenium.WebElement;

import static com.codahale.metrics.MetricRegistry.name;

public class GetAttributeUtils extends TolerantInteraction {

    private static final Timer tolerantGetAttributeAction = MetricRegistryHelper.get().timer(name("GetAttributeUtils.tolerantGetAttribute"));

    /**
     *
     * @param webElement active WebElement, already located
     * @param attribute WebElement attribute
     * @param timeout time in seconds to keep trying
     * @return attribute property value
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static String tolerantGetAttribute(WebElement webElement, String attribute, int timeout) throws Throwable {
        try (final Timer.Context ignored = tolerantGetAttributeAction.time()) {
            return new GetAttributeUtils().tolerantInteractionToGetAttribute(webElement,attribute,timeout);
        }
    }

    /**
     *
     * @param webElement active WebElement, already located
     * @param attribute WebElement attribute
     * @return attribute property value
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static String tolerantGetAttribute(WebElement webElement, String attribute) throws Throwable {
        try (final Timer.Context ignored = tolerantGetAttributeAction.time()) {
            return new GetAttributeUtils().tolerantInteractionToGetAttribute(webElement,attribute,
                    TestConfigHelper.get().getTolerantActionWaitTimeoutInSeconds());
        }
    }
}
