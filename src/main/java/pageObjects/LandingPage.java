package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LandingPage extends WidgetPage{

    public WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    By interaction = By.xpath("//*[text()='Interactions']");
    public void clickOnInteractions(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(interaction));
        driver.findElement(interaction).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        verifyinteractionhomepage();
    }
}
