package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WidgetPage {

    WebDriver driver;
    WebDriverWait wait;

    public WidgetPage(WebDriver driver){
        this.driver = driver;
    }

    By header = By.cssSelector("div.main-header");
    By selectable = By.xpath("//*[text() = 'Selectable']");
    By Droppable = By.xpath("//*[text() = 'Droppable']");


    public WebElement headerelement(){
        return driver.findElement(header);
    }
    public void clickonMenut(String groupname){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(groupname.equalsIgnoreCase("selectable")) {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(selectable));
            driver.findElement(selectable).click();
        }
        if(groupname.equalsIgnoreCase("Droppable")){
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(Droppable));
            driver.findElement(Droppable).click();
        }
    }

    public void verifyinteractionhomepage(){
       // wp = new WidgetPage(driver);
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(header),"Interactions"));
        System.out.println("Interactions page is opened.");
    }
    public void NavigatetoSelectable(String groupname){

        clickonMenut(groupname);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        System.out.println(driver.findElement(header).getText());
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(header),groupname));

        System.out.println(groupname + "page is opened");

    }

}
