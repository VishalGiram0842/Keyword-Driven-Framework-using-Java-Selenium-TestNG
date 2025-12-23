package com.keyworddriven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object Model for ACME Demo App Login Page
 * Handles all login-related interactions and verifications
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By signInButton = By.id("log-in");
    private By rememberMeCheckbox = By.xpath("//input[@type='checkbox']");
    private By loginForm = By.xpath("//div[contains(text(), 'Login Form')]");
    private By appLogo = By.xpath("//a[@href='/index.html']");
    
    /**
     * Constructor to initialize WebDriver and WebDriverWait
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Navigate to the ACME Demo App login page
     */
    public void navigateToLoginPage() {
        driver.navigate().to("https://demo.applitools.com/");
    }
    
    /**
     * Verify that the login page is displayed
     * @return true if login form is visible
     */
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Enter username in the username field
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }
    
    /**
     * Enter password in the password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    
    /**
     * Click the Sign In button
     */
    public void clickSignInButton() {
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInBtn.click();
    }
    
    /**
     * Perform login with provided credentials
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
    }
    
    /**
     * Check the Remember Me checkbox
     */
    public void checkRememberMe() {
        WebElement rememberCheckbox = wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (!rememberCheckbox.isSelected()) {
            rememberCheckbox.click();
        }
    }
    
    /**
     * Uncheck the Remember Me checkbox
     */
    public void uncheckRememberMe() {
        WebElement rememberCheckbox = wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (rememberCheckbox.isSelected()) {
            rememberCheckbox.click();
        }
    }
    
    /**
     * Verify if Remember Me checkbox is checked
     * @return true if checkbox is selected
     */
    public boolean isRememberMeChecked() {
        WebElement rememberCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(rememberMeCheckbox));
        return rememberCheckbox.isSelected();
    }
    
    /**
     * Get the username field value
     * @return Username value
     */
    public String getUsernameValue() {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        return usernameElement.getAttribute("value");
    }
    
    /**
     * Get the password field value
     * @return Password value
     */
    public String getPasswordValue() {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        return passwordElement.getAttribute("value");
    }
    
    /**
     * Verify if Sign In button is enabled
     * @return true if button is enabled
     */
    public boolean isSignInButtonEnabled() {
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
        return signInBtn.isEnabled();
    }
}
