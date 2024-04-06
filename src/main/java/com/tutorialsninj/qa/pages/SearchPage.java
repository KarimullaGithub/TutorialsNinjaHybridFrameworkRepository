package com.tutorialsninj.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	
	
	@FindBy(linkText="HP LP3065")
	private WebElement validProductMessage;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	public WebElement acutalInvalidProductWarningMessage;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public boolean displayStatusOfHPvalidProduct() {
		boolean displayStatus=validProductMessage.isDisplayed();
		return displayStatus;
	}
	public String retrieveAcutalInvalidProductWarningMessage() {
		String AcutalInvalidProduct=acutalInvalidProductWarningMessage.getText();
		return AcutalInvalidProduct;
	}
}
