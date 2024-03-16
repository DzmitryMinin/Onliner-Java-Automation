package pageObject.onliner;

import org.openqa.selenium.By;
import pageObject.baseObjects.BasePage;

public class CatalogPage extends BasePage {
    private final By searchField = By.cssSelector("input[class='fast-search__input']");
    private final By catalogFrame = By.cssSelector("iframe[class='modal-iframe']");
    private final By productsList = By.cssSelector("a[class='product__title-link']");
    private final By addToCart = By.xpath("(//a[text() = 'В корзину'])[1]");
    //(//a[contains(@class, 'product-aside__button_cart')])[1]
    private final By cityPopup = By.xpath("//span[contains(@class, 'product-aside__button')]");
    private final By openCart = By.xpath("//a[contains(text(), 'Перейти в корзину')]");

    public CatalogPage searchForProduct(String productName) {
        enterValue(searchField, productName);
        submit(searchField);
        return this;
    }

    public CatalogPage navigateTo(String url) {
        open(url);
        return this;
    }

    public CatalogPage switchToFrame() {
        switchToFrame(catalogFrame);
        return this;
    }

    public CatalogPage clickCityBtn() {
        clickElement(cityPopup);
        return this;
    }

    public CatalogPage selectProduct(Integer index) {
        clickElementFromList(productsList, index);
        return this;
    }

    public CatalogPage addProductToCart() {
        clickElement(addToCart);
        return this;
    }

    public CatalogPage openCart() {
        clickElement(openCart);
        return this;
    }
}
