package com.demoblaze.automation.utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to capture screenshots
 */
public class ScreenshotUtil {
    
    /**
     * Capture screenshot with timestamp
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return Path to saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String screenshotPath = ConfigReader.getProperty("screenshot.path") + fileName;
        
        try {
            // Create directory if not exists
            File directory = new File(ConfigReader.getProperty("screenshot.path"));
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            LoggerUtil.info("Screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        } catch (Exception e) {
            LoggerUtil.error("Error capturing screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Capture screenshot of specific element
     * @param element WebElement to capture
     * @param elementName Name of the element
     * @return Path to saved screenshot
     */
    public static String captureElementScreenshot(WebElement element, String elementName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = elementName + "_" + timestamp + ".png";
        String screenshotPath = ConfigReader.getProperty("screenshot.path") + fileName;
        
        try {
            // Create directory if not exists
            File directory = new File(ConfigReader.getProperty("screenshot.path"));
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            File source = element.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            LoggerUtil.info("Element screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            LoggerUtil.error("Failed to capture element screenshot: " + e.getMessage());
            return null;
        }
    }
}

