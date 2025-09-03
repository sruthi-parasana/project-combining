package com.taskmanager.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginAfterRegistration() {
        // Step 1: Register a new user first
        String username = "user" + System.currentTimeMillis();
        String password = "Pass@123";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Go to Register page
        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".link-btn")));
        registerLink.click();

        // Fill registration form
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys(password);

        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".auth-btn")));
        registerButton.click();

        // Accept alert after registration
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // Now on login page: enter credentials
        usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.sendKeys(username);

        passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys(password);

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".auth-btn")));
        loginButton.click();

        // Verify login success: check localStorage
        Boolean loggedIn = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return localStorage.getItem('registeredUser') !== null;"
        );

        Assert.assertTrue(loggedIn, "User should be logged in and localStorage set");

        // Optional: pause for 3 seconds to see result
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
