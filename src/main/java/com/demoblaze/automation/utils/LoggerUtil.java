package com.demoblaze.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Centralized logging utility using Log4j2
 */
public class LoggerUtil {
    private static Logger logger = LogManager.getLogger(LoggerUtil.class);
    
    /**
     * Log info message
     * @param message Message to log
     */
    public static void info(String message) {
        logger.info(message);
    }
    
    /**
     * Log debug message
     * @param message Message to log
     */
    public static void debug(String message) {
        logger.debug(message);
    }
    
    /**
     * Log warning message
     * @param message Message to log
     */
    public static void warn(String message) {
        logger.warn(message);
    }
    
    /**
     * Log error message
     * @param message Message to log
     */
    public static void error(String message) {
        logger.error(message);
    }
    
    /**
     * Log error message with throwable
     * @param message Message to log
     * @param throwable Exception to log
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}

