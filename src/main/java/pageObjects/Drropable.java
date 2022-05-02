package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.util.concurrent.TimeUnit;

public class Drropable extends WidgetPage{

        WebDriver driver;
        public Actions builder;

        public Drropable(WebDriver driver,String menuname){
            super(driver);
            this.driver = driver;
            NavigatetoSelectable(menuname);
        }

       private By simpledragcontainer = By.id("simpleDropContainer");
       private By drag = By.id("draggable");
        private By drop = By.id("droppable");
        private By Accepttab = By.xpath("//*[@id = 'droppableExample-tab-accept']");
    private By Acceptabledrag = By.id("acceptable");
   private By acceptdrop = By.xpath("//*[@id='acceptDropContainer']/div[2]");
   private By notAcceptDrop = By.id("notAcceptable");

        public WebElement getdrag(){
            return driver.findElement(drag);
        }
    public WebElement getdrop(){
        return driver.findElement(drop);
    }
    public WebElement getAcceptheader(){
        return driver.findElement(Accepttab);
    }
    public WebElement getAcceptdrag(){
        return driver.findElement(Acceptabledrag);
    }
    public WebElement Acceptdrop(){
        return driver.findElement(acceptdrop);
    }
    public WebElement NotAcceptdrop(){
        return driver.findElement(notAcceptDrop);
    }
     public void dragdropsimple() throws InterruptedException {
            wait = new WebDriverWait(driver,10);
         wait.until(ExpectedConditions.visibilityOfElementLocated(simpledragcontainer));
         System.out.println("In simple drg drop page");
         WebElement dragele = getdrag();
         WebElement dropele = getdrop();
         builder = new Actions(driver);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        builder.dragAndDrop(dragele,dropele).perform();
         //action.perform();
         Thread.sleep(1000);
         System.out.println(dropele.getText());
         //wait.until(ExpectedConditions.textToBePresentInElement(dropele,"Dropped!"));
         Assert.assertEquals(dropele.getText(),"Dropped!", "Not draggable");

     }

     public void AcceptNotAcceptdragdrop(){
            getAcceptheader().click();
            builder = new Actions(driver);
            builder.dragAndDrop(getAcceptdrag(),Acceptdrop()).build().perform();
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("window.scrollBy(0,500)");
         Assert.assertTrue(Acceptdrop().getText().equalsIgnoreCase("Dropped!"));
         System.out.println("Acceptable drap and drop successful");
         //Thread.sleep(1000);
         driver.navigate().refresh();
         getAcceptheader().click();
         builder.dragAndDrop(NotAcceptdrop(),Acceptdrop()).build().perform();
         Assert.assertTrue(Acceptdrop().getText().equalsIgnoreCase("Drop here"));
         System.out.println("Not Acceptable drag drop is successful");


     }
}
