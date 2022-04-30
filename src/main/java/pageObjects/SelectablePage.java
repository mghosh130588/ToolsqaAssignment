package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelectablePage extends WidgetPage{

    WebDriver driver;
    public SelectablePage(WebDriver driver,String menuname){
        super(driver);
        this.driver = driver;
        NavigatetoSelectable(menuname);

    }


    By listitems = By.xpath("//*[@id = 'verticalListContainer']/li");

/*public void NavigatetoSelectable(String groupname){

    WidgetPage wp = new WidgetPage(driver);
    wait = new WebDriverWait(driver,10);
    wait.until(ExpectedConditions.textToBePresentInElement(wp.headerelement(),"Interactions"));
    System.out.println("Interactions page is opened.");
    wp.clickonMenut(groupname);
    wait.until(ExpectedConditions.textToBePresentInElement(wp.headerelement(),groupname));
    System.out.println(groupname + "page is opened");

}*/
    public void clickOnAllListItems(){
        List<WebElement> listtable = driver.findElements(listitems);
        listtable.stream().forEach(element->
        {
            element.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println("Text present is " + element.getText());
        });
    }

}
