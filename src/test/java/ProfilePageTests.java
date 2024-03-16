import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.LoginPage;
import pageObject.onliner.ProfilePage;

public class ProfilePageTests extends BaseTest {
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @BeforeTest
    public void precondition() {
        loginPage = new LoginPage();
        loginPage
                .navigateTo("https://profile.onliner.by/login")
                .sleep1(2500)
                .enterLogin("gannon.tanveer@marsoak.com")
                .sleep1(2233)
                .enterPassword("Test123!")
                .sleep1(1500)
                .clickEnterBtn()
                .switchToFrame()
                .sleep1(1830)
                .moveCursorToCaptcha()
                .switchToDefaultContent();
    }

    @Test
    public void test() {
        profilePage = new ProfilePage();
        profilePage
                .navigateTo("https://profile.onliner.by/")
                .clickPersonalDataTab()
                .clickChangePhoneNumber();
    }
}
