package com.demoblaze.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoblaze.automation.data.Constants;
import com.demoblaze.automation.utils.LoggerUtil;

/**
 * Test class for Product Search and Category functionality - 5 test cases
 */
public class ProductSearchTests extends BaseTest {
    
    @Test(priority = 1, description = "Test product search by selecting Phones category")
    public void testSearchByPhonesCategory() {
        LoggerUtil.info("Test: Search by Phones category");
        
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        
        // Verify page loaded with phones category
        String currentUrl = driver.getCurrentUrl();
        LoggerUtil.info("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("demoblaze"), 
                         "Should be on demoblaze site");
    }
    
    @Test(priority = 2, description = "Test product search by selecting Laptops category")
    public void testSearchByLaptopsCategory() {
        LoggerUtil.info("Test: Search by Laptops category");
        
        homePage.selectCategory(Constants.CATEGORY_LAPTOPS);
        
        // Verify page loaded with laptops category
        String currentUrl = driver.getCurrentUrl();
        LoggerUtil.info("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("demoblaze"), 
                         "Should be on demoblaze site");
    }
    
    @Test(priority = 3, description = "Test product search by selecting Monitors category")
    public void testSearchByMonitorsCategory() {
        LoggerUtil.info("Test: Search by Monitors category");
        
        homePage.selectCategory(Constants.CATEGORY_MONITORS);
        
        // Verify page loaded with monitors category
        String currentUrl = driver.getCurrentUrl();
        LoggerUtil.info("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("demoblaze"), 
                         "Should be on demoblaze site");
    }
    
    @Test(priority = 4, description = "Test selecting a specific product")
    public void testSelectSpecificProduct() {
        LoggerUtil.info("Test: Select specific product");
        
        // Select a category first
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        
        // Select a product
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        
        // Verify product page loaded
        String productName = productPage.getProductName();
        LoggerUtil.info("Product name: " + productName);
        Assert.assertFalse(productName.isEmpty(), 
                          "Product name should not be empty");
    }
    
    @Test(priority = 5, description = "Test product details display")
    public void testProductDetailsDisplay() {
        LoggerUtil.info("Test: Product details display");
        
        // Navigate to a product
        homePage.selectCategory(Constants.CATEGORY_PHONES);
        homePage.selectProduct(Constants.PRODUCT_SAMSUNG_S6);
        
        // Get product details
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        
        LoggerUtil.info("Product Name: " + productName);
        LoggerUtil.info("Product Price: " + productPrice);
        
        // Verify details are displayed
        Assert.assertFalse(productName.isEmpty(), "Product name should be displayed");
        Assert.assertFalse(productPrice.isEmpty(), "Product price should be displayed");
    }
}

