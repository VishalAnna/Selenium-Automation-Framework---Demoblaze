package com.demoblaze.automation.data;

/**
 * Constants class to store constant values used across the framework
 */
public class Constants {
    // URLs
    public static final String BASE_URL = "https://www.demoblaze.com/index.html";
    
    // Timeouts
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    // Test Data
    public static final String TEST_DATA_FILE = "src/test/resources/testdata.xlsx";
    public static final String CONFIG_FILE = "src/test/resources/config.properties";
    
    // Alert Messages
    public static final String SIGN_UP_SUCCESS = "Sign up successful.";
    public static final String USER_EXISTS = "This user already exist.";
    public static final String EMPTY_FIELDS = "Please fill out Username and Password.";
    public static final String WRONG_PASSWORD = "Wrong password.";
    public static final String USER_NOT_EXIST = "User does not exist.";
    public static final String PRODUCT_ADDED = "Product added";
    
    // Categories
    public static final String CATEGORY_PHONES = "Phones";
    public static final String CATEGORY_LAPTOPS = "Laptops";
    public static final String CATEGORY_MONITORS = "Monitors";
    
    // Common Products (for testing)
    public static final String PRODUCT_SAMSUNG_S6 = "Samsung galaxy s6";
    public static final String PRODUCT_NOKIA_LUMIA = "Nokia lumia 1520";
    public static final String PRODUCT_NEXUS_6 = "Nexus 6";
    public static final String PRODUCT_SAMSUNG_S7 = "Samsung galaxy s7";
    public static final String PRODUCT_SONY_VAIO = "Sony vaio i5";
    public static final String PRODUCT_MACBOOK_AIR = "MacBook air";
}

