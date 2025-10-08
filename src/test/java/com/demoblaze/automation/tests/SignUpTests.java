package com.demoblaze.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoblaze.automation.utils.LoggerUtil;
import java.util.UUID;

/**
 * Test class for Sign Up functionality - 5 test cases
 */
public class SignUpTests extends BaseTest {
    
    @Test(priority = 1, description = "Test successful sign up with unique username")
    public void testSuccessfulSignUp() {
        LoggerUtil.info("Test: Successful sign up");
        
        // Generate unique username
        String uniqueUsername = "user_" + UUID.randomUUID().toString().substring(0, 8);
        
        homePage.clickSignUp();
        signUpPage.signUp(uniqueUsername, "Test@123");
        
        // Check for success alert
        String alertText = signUpPage.getSignUpAlertMessage();
        LoggerUtil.info("Sign up alert: " + alertText);
        Assert.assertTrue(alertText.contains("Sign up successful") || 
                         alertText.contains("successful"), 
                         "Sign up should be successful");
        signUpPage.acceptAlert();
    }
    
    @Test(priority = 2, description = "Test sign up with existing username")
    public void testSignUpWithExistingUser() {
        LoggerUtil.info("Test: Sign up with existing user");
        
        // Using a common username that likely exists
        homePage.clickSignUp();
        signUpPage.signUp("test", "Test@123");
        
        // Check for error alert
        String alertText = signUpPage.getSignUpAlertMessage();
        LoggerUtil.info("Sign up alert: " + alertText);
        Assert.assertTrue(alertText.contains("already exist") || 
                         alertText.contains("exists"), 
                         "Should show user exists message");
        signUpPage.acceptAlert();
    }
    
    @Test(priority = 3, description = "Test sign up with empty fields")
    public void testSignUpWithEmptyFields() {
        LoggerUtil.info("Test: Sign up with empty fields");
        
        homePage.clickSignUp();
        signUpPage.waitForSignUpModal();
        signUpPage.clickSignUpButtonWithoutAlert();
        
        // Wait for and check alert message that appears after clicking
        try {
            Thread.sleep(1000); // Wait for alert to appear
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String alertText = signUpPage.getSignUpAlertMessage();
        LoggerUtil.info("Sign up alert: " + alertText);
        Assert.assertTrue(alertText.contains("fill out") || 
                         alertText.contains("Please"), 
                         "Should ask to fill fields");
        signUpPage.acceptAlert();
    }
    
    @Test(priority = 4, description = "Test sign up with only username")
    public void testSignUpWithOnlyUsername() {
        LoggerUtil.info("Test: Sign up with only username");
        
        String uniqueUsername = "user_" + UUID.randomUUID().toString().substring(0, 8);
        
        homePage.clickSignUp();
        signUpPage.waitForSignUpModal();
        signUpPage.enterUsername(uniqueUsername);
        signUpPage.clickSignUpButtonWithoutAlert();
        
        // Wait for and check alert message that appears after clicking
        try {
            Thread.sleep(1000); // Wait for alert to appear
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String alertText = signUpPage.getSignUpAlertMessage();
        LoggerUtil.info("Sign up alert: " + alertText);
        Assert.assertTrue(alertText.contains("fill out") || 
                         alertText.contains("Please") ||
                         alertText.contains("password"), 
                         "Should ask for password");
        signUpPage.acceptAlert();
    }
    
    @Test(priority = 5, description = "Test sign up modal close functionality")
    public void testSignUpModalClose() {
        LoggerUtil.info("Test: Close sign up modal");
        
        homePage.clickSignUp();
        signUpPage.waitForSignUpModal();
        signUpPage.closeModal();
        
        // Verify still on home page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("demoblaze"), 
                         "Should remain on demoblaze home page");
        LoggerUtil.info("Sign up modal closed successfully");
    }
}

