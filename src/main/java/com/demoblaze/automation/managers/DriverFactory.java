package com.demoblaze.automation.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Factory class to create browser-specific WebDriver instances
 */
public class DriverFactory {
    
    /**
     * Create WebDriver based on browser type
     * @param browser Browser name (chrome, firefox, chrome-headless)
     * @return WebDriver instance
     */
    public static WebDriver createDriver(String browser) {
        WebDriver driver = null;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                LoggerUtil.info("Chrome browser initialized");
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                
                // Set Firefox binary location for macOS
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("mac")) {
                    firefoxOptions.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
                    LoggerUtil.info("Setting Firefox binary for macOS");
                }
                
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                LoggerUtil.info("Firefox browser initialized");
                break;
                
            case "chrome-headless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions headlessOptions = new ChromeOptions();
                headlessOptions.addArguments("--headless");
                headlessOptions.addArguments("--disable-gpu");
                headlessOptions.addArguments("--window-size=1920,1080");
                headlessOptions.addArguments("--no-sandbox");
                headlessOptions.addArguments("--disable-dev-shm-usage");
                headlessOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(headlessOptions);
                LoggerUtil.info("Chrome headless browser initialized");
                break;
                
            default:
                LoggerUtil.error("Browser not supported: " + browser);
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        return driver;
    }
}

