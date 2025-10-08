package com.demoblaze.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoblaze.automation.data.Constants;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Test class for Shopping Cart functionality - 5 test cases
 */
public class CartTests extends BaseTest {
    
    @Test(priority = 1, description = "Test adding product to cart")
    public void testAddProductToCart() {
        LoggerUtil.info("Test: Add product to cart");
        
        // Navigate to a product
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        
        // Add to cart
        productPage.addToCart();
        
        // Check for alert confirmation
        String alertText = productPage.getAlertText();
        LoggerUtil.info("Alert: " + alertText);
        Assert.assertTrue(alertText.contains("Product added") || 
                         alertText.contains("added"), 
                         "Product should be added to cart");
        productPage.acceptAlert();
    }
    
    @Test(priority = 2, description = "Test viewing cart after adding product")
    public void testViewCartAfterAddingProduct() {
        LoggerUtil.info("Test: View cart after adding product");
        
        // Add product to cart
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        // Navigate to cart
        productPage.goToHome();
        homePage.clickCart();
        
        // Verify cart has items
        int itemCount = cartPage.getCartItemCount();
        LoggerUtil.info("Cart item count: " + itemCount);
        Assert.assertTrue(itemCount > 0, "Cart should have at least one item");
    }
    
    @Test(priority = 3, description = "Test removing product from cart")
    public void testRemoveProductFromCart() {
        LoggerUtil.info("Test: Remove product from cart");
        
        // Add product to cart
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        // Navigate to cart
        productPage.goToHome();
        homePage.clickCart();
        
        // Get initial cart count
        int initialCount = cartPage.getCartItemCount();
        LoggerUtil.info("Initial cart count: " + initialCount);
        
        // Remove item if cart is not empty
        if (initialCount > 0) {
            cartPage.deleteFirstItem();
            
            // Verify item was removed
            int finalCount = cartPage.getCartItemCount();
            LoggerUtil.info("Final cart count: " + finalCount);
            Assert.assertEquals(finalCount, initialCount - 1, 
                              "Cart count should decrease by 1");
        }
    }
    
    @Test(priority = 4, description = "Test cart total price display")
    public void testCartTotalPriceDisplay() {
        LoggerUtil.info("Test: Cart total price display");
        
        // Add product to cart
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        
        // Get product price
        String productPrice = productPage.getProductPrice();
        LoggerUtil.info("Product price: " + productPrice);
        
        productPage.addToCart();
        productPage.acceptAlert();
        
        // Navigate to cart
        productPage.goToHome();
        homePage.clickCart();
        
        // Get cart total
        String totalPrice = cartPage.getTotalPrice();
        LoggerUtil.info("Cart total: " + totalPrice);
        
        Assert.assertFalse(totalPrice.isEmpty(), "Total price should be displayed");
    }
    
    @Test(priority = 5, description = "Test adding multiple products to cart")
    public void testAddMultipleProductsToCart() {
        LoggerUtil.info("Test: Add multiple products to cart");
        
        // Add first product
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        productPage.addToCart();
        productPage.acceptAlert();
        
        // Go back and add second product
        productPage.goToHome();
        homePage.selectProduct(Constants.PRODUCT_NOKIA_LUMIA);
        productPage.addToCart();
        productPage.acceptAlert();
        
        // Navigate to cart
        productPage.goToHome();
        homePage.clickCart();
        
        // Verify multiple items in cart
        int itemCount = cartPage.getCartItemCount();
        LoggerUtil.info("Cart item count: " + itemCount);
        Assert.assertTrue(itemCount >= 2, "Cart should have at least 2 items");
    }
}

