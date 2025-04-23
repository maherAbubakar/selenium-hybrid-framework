package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001AccountRegistrationTest extends BaseClass {

	@Test(groups = "regression")
	public void verify_account_registration() {
		logger.info("***** Starting TC001AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("Clicked on MyAccount Link.. ");

			homePage.clickRegister();
			logger.info("Clicked on Register Link.. ");

			AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details...");
			accountRegistrationPage.setFirstName(randomString().toUpperCase());
			accountRegistrationPage.setLastName(randomString().toUpperCase());
			accountRegistrationPage.setEmail(randomString() + "@gmail.com");// randomly generated the email
			accountRegistrationPage.setTelephone(randomNumber());

			String password = randomAlphaNumeric();

			accountRegistrationPage.setPassword(password);
			accountRegistrationPage.setConfirmPassword(password);

			accountRegistrationPage.setPrivacyPolicy();
			accountRegistrationPage.clickContinue();

			logger.info("Validating expected message..");

			String confirmationMsg = accountRegistrationPage.getConfirmationMsg();
			Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!", "Confirmation message mismatch");

			logger.info("Test passed");
		} catch (Exception e) {
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		} finally {
			logger.info("***** Finished TC001AccountRegistrationTest *****");
		}

	}


}
