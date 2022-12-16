package com.version1.webdriver.utils;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverUtils {
    private static final long DRIVER_WAIT_TIME = 10;
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverUtils.class);
    protected WebDriverWait Wait(EventFiringWebDriver webDriver){
    	WebDriverWait wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
    	return wait;
    }

    public String getCurrentUrl(EventFiringWebDriver webDriver) {
        return webDriver.getCurrentUrl();
    }

    public String getCurrentPageTitle(EventFiringWebDriver webDriver) {
        return webDriver.getTitle();
    }

    public boolean checkPageTitle(String title,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.titleIs(title));
    }

    public boolean checkPageTitleContains(String title,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.titleContains(title));
    }

    public boolean checkPageUrlToBe(String url,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.urlToBe(url));
    }

    public boolean checkPageUrlContains(String fraction,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.urlContains(fraction));
    }

    public boolean checkPageUrlMatches(String regex,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.urlMatches(regex));
    }

    protected WebElement waitForExpectedElement(final By by,EventFiringWebDriver webDriver) {
        return Wait(webDriver).until(visibilityOfElementLocated(by,webDriver));
    }

    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds,EventFiringWebDriver webDriver) throws TimeoutException {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by,webDriver));
        } catch (NoSuchElementException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }

    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by,EventFiringWebDriver webDriver) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
            }
            WebElement element = webDriver.findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }

    public boolean textToBePresentInElement(WebElement element, String text,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean textToBePresentInElementLocated(final By by, final String text,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public boolean textToBePresentInElementValue(final WebElement element, final String text,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public boolean textToBePresentInElementValue(final By by, final String text,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(by, text));
    }

    public WebDriver frameToBeAvailableAndSwitchToIt(final String frameLocator,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public WebDriver frameToBeAvailableAndSwitchToIt(final By by,EventFiringWebDriver webDriver) {
        return new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    public boolean invisibilityOfElementLocated(By by,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean invisibilityOfElementWithText(final By by, final String text,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }

    public WebElement elementToBeClickable(By by,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement elementToBeClickable(final WebElement element,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean stalenessOf(final WebElement element,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.stalenessOf(element));
    }

    public boolean elementToBeSelected(final By by,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeSelected(by));
    }

    public boolean elementToBeSelected(final WebElement element,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeSelected(element));
    }

    public boolean elementSelectionStateToBe(final WebElement element, final boolean selected,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    public boolean elementSelectionStateToBe(final By by,
                                             final boolean selected,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.elementSelectionStateToBe(by, selected));
    }

    public void waitForAlert(EventFiringWebDriver webDriver) {
        (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.alertIsPresent());
    }

    public List<WebElement> visibilityOfAllElementsLocatedBy(final By by,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public List<WebElement> visibilityOfAllElements(final List<WebElement> elements,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public List<WebElement> presenceOfAllElementsLocatedBy(final By by,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement visibilityOf(final WebElement element,EventFiringWebDriver webDriver) {
        return (new WebDriverWait(webDriver, DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementPresent(final By by,EventFiringWebDriver webDriver) {
        new WebDriverWait(webDriver, DRIVER_WAIT_TIME).until(ExpectedConditions.presenceOfElementLocated(by));
        return true;
    }

    public void navigateToPreviousPageUsingBrowserBackButton(EventFiringWebDriver webDriver) {
        webDriver.navigate().back();
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y,EventFiringWebDriver webDriver) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }

    public String getElementByTagNameWithJSExecutor(String tagName,EventFiringWebDriver webDriver) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')").toString();
    }

    public String getElementByQueryJSExecutor(String cssSelector,EventFiringWebDriver webDriver) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.querySelector('" + cssSelector + "')").toString();
    }

    protected WebElement element(final By by,EventFiringWebDriver webDriver) {
        return webDriver.findElement(by);
    }

    protected void clearEnterText(By by, String inputText,EventFiringWebDriver webDriver) {
        element(by,webDriver).clear();
        element(by,webDriver).sendKeys(inputText);
    }

    protected void waitClearEnterText(By by, String inputText,EventFiringWebDriver webDriver) {
        waitForExpectedElement(by,webDriver).clear();
        element(by,webDriver).sendKeys(inputText);

    }
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
}
