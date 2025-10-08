package com.demoblaze.automation.managers;

import org.openqa.selenium.WebDriver;
import com.demoblaze.automation.utils.ConfigReader;
import com.demoblaze.automation.utils.LoggerUtil;
import java.time.Duration;

/**
 * Thread-safe WebDriver management using ThreadLocal
 */
public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    /**
     * Get current thread's WebDriver instance
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    /**
     * Initialize WebDriver with browser from config
     */
    public static void initDriver() {
        String browser = ConfigReader.getBrowser();
        LoggerUtil.info("Initializing " + browser + " driver");
        
        WebDriver webDriver = DriverFactory.createDriver(browser);
        webDriver.manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        
        driver.set(webDriver);
        LoggerUtil.info("Driver initialized successfully");
    }
    
    /**
     * Initialize WebDriver with specific browser
     * @param browser Browser name
     */
    public static void initDriver(String browser) {
        LoggerUtil.info("Initializing " + browser + " driver");
        
        WebDriver webDriver = DriverFactory.createDriver(browser);
        webDriver.manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        
        driver.set(webDriver);
        LoggerUtil.info("Driver initialized successfully");
    }
    
    /**
     * Quit WebDriver and remove from ThreadLocal
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            LoggerUtil.info("Quitting driver");
            driver.get().quit();
            driver.remove();
            LoggerUtil.info("Driver quit successfully");
        }
    }
}

