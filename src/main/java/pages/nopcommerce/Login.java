package pages.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

    WebDriver driver;


    public Login(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private static final By loginLink = By.xpath("//a[contains(.,'Log in')]");
    private static final By emailField = By.xpath("//input[@class='email']");
    private static final By passwordField = By.xpath("//div[@class='login-password']/child::input");
    private static final By loginButton = By.xpath("//button[contains(.,'Log in')]");
    private static final By myAccountLink = By.xpath("//div[@class='header-links']//a[contains(.,'My account')]");
    private static final By logoutLink = By.xpath("//div[@class='header-links']//a[.='Log out']");
    private static final By loginErrorMessage = By.xpath("//div[contains(@class,'message-error')]");
    //actions
    public void validLogin(String email, String password) {
        driver.findElement(loginLink).click();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
    public void invalidLogin(String email, String password) {
        driver.findElement(loginLink).click();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
    public String getLoginErrorMessage() {
        return driver.findElement(loginErrorMessage).getText();
    }
    public boolean isMyAccountLinkDisplayed() {
        return driver.findElement(myAccountLink).isDisplayed();
    }
    public boolean isLogoutLinkDisplayed() {
        return driver.findElement(logoutLink).isDisplayed();
    }
}
