package pageObject.onliner;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObject.baseObjects.BasePage;

public class CartPage extends BasePage {
    private final By cartTitle = By.xpath("//div[contains(@class, 'cart-form__title')]");
    private final By removeItem = By.xpath("//a[contains(@class, 'cart-form__button_remove')]");
    private final By productLink = By.xpath("(//a[contains(@class, 'cart-form__link_base-alter')])[2]");

    public CartPage removeItemFromCart() {
        moveOverElementAndClick(driver.findElement(removeItem));
        return this;
    }

    public CartPage verifyCartTitle() {
        Assert.assertTrue(driver.findElement(cartTitle).getText().equals("Корзина"), "Incorrect title");
        return this;
    }
}
