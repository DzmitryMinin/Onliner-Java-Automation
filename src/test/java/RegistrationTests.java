import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.RegistrationPage;
import pageObject.tempEmail.TempEmailPage;

public class RegistrationTests extends BaseTest {
    private RegistrationPage registrationPage;
    private TempEmailPage tempEmailPage;


    @Test(priority = 1, description = "Generate test email to register new user")
    public void generateTestEmail() {
        tempEmailPage = new TempEmailPage();
        tempEmailPage
                .navigateTo("https://inboxes.com/")
                .copyEmail();
    }

    @Test(priority = 2, enabled = false, description = "New user registration")
    public void registerNewUser() {
        registrationPage = new RegistrationPage();
        tempEmailPage = new TempEmailPage();
        tempEmailPage
                .navigateTo("https://inboxes.com/")
                .copyEmail()
                .openNewTab();
        registrationPage
                .navigateTo("https://profile.onliner.by/registration")
                .enterEmail(tempEmailPage.getEmailAddress())
                .enterPassword("Test123!")
                .confirmPassword("Test123!")
                .checkConsent()
                .submitRegistration();
        tempEmailPage.confirmNewAccount();
    }
}
