import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.RegistrationPage;
import pageObject.onliner.TempEmailPage;

public class RegistrationTests extends BaseTest {
    private RegistrationPage registrationPage;
    private TempEmailPage tempEmailPage;


    @Test(priority = 1)
    public void generateTestEmail() {
        tempEmailPage = new TempEmailPage();
        tempEmailPage
                .navigateTo("https://inboxes.com/")
                .copyEmail();
        System.out.println(tempEmailPage.getEmailAddress());
    }

    @Test(priority = 2, enabled = false)
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
                .enterPassword("Test123!")//
                .confirmPassword("Test123!")
                .checkConsent()
                .submitRegistration();
        tempEmailPage.confirmNewAccount();
    }
}
