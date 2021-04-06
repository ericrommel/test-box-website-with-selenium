package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BoxHomePage {
    WebDriver driver;

    @FindBy(id = "main-nav--trigger")
    WebElement hamburgerMenu;

    @FindBy(className = "user-nav--get-started")
    WebElement getStartedBtn;

    @FindBy(className = "user-nav--login")
    WebElement loginBtn;

    public BoxHomePage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory, this);
    }

    public void clickOnLoginButton() {
        loginBtn.click();
    }

    public void clickOnHamburgerMenu() {
        hamburgerMenu.click();

    }

    public boolean isLoginButtonAvailable() {
        return loginBtn.isDisplayed();
    }

    public boolean isHamburguerMenuAvailable() {
        return hamburgerMenu.isDisplayed();
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }
}
