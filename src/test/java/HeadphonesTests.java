import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.CatalogPages.HeadphonesPage;

public class HeadphonesTests extends BaseTest {
    private HeadphonesPage headphonesPage;

    @BeforeTest
    public void precondition() {
        headphonesPage = new HeadphonesPage();
        headphonesPage
                .navigateTo()
                .expandRestFilters()
                .selectWiredHeadphones();
    }

    @Test(priority = 1, description = "Verify popup window")
    public void checkPopupWindow() {
        headphonesPage.verifyPopupWindow();
    }

    @Test(priority = 2, dataProvider = "cable length", description = "Boundary values analysis for cable length")
    public void cableLengthTest(String lengthFrom, String lengthUpTo, Boolean status) {
        headphonesPage
                .enterLengthFrom(lengthFrom)
                .enterLengthUpTo(lengthUpTo)
                .checkIfPageLoaded();
        if (status) {
            Assert.assertTrue(headphonesPage.getProductList().size() > 0);
        } else Assert.assertTrue(headphonesPage.getProductList().size() == 0);
    }

    @DataProvider(name="cable length")
    public Object[][] getData() {
        return new Object[][]{
                {"0", "0.3", true},
                {"0.3", "0.3", true},
                {"0.3", "23", true},
                {"23", "23", true},
                {"0", "0.29", false},
                {"23", "0", false},
                {"23.0001", "24", false},
                {"-1", "30", false},
                {"0", "0", false},
                {"2", "-1", false}
        };
    }
}
