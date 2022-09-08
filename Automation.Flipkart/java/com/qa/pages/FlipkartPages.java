package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipkartPages {
	
	@FindBy(linkText ="Login")
	WebElement loginlink;
	
	@FindBy(xpath = "//div[text()='Home']")
	WebElement homellink;
	
	@FindBy(xpath ="//span[text()='Enter Email/Mobile number']/../../input")
	WebElement untextfield;

	@FindBy(xpath ="//input[@type='password']")
	WebElement pwdtextfield;

	@FindBy(xpath ="//form[@autocomplete='on']/div[4]/button")
	WebElement loginbtn;

	@FindBy(xpath ="//div[text()='My Profile']")
	WebElement myprofilelink;

	@FindBy(xpath = "//div[text()='Logout']")
	WebElement logoutbtn;

	@FindBy(xpath = "//div[contains(text(),'Siddanagoud')]")
	WebElement username;

	@FindBy(name = "q")
	WebElement searchtextfield;
	
	@FindBy(xpath = "//form[@method='GET']/div/button")
	WebElement magnifierbtn;

	public WebElement getMagnifierbtn() {
		return magnifierbtn;
	}

	public WebElement gethomellink() {
		return homellink;
	}
	
	public WebElement getLogoutbtn() {
		return logoutbtn;
	}

	public WebElement getUsername() {
		return username;
	}

	public WebElement getSearchtextfield() {
		return searchtextfield;
	}

	public WebElement getMyprofilelink() {
		return myprofilelink;
	}

	public WebElement getLoginlink() {
		return loginlink;
	}

	public WebElement getUntextfield() {
		return untextfield;
	}

	public WebElement getPwdtextfield() {
		return pwdtextfield;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}

	public FlipkartPages(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


}
