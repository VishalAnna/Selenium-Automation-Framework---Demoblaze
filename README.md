# Demoblaze E-commerce Automation Framework

A Selenium WebDriver automation framework for testing the Demoblaze e-commerce website using Page Object Model design pattern with TestNG and multi-browser support.

## Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Coverage](#test-coverage)


## Overview

This framework tests the Demoblaze e-commerce website (https://www.demoblaze.com) covering:

- User authentication (Login/Sign Up)
- Product browsing and search
- Shopping cart management
- Checkout and order placement

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Language | Java | 11+ |
| Build Tool | Maven | 3.6+ |
| Automation Tool | Selenium WebDriver | 4.15.0 |
| Testing Framework | TestNG | 7.8.0 |
| Design Pattern | Page Object Model | - |
| Data Management | Apache POI | 5.2.5 |
| Driver Management | WebDriverManager | 5.6.2 |
| Logging | Log4j2 | 2.22.0 |

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

| Module | Test Cases | Description |
|--------|-----------|-------------|
| Login Tests | 5 | Valid/Invalid login, empty fields, logout |
| Sign Up Tests | 5 | New user registration, validations |
| Product Search Tests | 5 | Category selection, product details |
| Cart Tests | 5 | Add/remove products, price calculation |
| Checkout Tests | 5 | Complete checkout, form validation |



This project is created for educational  purposes.

