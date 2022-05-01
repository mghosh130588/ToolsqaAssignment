package com.toolsqa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.Drropable;
import pageObjects.LandingPage;
import pageObjects.SelectablePage;
import pageObjects.WidgetPage;
import resources.TestBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SelectTextTest extends TestBase {

    public WebDriverWait wait;
    public SelectablePage sc;
    public Drropable dr;
    public WebDriver driver;
    public  static Logger log = LogManager.getLogger(TestBase.class.getName());
    @BeforeTest
    public void openBrowser() throws IOException {
        this.driver = initializedriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        log.info("Browser initiated");

    }
    @BeforeClass
    public void clickonInteractions() throws IOException {
        driver.get(getPropertyValue("url"));
        log.info("link opened");
        LandingPage lp = new LandingPage(driver);
        lp.clickOnInteractions();
    }

    @Test(priority =0)
    public void verifySelectPageOpen(){

        sc = new SelectablePage(driver, "Selectable");
        log.info("Selectable page is opened");
    }

    @Test(priority =1)
    public void SelectLinks(){

        sc.clickOnAllListItems();
        log.info("All links are selected");
    }

    @Test(priority =2)
    public void VerifyDroppable(){
    dr = new Drropable(driver,"Droppable");
    log.info("Droppable page is opened");
    }
    @Test(priority =3)
    public void simpledragdrop() throws InterruptedException {
        dr.dragdropsimple();
        log.info("Simple drag and drop is completed");
    }
    @Test(priority =4)
    public void AcceptNotAcceptdragdrop() throws InterruptedException {
        dr.AcceptNotAcceptdragdrop();
        log.info("Accept not accept drag drop is completed");
    }

    @AfterMethod
    public void aftertestmethod(){
        driver.navigate().refresh();
        log.info("page refreshed");
    }
    @AfterTest
    public void closebrowser(){
        driver.quit();
        log.info("Browser closed");
    }
}
