import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.CartPage;
import pageObject.onliner.CatalogPage;

public class CatalogTests extends BaseTest {
    private CatalogPage catalogPage;
    private CartPage cartPage;

    @BeforeTest
    public void precondition() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        catalogPage.navigateTo("https://catalog.onliner.by/");
    }

    @Test(priority = 1, description = "Add a product to cart")
    public void AddProductToCartTest() {
        catalogPage
                .searchForProduct("Iphone")
                .switchToFrame()
                .selectProduct(1)
                .addProductToCart()
                .openCart();
    }

    @Test(priority = 2, description = "Remove added product from cart")
    public void removeProductFromCartTest() {
        catalogPage
                .searchForProduct("Asus")
                .switchToFrame()
                .selectProduct(2)
                .addProductToCart()
                .openCart();
        cartPage
                .verifyCartTitle()
                .removeItemFromCart();
    }
}
