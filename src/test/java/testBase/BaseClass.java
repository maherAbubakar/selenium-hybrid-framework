package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j


public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties properties;

	@BeforeClass(groups = {"sanity","master","regression","data-driven"})
	@Parameters({"operatingSystem", "browser"})
	public void setup(@Optional("Windows")String operatingSystem,@Optional("chrome") String browser) throws IOException {
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 properties=new Properties();
		 properties.load(file);
		//loading log4j file
		logger=LogManager.getLogger(this.getClass());//Log4j

         //Selenium Grid
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            //Operating Systems
			if(operatingSystem.equalsIgnoreCase("windows")){
				desiredCapabilities.setPlatform(Platform.WIN11);
			} else if(operatingSystem.equalsIgnoreCase("mac")){
				desiredCapabilities.setPlatform(Platform.MAC);
			} else if(operatingSystem.equalsIgnoreCase("linux")){
				desiredCapabilities.setPlatform(Platform.LINUX);
			}else{
				System.out.println("No Matching OS");
				return;
			}
			//Browsers
			switch ((browser.toLowerCase())){
				case "chrome" -> desiredCapabilities.setBrowserName("chrome");
				case "edge" -> desiredCapabilities.setBrowserName("MicrosoftEdge");
				case "firefox" -> driver = new FirefoxDriver();
				default -> {
					System.out.println("No matching browser..");
					return;
				}
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities,false);
		}
		//Local
		if(properties.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
				case "chrome" -> driver = new ChromeDriver();
				case "edge" -> driver = new EdgeDriver();
				case "firefox" -> driver = new FirefoxDriver();
				default -> {
					System.out.println("No matching browser..");
					return;
				}
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	public String captureScreen(String testName,WebDriver driver) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}
	
	@AfterClass(groups = {"sanity","master","regression","data-driven"})
	public void tearDown()
	{
		driver.quit();
	}

	public String randomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumber()
	{
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		return (str+"@"+num);
	}
}
