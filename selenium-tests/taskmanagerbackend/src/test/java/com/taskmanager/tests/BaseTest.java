package com.taskmanager.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    // change this if your app uses a different host/port
    private final String appUrl = "http://localhost:3000/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // optional: avoid system proxy if proxy interferes
        // options.addArguments("--no-proxy-server");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Wait for the dev server to be reachable before navigating
        waitForServer(appUrl, 30); // wait up to 30 seconds
        driver.get(appUrl);
    }

   
   
    
    @AfterMethod
    public void tearDown() {
        // Comment this out while debugging
        // if (driver != null) {
        //     driver.quit();
        // }
        
        // Optionally, add a pause to see results before closing
        try {
            Thread.sleep(5000); // waits 5 seconds before closing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    

    // --- helper: simple TCP check on host:port ---
    private void waitForServer(String url, int timeoutSeconds) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            int port = uri.getPort() == -1 ? (uri.getScheme().equals("https") ? 443 : 80) : uri.getPort();

            long deadline = System.currentTimeMillis() + timeoutSeconds * 1000L;
            while (System.currentTimeMillis() < deadline) {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(host, port), 2000);
                    // success
                    return;
                } catch (Exception ignored) {
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            // fall through
        }
        throw new RuntimeException("Timed out waiting for server: " + url);
    }
}
