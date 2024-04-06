package com.tutorialsninj.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	@FindBy(linkText="View your order history")
	private WebElement ViewyourorderhistoryOption;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public boolean getDisplayStatusOfViewYourOrderHistoryOprtion() {
		boolean orderHistoryStatus=ViewyourorderhistoryOption.isDisplayed();
		return orderHistoryStatus;
	}
}
