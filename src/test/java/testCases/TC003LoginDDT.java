package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "data-driven")
    public void verify_loginDDT(String email, String password, String expectedValue) {
        logger.info("**** Starting TC_003_LoginDDT *****");
        try {

            //Home page
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin(); //Login link under MyAccount

            //Login page
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin(); //Login button

            //My Account Page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExists();

            if (expectedValue.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }

            if (expectedValue.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    myAccountPage.clickLogout();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }

        logger.info("**** Finished TC_003_LoginDDT *****");
    }

}








