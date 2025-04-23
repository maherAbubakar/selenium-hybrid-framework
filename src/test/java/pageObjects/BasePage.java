package pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testBase.BaseClass;

import java.time.Duration;
@Slf4j
public class BasePage {

	WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;
	protected Actions builder;
	public BasePage(WebDriver driver)
	{
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.jsExecutor = ((JavascriptExecutor) driver);
		this.builder = new Actions(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public void waitTillWebElementIsVisible(String webElementName, WebElement webElement) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(webElement));
			log.info("webElementIsVisible", webElementName, webElement);
		} catch (Exception exception) {
			log.info("webElementIsNotVisible", webElementName, webElement, exception);
		}
	}

	public void waitTillWebElementIsEnabled(String webElementName, String webElement) {
		try {
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElement))).isEnabled();
			log.info("webElementIsEnabled", webElementName, webElement);
		} catch (Exception exception) {
			log.info("webElementIsNotEnabled", webElementName, webElement, exception);
		}
	}
	public void sendingValueToWebElement(String webElementName, WebElement webElement, String textToSend) throws AssertionError {
		try {
			waitTillWebElementIsVisible(webElementName, webElement);
			if (!(webElementName.equalsIgnoreCase("contractRateField") || webElementName.equalsIgnoreCase("residualValueDefinition"))) {
				clearField(webElement);
			}
			Thread.sleep(500);
			webElement.sendKeys(textToSend);
			log.info("webElementValueIsSet", webElementName + "'s value '" + textToSend + "'", webElement);
		} catch (Exception exception) {
			log.info("webElementValueIsNotSet", webElementName + "'s value '" + textToSend + "'", webElement, exception);
		}
	}

	public void actionMoveAndClick(String webElementName, WebElement webElement) throws AssertionError {
		Actions action = new Actions(driver);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
			action.moveToElement(webElement).click().build().perform();
			log.info("webElementIsClicked", webElementName, webElement);
		} catch (StaleElementReferenceException staleElementReferenceException) {
			Boolean isWebElementPresent = wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
			if (isWebElementPresent) {
				action.moveToElement(webElement).click().build().perform();
				log.info("webElementIsClicked", webElementName, webElement);
			}
		} catch (Exception exception) {
			log.info("webElementIsNotClicked", webElementName, webElement, exception);
		}
	}


	public void waitAndClickOnElement(WebElement element) throws InterruptedException {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		boolean clicked = false;
		int attempts = 0;
		Exception exceptionError = null;
		while (!clicked && attempts < 10) {
			try {
				tempWait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				System.out.println("Element has been clicked, Element :"+ element.toString());
				clicked = true;
			} catch (Exception exception) {
				exceptionError = exception;
				System.out.println("Unable to wait and click on WebElement, Exception: " + exception.getMessage());
				attempts++;
			}
		}
		if (attempts >= 9) {
			System.err.println("unable to click: " + getSelectorOfElement(element));
		}
	}

	public static String getSelectorOfElement(WebElement element) {
		String str = element.toString();
		return str.substring(str.indexOf("selector:") + 9, str.length() - 1);
	}

	public void clearField(WebElement element) {

		int lenText = element.getAttribute("value").length();

		for(int i = 0; i < lenText; i++){
			element.sendKeys(Keys.BACK_SPACE);
		}
	}


	public boolean isWebElementDisplayed(String webElementName, WebElement webElement) {
		return webElement.isDisplayed();
	}

	public String beautifyWebElement(WebElement webElement) {
		if (webElement.toString().contains("By.cssSelector")) {
			return webElement.toString().substring(webElement.toString().indexOf("cssSelector"), webElement.toString().length() - 1);
		} else {
			return "cssSelector".concat(webElement.toString().substring(webElement.toString().indexOf("css selector") + 12, webElement.toString().length() - 1));
		}
	}

	public String convertWebElementIntoString(WebElement webElement) {
		return webElement.toString().substring(webElement.toString().indexOf("css selector:") + 14, webElement.toString().length() - 1);
	}


}
