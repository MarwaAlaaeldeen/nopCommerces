package pages.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register {
    WebDriver driver;

    //variables
 public String uniqueEmail = "marwa" + System.currentTimeMillis() + "@gmail.com";
 public String firstName = "Marwa";
 public String lastName = "Alaaeldeen";
 public String password = "Marwa123";

    //locators
    private static final By registerLink = By.xpath("//a[@class='ico-register']");
    private static final By firstNameField = By.id("FirstName");
    private static final By lastNameField = By.id("LastName");
    private static final By emailField = By.id("Email");
    private static final By passwordField = By.id("Password");
    private static final By confirmPasswordField = By.id("ConfirmPassword");
    private static final By registerButton = By.xpath("//button[contains(.,'Register')]");
   private static final By logoutLink = By.xpath("//a[contains(.,'Log')]");
    //actions
    public Register(WebDriver driver) {
        this.driver = driver;
    }
   public void navigateToRegisterPage() {
        driver.findElement(registerLink).click();
    }

    public void register(String firstName, String lastName, String uniqueEmail , String password) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(uniqueEmail);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }
    public void logout() {
        driver.findElement(logoutLink).click();
    }

}
