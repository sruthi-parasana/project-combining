package com.taskmanager.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTest extends BaseTest {

    @Test
    public void testUserRegistration() {
        String username = "user" + System.currentTimeMillis();
        String password = "Pass@123";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Go to Register page
        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".link-btn")));
        registerLink.click();

        // Fill the registration form
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys(password);

        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".auth-btn")));
        registerButton.click();

        // Accept JS alert after registration
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // Verify localStorage contains registeredUser
        String storedUser = (String) ((JavascriptExecutor) driver).executeScript(
                "return localStorage.getItem('registeredUser');"
        );

        Assert.assertNotNull(storedUser, "registeredUser should exist in localStorage");
        Assert.assertTrue(storedUser.contains(username), "LocalStorage should contain the correct username");

        // Optional: pause for 3 seconds to see result
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
