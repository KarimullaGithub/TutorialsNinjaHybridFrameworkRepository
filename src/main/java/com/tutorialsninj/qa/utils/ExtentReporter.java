package com.tutorialsninj.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
	
	ExtentReports extentReport = new ExtentReports();
	
	File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("Tutorialninj Test Automation Reports");
	sparkReporter.config().setDocumentTitle("TN Automation Report");
	sparkReporter.config().setTimeStampFormat("dd//MM//yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties configProp = new Properties();
	File configProFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninj\\qa\\config\\config.properties");
	try {
	FileInputStream fisconfigProp = new FileInputStream(configProFile);
	configProp.load(fisconfigProp);
	} catch (Throwable e) {
		e.printStackTrace();
	}
		extentReport.setSystemInfo("Application URL", configProp.getProperty("URL"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("BrowserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("ValidEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("ValidPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
}
