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
    private static final By productPriceLocator = By.xpath("//div[@class='product-price']");
    private static final By productNameLocator = By.xpath("//div[@class='product-name']/h1");
    private static final By productCartNameLabel = By.xpath("//a[@class='product-name']");
    private static final By productCartPriceLabel = By.xpath("//span[@class='product-unit-price']");
    private static final By productQuantityInput = By.xpath("//input[@class='qty-input']");
    private static final By subTotalPriceLabel = By.xpath("//span[@class='product-subtotal']");
    private static final By updateCartButton = By.xpath("//button[@id='updatecart']");
    private static final By removeProductButton = By.xpath("//button[@class='remove-btn']");
    private static final By emptyCartMessageLabel = By.xpath("//div[@class='no-data']");
    private static final By shoppingCartLink = By.xpath("//a[@class='ico-cart']/span[@class='cart-qty']");


//methods
    public String getProductQuantity() {
        return driver.findElement(productQuantityInput).getAttribute("value");
    }

    public String getProductCartPrice() {
        return driver.findElement(productCartPriceLabel).getText();
    }

    public String getProductCartName() {
        return driver.findElement(productCartNameLabel).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPriceLocator).getText();
    }

    public String getProductName() {
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
        return Double.parseDouble(getProductCartPrice()
                        .replace("$", "").replace(",", "").trim());
    }
    public double getActualSubtotal() {
        return Double.parseDouble(
                getSubTotalPrice()
                        .replace("$", "").replace(",", "").trim());
    }

    public void removeProductFromCart() {
        driver.findElement(removeProductButton).click();

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