package com.version1.webdriver.utils;

import com.codahale.metrics.Timer;
import com.version1.metrics.MetricRegistryHelper;
import com.version1.webdriver.configuration.TestConfigHelper;

import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Utility methods to select radio buttons
 */
public final class RadioButtonUtils extends TolerantInteraction {

    private static final Timer selectByLabelAction = MetricRegistryHelper.get().timer(name("RadioButtonUtils.selectByLabelAction"));
    private static final Timer tolerantSelectByLabelAction = MetricRegistryHelper.get().timer(name("RadioButtonUtils.tolerantSelectByLabel"));

    /**
     * Given a list of WebElements that locate the labels of the radio buttons,
     * finds the radio button with the given visible label text and selects it.
     *
     * @param webElements      active WebElements, already located
     * @param visibleLabelText text that is visible on the page in the label tags
     */
    public static void selectByLabel(List<WebElement> webElements, String visibleLabelText) {
        try(final Timer.Context ignored = selectByLabelAction.time()) {
            for (WebElement webElement : webElements) {
                if (webElement.getText().equals(visibleLabelText)) {
                    webElement.click();
                    break;
                }
            }
        }
    }

    /**
     * @param webElements      active WebElements, already located
     * @param visibleLabelText text that is visible on the page in the label tags
     * @param timeout          time in seconds to keep trying
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static void tolerantSelectByLabel(List<WebElement> webElements, String visibleLabelText, int timeout)
            throws Throwable {
        try(final Timer.Context ignored = tolerantSelectByLabelAction.time()) {
            new RadioButtonUtils().tolerantInteraction(webElements, visibleLabelText, timeout);
        }
    }

    /**
     *
     * @param webElements active WebElements, already located
     * @param visibleLabelText text that is visible on the page in the label tags
     * @throws Throwable any unhandled or un-tolerated exception
     */
    public static void tolerantSelectByLabel(List<WebElement> webElements, String visibleLabelText)
            throws Throwable {
        try(final Timer.Context ignored = tolerantSelectByLabelAction.time()) {
            new RadioButtonUtils().tolerantInteraction(webElements, visibleLabelText,
                    TestConfigHelper.get().getTolerantActionWaitTimeoutInSeconds());
        }
    }
}
