package tests;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.nopcommerce.*;
import utils.Config;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



/*
 * Open the browser
 * Navigate to nopcommerce
 * Test the registration functionality
 * Test the login functionality
 * Test the shopping cart functionality
 * Close the browser
 */
public class NopCommerceTests {
    String uniqueEmail = "marwa" + System.currentTimeMillis() + "@gmail.com";
    WebDriver driver;
   Register registerPage;
   Login loginPage;
   productDetails productDetailsPage;
   Cart cartPage;
   home homePage;

    @Test
    public void TCREG01_registerWithValidCredentials () {
        //Test the registration functionality
        //Assertion to verify registration success

        homePage.navigateToRegisterPage();
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

  @Test
  public void TCLOG01_loginWithRegisteredCredentials() {
createUser();
homePage.logout();
homePage.clickLoginButton();

      Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl())
              .contains("login"), "Expected to navigate to login page, but current URL is: " + driver.getCurrentUrl());
        loginPage.validLogin(uniqueEmail, Config.getProperty("validPassword"));
Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/",
        "Expected to navigate back to home page after login, but current URL is: " + driver.getCurrentUrl());
//The current nopCommerce demo version does not display the user email/name in the header.
// Therefore, successful authentication was verified through the presence of "My Account" and "Log out" links.
Assert.assertTrue(loginPage.isMyAccountLinkDisplayed()&& loginPage.isLogoutLinkDisplayed(),
        "Expected My Account and Log out links to be visible after login, but they were not found.");
  }

@Test
public void TCLOG02_invalidLogin() {
createUser();
homePage.logout();
homePage.clickLoginButton();
loginPage.invalidLogin(uniqueEmail,Config.getProperty("invalidPassword"));
Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("login"),"Expected to remain on login page after invalid login attempt, but current URL is: " + driver.getCurrentUrl());
Assert.assertTrue(loginPage.getLoginErrorMessage().contains("Login was unsuccessful"), "Expected error message not found.");
   }

@Test
public void TCCART01_addProductToCart() {
createUser();
registerPage.backToHomePage();
    homePage.setCategoryselect();
Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("electronics"),
        "Expected to navigate to Electronics category page, but current URL is: " + driver.getCurrentUrl());

homePage.subCategorySelect();
    Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("cell-phones"),
            "Expected to navigate to Cell Phones category page, but current URL is: " + driver.getCurrentUrl());
    homePage.selectProduct();
    Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("apple-iphone"),
            "Expected to navigate to Apple iPhone product page, but current URL is: " + driver.getCurrentUrl());
    productDetailsPage.addToCartClick();
    String expectedName = cartPage.getPoductName();
    String expectedPrice = cartPage.getPoductPrice();
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    wait.until(d -> driver.findElement(By.xpath("//a[@class='ico-cart']/span[@class='cart-qty']")).isDisplayed());
cartPage.shoppingCartClick();
   Assert.assertTrue( cartPage.getPoductCartPrice().equals(expectedPrice)&& cartPage.getPoductQuantity().equals("1"),
           "Expected product details not found in cart."+"Expected product name: " + expectedName + ", but found: " + cartPage.getPoductCartName() + "\nExpected product price: " + expectedPrice + ", but found"+ cartPage.getPoductCartPrice() + "\nExpected quantity: 1, but found: " + cartPage.getPoductQuantity());

}
@Test
public void TCCART02_updateProductQuantityInCart() {
createUser();
registerPage.backToHomePage();
homePage.setCategoryselect();
homePage.subCategorySelect();
homePage.selectProduct();
productDetailsPage.addToCartClick();
cartPage.shoppingCartClick();
cartPage.updateProductQuantity(Config.getProperty("updateQty"));

    Assert.assertEquals(Config.getProperty("updateQty"), cartPage.getPoductQuantity(), "Expected quantity to be updated to " + Config.getProperty("updateQty") + ", but found: " + cartPage.getPoductQuantity());
    double expectedSubtotal =
            cartPage.getProductCartPriceAsDouble()
                    * Integer.parseInt(cartPage.getPoductQuantity());
    Assert.assertEquals(
            cartPage.getActualSubtotal(),
            expectedSubtotal,
            "Subtotal calculation is incorrect."
    );
   }
@Test
public void TCCART03_removeProductFromCart() {
       createUser();
        registerPage.backToHomePage();
       homePage.setCategoryselect();
       homePage.subCategorySelect();
       homePage.selectProduct();
       productDetailsPage.addToCartClick();
       cartPage.shoppingCartClick();
       cartPage.removeProductFromCart();
    Assert.assertTrue(cartPage.getEmptyCartMessage().contains("Your Shopping Cart is empty!"), "Expected empty cart message not found.");
    Assert.assertEquals(
            cartPage.getShoppingCartQuantity(),
            "(0)",
            "Expected shopping cart quantity to be (0) after removing product, but found: "
                    + cartPage.getShoppingCartQuantity());
    }
    private void createUser() {
        homePage.navigateToRegisterPage();
        registerPage.validRegister(
                Config.getProperty("firstName"),
                Config.getProperty("lastName"),
                uniqueEmail,
                Config.getProperty("validPassword"));
    }
    @BeforeMethod
    public void beforeMethod() throws IOException, InterruptedException {

        ChromeOptions options;
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--position=0,0");
        // Additional ChromeOptions were required because the Cloudflare
        // verification challenge was not consistently resolved using the
        // manual verification workaround alone.
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        // Disable password manager to prevent interference with test execution
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        Config config = new Config();
        registerPage = new Register(driver);
       loginPage= new Login(driver);
       productDetailsPage = new productDetails(driver);
       cartPage = new Cart(driver);
       homePage=new home(driver);
        driver.navigate().to(Config.getProperty("baseUrl"));
        // Temporary workaround recommended by assessment owner
        // to allow manual Cloudflare verification
        Thread.sleep(15000);

    }
    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}