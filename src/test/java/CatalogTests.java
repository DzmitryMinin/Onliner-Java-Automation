import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.CartPage;
import pageObject.onliner.CatalogPages.CatalogPage;

public class CatalogTests extends BaseTest {
    @Parameters({"url"})
    @BeforeTest
    public void precondition(String url) {
        get(CatalogPage.class).navigateTo(url);
    }

    @Test(priority = 1, description = "Add a product to cart")
    public void addProductToCart() {
        get(CatalogPage.class)
                .searchForProduct("Iphone")
                .switchToFrame()
                .selectProductFromSearchList(1)
                .addProductToCart()
                .openCart();
    }

    @Test(priority = 2, description = "Remove added product from cart")
    public void removeProductFromCart() {
        get(CatalogPage.class)
                .searchForProduct("Asus")
                .switchToFrame()
                .selectProductFromSearchList(2)
                .addProductToCart()
                .openCart();
        get(CartPage.class)
                .verifyCartTitle()
                .removeItemsFromCart();
    }
}