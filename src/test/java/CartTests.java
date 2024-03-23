import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.CartPage;
import pageObject.onliner.CatalogPages.CatalogPage;

public class CartTests extends BaseTest {
    @Parameters({"url"})
    @BeforeTest
    public void precondition(String url) {
        get(CatalogPage.class).navigateTo(url);
    }

    @Test(priority = 1, description = "Add products to cart and verify if all products price equals total sum")
    public void addProductsToCart() {
        get(CatalogPage.class)
                .searchForProduct("Iphone")
                .switchToFrame()
                .selectProductFromSearchList(1)
                .addProductToCart()
                .searchForProduct("Asus")
                .switchToFrame()
                .selectProductFromSearchList(4)
                .addProductToCart()
                .openCart();
        Assert.assertEquals(get(CartPage.class).getProductsSum(), get(CartPage.class).getCartSum(), "Products sum differs from cart sum");
    }

    @Test(priority = 2, description = "Remove product from cart")
    public void removeItemFormCartTest() {
        get(CatalogPage.class)
                .searchForProduct("Iphone")
                .switchToFrame()
                .selectProductFromSearchList(1)
                .addProductToCart()
                .searchForProduct("Asus")
                .switchToFrame()
                .selectProductFromSearchList(4)
                .addProductToCart()
                .openCart();
        get(CartPage.class).removeItemsFromCart();
    }
}
