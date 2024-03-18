package pageObject.onliner.CatalogPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.baseObjects.BasePage;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HeadphonesPage extends BasePage {
    private final By restFilters = By.xpath("(//div[contains(@class, 'catalog-form__button_show-secondary')]//span)[1]");
    private final By cableLengthFrom = By.xpath("(//div[contains(text(), 'Длина кабеля')]/../../../following-sibling::div//input[@type='text'])[1]");
    private final By cableLengthUpTo = By.xpath("(//div[contains(text(), 'Длина кабеля')]/../../../following-sibling::div//input[@type='text'])[2]");
    private final By wiredHeadphones = By.xpath("(//input[@type='checkbox' and @name='wireless']//following-sibling::span)[2]");
    private final By productList = By.xpath("//a[@class='catalog-form__link catalog-form__link_primary-additional catalog-form__link_base-additional catalog-form__link_font-weight_semibold catalog-form__link_nodecor']");
    private final By loader = By.cssSelector(".catalog-interaction__state_animated");

    public HeadphonesPage navigateTo() {
        open("https://catalog.onliner.by/headphones");
        return this;
    }

    public HeadphonesPage enterLengthFrom(String lengthFrom) {
        enterValue(cableLengthFrom, lengthFrom);
        return this;
    }

    public HeadphonesPage enterLengthUpTo(String lengthUpTo) {
        enterValue(cableLengthUpTo, lengthUpTo);
        return this;
    }

    public HeadphonesPage expandRestFilters() {
        scrollTo(restFilters);
        clickElement(restFilters);
        return this;
    }

    public HeadphonesPage selectWiredHeadphones() {
        scrollTo(wiredHeadphones);
        clickElement(wiredHeadphones);
        return this;
    }

    public List<WebElement> getProductList() {
        List<WebElement> allProducts = driver.findElements(productList);
        return allProducts;
    }

    public HeadphonesPage checkLoader() {
        waitUntilInvisibilityOfElementLocated(loader);
        return this;
    }
}
