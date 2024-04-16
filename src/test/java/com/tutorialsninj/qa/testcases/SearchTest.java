package com.tutorialsninj.qa.testcases;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninj.qa.base.Base;
import com.tutorialsninj.qa.pages.HomePage;
import com.tutorialsninj.qa.pages.SearchPage;


//Upadted comment
//Upadted comment once again - add more details 

public class SearchTest extends Base{
	
	SearchPage searchPage;
	HomePage homePage;
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=InitializeBrowserAndOpenAppliactionURL(pro.getProperty("BrowserName"));
		homePage=new HomePage(driver);
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	@Test(priority =1)
	public void SearchWithValidProduct() throws IOException {
		
		searchPage=homePage.searchForAProduct(proTest.getProperty("validProduct"));
	//	homePage.enterProductiIntoSerachBoxField(proTest.getProperty("validProduct"));
	//	searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.displayStatusOfHPvalidProduct(),"validProduct HP is not Displayed");	
		/*
		 * driver.findElement(By.name("search")).sendKeys(proTest.getProperty(
		 * "validProduct"));
		 * driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click(
		 * );
		 * Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed())
		 * ;
		 */
		
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen-1.png"));
	}
	@Test(priority =2)
	public void SearchWithInValidProduct() throws IOException {
		searchPage=homePage.searchForAProduct(proTest.getProperty("inValidProduct"));
		
//		String NoProductWarningMessage=searchPage.retrieveAcutalInvalidProductWarningMessage();
		Assert.assertEquals(searchPage.retrieveAcutalInvalidProductWarningMessage(),"abcd", "No product search message  not display");
		
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen-2.png"));
	}
	@Test(priority =3,dependsOnMethods= {"SearchWithValidProduct","SearchWithInValidProduct"})
	public void SearchWithOutProduct() throws IOException {
		
		searchPage=homePage.clickOnSearchButton();
		
//		String NoProductWarningMessage=searchPage.retrieveAcutalInvalidProductWarningMessage();
		Assert.assertEquals(searchPage.retrieveAcutalInvalidProductWarningMessage(),proTest.getProperty("noProductTextWarning"), "No product search message  not display");
		
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen-3.png"));
	}
}
//proTest.getProperty("noProductTextWarning")