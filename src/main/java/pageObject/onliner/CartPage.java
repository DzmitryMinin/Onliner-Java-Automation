package pageObject.onliner;

import org.openqa.selenium.*;
import org.testng.Assert;
import pageObject.baseObjects.BasePage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    private final By cartTitle = By.xpath("//div[contains(@class, 'cart-form__title')]");
    private final By removeItem = By.xpath("//a[contains(@class, 'cart-form__button_remove')]");
    private final By productsPriceList = By.xpath("//div[@class='cart-form__offers-part cart-form__offers-part_price helpers_show_tablet']/div[contains(@class, 'cart-form__description_base-alter')]");
    private final By cartSum = By.xpath("//div[@class='cart-form__description cart-form__description_other cart-form__description_base-alter cart-form__description_condensed-other']");
    double sum = 0.0;

    public CartPage removeItemsFromCart() {
        List<WebElement> productsToRemove = getListOfWebElements(removeItem);
        for (int i = 0; i < productsToRemove.size(); i++) {
            WebElement productToRemove = productsToRemove.get(i);
            actions.moveToElement(productToRemove).click(productToRemove).click(productToRemove).perform();
            refreshPage();
            productsToRemove = getListOfWebElements(removeItem);
        }
        return this;
    }

    public CartPage verifyCartTitle() {
        Assert.assertTrue(getWebElement(cartTitle).getText().equals("Корзина"), "Incorrect title");
        return this;
    }

    public Double getProductsSum() {
        List<WebElement> productPriceList = getListOfWebElements(this.productsPriceList);
        List<String> cartPrices = productPriceList
                .stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());
        for (String str : cartPrices) {
            String numericValue = extractNumericValue(str);
            if (numericValue != null) {
                sum += Double.parseDouble(numericValue.replace(",", "."));
            }
        }
        return sum;
    }

    public Double getCartSum() {
        String numericValue = extractNumericValue(getWebElement(cartSum).getText());
        return Double.parseDouble(numericValue.replace(",", "."));
    }

    public static String extractNumericValue(String input) {
        Pattern pattern = Pattern.compile("[0-9]+,[0-9]+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String match = matcher.group();
            if (!match.isEmpty()) {
                return match;
            }
        }
        return null;
    }
}
