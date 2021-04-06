package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
//*[text() = ‘Get started free’]
//*[contains (text(), ‘Get started’)]
public class BoxMainPage {
    WebDriver driver;

    @FindBy(css = "[data-resin-target=newmenubutton]")
    WebElement newMenuBtn;

    @FindBy(xpath = "//*[@id=\"menu32\"]/li[1]/span")
    WebElement folderOpt;

    @FindBy(xpath = "//*[@id=\"menu27\"]/span/li")
    WebElement fileOpt;

    @FindBy(xpath = "//*[@id=\"menu267\"]")
    WebElement listOptions;

    @FindBy(name = "folder-name")
    WebElement folderNameTxt;

    @FindBy(css = "[data-resin-target=primarybutton]")
    WebElement createBtn;

    @FindBy(css = "[data-resin-target=uploadmenubutton]")
    WebElement uploadButton;

    @FindBy(className = "ReactVirtualized__Grid__innerScrollContainer")
    List<WebElement> listFolders;

    @FindBy(xpath = "//input[@type=\"file\"]")
    WebElement inputFile;

    public BoxMainPage(WebDriver driver) {
        this.driver = driver;
//        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void clickOnAction(String action) {
        List<WebElement> options = listOptions.findElements(By.tagName("li"));
        for (int i = 1; i < options.size(); i++) {
            System.out.println(options.get(i).getText());
        }
    }

    public void clickOnNewMenuButton() {
        newMenuBtn.click();
    }

    public void clickOnUploadMenuButton() {
        uploadButton.click();
    }


    public void clickOnFolderOption() {
        folderOpt.click();
    }

    public void clickOnFileOption() {
        fileOpt.click();
    }

    public void clickOnCreateButton() {
        createBtn.click();
    }

    public void setFolderNameTxt(String strName) {
        folderNameTxt.sendKeys(strName);
    }

    public void waitAFewSeconds(int timeToWaitInSeconds) {
        // Used to avoid NoSuchElementException as the others patterns to wait didn't work properly.
        try {
            TimeUnit.SECONDS.sleep(timeToWaitInSeconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    public void createNewFolder(String strName) {
        waitAFewSeconds(5);
        this.clickOnNewMenuButton();
        this.clickOnFolderOption();
        waitAFewSeconds(1);
        this.setFolderNameTxt(strName);
        this.clickOnCreateButton();
        waitAFewSeconds(3);
    }

    public void uploadAFile(String strFilename) {
        waitAFewSeconds(2);
        this.clickOnUploadMenuButton();
        waitAFewSeconds(2);
        this.clickOnFileOption();
        driver.switchTo().activeElement().sendKeys(strFilename);
        windowsDialogHandler(strFilename);
        this.setInputFile(strFilename);
        waitAFewSeconds(5);

    }

    private void windowsDialogHandler(String strFilename) {
        StringSelection ss = new StringSelection(strFilename);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //native key strokes for CTRL, V and ENTER keys
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println("Error:\n" + ex.getMessage());
        }

        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        waitAFewSeconds(2);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public boolean isElementInTheList(String text) {
        List<WebElement> list = listFolders;
        for (WebElement webElement : list) {
            if (webElement.getText().contains(text) )
                return true;
        }
        return false;
    }

    public void setInputFile(String filename) {
        this.inputFile.sendKeys(filename);
    }
}
