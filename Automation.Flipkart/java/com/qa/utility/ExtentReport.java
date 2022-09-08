package com.qa.utility;


	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.testng.ITestContext;
	import org.testng.ITestResult;
	import org.testng.TestListenerAdapter;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.markuputils.ExtentColor;
	import com.aventstack.extentreports.markuputils.MarkupHelper;
	import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

		public class ExtentReport extends TestListenerAdapter{

			public ExtentHtmlReporter htmlreporter;
			public ExtentReports xReports;
			public ExtentTest xTest;
			
			@Override
			public void onStart(ITestContext testContext) {
				
				String datestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				String repName="Test Automation-Reports"+datestamp+".html";
				htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+repName);
				htmlreporter.config().setDocumentTitle("Automation Testing");
				htmlreporter.config().setReportName("Functional Testing");
				htmlreporter.config().setTheme(Theme.DARK);
				
				xReports=new ExtentReports();
				xReports.attachReporter(htmlreporter);
				xReports.setSystemInfo("QA-Name", "Siddanagoud");
				xReports.setSystemInfo("host Name", "locaal host");
				xReports.setSystemInfo("OS ", "Windows-10");
				
				
			}

			@Override
			public void onFinish(ITestContext testContext) {
				xReports.flush();
				
			}

			
			@Override
			public void onTestSuccess(ITestResult tr) {
				
			xTest=xReports.createTest(tr.getName());
			xTest.log(Status.PASS, " Test is Pass");
			xTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

			
			}

			@Override
			public void onTestFailure(ITestResult tr) {
				xTest=xReports.createTest(tr.getName());
				xTest.log(Status.FAIL, " Test is Fail");
				xTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
				
				 String path= System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
				 File file= new File(path);
				 if(file.exists()) {
					 try {
						xTest.fail("screenshot of failed test cases is: "+xTest.addScreenCaptureFromPath(path));
					} catch (IOException e) {
						e.printStackTrace();
					}
					 
				 }
			}

			@Override
			public void onTestSkipped(ITestResult tr) {
				xTest=xReports.createTest(tr.getName());
				xTest.log(Status.SKIP, " Test is Skipped");
				xTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));
			
			}
	}

