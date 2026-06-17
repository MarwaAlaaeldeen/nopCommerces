package pages.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
    WebDriver driver;

    public Home(WebDriver driver) {
        this.driver = driver;
    }
    //variables


    //Locators
    private static final By registerLink = By.xpath("//a[contains(.,'Register')]");
    private static final By logoutLink = By.xpath("//a[contains(.,'Log')]");
    private static final By loginLink = By.xpath("//a[contains(.,'Log in')]");
    private static final String categoryselectXpath = "//*[contains(@class,'title')]/a[contains(.,'%s')]";
    private static final String subCategorySelectXpath ="//*[@class='title']/a[contains(.,'Cell')]";
    private static final String productSelectXpath ="//*[@class='product-title']/a[contains(.,'Apple iPhone')]";
    private static final By shoppingCartLink=By.xpath("//a[@class='ico-cart']/span[@class='cart-qty']");
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

public void setCategoryselect(String categoryName)
{
    driver.findElement(By.xpath(String.format(categoryselectXpath,categoryName))).click();
}
public void subCategorySelect( String subcategoryName)
{
    driver.findElement(By.xpath(String.format(subCategorySelectXpath,subcategoryName))).click();

}
public void selectProduct(String productName)
{
    driver.findElement(By.xpath(String.format(productSelectXpath,productName))).click();

}
public boolean shoppingCartIsDisplayed(){
   return  driver.findElement(shoppingCartLink).isDisplayed();
}

}
