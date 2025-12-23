# Keyword-Driven Test Automation Framework using Java, Selenium, and TestNG

## Overview

This is a robust, scalable **Keyword-Driven Test Automation Framework** built with Java, Selenium WebDriver, and TestNG. The framework is designed to enable non-technical users to write and execute automated tests using simple keyword-driven approach, while maintaining code quality and reusability.

## Key Features

- **Keyword-Driven Approach**: Write test cases using simple keywords without deep programming knowledge
- **Page Object Model (POM)**: Organized structure for better maintainability and scalability
- **Extent Reports**: Comprehensive HTML reports with screenshots and detailed logs
- **Log4j2 Logging**: Detailed logging for debugging and test execution tracking
- **TestNG Integration**: Powerful test execution framework with parallel execution support
- **Cross-Browser Support**: Compatible with Chrome, Firefox, Edge, and Safari
- **Maven Build Tool**: Easy dependency management and project build
- **Modular Architecture**: Reusable components and base classes

## Project Structure

```
Keyword-Driven-Framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/keyworddriven/
│   │   │       ├── base/              # Base classes and utilities
│   │   │       ├── pages/             # Page Object Model classes
│   │   │       ├── keywords/          # Keyword implementation classes
│   │   │       └── utils/             # Utility classes
│   │   └── resources/
│   │       ├── log4j2.properties      # Logging configuration
│   │       └── config.properties      # Application configuration
│   └── test/
│       ├── java/
│       │   └── com/keyworddriven/tests/   # Test classes
│       └── resources/
│           └── testdata/              # Test data files
├── pom.xml                            # Maven configuration
├── testng.xml                         # TestNG suite configuration
└── README.md                          # This file
```

## Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: 3.6.0 or higher
- **Git**: For version control
- **IDE**: Eclipse, IntelliJ IDEA, or Visual Studio Code

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/VishalGiram0842/Keyword-Driven-Framework-using-Java-Selenium-TestNG.git
cd Keyword-Driven-Framework-using-Java-Selenium-TestNG
```

### 2. Install Dependencies

Using Maven, all dependencies will be automatically downloaded:

```bash
mvn clean install
```

This will download:
- Selenium WebDriver 4.15.0
- TestNG 7.9.0
- Extent Reports 5.1.1
- Log4j 2.21.0
- And other supporting libraries

### 3. Configure WebDriver

Download WebDriver for your browser and place it in your system PATH or specify the driver path in the framework.

## Technology Stack

| Technology | Version | Purpose |
|-----------|---------|----------|
| Java | 11+ | Core programming language |
| Selenium WebDriver | 4.15.0 | Browser automation |
| TestNG | 7.9.0 | Test execution framework |
| Maven | 3.6.0+ | Build and dependency management |
| Log4j2 | 2.21.0 | Logging framework |
| Extent Reports | 5.1.1 | Test reporting |

## Dependencies

### Core Dependencies

```xml
<!-- Selenium WebDriver -->
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.15.0</version>
</dependency>

<!-- TestNG -->
<dependency>
  <groupId>org.testng</groupId>
  <artifactId>testng</artifactId>
  <version>7.9.0</version>
  <scope>test</scope>
</dependency>

<!-- Extent Reports -->
<dependency>
  <groupId>com.aventstack</groupId>
  <artifactId>extentreports</artifactId>
  <version>5.1.1</version>
</dependency>

<!-- Log4j2 -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-api</artifactId>
  <version>2.21.0</version>
</dependency>
```

## Framework Architecture

### Base Classes

- **BaseTest**: Foundation class for all test classes
  - Browser initialization and teardown
  - Report initialization
  - Screenshot capture on failure
  - Test configuration management

- **BasePage**: Parent class for all Page Object classes
  - Common element locators and methods
  - Explicit wait handling
  - Element interaction methods

### Keyword Implementation

Keywords are simple methods that encapsulate specific actions:

- **Navigation Keywords**: openURL(), navigateBack(), navigateForward()
- **User Interaction Keywords**: click(), sendKeys(), selectDropdown()
- **Verification Keywords**: verifyElementPresent(), verifyText(), verifyAttribute()
- **Wait Keywords**: waitForElementVisible(), waitForElementClickable()

## Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run Tests in Parallel

Modify `testng.xml` to set parallel execution:

```xml
<suite name="Keyword Driven Framework Suite" verbose="2" thread-count="2" parallel="tests">
```

### Run with Maven Profiles

```bash
mvn clean test -Psmoke    # Run smoke tests
mvn clean test -Pregression # Run regression tests
```

## Test Execution Flow

1. **Test Initialization**
   - Browser launched
   - Extent Report initialized
   - Logger configured

2. **Test Execution**
   - Keywords executed in sequence
   - Actions performed on web elements
   - Logs recorded at each step
   - Screenshots captured on failures

3. **Test Teardown**
   - Browser closed
   - Report finalized
   - Results published

## Logging Configuration

Logging is configured via `log4j2.properties` in the resources folder:

```properties
# Console appender for immediate visibility
appender.console.type=Console
appender.console.name=ConsoleAppender
appender.console.layout.type=PatternLayout
appender.console.layout.pattern=[%d{yyyy-MM-dd HH:mm:ss}] [%-5p] [%c{1}] - %m%n

# File appender for persistent logs
appender.file.type=File
appender.file.name=FileAppender
appender.file.fileName=logs/automation.log
```

## Report Generation

Extent Reports are automatically generated in the `test-output` directory after test execution:

```
test-output/
├── ExtentReport.html      # Main HTML report
└── Screenshots/           # Screenshot directory
    ├── failure-001.png
    ├── failure-002.png
    └── ...
```

Reports include:
- Test execution summary
- Pass/Fail statistics
- Test duration
- Screenshots on failures
- Detailed logs

## Writing Test Cases

### Example Test Case Structure

```java
public class LoginTests extends BaseTest {

    @BeforeMethod
    public void setUp() {
        initializeDriver();
        initializeReport("Login Test Suite");
    }

    @Test(description = "Verify successful login")
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername("validuser");
        loginPage.enterPassword("validpass");
        loginPage.clickLogin();
        
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");
        test.pass("User logged in successfully");
    }

    @AfterMethod
    public void tearDown() {
        closeDriver();
        extent.flush();
    }
}
```

## Best Practices

1. **Maintainability**
   - Use Page Object Model for page elements
   - Keep keywords simple and single-purpose
   - Use meaningful names for methods and variables

2. **Reliability**
   - Use explicit waits instead of hard waits
   - Handle exceptions gracefully
   - Use try-catch blocks for critical operations

3. **Scalability**
   - Create reusable keyword libraries
   - Implement data-driven testing with TestNG parameters
   - Use base classes for common functionality

4. **Reporting**
   - Log every action with appropriate levels
   - Capture screenshots on failures
   - Include meaningful assertions in tests

5. **Version Control**
   - Commit frequently with meaningful messages
   - Maintain a clean git history
   - Document significant changes

## Troubleshooting

### Common Issues

| Issue | Solution |
|-------|----------|
| Driver not found | Ensure WebDriver is in PATH or configure driver path in code |
| Element not found | Check element locators, use explicit waits |
| Report not generated | Verify Extent Report initialization in BaseTest |
| Tests running in parallel fail | Check for thread safety in shared resources |
| Timeout errors | Increase wait timeout values in configuration |

## CI/CD Integration

### GitHub Actions Example

```yaml
name: Test Automation
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up Java
        uses: actions/setup-java@v2
        with:
          java-version: '11'
      - name: Run tests
        run: mvn clean test
      - name: Publish reports
        uses: actions/upload-artifact@v2
        with:
          name: ExtentReports
          path: test-output/
```

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or suggestions, please create an issue in the GitHub repository.

## Author

**Vishal Giram**
- GitHub: [@VishalGiram0842](https://github.com/VishalGiram0842)
- Email: vishal.giram@example.com

## Changelog

### Version 1.0.0 (December 2025)
- Initial framework setup
- Base test classes and utilities
- Extent Reports integration
- Log4j2 logging configuration
- TestNG suite configuration
- Basic documentation

## Future Enhancements

- [ ] Implement Database testing capabilities
- [ ] Add API testing integration with REST Assured
- [ ] Parallel execution optimization
- [ ] Performance testing framework
- [ ] Mobile testing support with Appium
- [ ] Cloud integration (BrowserStack, Sauce Labs)
- [ ] Advanced reporting features

---

**Last Updated**: December 23, 2025

**For more information, visit the [GitHub Wiki](https://github.com/VishalGiram0842/Keyword-Driven-Framework-using-Java-Selenium-TestNG/wiki)**
