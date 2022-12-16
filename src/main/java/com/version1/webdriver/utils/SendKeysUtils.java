package com.version1.webdriver.utils;

import com.codahale.metrics.Timer;
import com.version1.metrics.MetricRegistryHelper;
import com.version1.webdriver.configuration.TestConfigHelper;

import org.openqa.selenium.WebElement;

import java.util.Optional;

import static com.codahale.metrics.MetricRegistry.name;

public final class SendKeysUtils extends TolerantInteraction {

    private static final Timer tolerantTypeAction = MetricRegistryHelper.get().timer(name("SendKeysUtils.tolerantType"));

    /**
     * @param webElement active WebElement, already located
     * @param textToType text that should be typed
     * @param timeout    time in seconds to keep trying
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static void tolerantType(WebElement webElement, String textToType, int timeout) throws Throwable {
        try(final Timer.Context ignored = tolerantTypeAction.time()) {
            new SendKeysUtils().tolerantInteraction(webElement, Optional.of(textToType), timeout);
        }
    }

    public static void tolerantType(WebElement webElement, String textToType) throws Throwable {
        try(final Timer.Context ignored = tolerantTypeAction.time()) {
            new SendKeysUtils().tolerantInteraction(webElement, Optional.of(textToType),
                    TestConfigHelper.get().getTolerantActionWaitTimeoutInSeconds());
        }
    }
}
