package com.tutorialsninj.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninj.qa.base.Base;
import com.tutorialsninj.qa.pages.AccountPage;
import com.tutorialsninj.qa.pages.HomePage;
import com.tutorialsninj.qa.pages.LoginPage;
import com.tutorialsninj.qa.utils.Utility;

public class LoginTest extends Base {
	
	LoginPage loginpage;
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver=InitializeBrowserAndOpenAppliactionURL(pro.getProperty("BrowserName"));
		HomePage homePage=new HomePage(driver);
		loginpage=homePage.navigateToLoginPage();
	//	homePage.clickOnMyAccount();
	//	loginpage = homePage.selectLoginOption();	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password) throws IOException {
		
//		LoginPage loginpage=new LoginPage(driver);
		AccountPage accountpage = loginpage.login(email, password);
		/*
		 * loginpage.enterEmailField(email); loginpage.enterPasswordField(password);
		 * AccountPage accountpage = loginpage.clickLoginButton();
		 */
//		AccountPage accountpage=new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayStatusOfViewYourOrderHistoryOprtion(),"View your order history option is not displayed");
	//	Assert.assertTrue(driver.findElement(By.linkText("View your order history")).isDisplayed(),"View your order history option is not displayed");
		
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen1.png"));			
	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utility.getTestDataFromExcel("Login");
		return data;
	}
	@Test(priority = 2)
	public void verfiyLoginWithInValidCredentials() throws IOException {
			
		loginpage.login(Utility.genrateEmailWithTimeStamp(), proTest.getProperty("invalidPassword"));
		/*
		 * loginpage.enterEmailField(Utility.genrateEmailWithTimeStamp());
		 * loginpage.enterPasswordField(proTest.getProperty("invalidPassword"));
		 * loginpage.clickLoginButton();
		 */
			/*
			 * driver.findElement(By.xpath("//input[@name='email']")).sendKeys(
			 * "karimullask7399"+genraterTimeStamp()+"@gmail.com");
			 * driver.findElement(By.xpath("//input[@name='password']")).sendKeys(proTest.
			 * getProperty("invalidPassword"));
			 * driver.findElement(By.xpath("//input[@type='submit']")).click();
			 */
	//		String ActualWaringMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			
	//		String ActualWaringMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
	//		String ExpectedWaringMessage=proTest.getProperty("emailPasswordWarningMatch");
			Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(proTest.getProperty("emailPasswordWarningMatch")),"ExpectedWaringMessage is not diplayed");
			
			File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen2.png"));
	}
	@Test(priority = 3)
	public void verfiyLoginWithInValidUsernameandValidPassword() throws IOException {
			
	//		LoginPage loginpage=new LoginPage(driver);
		loginpage.login(Utility.genrateEmailWithTimeStamp(), pro.getProperty("ValidPassword"));
		/*
		 * loginpage.enterEmailField(Utility.genrateEmailWithTimeStamp());
		 * loginpage.enterPasswordField(pro.getProperty("ValidPassword"));
		 * loginpage.clickLoginButton();
		 */
		
			Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(proTest.getProperty("emailPasswordWarningMatch")),"ExpectedWaringMessage is not diplayed");
			
			File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen3.png"));
	}
	@Test(priority = 4)
	public void verfiyLoginWithValidUsernameandInValidPassword() throws IOException {
			
		loginpage.login(pro.getProperty("ValidEmail"),proTest.getProperty("invalidPassword"));
		/*
		 * loginpage.enterEmailField(pro.getProperty("ValidEmail"));
		 * loginpage.enterPasswordField(proTest.getProperty("invalidPassword"));
		 * loginpage.clickLoginButton();
		 */
			Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(proTest.getProperty("emailPasswordWarningMatch")),"ExpectedWaringMessage is not diplayed");
			
			File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen4.png"));
	}
	@Test(priority = 5)
	public void verfiyLoginWithoutCredentials() throws IOException {
	
			loginpage.clickLoginButton();
			
			Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(proTest.getProperty("emailPasswordWarningMatch")),"ExpectedWaringMessage is not diplayed");
			
			File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen5.png"));
	}
	
	public  String genraterTimeStamp() {
		Date date=new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
}
