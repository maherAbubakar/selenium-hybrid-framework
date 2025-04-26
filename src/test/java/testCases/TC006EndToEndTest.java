/**
 *
 */
package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC006EndToEndTest extends BaseClass {

    @Test(groups = {"master"})
    public void endToEndTest() throws InterruptedException {
        //Soft assertions
        SoftAssert softAssert = new SoftAssert();

        //Account Registration
        System.out.println("Account Registration....");
        HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickRegister();
        AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);
		accountRegistrationPage.setFirstName(randomString().toUpperCase());
		accountRegistrationPage.setLastName(randomString().toUpperCase());

        String email = randomString() + "@gmail.com";
		accountRegistrationPage.setEmail(email);// randomly generated the email

		accountRegistrationPage.setTelephone("1234567");
		accountRegistrationPage.setPassword("test123");
		accountRegistrationPage.setConfirmPassword("test123");
		accountRegistrationPage.setPrivacyPolicy();
		accountRegistrationPage.clickContinue();

        String confirmationMessage = accountRegistrationPage.getConfirmationMsg();
        System.out.println(confirmationMessage);
		softAssert.assertEquals(confirmationMessage, "Your Account Has Been Created!"); //validation

        MyAccountPage myAccountPage = new MyAccountPage(driver);
		myAccountPage.clickLogout();

        //Login
        System.out.println("Login to application...............");
        homePage.clickMyAccount();
		homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(email);
		loginPage.setPassword("test123");
		loginPage.clickLogin();


        System.out.println("Going to My Account Page? " + myAccountPage.isMyAccountPageExists());
		softAssert.assertEquals(myAccountPage.isMyAccountPageExists(), true); //validation


        //search & add product to cart
        System.out.println("search & add product to cart...............");
		homePage.enterProductName(properties.getProperty("searchProductName"));
		homePage.clickSearch();

        SearchPage searchPage = new SearchPage(driver);

        if (searchPage.isProductExist(properties.getProperty("searchProductName"))) {
			searchPage.selectProduct(properties.getProperty("searchProductName"));
			searchPage.setQuantity("2");
			searchPage.addToCart();

        }
        System.out.println("Added product to cart ? " + searchPage.checkConfMsg());
		softAssert.assertEquals(searchPage.checkConfMsg(), true);


        //Shopping cart
        System.out.println("Shopping cart......");
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.clickItemsToNavigateToCart();
		shoppingCartPage.clickViewCart();
        String totalPrice = shoppingCartPage.getTotalPrice();
        System.out.println("Total price is shopping cart: " + totalPrice);
		softAssert.assertEquals(totalPrice, "$246.40");   //validation
		shoppingCartPage.clickOnCheckout(); //navigate to checkout page

        //Checkout Product
        System.out.println("Checkout Product....");
        CheckoutPage checkoutPage = new CheckoutPage(driver);

		checkoutPage.setFirstName(randomString().toUpperCase());
		checkoutPage.setLastName(randomString().toUpperCase());
		checkoutPage.setFirstAddress("Gulberg");
		checkoutPage.setSecondAddress("Gulberg");
		checkoutPage.setCity("Lahore");
		checkoutPage.setPin("500070");
		checkoutPage.setCountry("Pakistan");
		checkoutPage.setState("Punjab");
		checkoutPage.clickOnContinueAfterBillingAddress();
		checkoutPage.clickOnContinueAfterDeliveryAddress();
		checkoutPage.setDeliveryMethodComment("Testing...");
		checkoutPage.clickOnContinueAfterDeliveryMethod();
		checkoutPage.selectTermsAndConditions();
		checkoutPage.clickOnContinueAfterPaymentMethod();

        String total_price_inOrderPage = checkoutPage.getTotalPriceBeforeConfOrder();
        System.out.println("total price in confirmed order page:" + total_price_inOrderPage);
		softAssert.assertEquals(total_price_inOrderPage, "$207.00"); //validation

        //Below code works only if you configure SMTP for emails
	/*ch.clickOnConfirmOrder();
	Thread.sleep(3000);
		
	boolean orderconf=ch.isOrderPlaced();
	System.out.println("Is Order Placed? "+orderconf);
	myassert.assertEquals(ch.isOrderPlaced(),true);*/

		softAssert.assertAll(); //conclusion
    }

}


