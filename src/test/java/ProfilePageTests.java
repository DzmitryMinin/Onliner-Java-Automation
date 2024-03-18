import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.LoginPage;
import pageObject.onliner.ProfilePage;

public class ProfilePageTests extends BaseTest {
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Test
    public void precondition() {
        loginPage = new LoginPage();
        loginPage
                .navigateTo("https://profile.onliner.by/login")
                .enterLogin("gannon.tanveer@marsoak.com")
                .enterPassword("Test123!")
                .clickEnterBtn()
                .switchToFrame()
                .checkCaptcha()
                .switchToContent();
    }

    @Test(enabled = false)
    public void test() {
        profilePage = new ProfilePage();
        profilePage
                .navigateTo("https://profile.onliner.by/")
                .clickPersonalDataTab()
                .clickChangePhoneNumber();
    }
}
