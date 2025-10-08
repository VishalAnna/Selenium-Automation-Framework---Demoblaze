package com.demoblaze.automation.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

/**
 * Centralized wait management for element synchronization
 */
public class WaitHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    
    /**
     * Constructor to initialize WaitHelper
     * @param driver WebDriver instance
     */
    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 
            Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }
    
    /**
     * Wait for element to be visible
     * @param locator Element locator
     * @return WebElement when visible
     */
    public WebElement waitForElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LoggerUtil.error("Element not visible: " + locator);
            throw e;
        }
    }
    
    /**
     * Wait for element to be clickable
     * @param locator Element locator
     * @return WebElement when clickable
     */
    public WebElement waitForElementClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            LoggerUtil.error("Element not clickable: " + locator);
            throw e;
        }
    }
    
    /**
     * Wait for element to be present in DOM
     * @param locator Element locator
     * @return WebElement when present
     */
    public WebElement waitForElementPresent(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            LoggerUtil.error("Element not present: " + locator);
            throw e;
        }
    }
    
    /**
     * Wait for alert to be present
     * @return Alert object
     */
    public Alert waitForAlert() {
        try {
            return wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            LoggerUtil.error("Alert not present");
            throw e;
        }
    }
    
    /**
     * Check if alert is present without waiting
     * @return true if alert is present, false otherwise
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    /**
     * Wait briefly for alert to appear
     * @param seconds Seconds to wait
     * @return true if alert appears, false otherwise
     */
    public boolean waitForAlertWithTimeout(int seconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            shortWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    /**
     * Wait for text to be present in element
     * @param locator Element locator
     * @param text Expected text
     * @return true if text is present
     */
    public boolean waitForTextPresent(By locator, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            LoggerUtil.error("Text not present in element: " + text);
            return false;
        }
    }
    
    /**
     * Fluent wait with custom polling interval
     * @param locator Element locator
     * @return WebElement
     */
    public WebElement fluentWait(By locator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(2))
            .ignoring(NoSuchElementException.class);
            
        return fluentWait.until(driver -> driver.findElement(locator));
    }
    
    /**
     * Wait for page to be fully loaded
     */
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        LoggerUtil.debug("Page loaded completely");
    }
    
    /**
     * Custom wait with timeout
     * @param seconds Seconds to wait
     */
    public void customWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            LoggerUtil.error("Wait interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}

