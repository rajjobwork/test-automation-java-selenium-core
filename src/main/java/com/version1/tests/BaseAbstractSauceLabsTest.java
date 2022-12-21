package com.version1.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.version1.exceptions.SauceLabsCredentialsException;
import com.version1.webdriver.WebDriverListener;
import com.version1.webdriver.configuration.TestConfigHelper;
import com.version1.webdriver.configuration.driver.ConfiguredDriver;
import com.version1.webdriver.configuration.driver.ConfiguredSauceLabsGridDriver;

import java.io.IOException;

/**
 * BaseAbstractTest to handle things that testers shouldn't need to worry about (like starting up a WebDriver instance
 * and getting everything in the right space for consumers to run tests that are correctly configured etc).
 */
public abstract class BaseAbstractSauceLabsTest {

    protected EventFiringWebDriver webDriver;

    /**
     * This will run before every test class.
     * This method gets the configuration and constructs the WebDriver instance, the screenshot
     * directory and the makes these items accessible.
     */
    @BeforeAll
    public static void beforeAll() {
        // Do nothing
    }

    /**
     * This will run before EVERY @Test that extends this class
     * The method will create a new instance of WebDriver and a browser and open Google.com
     * This ensures we always have a fresh browser window and a guaranteed starting point
     * @throws IOException if results directory isn't created or config file cannot be found
     */
    @BeforeEach
    public void setUp() throws SauceLabsCredentialsException, IOException {
        ConfiguredDriver sauceLabsDriver = new ConfiguredSauceLabsGridDriver();
        this.webDriver = new EventFiringWebDriver(sauceLabsDriver.getRemoteDriver());
        this.webDriver.register(new WebDriverListener());
        this.webDriver.get(TestConfigHelper.get().getBaseUrl());
        this.webDriver.manage().window().maximize();
    }

    /**
     * This will run after EVERY @Test that extends this class
     * The method will close the current browser and WebDriver instance down
     * This ensures we have cleaned up after ourselves (this happens even if the test fails)
     */
    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }
}
