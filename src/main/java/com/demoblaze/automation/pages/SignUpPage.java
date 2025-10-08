package com.demoblaze.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Page Object class for Sign Up Modal
 */
public class SignUpPage extends BasePage {
    
    // Page Elements
    @FindBy(id = "signInModal")
    private WebElement signUpModal;
    
    @FindBy(id = "sign-username")
    private WebElement usernameField;
    
    @FindBy(id = "sign-password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[contains(text(),'Sign up')]")
    private WebElement signUpButton;
    
    @FindBy(xpath = "//div[@id='signInModal']//button[contains(text(),'Close')]")
    private WebElement closeButton;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public SignUpPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("SignUpPage initialized");
    }
    
    /**
     * Wait for sign up modal to be visible
     */
    public void waitForSignUpModal() {
        waitHelper.waitForElementVisible(org.openqa.selenium.By.id("signInModal"));
        waitHelper.customWait(1);
        LoggerUtil.info("Sign up modal is visible");
    }
    
    /**
     * Enter username
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }
    
    /**
     * Enter password
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }
    
    /**
     * Click sign up button
     */
    public void clickSignUpButton() {
        click(signUpButton);
    }
    
    /**
     * Click sign up button without alert handling (for tests expecting alerts)
     */
    public void clickSignUpButtonWithoutAlert() {
        try {
            signUpButton.click();
            LoggerUtil.info("Clicked sign up button (alert expected)");
        } catch (Exception e) {
            LoggerUtil.error("Failed to click sign up button", e);
            throw e;
        }
    }
    
    /**
     * Close sign up modal
     */
    public void closeModal() {
        click(closeButton);
    }
    
    /**
     * Complete sign up flow
     * @param username Username
     * @param password Password
     */
    public void signUp(String username, String password) {
        waitForSignUpModal();
        enterUsername(username);
        enterPassword(password);
        clickSignUpButton();
        LoggerUtil.info("Sign up attempt with username: " + username);
        waitHelper.customWait(2);
    }
    
    /**
     * Get alert message after sign up
     * @return Alert message text
     */
    public String getSignUpAlertMessage() {
        return super.getAlertText();
    }
    
    /**
     * Accept alert (public wrapper)
     */
    public void acceptAlert() {
        super.acceptAlert();
    }
}

