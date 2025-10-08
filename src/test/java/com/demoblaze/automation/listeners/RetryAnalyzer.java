package com.demoblaze.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Retry Analyzer to automatically retry failed tests
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;
    
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            LoggerUtil.warn("Retrying test: " + result.getName() + 
                " (Attempt " + (retryCount + 1) + " of " + (MAX_RETRY_COUNT + 1) + ")");
            return true;
        }
        return false;
    }
}

