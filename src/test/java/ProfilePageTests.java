import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.LoginPage;
import pageObject.onliner.ProfilePage;

public class ProfilePageTests extends BaseTest {
    @Parameters({"url, username, password"})
    @BeforeTest(enabled = false)
    public void precondition(@Optional("") String url, String username, String password) {
        get(LoginPage.class)
                .navigateTo(url)
                .enterLogin(username)
                .enterPassword(password)
                .clickEnterBtn()
                .switchToFrame()
                .checkCaptcha()
                .switchToContent();
    }

    @Test(description = "Change user's phone number", enabled = false)
    public void changePhoneNumber() {
        get(ProfilePage.class)
                .navigateTo("https://profile.onliner.by/")
                .clickPersonalDataTab()
                .clickChangePhoneNumber();
    }
}
