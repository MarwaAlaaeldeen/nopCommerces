package pages.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register {
    WebDriver driver;

    //variables


    //locators
    private static final By firstNameField = By.id("FirstName");
    private static final By lastNameField = By.id("LastName");
    private static final By emailField = By.id("Email");
    private static final By passwordField = By.id("Password");
    private static final By confirmPasswordField = By.id("ConfirmPassword");
    private static final By registerButton = By.xpath("//button[contains(.,'Register')]");
    private static final By logoutLink = By.xpath("//a[contains(.,'Log')]");
    private static final By firstNameFieldErrorMessage = By.xpath("//input[@id='FirstName']/following-sibling::span[@class='field-validation-error']");
    private static final By lastNameFieldErrorMessage = By.xpath("//input[@id='LastName']/following-sibling::span[@class='field-validation-error']");
    private static final By emailFieldErrorMessage = By.xpath("//input[@id='Email']/following-sibling::span[@class='field-validation-error']");
    private static final By passwordFieldErrorMessage = By.xpath("//input[@id='Password']/following-sibling::span[@class='field-validation-error']");
    private static final By confirmPasswordFieldErrorMessage = By.xpath("//input[@id='ConfirmPassword']/following-sibling::span[@class='field-validation-error']");
    private static final By regMessage=By.xpath("//div[@class='result']");
    //actions
    public Register(WebDriver driver) {
        this.driver = driver;
    }


    public void validRegister(String firstName, String lastName, String uniqueEmail , String password) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(uniqueEmail);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }
    public void invalidRegister() {
        driver.findElement(registerButton).click();
    }
    public String getFirstNameErrorMessage() {
        return driver.findElement(firstNameFieldErrorMessage).getText();
    }

    public String getLastNameErrorMessage() {
        return driver.findElement(lastNameFieldErrorMessage).getText();
    }

    public String getEmailErrorMessage() {
        return driver.findElement(emailFieldErrorMessage).getText();
    }

    public String getPasswordErrorMessage() {
        return driver.findElement(passwordFieldErrorMessage).getText();
    }

    public String getConfirmPasswordErrorMessage() {
        return driver.findElement(confirmPasswordFieldErrorMessage).getText();
    }
    public String getSuccessMsg(){
      return  driver.findElement(regMessage).getText();
    }

    public void backToHomePage() {
        driver.findElement(By.xpath("//a[contains(.,'Continue')]")).click();}


}
