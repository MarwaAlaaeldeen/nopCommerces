package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Config;

import java.time.Duration;
import java.util.Objects;

public class CartTests extends BaseTests {

    @Test
    public void TCCART01_addProductToCart()  {
     createUser();
        registerPage.backToHomePage();
        homePage.setCategoryselect(Config.getProperty("categoryName"));
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("electronics"),
                "Expected to navigate to Electronics category page, but current URL is: " + driver.getCurrentUrl());

        homePage.subCategorySelect(Config.getProperty("subcategoryName"));
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("cell-phones"),
                "Expected to navigate to Cell Phones category page, but current URL is: " + driver.getCurrentUrl());
        homePage.selectProduct(Config.getProperty("productName"));
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("apple-iphone"),
                "Expected to navigate to Apple iPhone product page, but current URL is: " + driver.getCurrentUrl());
        productDetailsPage.addToCartClick();
        String expectedName = cartPage.getProductName();
        String expectedPrice = cartPage.getProductPrice();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(_ -> homePage.shoppingCartIsDisplayed());
        cartPage.shoppingCartClick();
        Assert.assertTrue( cartPage.getProductCartPrice().equals(expectedPrice)&& cartPage.getProductQuantity().equals("1"),
                "Expected product details not found in cart."+"Expected product name: " + expectedName + ", but found: " + cartPage.getProductCartName() + "\nExpected product price: " + expectedPrice + ", but found"+ cartPage.getProductCartPrice() + "\nExpected quantity: 1, but found: " + cartPage.getProductQuantity());

    }
    @Test
    public void TCCART02_updateProductQuantityInCart() {
       createUser();
        registerPage.backToHomePage();
        homePage.setCategoryselect(Config.getProperty("categoryName"));
        homePage.subCategorySelect(Config.getProperty("subcategoryName"));
        homePage.selectProduct(Config.getProperty("productName"));
        productDetailsPage.addToCartClick();
        cartPage.shoppingCartClick();
        cartPage.updateProductQuantity(Config.getProperty("updateQty"));

        Assert.assertEquals(Config.getProperty("updateQty"), cartPage.getProductQuantity(), "Expected quantity to be updated to " + Config.getProperty("updateQty") + ", but found: " + cartPage.getProductQuantity());
        double expectedSubtotal =
                cartPage.getProductCartPriceAsDouble()
                        * Integer.parseInt(cartPage.getProductQuantity());
        Assert.assertEquals(
                cartPage.getActualSubtotal(),
                expectedSubtotal,
                "Subtotal calculation is incorrect."
        );
    }
    @Test
    public void TCCART03_removeProductFromCart() {
      String email=  createUser();
        System.out.println();
        registerPage.backToHomePage();
        homePage.setCategoryselect(Config.getProperty("categoryName"));
        homePage.subCategorySelect(Config.getProperty("subcategoryName"));
        homePage.selectProduct(Config.getProperty("productName"));
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
}
