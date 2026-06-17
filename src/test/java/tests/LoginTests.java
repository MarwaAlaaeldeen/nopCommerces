package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Config;

import java.util.Objects;

public class LoginTests extends BaseTests {

    @Test
    public void TCLOG01_loginWithRegisteredCredentials() {
        String email =createUser();
        homePage.logout();
        homePage.clickLoginButton();

        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl())
                .contains("login"), "Expected to navigate to login page, but current URL is: " + driver.getCurrentUrl());
        loginPage.Login(email, Config.getProperty("validPassword"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/",
                "Expected to navigate back to Home page after login, but current URL is: " + driver.getCurrentUrl());
//The current nopCommerce demo version does not display the user email/name in the header.
// Therefore, successful authentication was verified through the presence of "My Account" and "Log out" links.
        Assert.assertTrue(loginPage.isMyAccountLinkDisplayed()&& loginPage.isLogoutLinkDisplayed(),
                "Expected My Account and Log out links to be visible after login, but they were not found.");
    }

    @Test
    public void TCLOG02_invalidLogin() {
       String email= createUser();
        homePage.logout();
        homePage.clickLoginButton();
        loginPage.Login(email,Config.getProperty("invalidPassword"));
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("login"),"Expected to remain on login page after invalid login attempt, but current URL is: " + driver.getCurrentUrl());
        Assert.assertTrue(loginPage.getLoginErrorMessage().contains("Login was unsuccessful"), "Expected error message not found.");
    }
}
