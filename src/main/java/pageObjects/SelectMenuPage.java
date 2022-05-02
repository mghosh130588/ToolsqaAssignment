package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectMenuPage extends WidgetPage{


    WebDriver driver;
    private By selectauto = By.id("oldSelectMenu");
    private By selectmulti = By.id("cars");

    public SelectMenuPage(WebDriver driver,String menuname){
        super(driver);
        this.driver = driver;
        NavigatetoSelectable(menuname);
    }

    public void selectolddropdown(String value){
        Select select = new Select(driver.findElement(selectauto));
        select.selectByVisibleText(value);
        if(select.getFirstSelectedOption().getText().equalsIgnoreCase(value))
            System.out.println(value + "has been selected");
    }
    public void selectoldmulti(String c[]){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        Select select = new Select(driver.findElement(selectmulti));
        for (String ca :c){
            select.selectByVisibleText(ca);
        }


    }
}
