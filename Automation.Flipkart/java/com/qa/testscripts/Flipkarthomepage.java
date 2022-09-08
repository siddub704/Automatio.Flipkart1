package com.qa.testscripts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

class Flipkarthomepage extends FlipkartBaseclass{
	@Test
	public void homepage() throws InterruptedException{
		boolean un= fp.getUsername().isDisplayed();
		Thread.sleep(3000);
		s.assertTrue(un,"PASS");
		Reporter.log("successfully user name is displayed on home page"+,un)
		
		fp.getSearchtextfield().sendKeys("phone");
		boolean sear =fp.getSearchtextfield().isDisplayed();
		System.out.println(sear);
		if(sear) {
			s.assertTrue(sear);
		}else {
			s.assertFalse(sear);
		}

		WebElement link1 = fp.getUsername();
		Actions a=new Actions(driver);
		a.doubleClick(link1).build().perform();
		Thread.sleep(2000);
		fp.getMyprofilelink().click();
		boolean profile = driver.getTitle().contains("my profile");
		System.out.println(profile);
		if(profile) {
			s.assertTrue(profile);
		}else {
			s.assertFalse(profile);
		}
	}

}
