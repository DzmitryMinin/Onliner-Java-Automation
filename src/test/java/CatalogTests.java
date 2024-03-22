import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.CartPage;
import pageObject.onliner.CatalogPages.CatalogPage;

public class CatalogTests extends BaseTest {
    private CatalogPage catalogPage;
    private CartPage cartPage;

    @BeforeTest
    public void precondition() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        catalogPage.navigateToCatalog();
    }

    @Test(priority = 1, description = "Add a product to cart")
    public void AddProductToCartTest() {
        catalogPage
                .searchForProduct("Iphone")
                .switchToFrame()
                .selectProductFromSearchList(1)
                .addProductToCart()
                .openCart();
    }

    @Test(priority = 2, description = "Remove added product from cart")
    public void removeProductFromCartTest() {
        catalogPage
                .searchForProduct("Asus")
                .switchToFrame()
                .selectProductFromSearchList(2)
                .addProductToCart()
                .openCart();
        cartPage
                .verifyCartTitle()
                .removeItemsFromCart();
    }
}
