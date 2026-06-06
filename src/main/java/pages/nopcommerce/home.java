package pages.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class home {
    WebDriver driver;

    public home(WebDriver driver) {
        this.driver = driver;
    }
    //variables


    //Locators
    private static final By registerLink = By.xpath("//a[contains(.,'Register')]");
    private static final By logoutLink = By.xpath("//a[contains(.,'Log')]");
    private static final By loginLink = By.xpath("//a[contains(.,'Log in')]");
    private static final By categoryselect = By.xpath("//*[contains(@class,'title')]/a[contains(.,'Electronics')]");
    private static final By subCategorySelect =By.xpath("//*[@class='title']/a[contains(.,'Cell')]");
    private static final By productSelect =By.xpath("//*[@class='product-title']/a[contains(.,'Apple iPhone')]");

    //actions
    public void navigateToRegisterPage()
    {
        driver.findElement(registerLink).click();
    }
    public void logout() {
        driver.findElement(logoutLink).click();
    }
public void clickLoginButton()
{
    driver.findElement(loginLink).click();

}

public void setCategoryselect()
{
    driver.findElement(categoryselect).click();
}
public void subCategorySelect()
{
    driver.findElement(subCategorySelect).click();

}
public void selectProduct()
{
    driver.findElement(productSelect).click();

}


}
