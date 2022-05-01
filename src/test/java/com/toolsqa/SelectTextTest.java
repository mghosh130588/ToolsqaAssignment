package com.toolsqa;

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

    @BeforeTest
    public void openBrowser() throws IOException {
        this.driver = initializedriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

    }
    @BeforeClass
    public void clickonInteractions(){
        driver.get("https://demoqa.com/");
        System.out.println("link opened");
        LandingPage lp = new LandingPage(driver);
        lp.clickOnInteractions();
    }

    @Test(priority =0)
    public void verifySelectPageOpen(){

        sc = new SelectablePage(driver, "Selectable");
    }

    @Test(priority =1)
    public void SelectLinks(){

        sc.clickOnAllListItems();
    }

    @Test(priority =2)
    public void VerifyDroppable(){
    dr = new Drropable(driver,"Droppable");
    }
    @Test(priority =3)
    public void simpledragdrop() throws InterruptedException {
        dr.dragdropsimple();
    }
    @Test(priority =4)
    public void AcceptNotAcceptdragdrop() throws InterruptedException {
        dr.AcceptNotAcceptdragdrop();
    }

    @AfterMethod
    public void aftertestmethod(){
        driver.navigate().refresh();
    }
    @AfterTest
    public void closebrowser(){
        driver.quit();
    }
}
