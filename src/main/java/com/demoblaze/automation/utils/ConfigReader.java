package com.demoblaze.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read configuration properties from config.properties file
 */
public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";
    
    // Load properties file
    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }
    
    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Get browser type from configuration
     * @return Browser name
     */
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    
    /**
     * Get application URL from configuration
     * @return Application URL
     */
    public static String getUrl() {
        return properties.getProperty("url");
    }
    
    /**
     * Get implicit wait time from configuration
     * @return Implicit wait time in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait", "10"));
    }
    
    /**
     * Get explicit wait time from configuration
     * @return Explicit wait time in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait", "20"));
    }
    
    /**
     * Get test data file path
     * @return Test data file path
     */
    public static String getTestDataPath() {
        return properties.getProperty("testdata.path", "src/test/resources/testdata.xlsx");
    }
}

