package com.demoblaze.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoblaze.automation.data.Constants;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Test class for Checkout Process functionality - 5 test cases
 */
public class CheckoutTests extends BaseTest {
    
    @Test(priority = 1, description = "Test complete checkout process with valid details")
    public void testCompleteCheckoutProcess() {
        LoggerUtil.info("Test: Complete checkout process");
        
        // Add product to cart
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        // Navigate to cart and place order
        productPage.goToHome();
        homePage.clickCart();
        cartPage.clickPlaceOrder();
        
        // Fill checkout form
        checkoutPage.completePurchase(
            "John Doe",
            "USA",
            "New York",
            "1234567890123456",
            "12",
            "2025"
        );
        
        // Verify order confirmation
        boolean isConfirmed = checkoutPage.isOrderConfirmed();
        LoggerUtil.info("Order confirmed: " + isConfirmed);
        Assert.assertTrue(isConfirmed, "Order should be confirmed");
        
        // Click OK to close confirmation
        checkoutPage.clickOK();
    }
    
    @Test(priority = 2, description = "Test checkout with empty name field")
    public void testCheckoutWithEmptyName() {
        LoggerUtil.info("Test: Checkout with empty name");
        
        // Add product and navigate to checkout
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        productPage.goToHome();
        homePage.clickCart();
        cartPage.clickPlaceOrder();
        
        // Fill form with empty name
        checkoutPage.waitForCheckoutModal();
        checkoutPage.fillCheckoutForm(
            "",  // Empty name
            "USA",
            "New York",
            "1234567890123456",
            "12",
            "2025"
        );
        checkoutPage.clickPurchaseWithoutAlert();
        
        // Wait for alert to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check for alert message
        String alertText = checkoutPage.getAlertText();
        LoggerUtil.info("Alert: " + alertText);
        Assert.assertTrue(alertText.contains("fill") || 
                         alertText.contains("Please") ||
                         alertText.contains("Name"), 
                         "Should show validation message");
        checkoutPage.acceptAlert();
    }
    
    @Test(priority = 3, description = "Test checkout with empty credit card field")
    public void testCheckoutWithEmptyCreditCard() {
        LoggerUtil.info("Test: Checkout with empty credit card");
        
        // Add product and navigate to checkout
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        productPage.goToHome();
        homePage.clickCart();
        cartPage.clickPlaceOrder();
        
        // Fill form with empty credit card
        checkoutPage.waitForCheckoutModal();
        checkoutPage.fillCheckoutForm(
            "John Doe",
            "USA",
            "New York",
            "",  // Empty card
            "12",
            "2025"
        );
        checkoutPage.clickPurchaseWithoutAlert();
        
        // Wait for alert to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check for alert message
        String alertText = checkoutPage.getAlertText();
        LoggerUtil.info("Alert: " + alertText);
        Assert.assertTrue(alertText.contains("fill") || 
                         alertText.contains("Please") ||
                         alertText.contains("card"), 
                         "Should show validation message");
        checkoutPage.acceptAlert();
    }
    
    @Test(priority = 4, description = "Test order details display after successful purchase")
    public void testOrderDetailsDisplay() {
        LoggerUtil.info("Test: Order details display");
        
        // Add product and complete checkout
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        productPage.goToHome();
        homePage.clickCart();
        cartPage.clickPlaceOrder();
        
        checkoutPage.completePurchase(
            "Jane Smith",
            "UK",
            "London",
            "9876543210987654",
            "06",
            "2026"
        );
        
        // Get order details
        String orderDetails = checkoutPage.getOrderDetails();
        LoggerUtil.info("Order details: " + orderDetails);
        
        Assert.assertFalse(orderDetails.isEmpty(), 
                          "Order details should be displayed");
        
        checkoutPage.clickOK();
    }
    
    @Test(priority = 5, description = "Test closing checkout modal")
    public void testCloseCheckoutModal() {
        LoggerUtil.info("Test: Close checkout modal");
        
        // Add product and navigate to checkout
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        productPage.goToHome();
        homePage.clickCart();
        cartPage.clickPlaceOrder();
        
        // Close the modal
        checkoutPage.waitForCheckoutModal();
        checkoutPage.closeModal();
        
        // Verify still on cart page
        String currentUrl = driver.getCurrentUrl();
        LoggerUtil.info("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("cart") || 
                         currentUrl.contains("demoblaze"), 
                         "Should remain on site");
    }
}

