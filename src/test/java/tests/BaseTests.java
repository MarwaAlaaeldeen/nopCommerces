package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.nopcommerce.*;
import utils.Config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTests {

    protected WebDriver driver;

    protected Register registerPage;
    protected Login loginPage;
    protected ProductDetails productDetailsPage;
    protected Cart cartPage;
    protected Home homePage;

    protected String createUser() {

        String uniqueEmail =
                "marwa" + System.currentTimeMillis() + "@gmail.com";
        System.out.println("CREATED EMAIL = " + uniqueEmail);
        homePage.navigateToRegisterPage();
        registerPage.validRegister(
                Config.getProperty("firstName"),
                Config.getProperty("lastName"),
                uniqueEmail,
                Config.getProperty("validPassword"));
        return uniqueEmail;
    }
    @BeforeMethod
    public void beforeMethod() throws InterruptedException, IOException {

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
        productDetailsPage = new ProductDetails(driver);
        cartPage = new Cart(driver);
        homePage=new Home(driver);
        driver.navigate().to(Config.getProperty("baseUrl"));
        // Temporary workaround recommended by assessment owner
        // to allow manual Cloudflare verification
        Thread.sleep(15000);

    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
