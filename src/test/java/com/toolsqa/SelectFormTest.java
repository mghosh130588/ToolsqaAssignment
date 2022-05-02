package com.toolsqa;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.LandingPage;
import pageObjects.SelectMenuPage;
import resources.TestBase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SelectFormTest extends TestBase
{
    public  static Logger log = LogManager.getLogger(TestBase.class.getName());
    public WebDriver driver;
   public SelectMenuPage sm;

    @BeforeTest
    public void openBrowser() throws IOException {
        this.driver = initializedriver();
        log.info("Browser has been initialized");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

    }
    @BeforeClass
    public void clickonWidgets() throws IOException {
        driver.get(getPropertyValue("url"));
       log.info("link opened");
        LandingPage lp = new LandingPage(driver);
       lp.clickOnWidgest();
    }


    @Test(priority =0)
    public void verifySelectMenuOpen(){
    sm = new SelectMenuPage(driver,"Select Menu");
    log.info("Select Menu page is opened");

    }

    @Test(priority = 1,dataProvider = "old dropdown")
    public void selectolddropdown(String colour){
        sm.selectolddropdown(colour);
        log.info(colour +"is selected");
    }

    @Test(priority = 2, dataProvider = "old dropdown")
    public void multipleselectolddrop(String[] caropt){
        sm.selectoldmulti(caropt);
        log.info("Multiple options selected is " +caropt.toString());

    }
    @AfterTest
    public void closebrowser(){
        driver.quit();
        log.info("Browser closed");
    }


    @DataProvider(name = "old dropdown")
    public Object[][] oldselectdropdowntestdata(Method m){
        switch(m.getName()){
            case "selectolddropdown":
                return new Object[][]{{"Yellow"},{"Red"},{"Blue"}};
            case "multipleselectolddrop":
                return new Object[][]{
                        {new String[]{"Volvo", "Opel"}},
                        {new String[]{"Saab","Opel"}}
                };
        }
    return null;

    }
}
