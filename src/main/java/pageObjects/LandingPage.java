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
    private By interaction = By.xpath("//*[text()='Interactions']");
    private By Widgets = By.xpath("//*[text()='Widgets']");

    public void clickOnInteractions(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(interaction));
        driver.findElement(interaction).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        verifyinteractionhomepage();
    }

    public void clickOnWidgest(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(Widgets));
        driver.findElement(Widgets).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        verifyWidgetspage();
    }
}
