# Demoblaze E-commerce Automation Framework

This Selenium automation framework implements the Page Object Model design pattern with TestNG for test execution and reporting. It supports data-driven testing with Excel, configuration through properties files, and cross-browser testing.

## Table of Contents

- Overview
- Technology Stack
- Features]
- Prerequisites
- Setup
- Configuration
- Running Tests
- Test Coverage


## Overview

This framework tests the Demoblaze e-commerce website (https://www.demoblaze.com) covering:

- User authentication (Login/Sign Up)
- Product browsing and search
- Shopping cart management
- Checkout and order placement

## Technology Stack

1.Language  --Java  11+ 
2.Build Tool -- Maven  3.6+ 
3.utomation Tool -- Selenium WebDriver  4.15.0 
4.Testing Framework -- TestNG  7.8.0 
5.Design Pattern -- Page Object Model  
6.Data Management -- Apache POI  5.2.5 
7.Driver Management -- WebDriverManager  5.6.2 
8.Logging -- Log4j2  2.22.0 

## Features

- Page Object Model (POM) design pattern
- TestNG integration for test execution and reporting
- Data-driven testing with Excel support
- Multi-browser support (Chrome, Firefox, headless)
- Parallel test execution
- Comprehensive logging with Log4j2
- Automatic screenshot capture for failed tests
- Retry mechanism for flaky tests
- Explicit wait strategies for element synchronization
- Thread-safe WebDriver management

## Prerequisites

- Java JDK 11 or higher
- Apache Maven 3.6+
- Google Chrome (latest version)
- Mozilla Firefox (optional)

## Setup

1. **Clone or download the project**

2. **Navigate to project directory**
   ```bash
   cd demoblaze
   ```

3. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

## Configuration

Edit `src/test/resources/config.properties`:

```properties
# Browser Configuration
browser=chrome                        # Options: chrome, firefox, chrome-headless

# Application URL
url=https://www.demoblaze.com/index.html

# Wait Times (in seconds)
implicit.wait=10
explicit.wait=20

# File Paths
screenshot.path=output/screenshots/
report.path=output/reports/
log.path=output/logs/
testdata.path=src/test/resources/testdata.xlsx
```

## Running Tests

**Run all tests:**
```bash
mvn clean test
```

**Run with specific browser:**
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=chrome-headless
```

**Run specific test class:**
```bash
mvn test -Dtest=LoginTests
mvn test -Dtest=CartTests
```

**Run via TestNG XML:**
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Coverage

Total: 25+ test cases

1. Login Tests -- 5 Test -- Valid/Invalid login, empty fields, logout 
2. Sign Up Tests -- 5 Test-- New user registration, validations 
3. Product Search Tests -- 5 Test -- Category selection, product details 
4. Cart Tests -- 5 Test-- Add/remove products, price calculation 
5. Checkout Tests -- 5 Test-- Complete checkout, form validation 



