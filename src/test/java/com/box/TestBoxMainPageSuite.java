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

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestBoxMainPageSuite {

    WebDriver driver;
    String driverPath = PropertyManager.getInstance().getDriverPath();
    String baseUrl = PropertyManager.getInstance().getURL();
    String filename = "test.txt";
    String rightEmail = PropertyManager.getInstance().getRightEmail();
    String rightPassword = PropertyManager.getInstance().getRightPassword();
    BoxLogin objLogin;
    BoxHomePage objHomePage;
    BoxMainPage objMainPage;
    String folderName;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=19201200");
        folderName = generateAWorld();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(baseUrl);
    }

    @Test
    public void testCreateFolderHomePage() {
        logInBoxWebsite();
        objMainPage = new BoxMainPage(driver);
        objMainPage.createNewFolder(folderName);
        Assertions.assertTrue(objMainPage.isElementInTheList(folderName));
    }

    @Test
    public void testUploadAFileHomePage() {
        String fileToUpload = filename;
        filename = getPathFromFilename("/" + fileToUpload);
        logInBoxWebsite();
        objMainPage = new BoxMainPage(driver);
        objMainPage.uploadAFile(filename);

        Assertions.assertTrue(objMainPage.isElementInTheList(fileToUpload));
    }

    @AfterEach
    public void testDown() {
        driver.close();
        driver.quit();
    }

    private String getPathFromFilename(String filename) {
        URL resource =  TestBoxMainPageSuite.class.getResource(filename);
        File file = new File(resource.getPath());
        return file.getAbsolutePath();
    }

    private String generateAWorld() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void logInBoxWebsite() {
        objLogin = new BoxLogin(driver);
        objHomePage = new BoxHomePage(driver);
        if (objHomePage.isLoginButtonAvailable()) {
            objHomePage.clickOnLoginButton();
        } else {
            if (objHomePage.isHamburguerMenuAvailable()) {
                objHomePage.clickOnHamburgerMenu();
                objHomePage.clickOnLoginButton();
            }
        }
        objLogin.loginToBoxWebsite(rightEmail, rightPassword);
    }
}
