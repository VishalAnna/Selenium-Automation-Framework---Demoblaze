package com.demoblaze.automation.tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import com.demoblaze.automation.managers.DriverManager;
import com.demoblaze.automation.utils.*;
import com.demoblaze.automation.pages.*;

/**
 * Base Test class with setup and teardown methods
 */
public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected SignUpPage signUpPage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    
    /**
     * Setup method - runs before each test method
     * @param browser Browser parameter from TestNG XML
     */
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {
        LoggerUtil.info("============================================");
        LoggerUtil.info("Starting test setup for browser: " + browser);
        
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.getUrl());
        
        // Initialize page objects
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        
        LoggerUtil.info("Test setup completed successfully");
        LoggerUtil.info("============================================");
    }
    
    /**
     * Teardown method - runs after each test method
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LoggerUtil.info("============================================");
        LoggerUtil.info("Starting test teardown");
        
        // Dismiss any remaining alerts before quitting
        try {
            if (driver != null) {
                dismissAnyRemainingAlert();
            }
        } catch (Exception e) {
            LoggerUtil.debug("No alert to dismiss during teardown");
        }
        
        DriverManager.quitDriver();
        LoggerUtil.info("Test teardown completed");
        LoggerUtil.info("============================================");
    }
    
    /**
     * Dismiss any remaining alert
     */
    private void dismissAnyRemainingAlert() {
        try {
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            LoggerUtil.warn("Dismissing alert during teardown: " + alertText);
            alert.accept();
        } catch (Exception e) {
            // No alert present
        }
    }
}

