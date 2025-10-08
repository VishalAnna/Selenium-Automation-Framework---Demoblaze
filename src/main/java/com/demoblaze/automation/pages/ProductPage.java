package com.demoblaze.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Page Object class for Product Details Page
 */
public class ProductPage extends BasePage {
    
    // Page Elements
    @FindBy(css = "h2.name")
    private WebElement productName;
    
    @FindBy(css = "h3.price-container")
    private WebElement productPrice;
    
    @FindBy(id = "more-information")
    private WebElement productDescription;
    
    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;
    
    @FindBy(linkText = "Home")
    private WebElement homeLink;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public ProductPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("ProductPage initialized");
    }
    
    /**
     * Get product name
     * @return Product name
     */
    public String getProductName() {
        waitHelper.customWait(1);
        return getText(productName);
    }
    
    /**
     * Get product price
     * @return Product price
     */
    public String getProductPrice() {
        String priceText = getText(productPrice);
        return priceText.replace("*includes tax", "").trim();
    }
    
    /**
     * Get product description
     * @return Product description
     */
    public String getProductDescription() {
        return getText(productDescription);
    }
    
    /**
     * Add product to cart
     */
    public void addToCart() {
        click(addToCartButton);
        LoggerUtil.info("Clicked Add to Cart button");
        waitHelper.customWait(1);
    }
    
    /**
     * Navigate back to home
     */
    public void goToHome() {
        try {
            // Dismiss any remaining alerts before navigating
            dismissUnexpectedAlert();
            
            // Wait briefly for page to be ready after alert dismissal
            waitHelper.customWait(2);
            
            // Try multiple strategies to find and click the home link
            try {
                // Strategy 1: Wait for home link to be clickable
                waitHelper.waitForElementClickable(org.openqa.selenium.By.linkText("Home"));
                click(homeLink);
                LoggerUtil.info("Navigated to home page using link");
            } catch (Exception e) {
                LoggerUtil.warn("Could not click Home link, trying alternative method");
                // Strategy 2: Use JavaScript to navigate to home
                org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
                js.executeScript("window.location.href = 'https://www.demoblaze.com/index.html';");
                waitHelper.customWait(2);
                LoggerUtil.info("Navigated to home page using JavaScript");
            }
        } catch (Exception e) {
            LoggerUtil.error("Failed to navigate to home page", e);
            throw e;
        }
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

