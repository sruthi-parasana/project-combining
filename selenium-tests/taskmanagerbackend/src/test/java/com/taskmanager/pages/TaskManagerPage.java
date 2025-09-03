
package com.taskmanager.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TaskManagerPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By welcomeH2 = By.xpath("//h2[contains(.,'Welcome')]");
    private By dashboardHeader = By.cssSelector(".dashboard-header h1");

    public TaskManagerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public boolean isUserLoggedIn(String username) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeH2));
            String text = driver.findElement(welcomeH2).getText();
            return text.contains(username);
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
                return true;
            } catch (Exception e2) {
                return false;
            }
        }
    }
}

