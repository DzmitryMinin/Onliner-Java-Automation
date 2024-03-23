import org.testng.annotations.*;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.LoginPage;

public class LoginTests extends BaseTest {
    @Parameters({"url"})
    @BeforeTest
    public void precondition(@Optional("") String url) {
        get(LoginPage.class).navigateTo(url);
    }

    @Test(priority = 1, description = "Verify links on login page")
    public void verifyLoginPageLinks() {
        get(LoginPage.class)
                .verifyRegistrationLink()
                .verifyRecoverPasswordLink()
                .verifyFooter();
    }

    @Parameters({"username", "password"})
    @Test(priority = 2, description = "Successful login", enabled = false)
    public void successfulLogin(@Optional("") String username, String password) {
        get(LoginPage.class)
                .enterLogin(username)
                .enterPassword(password)
                .clickEnterBtn()
                .switchToFrame()
                .checkCaptcha()
                .switchToContent();
    }
    @Test(priority = 3, description = "Invalid login", enabled = false)
    public void invalidLogin() {
        get(LoginPage.class)
                .enterLogin("Test123!")
                .enterPassword("Qwerty123!")
                .clickEnterBtn()
                .verifyInvalidCredsText();
    }
}
