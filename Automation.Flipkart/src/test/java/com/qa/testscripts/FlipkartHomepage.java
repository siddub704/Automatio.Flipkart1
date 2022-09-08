package com.qa.testscripts;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;
public class FlipkartHomepage extends FlipkartBaseclass{
	@Test(priority = 0)
	public void username() throws InterruptedException, IOException{
		boolean un= fp.getUsername().isDisplayed();
		if(un) {
		s.assertTrue(un,"PASS");
		Reporter.log("successfully user name is displayed on home page",true);
		}else {
			captureScreenshot(driver, "username");
			s.assertTrue(un,"FAIL");
			Reporter.log("user name is not displayed on home page",true);
		}
	}
	
	@Test(priority = 1)
	public void search() throws IOException {
		fp.getSearchtextfield().sendKeys("phone");
		boolean sear =fp.getSearchtextfield().isDisplayed();
		if(sear) {
			Reporter.log("Search fuctionality is present on the home page",true);
			s.assertTrue(sear);
		}else {
			captureScreenshot(driver, "search");
			Reporter.log("Search fuctionality is not present on the home page",true);
			s.assertFalse(sear);
		}
	}

	@Test(priority = 2)
	public void profile() throws IOException, InterruptedException {
		WebElement link1 = fp.getUsername();
		Actions a=new Actions(driver);
		a.doubleClick(link1).build().perform();
		Thread.sleep(2000);
		fp.getMyprofilelink().click();
		boolean profile = driver.getTitle().contains("my profile");
		Reporter.log("my profile option present on the homepage");
		if(profile) {
			s.assertTrue(profile);
		}else {
			captureScreenshot(driver, "profile");
			s.assertFalse(profile);
		}
	}
}


