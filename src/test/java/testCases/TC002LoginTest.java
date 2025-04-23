package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002LoginTest extends BaseClass {

    @Test(groups = {"sanity", "master"})
    public void verify_login() {
        logger.info("**** Starting TC_002_LoginTest  ****");
        logger.debug("capturing application debug logs....");
        try {
            //Home page
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("clicked on my account link on the home page..");
            homePage.clickLogin(); //Login link under MyAccount
            logger.info("clicked on login link under my account..");

            //Login page
            LoginPage loginPage = new LoginPage(driver);
            logger.info("Entering valid email and password..");
            loginPage.setEmail(properties.getProperty("email"));
            loginPage.setPassword(properties.getProperty("password"));
            loginPage.clickLogin(); //Login button
            logger.info("clicked on login button..");

            //My Account Page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExists();
            Assert.assertTrue(targetPage, "Login failed");
        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("**** Finished TC_002_LoginTest  ****");
    }
}
