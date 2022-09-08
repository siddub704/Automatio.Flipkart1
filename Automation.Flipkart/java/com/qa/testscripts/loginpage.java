package com.qa.testscripts;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class loginpage extends FlipkartBaseclass{
	@Test
	public void login() throws InterruptedException {

		WebElement link = fp.getLoginlink();
		Actions a=new Actions(driver);
		a.moveToElement(link).build().perform();
		fp.getUntextfield().sendKeys("9113642949");
		fp.getPwdtextfield().sendKeys("siddu123");
		fp.getLoginbtn().click();
		String atitle = driver.getTitle();
		String exptitle="Flipkart";
		if (atitle.contains(exptitle)) {
			System.out.println("successfuly Home page is Displayed");
		} else {
			System.out.println(" Home page is not Displayed");
		}

	}
	@DataProvider
	public String[][] getData() throws IOException{
		String file="\"C:\\Users\\JANI MOHD\\eclipse-workspace\\TestAutomation.Selenium\\src\\test\\java\\com\\qa\\testdata\\Sheet1.xlsx\"";
		String sheetname="Sheet1";
		
		int rowcount =ExcelUtility.getRowCount(file, sheetname);
		int cellcount=ExcelUtility.getCellCount(file, sheetname, rowcount);
		
				String[][] data1 =new String[rowcount][cellcount];
				for (int i = 1; i <=rowcount; i++) {
					for (int j = 0; j <cellcount; j++) {
						data1[i-1][j]=ExcelUtility.getCellData(file, sheetname, i, j);
					}
				}
		
		return data1;

}

}
