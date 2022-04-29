package com.toolsqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Testlocal{

    WebDriver driver;
    public String driverpath = "//Users//mousumighosh//Selenium//Driver//chromedriver";
    @BeforeClass
    public void beforeClass() {

        System.setProperty("webdriver.chrome.driver",driverpath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser opened");
    }

    @Test
    public void postjirs(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/selectable/");
        System.out.println("Initial run");
    }

    @AfterClass
    public void quitbrowser() {
        driver.quit();
    }
}
