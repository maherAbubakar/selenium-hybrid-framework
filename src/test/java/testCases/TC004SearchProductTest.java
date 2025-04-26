package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004SearchProductTest extends BaseClass {

	@Test(groups= {"master"})
	public void verify_productSearch() {
		logger.info("Starting TC004SearchProductTest");
		try {
			HomePage homePage=new HomePage(driver);
			//homePage.enterProductName("iPhone");
			homePage.enterProductName("mac");
			homePage.clickSearch();
			
			SearchPage searchPage=new SearchPage(driver);
			searchPage.isProductExist("MacBook");
			Assert.assertEquals(searchPage.isProductExist("MacBook"),true);

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info(" Finished TC004SearchProductTest");

	}
}
