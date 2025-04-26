package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;
import java.net.URL;

//Extent report 5.x...//version

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	String reportName;

	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		reportName = "Test-Report-" + timeStamp + ".html";
		extentSparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);// specify location of the report

		extentSparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of report
		extentSparkReporter.config().setReportName("Opencart Functional Testing"); // name of the report
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Application", "Opencart");
		extentReports.setSystemInfo("Module", "Admin");
		extentReports.setSystemInfo("Sub Module", "Customers");
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extentReports.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extentReports.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extentReports.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups()); // to display groups in report
		extentTest.log(Status.PASS,result.getName()+" got successfully executed");
		
	}

	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		WebDriver driver = ((BaseClass) testClass).driver;
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL,result.getName()+" got failed");
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName(),driver);
			extentTest.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extentReports.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*  try {
			  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		  
		  // Create the email message 
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com"); 
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
		  email.setSSLOnConnect(true);
		  email.setFrom("pavanoltraining@gmail.com"); //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
		  email.attach(url, "extent report", "please check report..."); 
		  email.send(); // send the email 
		  }
		  catch(Exception e) 
		  { 
			  e.printStackTrace(); 
			  }
		 */ 
		 
	}

}
