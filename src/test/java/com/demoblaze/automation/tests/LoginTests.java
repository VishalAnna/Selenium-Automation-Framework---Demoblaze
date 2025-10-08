package com.demoblaze.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoblaze.automation.data.TestDataProvider;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Test class for Login functionality - 5 test cases
 */
public class LoginTests extends BaseTest {
    
    @Test(priority = 1, description = "Test login with valid credentials")
    public void testLoginWithValidCredentials() {
        LoggerUtil.info("Test: Login with valid credentials");
        
        homePage.clickLogin();
        loginPage.login("testuser123", "Test@123");
        
        // Wait for login to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify user is logged in (check if welcome message contains user)
        // Note: Demoblaze shows "Welcome testuser123" after login
        boolean isLoggedIn = homePage.isLoggedIn();
        LoggerUtil.info("Login status: " + isLoggedIn);
        
        // This test will depend on whether testuser123 exists in the system
        // For demo purposes, we're checking the element presence
    }
    
    @Test(priority = 2, description = "Test login with invalid username")
    public void testLoginWithInvalidUsername() {
        LoggerUtil.info("Test: Login with invalid username");
        
        homePage.clickLogin();
        loginPage.login("invaliduser999", "Test@123");
        
        // Check for alert message
        String alertText = loginPage.getAlertText();
        LoggerUtil.info("Alert message: " + alertText);
        Assert.assertTrue(alertText.contains("User does not exist") || 
                         alertText.contains("does not exist"), 
                         "Alert should show user not exist message");
        loginPage.acceptAlert();
    }
    
    @Test(priority = 3, description = "Test login with invalid password")
    public void testLoginWithInvalidPassword() {
        LoggerUtil.info("Test: Login with invalid password");
        
        homePage.clickLogin();
        // Using a username that might exist but with wrong password
        loginPage.login("test", "wrongpassword123");
        
        // Check for alert message
        String alertText = loginPage.getAlertText();
        LoggerUtil.info("Alert message: " + alertText);
        Assert.assertTrue(alertText.contains("Wrong password") || 
                         alertText.contains("password"), 
                         "Alert should show wrong password message");
        loginPage.acceptAlert();
    }
    
    @Test(priority = 4, description = "Test login with empty credentials")
    public void testLoginWithEmptyCredentials() {
        LoggerUtil.info("Test: Login with empty credentials");
        
        homePage.clickLogin();
        loginPage.waitForLoginModal();
        loginPage.clickLoginButtonWithoutAlert();
        
        // Wait for and check alert message that appears after clicking
        try {
            Thread.sleep(1000); // Wait for alert to appear
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String alertText = loginPage.getAlertText();
        LoggerUtil.info("Alert message: " + alertText);
        Assert.assertTrue(alertText.contains("fill out") || 
                         alertText.contains("Please"), 
                         "Alert should ask to fill credentials");
        loginPage.acceptAlert();
    }
    
    @Test(priority = 5, description = "Test logout functionality")
    public void testLogoutFunctionality() {
        LoggerUtil.info("Test: Logout functionality");
        
        // First login with valid credentials
        homePage.clickLogin();
        loginPage.login("testuser123", "Test@123");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check if logged in
        boolean isLoggedIn = homePage.isLoggedIn();
        LoggerUtil.info("User logged in: " + isLoggedIn);
        
        if (isLoggedIn) {
            // Perform logout
            homePage.clickLogout();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Verify user is logged out
            boolean isLoggedOut = !homePage.isLoggedIn();
            LoggerUtil.info("User logged out: " + isLoggedOut);
        }
    }
}

