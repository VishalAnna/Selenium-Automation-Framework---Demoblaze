package com.demoblaze.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Page Object class for Checkout/Place Order Modal
 */
public class CheckoutPage extends BasePage {
    
    // Page Elements
    @FindBy(id = "orderModal")
    private WebElement checkoutModal;
    
    @FindBy(id = "name")
    private WebElement nameField;
    
    @FindBy(id = "country")
    private WebElement countryField;
    
    @FindBy(id = "city")
    private WebElement cityField;
    
    @FindBy(id = "card")
    private WebElement creditCardField;
    
    @FindBy(id = "month")
    private WebElement monthField;
    
    @FindBy(id = "year")
    private WebElement yearField;
    
    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    private WebElement purchaseButton;
    
    @FindBy(xpath = "//div[@id='orderModal']//button[contains(text(),'Close')]")
    private WebElement closeButton;
    
    @FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase!')]")
    private WebElement confirmationMessage;
    
    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElement okButton;
    
    @FindBy(css = ".lead.text-muted")
    private WebElement orderDetails;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public CheckoutPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("CheckoutPage initialized");
    }
    
    /**
     * Wait for checkout modal to be visible
     */
    public void waitForCheckoutModal() {
        waitHelper.waitForElementVisible(org.openqa.selenium.By.id("orderModal"));
        waitHelper.customWait(1);
        LoggerUtil.info("Checkout modal is visible");
    }
    
    /**
     * Fill checkout form
     * @param name Customer name
     * @param country Country
     * @param city City
     * @param card Credit card number
     * @param month Expiry month
     * @param year Expiry year
     */
    public void fillCheckoutForm(String name, String country, String city, 
                                  String card, String month, String year) {
        waitForCheckoutModal();
        sendKeys(nameField, name);
        sendKeys(countryField, country);
        sendKeys(cityField, city);
        sendKeys(creditCardField, card);
        sendKeys(monthField, month);
        sendKeys(yearField, year);
        LoggerUtil.info("Filled checkout form for: " + name);
    }
    
    /**
     * Click Purchase button
     */
    public void clickPurchase() {
        click(purchaseButton);
        LoggerUtil.info("Clicked Purchase button");
        waitHelper.customWait(2);
    }
    
    /**
     * Click Purchase button without alert handling (for tests expecting alerts)
     */
    public void clickPurchaseWithoutAlert() {
        try {
            purchaseButton.click();
            LoggerUtil.info("Clicked Purchase button (alert expected)");
        } catch (Exception e) {
            LoggerUtil.error("Failed to click Purchase button", e);
            throw e;
        }
    }
    
    /**
     * Close checkout modal
     */
    public void closeModal() {
        click(closeButton);
    }
    
    /**
     * Check if order confirmation is displayed
     * @return true if confirmed, false otherwise
     */
    public boolean isOrderConfirmed() {
        return isDisplayed(confirmationMessage);
    }
    
    /**
     * Get order details text
     * @return Order details
     */
    public String getOrderDetails() {
        return getText(orderDetails);
    }
    
    /**
     * Click OK button on confirmation
     */
    public void clickOK() {
        click(okButton);
        LoggerUtil.info("Clicked OK on order confirmation");
    }
    
    /**
     * Complete purchase flow
     * @param name Customer name
     * @param country Country
     * @param city City
     * @param card Credit card number
     * @param month Expiry month
     * @param year Expiry year
     */
    public void completePurchase(String name, String country, String city, 
                                  String card, String month, String year) {
        fillCheckoutForm(name, country, city, card, month, year);
        clickPurchase();
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

