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
import com.tutorialsninj.qa.pages.AccountSucessPage;
import com.tutorialsninj.qa.pages.HomePage;
import com.tutorialsninj.qa.pages.RegisterPage;
import com.tutorialsninj.qa.utils.Utility;

public class RegisterTest extends Base{
	
	RegisterPage registerPage;
	AccountSucessPage accountSucessPage;
	
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
	
	driver=InitializeBrowserAndOpenAppliactionURL(pro.getProperty("BrowserName"));
	HomePage homePage=new HomePage(driver);
	registerPage=homePage.register();
//	homePage.clickOnMyAccount();
//	registerPage = homePage.selectRegisterOption();
}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1)
	public void verfyRegisteringAnAccountWithMandatoryFields() throws IOException {
		
	//	RegisterPage registerPage=new RegisterPage(driver);
		accountSucessPage=registerPage.registeringAnAccountWithMandatoryFields(proTest.getProperty("firstName"), proTest.getProperty("lastName"), Utility.genrateEmailWithTimeStamp(), proTest.getProperty("telePhone"), pro.getProperty("ValidPassword"),pro.getProperty("ValidPassword"));
		
	/*
	 * registerPage.enterFirstName(proTest.getProperty("firstName"));
	 * registerPage.enterLastName(proTest.getProperty("lastName"));
	 * registerPage.enterEmail(Utility.genrateEmailWithTimeStamp());
	 * registerPage.enterTelephone(proTest.getProperty("telePhone"));
	 * registerPage.enterPassword(pro.getProperty("ValidPassword"));
	 * registerPage.enterConfirmPassword(pro.getProperty("ValidPassword"));
	 * registerPage.clickAgree(); registerPage.clickSubmit();
	 */
		/*
		 * driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(proTest.
		 * getProperty("firstName"));
		 * driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(proTest.
		 * getProperty("lastName"));
		 * driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Utility.
		 * genrateEmailWithTimeStamp());
		 * driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(proTest.
		 * getProperty("telePhone"));
		 * driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pro.
		 * getProperty("ValidPassword"));
		 * driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys(pro.
		 * getProperty("ValidPassword"));
		 * driver.findElement(By.xpath("//input[@name='agree']")).click();
		 * driver.findElement(By.xpath("//input[@type='submit']")).click();
		 */
		
	//	accountSucessPage=new AccountSucessPage(driver);
	//	String acutalSucessHeading=accountSucessPage.retrieveAccountSucessPageHeading();
		Assert.assertEquals(accountSucessPage.retrieveAccountSucessPageHeading(),proTest.getProperty("accountSucessfullMessage"), "Your Account Has not Created");
		
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen_1.png"));
	}
	
	@Test(priority=2)
	public void verfyRegisteringAnAccountWithMandatoryFieldsWithSubscribe() throws IOException {
	
//		RegisterPage registerPage=new RegisterPage(driver);
		accountSucessPage=registerPage.verfyRegisteringAnAccountWithMandatoryFieldsWithSubscribe(proTest.getProperty("firstName"), proTest.getProperty("lastName"), Utility.genrateEmailWithTimeStamp(), proTest.getProperty("telePhone"), pro.getProperty("ValidPassword"), pro.getProperty("ValidPassword"));
		/*registerPage.enterFirstName(proTest.getProperty("firstName"));
		registerPage.enterLastName(proTest.getProperty("lastName"));
		registerPage.enterEmail(Utility.genrateEmailWithTimeStamp());
		registerPage.enterTelephone(proTest.getProperty("telePhone"));
		registerPage.enterPassword(pro.getProperty("ValidPassword"));
		registerPage.enterConfirmPassword(pro.getProperty("ValidPassword"));
		registerPage.clickAgree();
		registerPage.clickNewsLetterSubscribeYesButton();
		accountSucessPage = registerPage.clickSubmit();*/
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		
//		AccountSucessPage accountSucessPage=new AccountSucessPage(driver);		
//		String acutalSucessHeading=accountSucessPage.retrieveAccountSucessPageHeading();
		Assert.assertEquals(accountSucessPage.retrieveAccountSucessPageHeading(),proTest.getProperty("accountSucessfullMessage"), "Your Account Has not Created");
		
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen_3.png"));
	}
	@Test(priority=3)
	public void verfyRegisteringAnAccountWithExistingEmailAddress() throws IOException {
	
//		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.registeringAnAccountWithMandatoryFields(proTest.getProperty("firstName"), proTest.getProperty("lastName"), pro.getProperty("ValidEmail"), proTest.getProperty("telePhone"), pro.getProperty("ValidPassword"), pro.getProperty("ValidPassword"));
		/*
		 * registerPage.enterFirstName(proTest.getProperty("firstName"));
		 * registerPage.enterLastName(proTest.getProperty("lastName"));
		 * registerPage.enterEmail(pro.getProperty("ValidEmail"));
		 * registerPage.enterTelephone(proTest.getProperty("telePhone"));
		 * registerPage.enterPassword(pro.getProperty("ValidPassword"));
		 * registerPage.enterConfirmPassword(pro.getProperty("ValidPassword"));
		 * registerPage.clickAgree(); registerPage.clickSubmit();
		 */
		
//		String duplicateWarningHeading=registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(proTest.getProperty("duplicateEmailWarning")), "Warning: E-Mail Address is already registered! is not showing");
		
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen_2.png"));
	}
	@Test(priority=4)
	public void verfyRegisteringAnAccountWithOutMandatoryFields() throws IOException {
	
//		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.clickSubmit();
		
		Assert.assertTrue(registerPage.displayStatusOfWarningMessage(proTest.getProperty("privacyPolicyWarning"), proTest.getProperty("firstNameMessage"), proTest.getProperty("lastNameMessage"), proTest.getProperty("emailMessage"), proTest.getProperty("telePhoneMessage"), proTest.getProperty("passWordMessage")));
		
		/*
		 * // String AcutalPrivacyPolicyWarningMessage=registerPage.
		 * retrieveAcutalPrivacyPolicyWarning();
		 * Assert.assertTrue(registerPage.retrieveAcutalPrivacyPolicyWarning().contains(
		 * proTest.getProperty("privacyPolicyWarning")),"Warning message not displayed"
		 * );
		 * 
		 * // String
		 * AcutalFirstNameWarning=registerPage.retrieveAcutalFirstNameWarning();
		 * Assert.assertEquals(registerPage.retrieveAcutalFirstNameWarning(),
		 * proTest.getProperty("firstNameMessage")
		 * ,"First Name Warning message not displayed");
		 * 
		 * // String AcutalLastNameWarning=registerPage.retrieveAcutalLastNameWarning();
		 * Assert.assertEquals(registerPage.retrieveAcutalLastNameWarning(),
		 * proTest.getProperty("lastNameMessage")
		 * ,"Last Name Warning message not displayed");
		 * 
		 * // String AcutalEmailWarning=registerPage.retrieveAcutalEmailWarning();
		 * Assert.assertEquals(registerPage.retrieveAcutalEmailWarning(),
		 * proTest.getProperty("emailMessage"),"E-Mail Warning message not displayed");
		 * 
		 * // String
		 * AcutaltelephoneWarning=registerPage.retrieveAcutaltelephoneWarning();
		 * Assert.assertEquals(registerPage.retrieveAcutaltelephoneWarning(),
		 * proTest.getProperty("telePhoneMessage")
		 * ,"Telephone Warning message not displayed");
		 * 
		 * // String AcutalPassWordWarning=registerPage.retrieveAcutalPassWordWarning();
		 * Assert.assertEquals(registerPage.retrieveAcutalPassWordWarning(),
		 * proTest.getProperty("passWordMessage")
		 * ,"Password Warning message not displayed");
		 */	
		File Screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screenshotfile, new File(".//Screenshot//screen_4.png"));
	}
}
