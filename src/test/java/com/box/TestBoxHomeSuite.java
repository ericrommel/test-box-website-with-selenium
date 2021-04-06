package com.box;

import com.pages.BoxHomePage;
import com.pages.BoxLogin;
import com.pages.BoxMainPage;
import com.utilities.PropertyManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBoxHomeSuite {
    String driverPath = PropertyManager.getInstance().getDriverPath();
    String baseUrl = PropertyManager.getInstance().getURL();
    String expectedHomeTitle = "Box — Secure Cloud Content Management, Workflow, and Collaboration";
    BoxHomePage objHomePage;
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=19201200");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(baseUrl);
        objHomePage = new BoxHomePage(driver);
    }

    @Test
    public void testHomePage() {
        Assertions.assertEquals(expectedHomeTitle.toLowerCase(), objHomePage.getHomePageTitle().toLowerCase());
    }

    @AfterEach
    public void testDown() {
        // Close Driver
        driver.close();

        // Quit Driver
        driver.quit();
    }
}
