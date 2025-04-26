package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;


public class TC005AddToCartPageTest extends BaseClass {
	
	@Test(groups= {"master"})
	public void verify_addToCart() {
		logger.info("Starting TC005AddToCartPageTest");
		try {
			HomePage homePage=new HomePage(driver);
			homePage.enterProductName("iPhone");
			homePage.clickSearch();

			SearchPage searchPage=new SearchPage(driver);
			if(searchPage.isProductExist("iPhone"))
			{
				searchPage.selectProduct("iPhone");
				searchPage.setQuantity("2");
				searchPage.addToCart();
				
			}
			Assert.assertEquals(searchPage.checkConfMsg(),true);

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("Finished TC005_AddToCartPageTest");

	}
}
