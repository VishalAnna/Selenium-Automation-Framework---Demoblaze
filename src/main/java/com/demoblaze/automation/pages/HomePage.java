package com.demoblaze.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Page Object class for Home Page
 */
public class HomePage extends BasePage {
    
    // Page Elements
    @FindBy(id = "login2")
    private WebElement loginLink;
    
    @FindBy(id = "signin2")
    private WebElement signUpLink;
    
    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;
    
    @FindBy(id = "logout2")
    private WebElement logoutLink;
    
    @FindBy(linkText = "Home")
    private WebElement homeLink;
    
    @FindBy(linkText = "Contact")
    private WebElement contactLink;
    
    @FindBy(linkText = "About us")
    private WebElement aboutUsLink;
    
    @FindBy(id = "cartur")
    private WebElement cartLink;
    
    @FindBy(linkText = "Phones")
    private WebElement phonesCategory;
    
    @FindBy(linkText = "Laptops")
    private WebElement laptopsCategory;
    
    @FindBy(linkText = "Monitors")
    private WebElement monitorsCategory;
    
    @FindBy(id = "next2")
    private WebElement nextButton;
    
    @FindBy(id = "prev2")
    private WebElement previousButton;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("HomePage initialized");
    }
    
    /**
     * Click on Login link
     */
    public void clickLogin() {
        waitHelper.customWait(1);
        click(loginLink);
        LoggerUtil.info("Clicked on Login link");
    }
    
    /**
     * Click on Sign Up link
     */
    public void clickSignUp() {
        waitHelper.customWait(1);
        click(signUpLink);
        LoggerUtil.info("Clicked on Sign Up link");
    }
    
    /**
     * Click on Logout link
     */
    public void clickLogout() {
        click(logoutLink);
        LoggerUtil.info("Clicked on Logout link");
    }
    
    /**
     * Click on Cart link
     */
    public void clickCart() {
        click(cartLink);
        LoggerUtil.info("Clicked on Cart link");
    }
    
    /**
     * Get welcome message text
     * @return Welcome message
     */
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }
    
    /**
     * Check if user is logged in
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isDisplayedSafe(welcomeMessage);
    }
    
    /**
     * Select product category
     * @param category Category name (Phones, Laptops, Monitors)
     */
    public void selectCategory(String category) {
        waitHelper.customWait(1);
        switch (category.toLowerCase()) {
            case "phones":
                click(phonesCategory);
                break;
            case "laptops":
                click(laptopsCategory);
                break;
            case "monitors":
                click(monitorsCategory);
                break;
            default:
                LoggerUtil.warn("Unknown category: " + category);
        }
        LoggerUtil.info("Selected category: " + category);
        waitHelper.customWait(2);
    }
    
    /**
     * Select product by name
     * @param productName Name of the product
     */
    public void selectProduct(String productName) {
        waitHelper.customWait(1);
        WebElement product = driver.findElement(By.linkText(productName));
        click(product);
        LoggerUtil.info("Selected product: " + productName);
    }
    
    /**
     * Click on Home link
     */
    public void clickHome() {
        click(homeLink);
        LoggerUtil.info("Clicked on Home link");
    }
}

