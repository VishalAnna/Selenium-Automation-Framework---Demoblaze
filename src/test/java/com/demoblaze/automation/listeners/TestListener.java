package com.demoblaze.automation.listeners;

import org.testng.*;
import com.demoblaze.automation.managers.DriverManager;
import com.demoblaze.automation.utils.*;

/**
 * TestNG Listener to handle test execution events
 */
public class TestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.info("========================================");
        LoggerUtil.info("STARTING TEST: " + result.getName());
        LoggerUtil.info("Description: " + result.getMethod().getDescription());
        LoggerUtil.info("========================================");
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtil.info("✓ TEST PASSED: " + result.getName());
        LoggerUtil.info("Execution Time: " + 
            (result.getEndMillis() - result.getStartMillis()) + " ms");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtil.error("✗ TEST FAILED: " + result.getName());
        LoggerUtil.error("Failure reason: " + result.getThrowable());
        
        // Dismiss any pending alerts before capturing screenshot
        try {
            if (DriverManager.getDriver() != null) {
                dismissAnyAlert();
            }
        } catch (Exception e) {
            LoggerUtil.debug("No alert to dismiss on failure");
        }
        
        // Capture screenshot on failure
        try {
            if (DriverManager.getDriver() != null) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(
                    DriverManager.getDriver(), result.getName());
                
                if (screenshotPath != null) {
                    LoggerUtil.info("Screenshot saved at: " + screenshotPath);
                    // Attach screenshot to TestNG report
                    Reporter.log("<br><img src='" + screenshotPath + 
                               "' height='400' width='600'/><br>");
                }
            }
        } catch (Exception e) {
            LoggerUtil.error("Failed to capture screenshot", e);
        }
    }
    
    /**
     * Dismiss any pending alert
     */
    private void dismissAnyAlert() {
        try {
            org.openqa.selenium.Alert alert = DriverManager.getDriver().switchTo().alert();
            String alertText = alert.getText();
            LoggerUtil.warn("Dismissing alert on test failure: " + alertText);
            alert.accept();
            Thread.sleep(500);
        } catch (Exception e) {
            // No alert present
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.warn("⊘ TEST SKIPPED: " + result.getName());
        LoggerUtil.warn("Skip reason: " + result.getThrowable());
    }
    
    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.info("========================================");
        LoggerUtil.info("TEST SUITE STARTED: " + context.getName());
        LoggerUtil.info("========================================");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.info("========================================");
        LoggerUtil.info("TEST SUITE FINISHED: " + context.getName());
        LoggerUtil.info("Total Tests: " + context.getAllTestMethods().length);
        LoggerUtil.info("Passed: " + context.getPassedTests().size());
        LoggerUtil.info("Failed: " + context.getFailedTests().size());
        LoggerUtil.info("Skipped: " + context.getSkippedTests().size());
        LoggerUtil.info("Success Rate: " + 
            String.format("%.2f%%", 
                (double) context.getPassedTests().size() / 
                context.getAllTestMethods().length * 100));
        LoggerUtil.info("========================================");
    }
}

