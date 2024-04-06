package com.tutorialsninj.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@name='firstname']")
	private WebElement firstNameField;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement lastNameField;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@name='telephone']")
	private WebElement telephoneField;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@name='confirm']")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submitField;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsLetterField;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement acutalPrivacyPolicyWarning;
	
	@FindBy(xpath="//div[@class='text-danger'][contains(.,'First Name must be between 1 and 32 characters!')]")
	private WebElement acutalFirstNameWarning;
	
	@FindBy(xpath="//div[@class='text-danger'][contains(.,'Last Name must be between 1 and 32 characters!')]")
	private WebElement acutalLastNameWarning;
	
	@FindBy(xpath="//div[@class='text-danger'][contains(.,'E-Mail Address does not appear to be valid!')]")
	private WebElement acutalEmailWarning;
	
	@FindBy(xpath="//div[@class='text-danger'][contains(.,'Telephone must be between 3 and 32 characters!')]")
	private WebElement acutaltelephoneWarning;
	
	@FindBy(xpath="//div[@class='text-danger'][contains(.,'Password must be between 4 and 20 characters!')]")
	private WebElement acutalPassWordWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver ,this);
	}
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmail(String enterEmailText) {
		emailField.sendKeys(enterEmailText);
	}
	public void enterTelephone(String enterTelephoneText) {
		telephoneField.sendKeys(enterTelephoneText);
	}
	public void enterPassword(String enterPasswordText) {
		passwordField.sendKeys(enterPasswordText);
	}
	public void enterConfirmPassword(String enterConfirmPasswordText) {
		confirmPasswordField.sendKeys(enterConfirmPasswordText);
	}
	public void clickAgree() {
		agreeField.click();
	}
	public AccountSucessPage clickSubmit() {
		submitField.click();
		return new AccountSucessPage(driver);
	}
	public void clickNewsLetterSubscribeYesButton() {
		newsLetterField.click();
	}
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText=duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	public String retrieveAcutalPrivacyPolicyWarning() {
		String acutalPrivacyPolicyWarningText=acutalPrivacyPolicyWarning.getText();
		return acutalPrivacyPolicyWarningText;
	}
	public String retrieveAcutalFirstNameWarning() {
		String acutalFirstNameWarningText=acutalFirstNameWarning.getText();
		return acutalFirstNameWarningText;
	}
	public String retrieveAcutalLastNameWarning() {
		String acutalLastNameWarningText=acutalLastNameWarning.getText();
		return acutalLastNameWarningText;
	}
	public String retrieveAcutalEmailWarning() {
		String acutalEmailWarningText=acutalEmailWarning.getText();
		return acutalEmailWarningText;
	}
	public String retrieveAcutaltelephoneWarning() {
		String acutaltelephoneWarningText=acutaltelephoneWarning.getText();
		return acutaltelephoneWarningText;
	}
	public String retrieveAcutalPassWordWarning() {
		String acutalPassWordWarningText=acutalPassWordWarning.getText();
		return acutalPassWordWarningText;
	}
	public AccountSucessPage registeringAnAccountWithMandatoryFields(String firstNameText,String lastNameText,String enterEmailText,String enterTelephoneText,String enterPasswordText,String enterConfirmPasswordText ) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(enterEmailText);
		telephoneField.sendKeys(enterTelephoneText);
		passwordField.sendKeys(enterPasswordText);
		confirmPasswordField.sendKeys(enterConfirmPasswordText);
		agreeField.click();
		submitField.click();
		return new AccountSucessPage(driver);
	}
	public AccountSucessPage verfyRegisteringAnAccountWithMandatoryFieldsWithSubscribe(String firstNameText,String lastNameText,String enterEmailText,String enterTelephoneText,String enterPasswordText,String enterConfirmPasswordText  ) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(enterEmailText);
		telephoneField.sendKeys(enterTelephoneText);
		passwordField.sendKeys(enterPasswordText);
		confirmPasswordField.sendKeys(enterConfirmPasswordText);
		newsLetterField.click();
		agreeField.click();
		submitField.click();
		return new AccountSucessPage(driver);	
	}
	public boolean displayStatusOfWarningMessage(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,String expectedLastNameWarning,String expectedEmailWarning,String expectedtelephoneWarning,String expectedPassWordWarning) {
		
//		String acutalPrivacyPolicyWarningText=acutalPrivacyPolicyWarning.getText();
		boolean privacyPolicyWarningStatus = acutalPrivacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
		
//		String acutalFirstNameWarningText=acutalFirstNameWarning.getText();
		boolean firstNameWarningStatus = acutalFirstNameWarning.getText().equals(expectedFirstNameWarning);
		
//		String acutalLastNameWarningText=acutalLastNameWarning.getText();
		boolean lastNamewaringstatus=acutalLastNameWarning.getText().equals(expectedLastNameWarning);
		
//		String acutalEmailWarningText=acutalEmailWarning.getText();
		boolean emailWarningSatus=acutalEmailWarning.getText().equals(expectedEmailWarning);
		
//		String acutaltelephoneWarningText=acutaltelephoneWarning.getText();
		boolean telephoneWaringStatus=acutaltelephoneWarning.getText().equals(expectedtelephoneWarning);
		
//		String acutalPassWordWarningText=acutalPassWordWarning.getText();
		boolean passWordWarningStatus=acutalPassWordWarning.getText().equals(expectedPassWordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNamewaringstatus && emailWarningSatus && telephoneWaringStatus && passWordWarningStatus;
		
	}
	
}
