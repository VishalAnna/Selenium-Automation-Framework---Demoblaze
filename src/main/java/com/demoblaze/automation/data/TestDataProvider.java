package com.demoblaze.automation.data;

import org.testng.annotations.DataProvider;
import com.demoblaze.automation.utils.ConfigReader;
import com.demoblaze.automation.utils.ExcelReader;
import java.io.File;

/**
 * TestNG Data Provider class to provide test data from Excel
 */
public class TestDataProvider {
    private static final String TEST_DATA_PATH = ConfigReader.getTestDataPath();
    
    /**
     * Login test data provider
     * @return 2D Object array with login test data
     */
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        // Check if file exists
        File file = new File(TEST_DATA_PATH);
        if (!file.exists()) {
            // Return default test data if Excel file doesn't exist
            return new Object[][] {
                {"testuser", "Test@123", "success"},
                {"invaliduser", "Test@123", "fail"},
                {"testuser", "wrongpass", "fail"}
            };
        }
        
        ExcelReader reader = new ExcelReader(TEST_DATA_PATH);
        Object[][] data = reader.getTestData("LoginData");
        reader.close();
        return data;
    }
    
    /**
     * Sign up test data provider
     * @return 2D Object array with sign up test data
     */
    @DataProvider(name = "signUpData")
    public static Object[][] getSignUpData() {
        // Check if file exists
        File file = new File(TEST_DATA_PATH);
        if (!file.exists()) {
            // Return default test data if Excel file doesn't exist
            return new Object[][] {
                {"newuser", "Test@123", "success"},
                {"existinguser", "Test@123", "fail"}
            };
        }
        
        ExcelReader reader = new ExcelReader(TEST_DATA_PATH);
        Object[][] data = reader.getTestData("SignUpData");
        reader.close();
        return data;
    }
    
    /**
     * Product search data provider
     * @return 2D Object array with product data
     */
    @DataProvider(name = "productData")
    public static Object[][] getProductData() {
        // Check if file exists
        File file = new File(TEST_DATA_PATH);
        if (!file.exists()) {
            // Return default test data if Excel file doesn't exist
            return new Object[][] {
                {"Samsung galaxy s6", "Phones"},
                {"Sony vaio i5", "Laptops"},
                {"Apple monitor 24", "Monitors"}
            };
        }
        
        ExcelReader reader = new ExcelReader(TEST_DATA_PATH);
        Object[][] data = reader.getTestData("ProductData");
        reader.close();
        return data;
    }
    
    /**
     * Checkout data provider
     * @return 2D Object array with checkout data
     */
    @DataProvider(name = "checkoutData")
    public static Object[][] getCheckoutData() {
        // Check if file exists
        File file = new File(TEST_DATA_PATH);
        if (!file.exists()) {
            // Return default test data if Excel file doesn't exist
            return new Object[][] {
                {"John Doe", "USA", "New York", "1234567890123456", "12", "2025"},
                {"Jane Smith", "UK", "London", "9876543210987654", "06", "2026"}
            };
        }
        
        ExcelReader reader = new ExcelReader(TEST_DATA_PATH);
        Object[][] data = reader.getTestData("CheckoutData");
        reader.close();
        return data;
    }
}

