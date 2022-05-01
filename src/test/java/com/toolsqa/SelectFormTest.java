package com.toolsqa;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import pageObjects.SelectMenuPage;
import pageObjects.SelectablePage;
import resources.TestBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SelectFormTest extends TestBase
{
    public WebDriver driver;
   public SelectMenuPage sm;

    @BeforeTest
    public void openBrowser() throws IOException {
        this.driver = initializedriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

    }
    @BeforeClass
    public void clickonWidgets(){
        driver.get("https://demoqa.com/");
        System.out.println("link opened");
        LandingPage lp = new LandingPage(driver);
       lp.clickOnWidgest();
    }


    @Test(priority =0)
    public void verifySelectMenuOpen(){
    sm = new SelectMenuPage(driver,"Select Menu");
    }

    @Test(priority = 1)
    public void selectolddropdown(){
        sm.selectolddropdown("Yellow");
    }

    @Test(priority = 2)
    public void multipleselectolddrop(){
        sm.selectoldmulti(new String[]{"Volvo", "Opel"});

    }
    @AfterTest
    public void closebrowser(){
        driver.quit();
        System.out.println("Browser closed");
    }
}
