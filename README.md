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

### Pre-requisites

Ensure the following are installed on your machine:

- **Java Development Kit (JDK):** Required for Java development.
- **Maven:** Dependency management and build tool.
- **Chrome, Firefox and Safari browsers:** Needed for testing.

### WebDriver Setup

#### WebDriver Executables:

1. **Download WebDriver Executables:**
   - Download compatible versions of chromedriver, geckodriver, and safaridriver for Chrome, Firefox, and Safari browsers, respectively.

2. **Place Executables:**
   - Place these WebDriver executables in the `src/main/resources` directory of your project.

### Log4j2 Configuration

#### Log4j2 Configuration File:

1. **Customize Log4j2 Configuration:**
   - Locate and customize the `log4j2.xml` file located in `src/main/resources` according to your logging preferences.


## Test Suites

TestNG Suite Configuration

The testng.xml file defines the test suite configuration for executing tests of the DemoQA website using different parameters.

The suite configuration specifies different test classes for the differents elements of the DemoQA website.

## Resources

The resources folder contains WebDriver executables (chromedriver, geckodriver), Log4j2 configuration (log4j2.xml), and the TestNG suite configuration (testng.xml) for seamless test execution.

## Usage

Execute the tests using the following command:

`mvn clean test`

This command triggers Maven to compile the code, execute the tests, and generate comprehensive test reports.

## Contributing

Contributions are welcome! Please feel free to create pull requests for enhancements, bug fixes, or additional test cases to further improve the test coverage.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see the [LICENSE](LICENSE) file for details.
