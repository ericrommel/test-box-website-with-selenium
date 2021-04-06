package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BoxLogin {
    WebDriver driver;

    @FindBy(name = "login")
    WebElement loginTxt;

    @FindBy(css = "[data-resin-target=\"logout\"]")
    WebElement logoutBtn;

    @FindBy(className = "ProfileButton-avatar")
    WebElement profileAvatar;

    @FindBy(name = "password")
    WebElement passwordTxt;

    @FindBy(id = "login-submit")
    WebElement nextBtn;

    @FindBy(id = "login-submit-password")
    WebElement logInBtn;

    @FindBy(className = "form-error")
    WebElement invalidLogin;

    public BoxLogin(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory, this);
    }

    public void setLoginTxt(String strEmail) {
        loginTxt.sendKeys(strEmail);
    }

    public void setPasswordTxt(String strPassword) {
        passwordTxt.sendKeys(strPassword);
    }

    public void clickOnNextButton() {
        nextBtn.click();
    }

    public void clickOnLogInButton() {
        logInBtn.click();
    }

    public void clickOnProfileAvatarButton() {
        profileAvatar.click();
    }

    public void clickOnLogOutButton() {
        logoutBtn.click();
    }

    public String getLoginPageTitle() {
            return driver.getTitle();
    }

    public boolean isInvalidLoginTextAvailable() {
        return invalidLogin.isDisplayed();
    }

    public String getInvalidLoginText() {
        return invalidLogin.getText();
    }

    public void waitAFewSeconds(int timeToWaitInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeToWaitInSeconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    public void loginToBoxWebsite(String strEmail, String strPassword) {
        this.setLoginTxt(strEmail);
        this.clickOnNextButton();
        waitAFewSeconds(2);
        // ToDo: THere is an issue here: Captcha. I figured out that if we have another instance connected, the captcha won't be showed
        this.setPasswordTxt(strPassword);
        this.clickOnLogInButton();
    }

    public void logoutOfBoxWebsite() {
        waitAFewSeconds(2);
        this.clickOnProfileAvatarButton();
        waitAFewSeconds(2);
        this.clickOnLogOutButton();
    }
}
