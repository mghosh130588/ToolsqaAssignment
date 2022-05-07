package com.toolsqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;


public class nbrowserwithwebdrivermanager {

    @Test
    public void startwebdrivermanage(){
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);

        // Navigate to the demoqa website
        driver.get("https://www.demoqa.com");

        driver.quit();
    }
}
