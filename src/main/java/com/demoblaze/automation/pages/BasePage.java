package com.demoblaze.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import com.demoblaze.automation.utils.*;

/**
 * Base Page class with common functionalities for all page objects
 */
public class BasePage {
    protected WebDriver driver;
    protected WaitHelper waitHelper;
    
    /**
     * Constructor to initialize BasePage
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Click on an element with wait
     * @param element WebElement to click
     */
    protected void click(WebElement element) {
        try {
            // Dismiss any unexpected alerts before clicking
            dismissUnexpectedAlert();
            element.click();
            LoggerUtil.info("Clicked on element: " + element.toString());
        } catch (Exception e) {
            LoggerUtil.error("Failed to click element", e);
            throw e;
        }
    }
    
    /**
     * Enter text into an element
     * @param element WebElement to send keys
     * @param text Text to enter
     */
    protected void sendKeys(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            LoggerUtil.info("Entered text: " + text);
        } catch (Exception e) {
            LoggerUtil.error("Failed to send keys to element", e);
            throw e;
        }
    }
    
    /**
     * Get text from an element
     * @param element WebElement to get text from
     * @return Text content
     */
    protected String getText(WebElement element) {
        try {
            String text = element.getText();
            LoggerUtil.info("Retrieved text: " + text);
            return text;
        } catch (Exception e) {
            LoggerUtil.error("Failed to get text from element", e);
            return "";
        }
    }
    
    /**
     * Check if element is displayed
     * @param element WebElement to check
     * @return true if displayed, false otherwise
     */
    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
    
    /**
     * Accept alert
     */
    protected void acceptAlert() {
        try {
            Alert alert = waitHelper.waitForAlert();
            LoggerUtil.info("Alert text: " + alert.getText());
            alert.accept();
        } catch (UnhandledAlertException uae) {
            // Firefox may throw UnhandledAlertException when alert is auto-dismissed
            LoggerUtil.warn("Alert already handled by browser: " + uae.getAlertText());
            try {
                driver.switchTo().alert().accept();
            } catch (NoAlertPresentException nape) {
                // Alert already dismissed, that's fine
                LoggerUtil.debug("Alert already dismissed");
            }
        } catch (NoAlertPresentException e) {
            LoggerUtil.warn("No alert present to accept");
        } catch (Exception e) {
            LoggerUtil.error("Failed to accept alert", e);
        }
    }
    
    /**
     * Dismiss alert
     */
    protected void dismissAlert() {
        try {
            Alert alert = waitHelper.waitForAlert();
            LoggerUtil.info("Alert text: " + alert.getText());
            alert.dismiss();
        } catch (Exception e) {
            LoggerUtil.error("Failed to dismiss alert", e);
        }
    }
    
    /**
     * Get alert text
     * @return Alert text
     */
    protected String getAlertText() {
        try {
            // Try to wait for and get alert
            Alert alert = waitHelper.waitForAlert();
            String text = alert.getText();
            LoggerUtil.info("Alert text: " + text);
            return text;
        } catch (UnhandledAlertException uae) {
            // Firefox may throw UnhandledAlertException when alert is auto-dismissed
            try {
                String alertText = uae.getAlertText();
                LoggerUtil.warn("Got alert text from UnhandledAlertException: " + alertText);
                // Try to handle the alert
                try {
                    driver.switchTo().alert().accept();
                } catch (NoAlertPresentException nape) {
                    // Alert already dismissed, that's fine
                }
                return alertText;
            } catch (Exception e) {
                LoggerUtil.error("Failed to get alert text from exception", e);
                return "";
            }
        } catch (Exception e) {
            LoggerUtil.error("Failed to get alert text", e);
            return "";
        }
    }
    
    /**
     * Scroll to element
     * @param element WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            LoggerUtil.debug("Scrolled to element");
        } catch (Exception e) {
            LoggerUtil.error("Failed to scroll to element", e);
        }
    }
    
    /**
     * Wait for element to be visible
     * @param locator By locator
     * @return WebElement
     */
    protected WebElement waitForElement(By locator) {
        return waitHelper.waitForElementVisible(locator);
    }
    
    /**
     * Dismiss unexpected alert if present (checks immediately, no wait)
     */
    protected void dismissUnexpectedAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            LoggerUtil.warn("Dismissing unexpected alert: " + alertText);
            alert.accept();
            Thread.sleep(500); // Brief wait after dismissing
        } catch (NoAlertPresentException e) {
            // No alert present - this is fine
        } catch (Exception e) {
            LoggerUtil.debug("Error checking for alert: " + e.getMessage());
        }
    }
    
    /**
     * Wait for and dismiss alert if it appears
     * @param timeoutSeconds How long to wait for alert
     */
    protected void waitAndDismissAlert(int timeoutSeconds) {
        try {
            if (waitHelper.waitForAlertWithTimeout(timeoutSeconds)) {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                LoggerUtil.info("Dismissing alert: " + alertText);
                alert.accept();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            LoggerUtil.debug("No alert appeared within timeout");
        }
    }
    
    /**
     * Check if element is displayed with alert handling
     * @param element WebElement to check
     * @return true if displayed, false otherwise
     */
    protected boolean isDisplayedSafe(WebElement element) {
        try {
            dismissUnexpectedAlert();
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
}

