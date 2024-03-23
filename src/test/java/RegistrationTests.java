import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.baseObjects.BaseTest;
import pageObject.onliner.RegistrationPage;
import pageObject.tempEmail.TempEmailPage;

public class RegistrationTests extends BaseTest {
    @Parameters({"urlTempEmail", "urlOnliner", "password", "confirmPassword"})
    @Test(priority = 1, description = "New user registration")
    public void registerNewUser(String urlTempEmail, String urlOnliner, String password, String confirmPassword) {
        TempEmailPage tempEmailPage;
        tempEmailPage = new TempEmailPage();
        tempEmailPage
                .navigateTo(urlTempEmail)
                .copyEmail()
                .openNewTab();
        get(RegistrationPage.class)
                .navigateTo(urlOnliner)
                .enterEmail(tempEmailPage.getEmailAddress())
                .enterPassword(password)
                .confirmPassword(confirmPassword)
                .checkConsent()
                .submitRegistration();
        tempEmailPage.confirmNewAccount();
        get(RegistrationPage.class).verifySuccessRegistration();
    }
}
