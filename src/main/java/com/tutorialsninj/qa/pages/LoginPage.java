package com.tutorialsninj.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	@FindBy(xpath="//input[@name='email']")
	private WebElement EmailField;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement PasswordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingwaring;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void enterEmailField(String emailText) {
		EmailField.sendKeys(emailText);
	}
	public void enterPasswordField(String PasswordText) {
		PasswordField.sendKeys(PasswordText);
	}
	public AccountPage clickLoginButton() {
		LoginButton.click();
		return new AccountPage(driver);
	}
	public AccountPage login(String emailText,String PasswordText) {
		EmailField.sendKeys(emailText);
		PasswordField.sendKeys(PasswordText);
		LoginButton.click();
		return new AccountPage(driver);
		
	}
	public String retrieveEmailPasswordNotMatchingWarningMessageText() {
		String warningText=emailPasswordNotMatchingwaring.getText();
		return warningText;
	}
}
