package com.qa.testscripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.pages.FlipkartPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartBaseclass {
	WebDriver driver=null;
	FlipkartPages fp;
	Actions a;
	SoftAssert s=new SoftAssert();
	@Parameters({"Browser","Url"})
	@BeforeClass
	public void setup(String Browser,String Url) {

		WebDriverManager.chromedriver().setup();
		if(Browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();

		}
		else if (Browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}

		else if (Browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}

		else if (Browser.equalsIgnoreCase("ie")) {
			driver=new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Url);
		fp=new FlipkartPages(driver);
	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@BeforeMethod 
	public void login(String number,String password ) throws InterruptedException, IOException {
		String file="C:\\Users\\acer\\eclipse-workspace2\\Automation.Flipkart\\java\\com\\qa\\testdata\\logincredential.xlsx";
		String sheetname="Sheet1";
		
		String number =ExcelUtility.getCellData(file, sheetname,1,0);
		String password =ExcelUtility.getCellData(file, sheetname,1,1);

		WebElement link = fp.getLoginlink();
		a=new Actions(driver);
		a.moveToElement(link).build().perform();
		fp.getUntextfield().sendKeys(number);
		fp.getPwdtextfield().sendKeys(password);
		fp.getLoginbtn().click();
		String atitle = driver.getTitle();
		String exptitle="Flipkart";
		s.assertEquals(atitle, exptitle);
		Reporter.log("successfuly Home page is Displayed",true);

	}

	@AfterMethod
	public void logout() {

		WebElement l=fp.getUsername();
		a.doubleClick(l).build().perform();
		fp.getLogoutbtn().click();
	}
}

