# DemoQA Automation Test Portfolio

This repository contains automated test suites designed and implemented by Filipe Preto Alves for the DemoQA website using Selenium WebDriver with Java, TestNG, and Log4j2.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [Test Suites](#test-suites)
- [Resources](#resources)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This repository showcases comprehensive automation tests for the DemoQA website, covering various functionalities to ensure stability and reliability.

## Project Structure

│   README.md
│   pom.xml
│
└───src
    └───main
    │   └───java
    │       └───com
    │           └───yourcompany
    │               └───automation
    │                   ├───pages
    │                   │   ├───HomePage.java
    │                   │   ├───LoginPage.java
    │                   │   └───... (other page objects)
    │                   │
    │                   ├───tests
    │                   │   ├───HomePageTest.java
    │                   │   ├───LoginTest.java
    │                   │   └───... (other test classes)
    │                   │
    │                   ├───utilities
    │                   │   ├───DriverFactory.java
    │                   │   ├───LoggerUtil.java
    │                   │   └───... (other utility classes)
    │                   │
    │                   └───config
    │                       ├───ConfigReader.java
    │                       └───... (other configuration-related classes)
    │
    └───resources
        │   log4j2.xml
        │   testng.xml
        │   ...
        │
        └───drivers
            ├───chromedriver.exe
            └───geckodriver.exe



## Setup

### Prerequisites

Ensure the following are installed on your machine:

- **Java Development Kit (JDK):** Required for Java development.
- **Maven:** Dependency management and build tool.
- **Chrome and Firefox browsers:** Needed for testing.

### WebDriver Setup

#### WebDriver Executables:

1. **Download WebDriver Executables:**
   - Download compatible versions of `chromedriver` and `geckodriver` for Chrome and Firefox browsers, respectively.

2. **Place Executables:**
   - Place these WebDriver executables in the `src/main/resources` directory of your project.

### Log4j2 Configuration

#### Log4j2 Configuration File:

1. **Customize Log4j2 Configuration:**
   - Locate and customize the `log4j2.xml` file located in `src/main/resources` according to your logging preferences.


Test Suites
TestNG Suite Configuration
The testng.xml file defines the test suite configuration for executing tests related to the HomePage and various elements sections of the DemoQA website.

testng.xml content:
xml
Copy code
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="All Tests" verbose="1">
    <!-- ... -->
    <!-- Test configurations for HomePageTest -->
    <!-- ... -->
    <test name="Elements Tests">
        <classes>
            <class name="com.demoqa.elements.ElementsTest"/>
            <class name="com.demoqa.elements.TextBoxTest"/>
            <class name="com.demoqa.elements.CheckBoxTest"/>
            <class name="com.demoqa.elements.RadioButtonTest"/>
            <class name="com.demoqa.elements.WebTablesTest"/>
            <class name="com.demoqa.elements.ButtonsTest"/>
            <!-- Additional test configurations can be added here -->
        </classes>
    </test>
</suite>
The suite configuration specifies different test classes (ElementsTest, TextBoxTest, CheckBoxTest, RadioButtonTest, WebTablesTest, ButtonsTest) for the elements section of the DemoQA website.

Resources
The resources folder contains WebDriver executables (chromedriver, geckodriver), Log4j2 configuration (log4j2.xml), and the TestNG suite configuration (testng.xml) for seamless test execution.

Usage
Execute the tests using the following command:

bash
Copy code
mvn clean test
This command triggers Maven to compile the code, execute the tests, and generate comprehensive test reports.

Contributing
Contributions are welcome! Please feel free to create pull requests for enhancements, bug fixes, or additional test cases to further improve the test coverage.
