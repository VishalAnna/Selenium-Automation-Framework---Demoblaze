package com.demoblaze.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Page Object class for Login Modal
 */
public class LoginPage extends BasePage {
    
    // Page Elements
    @FindBy(id = "logInModal")
    private WebElement loginModal;
    
    @FindBy(id = "loginusername")
    private WebElement usernameField;
    
    @FindBy(id = "loginpassword")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginButton;
    
    @FindBy(xpath = "//div[@id='logInModal']//button[contains(text(),'Close')]")
    private WebElement closeButton;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("LoginPage initialized");
    }
    
    /**
     * Wait for login modal to be visible
     */
    public void waitForLoginModal() {
        waitHelper.waitForElementVisible(org.openqa.selenium.By.id("logInModal"));
        waitHelper.customWait(1);
        LoggerUtil.info("Login modal is visible");
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
     * Click login button
     */
    public void clickLoginButton() {
        click(loginButton);
    }
    
    /**
     * Click login button without alert handling (for tests expecting alerts)
     */
    public void clickLoginButtonWithoutAlert() {
        try {
            loginButton.click();
            LoggerUtil.info("Clicked login button (alert expected)");
        } catch (Exception e) {
            LoggerUtil.error("Failed to click login button", e);
            throw e;
        }
    }
    
    /**
     * Close login modal
     */
    public void closeModal() {
        click(closeButton);
    }
    
    /**
     * Complete login flow
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        waitForLoginModal();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        LoggerUtil.info("Login attempt with username: " + username);
        waitHelper.customWait(2);
    }
    
    /**
     * Get alert text (public wrapper)
     * @return Alert text
     */
    public String getAlertText() {
        return super.getAlertText();
    }
    
    /**
     * Accept alert (public wrapper)
     */
    public void acceptAlert() {
        super.acceptAlert();
    }
}

