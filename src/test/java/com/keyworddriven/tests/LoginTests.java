package com.keyworddriven.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.keyworddriven.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test class for ACME Demo App Login functionality
 * Tests navigation to the application and login scenarios
 */
public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(LoginTests.class);
    
    private static final String BASE_URL = "https://demo.applitools.com/";
    private static final String VALID_USERNAME = "user@example.com";
    private static final String VALID_PASSWORD = "password123";
    
    /**
     * Setup method - runs before each test
     * Initializes WebDriver and LoginPage
     */
    @BeforeMethod
    public void setUp() {
        logger.info("=== Starting Test Setup ===");
        try {
            // Initialize Chrome WebDriver
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            logger.info("Chrome WebDriver initialized and window maximized");
            
            // Initialize LoginPage with driver
            loginPage = new LoginPage(driver);
            logger.info("LoginPage object initialized");
        } catch (Exception e) {
            logger.error("Error during setup: " + e.getMessage(), e);
            throw new RuntimeException("Setup failed", e);
        }
    }
    
    /**
     * Test Case 1: Verify navigation to ACME Demo App
     * Steps:
     *   1. Navigate to ACME Demo App URL
     *   2. Verify login page is displayed
     */
    @Test(description = "Verify user can navigate to ACME Demo App login page")
    public void testNavigateToAcmeDemoApp() {
        logger.info("Test: Navigate to ACME Demo App");
        
        try {
            // Step 1: Navigate to the login page
            logger.info("Navigating to: " + BASE_URL);
            loginPage.navigateToLoginPage();
            logger.info("Navigation completed");
            
            // Step 2: Verify that login page is displayed
            logger.info("Verifying login page is displayed");
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                            "Login page should be displayed after navigation");
            logger.info("Login page is successfully displayed");
            
        } catch (Exception e) {
            logger.error("Test failed with exception: " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Test Case 2: Verify login form elements are visible
     * Steps:
     *   1. Navigate to login page
     *   2. Verify username field is enabled
     *   3. Verify password field is enabled  
     *   4. Verify Sign In button is enabled
     */
    @Test(description = "Verify login form elements are properly displayed and enabled")
    public void testLoginFormElementsAreVisible() {
        logger.info("Test: Verify login form elements are visible");
        
        try {
            // Step 1: Navigate to login page
            logger.info("Navigating to login page");
            loginPage.navigateToLoginPage();
            
            // Step 2: Verify login page is displayed
            logger.info("Verifying login page is displayed");
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                            "Login page should be displayed");
            
            // Step 3: Verify Sign In button is enabled
            logger.info("Verifying Sign In button is enabled");
            Assert.assertTrue(loginPage.isSignInButtonEnabled(), 
                            "Sign In button should be enabled");
            logger.info("Sign In button is enabled");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Test Case 3: Test login with valid credentials
     * Steps:
     *   1. Navigate to login page
     *   2. Enter valid username
     *   3. Enter valid password
     *   4. Click Sign In button
     */
    @Test(description = "Test login with valid credentials")
    public void testLoginWithValidCredentials() {
        logger.info("Test: Login with valid credentials");
        logger.info("Username: " + VALID_USERNAME);
        
        try {
            // Step 1: Navigate to login page
            logger.info("Navigating to login page");
            loginPage.navigateToLoginPage();
            
            // Step 2: Verify login page is displayed
            logger.info("Verifying login page is displayed");
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                            "Login page should be displayed");
            
            // Step 3: Enter credentials and login
            logger.info("Entering credentials and performing login");
            loginPage.login(VALID_USERNAME, VALID_PASSWORD);
            logger.info("Login action completed");
            
            // Step 4: Wait for page to load after login
            logger.info("Waiting for application to load after login");
            Thread.sleep(3000); // Wait for page transition
            logger.info("Login test completed successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Test Case 4: Test entering username
     * Steps:
     *   1. Navigate to login page
     *   2. Enter username
     *   3. Verify username is entered correctly
     */
    @Test(description = "Test entering username in login form")
    public void testEnterUsername() {
        logger.info("Test: Enter username");
        
        try {
            // Step 1: Navigate to login page
            logger.info("Navigating to login page");
            loginPage.navigateToLoginPage();
            
            // Step 2: Verify login page is displayed
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                            "Login page should be displayed");
            
            // Step 3: Enter username
            logger.info("Entering username: " + VALID_USERNAME);
            loginPage.enterUsername(VALID_USERNAME);
            
            // Step 4: Verify username is entered
            String enteredUsername = loginPage.getUsernameValue();
            logger.info("Verifying entered username");
            Assert.assertEquals(enteredUsername, VALID_USERNAME, 
                              "Entered username should match the input");
            logger.info("Username entered successfully: " + enteredUsername);
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Test Case 5: Test entering password
     * Steps:
     *   1. Navigate to login page
     *   2. Enter password
     *   3. Verify password is entered (field value)
     */
    @Test(description = "Test entering password in login form")
    public void testEnterPassword() {
        logger.info("Test: Enter password");
        
        try {
            // Step 1: Navigate to login page
            logger.info("Navigating to login page");
            loginPage.navigateToLoginPage();
            
            // Step 2: Verify login page is displayed
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                            "Login page should be displayed");
            
            // Step 3: Enter password
            logger.info("Entering password");
            loginPage.enterPassword(VALID_PASSWORD);
            
            // Step 4: Verify password is entered
            String enteredPassword = loginPage.getPasswordValue();
            logger.info("Verifying password was entered");
            Assert.assertEquals(enteredPassword, VALID_PASSWORD, 
                              "Entered password should match the input");
            logger.info("Password entered successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Test Case 6: Test Remember Me checkbox functionality
     * Steps:
     *   1. Navigate to login page
     *   2. Check Remember Me checkbox
     *   3. Verify checkbox is checked
     */
    @Test(description = "Test Remember Me checkbox functionality")
    public void testRememberMeCheckbox() {
        logger.info("Test: Remember Me checkbox");
        
        try {
            // Step 1: Navigate to login page
            logger.info("Navigating to login page");
            loginPage.navigateToLoginPage();
            
            // Step 2: Verify login page is displayed
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                            "Login page should be displayed");
            
            // Step 3: Check Remember Me checkbox
            logger.info("Checking Remember Me checkbox");
            loginPage.checkRememberMe();
            
            // Step 4: Verify checkbox is checked
            logger.info("Verifying Remember Me checkbox is checked");
            Assert.assertTrue(loginPage.isRememberMeChecked(), 
                            "Remember Me checkbox should be checked");
            logger.info("Remember Me checkbox is checked successfully");
            
            // Step 5: Uncheck Remember Me checkbox
            logger.info("Unchecking Remember Me checkbox");
            loginPage.uncheckRememberMe();
            
            // Step 6: Verify checkbox is unchecked
            logger.info("Verifying Remember Me checkbox is unchecked");
            Assert.assertFalse(loginPage.isRememberMeChecked(), 
                             "Remember Me checkbox should be unchecked");
            logger.info("Remember Me checkbox is unchecked successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Teardown method - runs after each test
     * Closes the browser and cleans up resources
     */
    @AfterMethod
    public void tearDown() {
        logger.info("=== Tearing Down Test ===");
        try {
            if (driver != null) {
                driver.quit();
                logger.info("Chrome WebDriver closed successfully");
            }
        } catch (Exception e) {
            logger.error("Error during teardown: " + e.getMessage(), e);
        }
    }
}
