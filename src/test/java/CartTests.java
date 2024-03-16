import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.CartPage;

public class CartTests extends BaseTest {
    private CartPage cartPage;
    @BeforeTest
    public void precondition() {
        cartPage = new CartPage();
    }

    @Test
    public void removeItemFormCartTest() {
        cartPage.removeItemFromCart();
    }
}
