package com.tutorialsninj.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninj.qa.utils.ExtentReporter;
import com.tutorialsninj.qa.utils.Utility;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
		
	//	System.out.println("Execution of Project Tests Started");
		  extentReport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
	//	testName=result.getName();
	//	System.out.println(testName+":started executing");
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+":started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
	//	String testName=result.getName();
	//	System.out.println(testName+":got sucessfully executed");
		extentTest.log(Status.PASS, result.getName()+":got sucessfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
	/*	File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenShotPath = System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		} */
		
		String destinationScreenShotPath = Utility.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+":got failed");
		
	//	System.out.println(result.getThrowable());
	//	System.out.println(testName+":got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+":got skipped");
		
	//	System.out.println(result.getThrowable());
	//	System.out.println(testName+":got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	//	System.out.println("finished executing Project Tests");
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
