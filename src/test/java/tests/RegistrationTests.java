package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Config;

import java.util.Objects;

public class RegistrationTests extends BaseTests {
    @Test
    public void TCREG01_registerWithValidCredentials () {
        //Test the registration functionality
        //Assertion to verify registration success

        homePage.navigateToRegisterPage();

        String uniqueEmail = "marwa" + System.currentTimeMillis() + "@gmail.com";
        System.out.println("EMAIL = " + uniqueEmail);
        registerPage.validRegister(Config.getProperty("firstName"),Config.getProperty("lastName"),uniqueEmail,Config.getProperty("validPassword"));
        String registrationSuccessMessage = registerPage.getSuccessMsg();
        Assert.assertEquals(registrationSuccessMessage, "Your registration completed");
    }
    @Test
    public void TCREG02_registrationWithInvalidCredentials() {

        homePage.navigateToRegisterPage();
        registerPage.invalidRegister();
        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("register"),
                "Expected to remain on registration page after invalid submission, but current URL is: " + driver.getCurrentUrl());

        Assert.assertEquals(registerPage.getFirstNameErrorMessage(),
                "First name is required.",
                "Incorrect First Name validation message");

        Assert.assertEquals(registerPage.getLastNameErrorMessage(),
                "Last name is required.",
                "Incorrect Last Name validation message");

        Assert.assertEquals(registerPage.getEmailErrorMessage(),
                "Email is required.",
                "Incorrect Email validation message");

        Assert.assertEquals(registerPage.getPasswordErrorMessage(),
                "Password is required.",
                "Incorrect Password validation message");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(),
                "Password is required.",
                "Incorrect Confirm Password validation message");
    }

}
