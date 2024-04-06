package com.tutorialsninj.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninj.qa.utils.Utility;

public class Base {
	WebDriver driver;
	public Properties pro;
	public Properties proTest;
	
	  public Base() {
	  
	  pro=new Properties(); 
	  File file=new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninj\\qa\\config\\config.properties");
	  
	  proTest=new Properties();
	  File filetest=new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninj\\qa\\testdata\\testdata.properties");
	  
	  try {
		FileInputStream fis1=new FileInputStream(filetest);
		proTest.load(fis1);
		
	} catch (Throwable e1) {
		e1.printStackTrace();
	}
	  try { 
		  FileInputStream fis = new FileInputStream(file); 
		  pro.load(fis); 
	
	 } catch(Throwable e) { 
		  e.printStackTrace(); 
	 }
	  
}
	
	public WebDriver InitializeBrowserAndOpenAppliactionURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("Chrome")) { 
			driver=new ChromeDriver(); } 
			else if(browserName.equalsIgnoreCase("Firefox")) { 
				  driver=new FirefoxDriver(); } 
		    else  if(browserName.equalsIgnoreCase("safari")) { 
		    	  driver=new SafariDriver(); }
		     else  if(browserName.equalsIgnoreCase("ie")) {
				  driver =new InternetExplorerDriver();
				  }
		driver.manage().window().maximize();
		driver.get(pro.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.PAGE_LOAD_TIME));
		return driver;
	}
}
