package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public String driverpath = "//Users//mousumighosh//Selenium//Driver//";
    public WebDriver initializedriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/main/java/resources/data.properties");
        prop.load(fs);
        String browsername = prop.getProperty("browser");
        if(browsername.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",driverpath+"chromedriver");
            driver = new ChromeDriver();
            System.out.println("Browser initialized");

        }else if(browsername.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverpath + "geckodriver");
            driver = new FirefoxDriver();
            System.out.println("Browser initialized");
        }
        return driver;

    }
}
