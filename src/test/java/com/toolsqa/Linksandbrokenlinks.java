package com.toolsqa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.net.UrlChecker;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Linksandbrokenlinks {

    WebDriver driver;
    public String driverpath = "//Users//mousumighosh//Selenium//Driver//chromedriver";
    @BeforeClass
    public void beforeClass() {

        System.setProperty("webdriver.chrome.driver",driverpath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser opened");

    }

    //@Test
    public void getAllLinks(){
        driver.get("https://demoqa.com/links");
        List<WebElement> alllinks = driver.findElements(By.tagName("a"));
        System.out.println("The total number of links = " +alllinks.size());
        Iterator<WebElement> ls = alllinks.iterator();
        while(ls.hasNext()){
            System.out.println(ls.next().getText());
        }
    }

    //@Test
    public void brokenlinks(){
        driver.get("https://demoqa.com/broken");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("No of links are "+ links.size());
        Iterator<WebElement> le = links.iterator();
        while(le.hasNext()){

            WebElement a = le.next();
            String url = a.getAttribute("href");
            System.out.println(url + a.getText());
            verifylinks(url);
        }


    }

    private void verifylinks(String url) {
        try {
            URL linkurl = new URL(url);
            HttpURLConnection httpconnection = (HttpURLConnection)linkurl.openConnection();
            httpconnection.setConnectTimeout(5000);
            httpconnection.connect();
            if(httpconnection.getResponseCode()>=400){
                System.out.println(url +"-" + httpconnection.getResponseMessage() +" is broken");
            }
            else{
                System.out.println(url +"-" + httpconnection.getResponseMessage() +" is working");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void BrolkenLinkImage(){
        driver.get("https://demoqa.com/broken");
        List<WebElement> imglinks = driver.findElements(By.tagName("img"));
        System.out.println(imglinks.size());
        Iterator<WebElement> IMG = imglinks.iterator();
        while(IMG.hasNext()){
            WebElement image = IMG.next();
            String url = image.getAttribute("src");
            System.out.println("Url is " +url);
            verifylinks(url);
            boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript("return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);", image);
            if (imageDisplayed) {
                System.out.println("DISPLAY - OK");
            }else {
                System.out.println("DISPLAY - BROKEN");
            }
        }


    }

    @AfterClass
    public void quitbrowser() {
        driver.quit();
    }
}
