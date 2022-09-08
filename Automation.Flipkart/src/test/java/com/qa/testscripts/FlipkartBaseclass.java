package com.qa.testscripts;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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


import com.qa.pages.Flipkartpages;
import com.qa.utility.Excelclass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartBaseclass {
	WebDriver driver=null;
	Flipkartpages fp;
	Actions a;
	SoftAssert s=new SoftAssert();
	@Parameters({"Browser","Url"})
	@BeforeClass
	public void setup(String Browser,String Url) {

		if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

		}
		else if (Browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}

		else if (Browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}

		else if (Browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Url);
		fp=new Flipkartpages(driver);

	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@BeforeMethod 
	public void login() throws InterruptedException, IOException {
		String file="C:\\Users\\acer\\eclipse-workspace2\\Automation.Flipkart\\src\\test\\java\\com\\qa\\testdata\\logincredential.xlsx";
		String sheetname="Sheet1";

		String number =Excelclass.getCellData(file, sheetname,1,0);
		String password =Excelclass.getCellData(file, sheetname,1,1);

		WebElement link = fp.getLoginlink();
		a=new Actions(driver);
		a.moveToElement(link).build().perform();
		fp.getUntextfield().sendKeys(number);
		fp.getPwdtextfield().sendKeys(password);
		fp.getLoginbtn().click();
		String atitle = driver.getTitle();
		String exptitle="Flipkart";
		boolean contain = atitle.contains(exptitle);
		if(contain) {
			s.assertTrue(true);
			Reporter.log("successfuly Home page is Displayed",true);
		}else {
			captureScreenshot(driver, sheetname);
			s.assertFalse(true);
			Reporter.log("Home page is not Displayed",true);
		}
	}

	@AfterMethod
	public void logout() {

		WebElement l=fp.getUsername();
		a.doubleClick(l).build().perform();
		fp.getLogoutbtn().click();
	}
	public void captureScreenshot(WebDriver driver,String tname) throws IOException {
		TakesScreenshot s=(TakesScreenshot) driver;
		File src=s.getScreenshotAs(OutputType.FILE);

		String dest=System.getProperty("user.dir")+"/Screenshots/"+tname+".png";
		FileUtils.copyFile(src, new File(dest));
	}
}

