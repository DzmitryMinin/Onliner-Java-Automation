import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.CartPage;
import pageObject.onliner.CatalogPages.CatalogPage;

public class CartTests extends BaseTest {
    private CartPage cartPage;
    private CatalogPage catalogPage;
    @BeforeTest
    public void precondition() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        catalogPage.navigateToCatalog();
    }

    @Test(priority = 1, description = "Add products to cart and verify if all products price equals total sum", enabled = false)
    public void addProductsToCart() {
        catalogPage
                .searchForProduct("Iphone")
                .switchToFrame()
                .selectProductFromSearchList(1)
                .addProductToCart()
                .searchForProduct("Asus")
                .switchToFrame()
                .selectProductFromSearchList(4)
                .addProductToCart()
                .openCart();
        Assert.assertEquals(cartPage.getProductsSum(), cartPage.getCartSum(), "Products sum differs from cart sum");
    }

    @Test(priority = 2, description = "Remove product from cart")
    public void removeItemFormCartTest() {
        catalogPage
                .searchForProduct("Iphone")
                .switchToFrame()
                .selectProductFromSearchList(1)
                .addProductToCart()
                .searchForProduct("Asus")
                .switchToFrame()
                .selectProductFromSearchList(4)
                .addProductToCart()
                .openCart();
        cartPage.removeItemsFromCart();
    }
}
