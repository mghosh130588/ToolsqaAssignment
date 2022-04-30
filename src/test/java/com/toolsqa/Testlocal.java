package com.toolsqa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Testlocal{

    WebDriver driver;
    public String driverpath = "//Users//mousumighosh//Selenium//Driver//chromedriver";
    public String Screenshotpath = "//Users//mousumighosh//Selenium//ToolsqaAssignment//Screenshot//";
    @BeforeClass
    public void beforeClass() {

        System.setProperty("webdriver.chrome.driver",driverpath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser opened");
    }
    @Test(priority = 0)
    public void selecttext() throws InterruptedException, IOException {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("https://demoqa.com/selectable/");
        List<WebElement> listtable = driver.findElements(By.xpath("//*[@id = 'verticalListContainer']/li"));
        //*[@id="verticalListContainer"]
        listtable.stream().forEach(element->
        {
            element.click();
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            System.out.println("Text present is " + element.getText());
        });
        Utils.takescreenshot(driver, Screenshotpath+"selecttext.png");

        //System.out.println(listtable.size());

        //listtable.get(0).click();
        //Thread.sleep(5000);
        //System.out.println(listtable.get(0).getText());

    }

    //@Test(priority = 1)
    public void assignment1_form() throws IOException {

        String firstname = "Test First";
        String Lastname = "Last name";
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id = 'firstName']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id = 'lastName']")).sendKeys(Lastname);
        driver.findElement(By.xpath("//input[@id = 'userEmail']")).sendKeys("test.test@gmail.com");
        driver.findElement(By.xpath("//*[@id='genterWrapper']/div[2]/div[2]/label")).click();
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");
        WebElement calender =  driver.findElement(By.id("dateOfBirthInput"));
        Utils.setbirthdate(driver, calender);
        //driver.findElement(By.id("dateOfBirthInput")).sendKeys("01 Jan 1988");

        //*[@id="genterWrapper"]/div[2]/div[2]/label
        //*[@id="gender-radio-2"]
        Utils.autoselectSubject("English", "Science", driver);
        String[] hobbylist = {"Sports","Reading"};
        //driver.findElement(By.xpath("//*[@id='hobbies-checkbox-2']")).click();
        Utils.selectHobby(hobbylist, driver);
        //Utils.uploadPicture(driver, "//Users//mousumighosh//test.txt");
        Utils.addressDetails(driver, "Stoholm 31");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)");
        //driver.findElement(By.xpath("//*[@id = 'state']/div/div/div")).click();
        Utils.stateCitySelector(driver, "Haryana");
        Utils.cityselection(driver, "Panipat");
        //driver.findElement(By.xpath("//*[@id='city']/div/div[1]/div[1]")).click();
        Utils.takescreenshot(driver, Screenshotpath+"beforesubmit.png");
        driver.findElement(By.id("submit")).submit();
        Utils.takescreenshot(driver, Screenshotpath+"aftersubmit.png");
        Utils.verifymodal(driver, firstname, Lastname,"1234567890");
    }

    @Test(priority = 2)
    public void draggable() throws InterruptedException, IOException {

        driver.get("https://demoqa.com/droppable");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("simpleDropContainer")));
        Actions action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        action.dragAndDrop(drag, drop).build().perform();
        Thread.sleep(1000);
        Utils.takescreenshot(driver, Screenshotpath+"simpledraggable.png");
        //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertEquals(drop.getText(), "Dropped!", "Not draggable");
        driver.findElement(By.xpath("//*[@id = 'droppableExample-tab-accept']")).click();
        WebElement acct = driver.findElement(By.id("acceptable"));

        WebElement drop2 = driver.findElement(By.xpath("//*[@id='acceptDropContainer']/div[2]"));
        action.dragAndDrop(acct, drop2).build().perform();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        Assert.assertTrue(drop2.getText().equalsIgnoreCase("Dropped!"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        driver.findElement(By.xpath("//*[@id = 'droppableExample-tab-accept']")).click();
        WebElement nonacct = driver.findElement(By.id("notAcceptable"));
        WebElement drop3 = driver.findElement(By.xpath("//*[@id='acceptDropContainer']/div[2]"));
        action.dragAndDrop(nonacct, drop3).build().perform();
        Assert.assertTrue(drop3.getText().equalsIgnoreCase("Drop here"));
        Thread.sleep(500);
        Utils.preventpropagable(driver);
        Utils.takescreenshot(driver, Screenshotpath+"preventpropagate.png");
        Utils.revertableDrag(driver);
        //Utils.takescreenshot(driver, Screenshotpath+"revertdrag.png");

    }

   //@Test(priority = 3)
    public void datepicker() throws IOException {
        driver.get("https://demoqa.com/date-picker");
        String Day = "30";
        String Month = "November";
        String year = "1995";
        String Time = "12:15";
        String Day1 = "1";
        String month1 = "December";
        String year1 = "2016";
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement datepk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id = 'datePickerMonthYearInput']")));
        datepk.click();
        Utils.datePicker(driver,Day,Month,year);
        Utils.takescreenshot(driver, Screenshotpath+"datepicker1.png");
        WebElement datetime = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateAndTimePickerInput")));
        datetime.click();
        Utils.datetimepicker(driver,Day1,month1,year1,Time);
        Utils.takescreenshot(driver, Screenshotpath+"datepicker2.png");



    }

   @Test(priority = 4)
    public void selectMenu() throws IOException {

        driver.get("https://demoqa.com/select-menu");
        Utils.selectauto(driver);
        driver.findElement(By.xpath("//*[@id = 'selectOne']/div[1]/div[1]")).click();
        Utils.selectoneauto(driver);
        Utils.olddropdown(driver, "Aqua");
        //driver.findElement(By.xpath("//*[@id='selectMenuContainer']/div[7]/div/div/div/div[1]/div[1]")).click();
        String list[] = {"Red","Green"};
        Utils.autoselectmultiple(driver,list);
        String cars[] = {"Volvo","Opel"};
        Utils.multiold(driver,cars);
        Utils.takescreenshot(driver, Screenshotpath+"selectmenu.png");

    }

    @AfterClass
    public void quitbrowser() {
        driver.quit();
    }
}

