package com.demoblaze.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Page Object class for Shopping Cart Page
 */
public class CartPage extends BasePage {
    
    // Page Elements
    @FindBy(id = "totalp")
    private WebElement totalPrice;
    
    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;
    
    @FindBy(xpath = "//tbody[@id='tbodyid']/tr")
    private List<WebElement> cartItems;
    
    @FindBy(xpath = "//tbody[@id='tbodyid']/tr/td[2]")
    private List<WebElement> productTitles;
    
    @FindBy(xpath = "//tbody[@id='tbodyid']/tr/td[3]")
    private List<WebElement> productPrices;
    
    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    private List<WebElement> deleteButtons;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public CartPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("CartPage initialized");
    }
    
    /**
     * Get total price
     * @return Total price as string
     */
    public String getTotalPrice() {
        waitHelper.customWait(1);
        return getText(totalPrice);
    }
    
    /**
     * Get number of items in cart
     * @return Number of cart items
     */
    public int getCartItemCount() {
        waitHelper.customWait(1);
        return cartItems.size();
    }
    
    /**
     * Check if cart is empty
     * @return true if cart is empty, false otherwise
     */
    public boolean isCartEmpty() {
        waitHelper.customWait(1);
        return cartItems.isEmpty();
    }
    
    /**
     * Get list of product names in cart
     * @return List of product names
     */
    public List<String> getProductNames() {
        waitHelper.customWait(1);
        return productTitles.stream()
            .map(this::getText)
            .collect(Collectors.toList());
    }
    
    /**
     * Delete first item from cart
     */
    public void deleteFirstItem() {
        if (!deleteButtons.isEmpty()) {
            click(deleteButtons.get(0));
            LoggerUtil.info("Deleted first item from cart");
            waitHelper.customWait(2);
        }
    }
    
    /**
     * Delete item by product name
     * @param productName Name of the product to delete
     */
    public void deleteItemByName(String productName) {
        for (int i = 0; i < productTitles.size(); i++) {
            if (getText(productTitles.get(i)).equals(productName)) {
                click(deleteButtons.get(i));
                LoggerUtil.info("Deleted product: " + productName);
                waitHelper.customWait(2);
                break;
            }
        }
    }
    
    /**
     * Click Place Order button
     */
    public void clickPlaceOrder() {
        click(placeOrderButton);
        LoggerUtil.info("Clicked Place Order button");
        waitHelper.customWait(1);
    }
}

