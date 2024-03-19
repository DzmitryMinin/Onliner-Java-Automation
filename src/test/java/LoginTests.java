import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.LoginPage;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    @BeforeTest
    public void precondition() {
        loginPage = new LoginPage();
        loginPage.navigateTo("https://profile.onliner.by/login");
    }

    @Test(priority = 1, description = "Verify links on login page")
    public void verifyLinks() {
        loginPage
                .verifyRegistrationLink()
                .verifyRecoverPasswordLink()
                .verifyFooter();
    }

    @Test(priority = 2, description = "Successful login")
    public void successfulLoginTest() {
        loginPage
                .enterLogin("gannon.tanveer@marsoak.com")
                .enterPassword("Test123!")
                .clickEnterBtn()
                .switchToFrame()
                .checkCaptcha()
                .switchToContent();
    }
}
