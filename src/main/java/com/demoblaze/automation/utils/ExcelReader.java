package com.demoblaze.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class to read test data from Excel files using Apache POI
 */
public class ExcelReader {
    private String filePath;
    private Workbook workbook;
    
    /**
     * Constructor to initialize ExcelReader
     * @param filePath Path to Excel file
     */
    public ExcelReader(String filePath) {
        this.filePath = filePath;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            LoggerUtil.error("Failed to load Excel file: " + filePath, e);
            throw new RuntimeException("Failed to load Excel file: " + filePath);
        }
    }
    
    /**
     * Get row count from a sheet (excluding header)
     * @param sheetName Name of the sheet
     * @return Number of rows
     */
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            LoggerUtil.error("Sheet not found: " + sheetName);
            return 0;
        }
        return sheet.getLastRowNum();
    }
    
    /**
     * Get column count from a sheet
     * @param sheetName Name of the sheet
     * @return Number of columns
     */
    public int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            LoggerUtil.error("Sheet not found: " + sheetName);
            return 0;
        }
        return sheet.getRow(0).getLastCellNum();
    }
    
    /**
     * Get cell data as String
     * @param sheetName Name of the sheet
     * @param rowNum Row number
     * @param colNum Column number
     * @return Cell data as String
     */
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            LoggerUtil.error("Sheet not found: " + sheetName);
            return "";
        }
        
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }
        
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }
        
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }
    
    /**
     * Get all test data from a sheet as 2D array
     * @param sheetName Name of the sheet
     * @return 2D Object array with test data
     */
    public Object[][] getTestData(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            LoggerUtil.error("Sheet not found: " + sheetName);
            return new Object[0][0];
        }
        
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        
        Object[][] data = new Object[rowCount][colCount];
        
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    DataFormatter formatter = new DataFormatter();
                    data[i-1][j] = formatter.formatCellValue(cell);
                }
            }
        }
        
        LoggerUtil.info("Loaded test data from sheet: " + sheetName + 
                       " (Rows: " + rowCount + ", Cols: " + colCount + ")");
        return data;
    }
    
    /**
     * Close workbook
     */
    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            LoggerUtil.error("Failed to close workbook", e);
        }
    }
}

