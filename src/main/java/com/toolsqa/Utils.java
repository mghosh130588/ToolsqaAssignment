package com.toolsqa;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static void takescreenshot(WebDriver webdriver, String filepath) throws IOException {

        TakesScreenshot scrShot = ((TakesScreenshot)webdriver);
        File Srcfile = scrShot.getScreenshotAs(OutputType.FILE);
        File Destfile = new File(filepath);
        FileUtils.copyFile(Srcfile, Destfile);
    }

    public static void setbirthdate(WebDriver webdriver, WebElement calender) {

        calender.click();
        Select select = new Select(webdriver.findElement(By.className("react-datepicker__year-select")));
        select.selectByValue("1992");
        select = new Select(webdriver.findElement(By.className("react-datepicker__month-select")));
        select.selectByValue("0");
        WebElement monthbox = webdriver.findElement(By.className("react-datepicker__month"));
        monthbox.findElement(By.xpath("//*[text()='15']")).click();


    }

    public static void autoselectSubject(String Sub1, String sub2, WebDriver webdriver) {

        webdriver.findElement(By.xpath("//*[@id = 'subjectsContainer']/div")).click();
        webdriver.findElement(By.xpath("//*[@id = 'subjectsInput']")).sendKeys(Sub1);
        webdriver.findElement(By.xpath("//*[@id = 'subjectsInput']")).sendKeys(Keys.ENTER);
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webdriver.findElement(By.xpath("//*[@id = 'subjectsInput']")).sendKeys(sub2);
        webdriver.findElement(By.xpath("//*[@id = 'subjectsInput']")).sendKeys(Keys.ENTER);

    }

    public static void selectHobby(String[] hobby,WebDriver webdriver) {

        java.util.List<WebElement> hobbywrap = webdriver.findElements(By.xpath("//*[@id = 'hobbiesWrapper']/div[2]/div/label"));
        System.out.println(hobbywrap.size());
        for (String hb : hobby) {
            if(hb.equalsIgnoreCase("Sports"))
            {

                for(WebElement a :hobbywrap ) {
                    if(a.getText().equalsIgnoreCase("Sports"))
                    {
                        a.click();
                        System.out.println("Sports is selected");
                        break;

                    }
                }
            }else if(hb.equalsIgnoreCase("Reading")) {

                for(WebElement a :hobbywrap ) {
                    if(a.getText().equalsIgnoreCase("Reading"))
                    {
                        a.click();
                        System.out.println("Reading is selected");
                        break;

                    }
                }
            }else
            {
                if(hb.equalsIgnoreCase("Music"))
                {
                    for(WebElement a :hobbywrap ) {
                        if(a.getText().equalsIgnoreCase("Music"))
                        {
                            a.click();
                            System.out.println("Music is selected");
                            break;
                        }
                    }
                }
            }
        }
    }



    public static void uploadPicture(WebDriver webdriver, String path) {
        WebElement upload = webdriver.findElement(By.id("uploadPicture"));
        upload.sendKeys(path);

    }

    public static void addressDetails(WebDriver webdriver, String address) {
        webdriver.findElement(By.id("currentAddress")).sendKeys(address);
    }

    public static void stateCitySelector(WebDriver webdriver,String state) {

        webdriver.findElement(By.xpath("//*[@id = 'state']/div/div/div")).click();
        java.util.List<WebElement> statedropdown = webdriver.findElements(By.xpath("//*[@id = 'state']/div[2]/div/div"));
        //System.out.println(statedropdown.size());
        //statedropdown.stream().filter(s-> s.getText().equalsIgnoreCase(state)).forEach(s->s.click());
        //Thread.sleep(1000);
        //WebElement city1 = webdriver.findElement(By.xpath("//*[@id='city']/div/div[1]/div[1]"));
        //city1.click();

        //webdriver.findElement(By.id("submit")).submit();
        for(WebElement a : statedropdown)
        {
            if(a.getText().equalsIgnoreCase(state))
            {
                a.click();
                break;
            }
        }


    }

    public static void cityselection(WebDriver webdriver, String city) {
        WebDriverWait wait = new WebDriverWait(webdriver,20);
        WebElement city1;
        city1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='city']/div/div[1]/div[1]")));
        city1.click();
        java.util.List<WebElement> citylist = webdriver.findElements(By.xpath("//*[@id='city']/div[2]/div/div"));
        for(WebElement a: citylist)
        {
            if(a.getText().equalsIgnoreCase(city))
            {
                a.click();
                break;
            }
        }
    }

    public static void verifymodal(WebDriver webdriver, String fn, String ln, String mob) {


        SoftAssert softassert = new SoftAssert();
        String title = webdriver.findElement(By.xpath("//*[@id='example-modal-sizes-title-lg']")).getText();
        String Expected = "Thanks for submitting the form";
        softassert.assertEquals(title, Expected);

        //WebElement table = webdriver.findElement(By.className("table-responsive"));
        String name = webdriver.findElement(By.xpath("//*[@class= 'table-responsive']/table/tbody/tr[1]/td[2]")).getText();
        softassert.assertEquals(name, fn+" "+ln);
        String Email = webdriver.findElement(By.xpath("//*[@class= 'table-responsive']/table/tbody/tr[2]/td[2]")).getText();
        softassert.assertEquals(Email, "test.test@gmail.com");
        String mobile = webdriver.findElement(By.xpath("//*[@class= 'table-responsive']/table/tbody/tr[4]/td[2]")).getText();
        softassert.assertEquals(mobile, mob);
        softassert.assertAll();
        webdriver.findElement(By.id("closeLargeModal")).click();
    }


    public static void preventpropagable(WebDriver webdriver) throws InterruptedException {

        webdriver.findElement(By.xpath("//*[@id = 'droppableExample-tab-preventPropogation']")).click();
        WebElement drag = webdriver.findElement(By.id("dragBox"));
        WebElement outrngrd = webdriver.findElement(By.xpath("//*[@id ='notGreedyDropBox']/p"));
        WebElement innnergdr = webdriver.findElement(By.xpath("//*[@id = 'notGreedyInnerDropBox']"));

        Actions action = new Actions(webdriver);

        action.dragAndDrop(drag, innnergdr).build().perform();
        Assert.assertEquals(outrngrd.getText(), innnergdr.getText());
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webdriver.navigate().refresh();
        webdriver.findElement(By.xpath("//*[@id = 'droppableExample-tab-preventPropogation']")).click();
        JavascriptExecutor js = (JavascriptExecutor)webdriver;
        js.executeScript("window.scrollBy(0,200)");
        WebElement drag2 = webdriver.findElement(By.id("dragBox"));
        WebElement innerngrddr = webdriver.findElement(By.id("greedyDropBoxInner"));
        WebElement outergr = webdriver.findElement(By.xpath("//*[@id = 'greedyDropBox']"));
        action.dragAndDrop(drag2, innerngrddr).build().perform();
        Assert.assertNotEquals(innerngrddr.getText(), outergr.getText());




    }

    public static void revertableDrag(WebDriver webdriver) throws InterruptedException {
        webdriver.findElement(By.id("droppableExample-tab-revertable")).click();
        WebElement willrevert = webdriver.findElement(By.xpath("//*[@id = 'revertable']"));
        WebElement droploc = webdriver.findElement(By.id("droppable"));
        int xr = willrevert.getLocation().getX();
        int yr = willrevert.getLocation().getY();
        System.out.println(xr + " " +yr);
        Actions action = new Actions(webdriver);
        action.dragAndDropBy(willrevert,300,100).build().perform();
        Thread.sleep(1000);
        WebElement droptitle = webdriver.findElement(By.xpath("//*[@id='revertableDropContainer']/div[2]/p"));
        Assert.assertEquals(droptitle.getText(), "Dropped!");
        if((willrevert.getLocation().getX()==xr)&&(willrevert.getLocation().getY()==yr))
            System.out.println("Willrevertis dropped");
        else
            System.out.println("failed");
        webdriver.navigate().refresh();
        webdriver.findElement(By.xpath("//*[@id = 'droppableContainer']/nav/a[4]")).click();
        WebDriverWait wait = new WebDriverWait(webdriver,10);

        WebElement notrevert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id = 'notRevertable']")));
        int nrx = notrevert.getLocation().getX();
        int nry = notrevert.getLocation().getY();
        System.out.println(nrx + " " +nry);
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        action.dragAndDropBy(notrevert, 300, 50).build().perform();
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[@id='revertableDropContainer']/div[2]/p"),"Drop here")));
        WebElement droptitle2 = webdriver.findElement(By.xpath("//*[@id='revertableDropContainer']/div[2]/p"));
        Assert.assertEquals(droptitle2.getText(), "Dropped!");
        if((notrevert.getLocation().getX()!=xr)&&(notrevert.getLocation().getY()!=yr))
            System.out.println("Notrevertis dropped");
        else
            System.out.println("Not revert failed");
    }

    public static void datePicker(WebDriver webdriver,String day,String month,String year) {
        WebDriverWait wait = new WebDriverWait(webdriver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='datePickerMonthYear']/div[2]/div[2]/div/div/div[2]")));
        if(!(webdriver.findElement(By.className("react-datepicker__year-select")).getText().equalsIgnoreCase(year)))
        {
            Select select = new Select(webdriver.findElement(By.className("react-datepicker__year-select")));

            select.selectByVisibleText(year);
        }
        if(!(webdriver.findElement(By.className("react-datepicker__month-select")).getText().equalsIgnoreCase(month)))
        {
            Select select = new Select(webdriver.findElement(By.className("react-datepicker__month-select")));

            select.selectByVisibleText(month);
        }
        java.util.List<WebElement> week = webdriver.findElements(By.xpath("//*[@class = 'react-datepicker__month']/div"));
        int flag = 0;
        for (int i = 0;i<week.size();i++)
        {
            for (int j =0;j<7;j++) {
                WebElement a = webdriver.findElement(By.xpath("//*[@id='datePickerMonthYear']/div[2]/div[2]/div/div/div[2]/div[2]/div[" +(i+1)+"]/div["+(j+1)+"]"));
                if((a.getText().equalsIgnoreCase(day))&&(a.getAttribute("aria-label").contains(month)))
                {
                    a.click();
                    flag++;
                    break;
                }

                //System.out.println(a.getText());
            }

            if(flag!=0)
                break;

        }


    }

    public static void datetimepicker(WebDriver webdriver,String d,String m,String y,String t) {
        WebDriverWait wait = new WebDriverWait(webdriver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div")));
        if(!(webdriver.findElement(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/div/span[2]")).getText().equalsIgnoreCase(y))) {
            webdriver.findElement(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/div/span[2]")).click();
            java.util.List<WebElement> caldrp = webdriver.findElements(By.className("react-datepicker__year-option"));
            System.out.println(caldrp.size());
            int high = Integer.parseInt(caldrp.get(1).getText());
            int low = Integer.parseInt(caldrp.get(11).getText());
            int yearint = Integer.parseInt(y);
            if ((yearint>=low)&&(yearint<=high))
            {
                for(int i =1;i<=11;i++)
                {
                    if(caldrp.get(i).getText().equalsIgnoreCase(y))
                    {
                        Actions action = new Actions(webdriver);
                        action.moveToElement(caldrp.get(i)).click().build().perform();
                        break;
                    }
                }

            }
            else if (yearint<low){
                int diff = low - yearint;
                while(diff!=0) {
                    caldrp.get(12).click();
                    webdriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
                    diff--;

                }
                webdriver.findElement(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[12]")).click();

            }else {
                int diffh= yearint-high;
                while(diffh!=0) {
                    caldrp.get(0).click();
                    webdriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
                    diffh--;
                }
                webdriver.findElement(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]")).click();
            }

        }

        if(!(webdriver.findElement(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/div/span[2]")).getText().equalsIgnoreCase(m))){
            webdriver.findElement(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/div/span[2]")).click();
            java.util.List<WebElement> monthdrop = webdriver.findElements(By.className("react-datepicker__month-option"));
            for(WebElement a: monthdrop) {
                if(a.getText().equalsIgnoreCase(m))
                {
                    Actions action2 = new Actions(webdriver);
                    action2.moveToElement(a).click().build().perform();
                    break;
                }
            }

        }

        java.util.List<WebElement> weekpck = webdriver.findElements(By.xpath("//*[@class = 'react-datepicker__month']/div"));
        int flag = 0;
        for (int i = 0;i<weekpck.size();i++)
        {
            for (int j =0;j<7;j++) {
                WebElement a = webdriver.findElement(By.xpath("//*[@id='dateAndTimePicker']/div[2]/div[2]/div/div/div[2]/div[2]/div[" +(i+1)+"]/div["+(j+1)+"]"));
                if((a.getText().equalsIgnoreCase(d))&&(a.getAttribute("aria-label").contains(m)))
                {
                    a.click();
                    flag++;
                    break;
                }

                //System.out.println(a.getText());
            }

            if(flag!=0)
                break;

        }

        WebElement timebox = webdriver.findElement(By.xpath("//*[@class='react-datepicker__time']"));

        java.util.List<WebElement> timelist = webdriver.findElements(By.className("react-datepicker__time-list-item"));
        System.out.println(timelist.size());
        Actions action3 = new Actions(webdriver);
        action3.moveToElement(timebox).build().perform();
        for(WebElement a: timelist) {
            if(a.getText().equalsIgnoreCase(t)) {
                //JavascriptExecutor js = (JavascriptExecutor)webdriver ;
                //js.executeScript("argument[0].scrollIntoView();",a);
                webdriver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
                action3.moveToElement(a).click().build().perform();
                break;
            }

            //System.out.println(a.getText());
        }



    }


    public static void selectauto(WebDriver webdriver) {

        WebDriverWait wait = new WebDriverWait(webdriver,10);
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='withOptGroup']/div/div[1]")));
        input.click();
        String opt= "A root";
        int flag = 0;
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='withOptGroup']/div[2]")));
        while(flag==0) {

            java.util.List<WebElement> option = webdriver.findElements(By.xpath("//*[@id='withOptGroup']/div[2]/div/div/div[2]/div"));
            System.out.println(option.size());
            for(WebElement a: option) {
                if(a.getText().equalsIgnoreCase(opt))
                {
                    Actions act = new Actions(webdriver);
                    act.moveToElement(a).click().build().perform();
                    flag++;
                    break;
                }
            }
            if(flag==0) {
                java.util.List<WebElement> option2 = webdriver.findElements(By.xpath("//*[@id='withOptGroup']/div[2]/div/div"));
                System.out.println(option2.size());
                for(WebElement b : option2) {
                    if(b.getText().equalsIgnoreCase(opt))
                    {
                        Actions act = new Actions(webdriver);
                        act.moveToElement(b).click().build().perform();
                        flag++;
                        break;
                    }
                }

            }
            if(flag==0)
            {
                System.out.println("Option not available in dropdown");
                webdriver.findElement(By.xpath("//*[@id='withOptGroup']/div[2]/div/div[1]/div[2]/div")).click();
                flag++;
                break;
            }
        }

    }

    public static void selectoneauto(WebDriver webdriver) {
        WebDriverWait wait = new WebDriverWait(webdriver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id = 'selectOne']/div[2]/div")));
        String title = "Mrs.";
        java.util.List<WebElement> dropdownlist = webdriver.findElements(By.xpath("//*[@id = 'selectOne']/div[2]/div/div/div[2]/div"));
        for (WebElement a : dropdownlist) {

            if(a.getText().equalsIgnoreCase(title))
            {
                a.click();
                break;
            }
        }

    }

    public static void olddropdown(WebDriver webdriver,String s) {

        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        Select select = new Select(webdriver.findElement(By.id("oldSelectMenu")));
        select.selectByVisibleText(s);

    }

    public static void autoselectmultiple(WebDriver webdriver, String list[]) {
        JavascriptExecutor js = (JavascriptExecutor) webdriver;
        js.executeScript("window.scrollBy(0,1000)", "");
        webdriver.findElement(By.xpath("//*[@id='selectMenuContainer']/div[7]/div/div/div/div[1]/div[1]")).click();
        for(String lis: list)
        {
            //webdriver.findElement(By.xpath("//*[@id='selectMenuContainer']/div[7]/div/div/div/div[1]")).click();
            //*[@id="selectMenuContainer"]/div[7]/div/div/div/div[1]
            WebDriverWait wait = new WebDriverWait(webdriver,10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='selectMenuContainer']/div[7]/div/div/div[2]/div")));
            java.util.List<WebElement> mul = webdriver.findElements(By.xpath("//*[@id='selectMenuContainer']/div[7]/div/div/div[2]/div/div"));
            for(WebElement a : mul)
            {
                if(a.getText().equalsIgnoreCase(lis))
                {
                    a.click();
                    break;
                }

            }

        }
        webdriver.findElement(By.xpath("//*[@id='selectMenuContainer']/div[7]/div/div/div/div[1]/div[1]")).click();


    }

    public static void multiold(WebDriver webdriver, String c[]) {

        JavascriptExecutor js = (JavascriptExecutor) webdriver;
        js.executeScript("window.scrollBy(0,500)", "");
        Select select = new Select (webdriver.findElement(By.id("cars")));
        for(String ca: c) {
            select.selectByVisibleText(ca);
        }
    }
}
