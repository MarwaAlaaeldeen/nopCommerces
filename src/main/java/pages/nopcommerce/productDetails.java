package pages.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class productDetails {
    WebDriver driver;

    public productDetails(WebDriver driver) {
        this.driver = driver;
    }
    //locators
private static final By addToCartBtn=By.xpath("//button[contains(.,'Add to cart')]");
    //actions
public void addToCartClick()
{
    driver.findElement(addToCartBtn).click();

}




}