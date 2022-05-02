package com.toolsqa;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.EntentReportNG;
import resources.TestBase;

import java.io.IOException;

public class ListnersTest extends TestBase implements ITestListener {

    public  static Logger log = LogManager.getLogger(TestBase.class.getName());
    ExtentTest test;
    ExtentReports extent = EntentReportNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("******" +iTestResult.getMethod().getMethodName()+" is started ***********");
        test = extent.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS,"Test Pass");
        WebDriver driver =null;
        String dest = "./screenshots/passtest/";
        String methodname = iTestResult.getMethod().getMethodName();
        log.info(methodname+" is passed");
        try {
            driver =(WebDriver)iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            takeScreenshot(methodname,dest,driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().fail(iTestResult.getThrowable());
        WebDriver driver =null;
        String dest = "./screenshots/Failedtest/";
        String methodname = iTestResult.getMethod().getMethodName();
        log.info(methodname+" is failed");
        try {
            driver =(WebDriver)iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            extentTest.get().addScreenCaptureFromPath((takeScreenshot(methodname,dest,driver)),iTestResult.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(iTestResult.getMethod().getMethodName() +" is skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("$$$$$$$" +iTestContext.getSuite().getName()+" is started $$$$$$$");

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("$$$$$$$" +iTestContext.getSuite().getName()+" is finished $$$$$$$");
        extent.flush();
    }
}
