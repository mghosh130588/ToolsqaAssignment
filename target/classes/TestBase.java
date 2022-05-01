package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public String driverpath = "//Users//mousumighosh//Selenium//Driver//";
    Properties prop;

    public WebDriver initializedriver() throws IOException {

        String browsername = getPropertyValue("browser");
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

    public String getPropertyValue(String PropertyName) throws IOException {
         prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/main/java/resources/data.properties");
        prop.load(fs);
       return prop.getProperty(PropertyName);

    }

    public String takeScreenshot(String Testcasename, String Destination,WebDriver driver) throws IOException {
        TakesScreenshot scr = (TakesScreenshot)driver;
        File source = scr.getScreenshotAs(OutputType.FILE);
        String FileDestination = Destination + Testcasename+".png";
        FileUtils.copyFile(source,new File(FileDestination));
        return FileDestination;
    }
}
