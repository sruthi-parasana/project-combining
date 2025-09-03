
package com.taskmanager.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By header = By.tagName("h1");
    private By username = By.name("username");
    private By password = By.name("password");
    private By registerButton = By.cssSelector(".auth-btn");
    private By loginLink = By.cssSelector(".link-btn");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void register(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(registerButton).click();

        // Accept JS alert
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }
}
