package com.box;

import com.pages.BoxHomePage;
import com.pages.BoxLogin;
import com.pages.BoxMainPage;
import com.utilities.PropertyManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBoxLoginSuite {

    WebDriver driver;
    String driverPath = PropertyManager.getInstance().getDriverPath();
    String baseUrl = PropertyManager.getInstance().getURL();
    String filename = "test.txt";
    String rightEmail = PropertyManager.getInstance().getRightEmail();
    String rightPassword = PropertyManager.getInstance().getRightPassword();
    String wrongEmail = PropertyManager.getInstance().getWrongEmail();
    String wrongPassword = PropertyManager.getInstance().getWrongPassword();
    BoxLogin objLogin;
    BoxHomePage objHomePage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=19201200");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(baseUrl);
    }

    @Test
    public void testLoginPageInvalidLoginCredentials() {
        String expectedLoginTitleValue = "Box | Login";
        String expectedInvalidLoginMessage = "Invalid login credentials";
        objLogin = new BoxLogin(driver);
        clickOnLoginOption();

        objLogin.loginToBoxWebsite(wrongEmail, wrongPassword);

        assertEquals(expectedLoginTitleValue.toLowerCase(), objLogin.getLoginPageTitle().toLowerCase());
        assertEquals(expectedInvalidLoginMessage.toLowerCase(), objLogin.getInvalidLoginText().toLowerCase());
    }

    @Test
    public void testLoginPageValidLoginCredentials() {
        String expectedTitleValueAfterLogin = "All Files | Powered by Box";
        objLogin = new BoxLogin(driver);
        clickOnLoginOption();

        objLogin.loginToBoxWebsite(rightEmail, rightPassword);

        assertEquals(expectedTitleValueAfterLogin.toLowerCase(), objLogin.getLoginPageTitle().toLowerCase());
    }

    @Test
    public void testLogoutOption() {
        String expectedLoginTitleValue = "Box | Login";
        objLogin = new BoxLogin(driver);
        clickOnLoginOption();
        objLogin.loginToBoxWebsite(rightEmail, rightPassword);
        objLogin.logoutOfBoxWebsite();
        assertEquals(expectedLoginTitleValue.toLowerCase(), objLogin.getLoginPageTitle().toLowerCase());
    }

    @AfterEach
    public void testDown() {
        driver.close();
        driver.quit();
    }

    private void clickOnLoginOption() {
        objHomePage = new BoxHomePage(driver);
        if (objHomePage.isLoginButtonAvailable()) {
            objHomePage.clickOnLoginButton();
        } else {
            if (objHomePage.isHamburguerMenuAvailable()) {
                objHomePage.clickOnHamburgerMenu();
                objHomePage.clickOnLoginButton();
            }
        }
    }

}
