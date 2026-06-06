package pages.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Cart {
    WebDriver driver;

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    //TODO: EDIT LOCATORS OF DETAILS PAGE TO BE DYNAMIC
    private static final By prductPriceLocator = By.xpath("//div[@class='product-price']");
    private static final By productNameLocator = By.xpath("//div[@class='product-name']/h1");
    private static final By productCartNameLable = By.xpath("//a[@class='product-name']");
    private static final By productCartPriceLable = By.xpath("//span[@class='product-unit-price']");
    private static final By productQuantityInput = By.xpath("//input[@class='qty-input']");
    private static final By subTotalPriceLabel = By.xpath("//span[@class='product-subtotal']");
   private static final By updateCartButton = By.xpath("//button[@id='updatecart']");
    private static final By removeProductButton = By.xpath("//button[@class='remove-btn']");
private static final By emptyCartMessageLabel = By.xpath("//div[@class='no-data']");
private static final By shoppingCartLink = By.xpath("//a[@class='ico-cart']/span[@class='cart-qty']");


//methods
    public String getPoductQuantity() {
        return driver.findElement(productQuantityInput).getAttribute("value");
    }

    public String getPoductCartPrice() {
        return driver.findElement(productCartPriceLable).getText();
    }

    public String getPoductCartName() {
        return driver.findElement(productCartNameLable).getText();
    }

    public String getPoductPrice() {
        return driver.findElement(prductPriceLocator).getText();
    }

    public String getPoductName() {
        return driver.findElement(productNameLocator).getText();
    }


    public void updateProductQuantity(String quantity) {
        productQuantityInput.findElement(driver).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        productQuantityInput.findElement(driver).sendKeys(quantity);
        // Click the update cart button using JavaScript to ensure it works even if the button is not interactable
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", driver.findElement(updateCartButton));
    }

    public String getSubTotalPrice() {
        return driver.findElement(subTotalPriceLabel).getText();
    }


    public double getProductCartPriceAsDouble() {
        return Double.parseDouble(
                getPoductCartPrice()
                        .replace("$", "")
                        .replace(",", "")
                        .trim());
    }
    public double getActualSubtotal() {
        return Double.parseDouble(
                getSubTotalPrice()
                        .replace("$", "")
                        .replace(",", "")
                        .trim());
    }

    public void removeProductFromCart() {
        driver.findElement(removeProductButton).click();
        // Click the update cart button using JavaScript to ensure it works even if the button is not interactable
//        ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].click();", driver.findElement(updateCartButton));


    }
    public String getEmptyCartMessage() {
        return driver.findElement(emptyCartMessageLabel).getText();
    }
    public String getShoppingCartQuantity() {
        return driver.findElement(shoppingCartLink).getText();
    }
    public void shoppingCartClick(){
        driver.findElement(shoppingCartLink).click();
    }
}