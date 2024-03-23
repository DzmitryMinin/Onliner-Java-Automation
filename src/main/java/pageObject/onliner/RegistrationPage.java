package pageObject.onliner;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObjects.BasePage;

public class RegistrationPage extends BasePage {
    private final By email = By.cssSelector("input[type='email']");
    private final By password = By.cssSelector("input[type='password']");
    private final By confirmPassword = By.xpath("(//input[@type='password'])[2]");
    private final By consentCheckbox = By.cssSelector("span[class='auth-checkbox__faux']");
    private final By submit = By.cssSelector("button[type='submit']");

    public RegistrationPage navigateTo(String url) {
        open(url);
        return this;
    }

    public RegistrationPage enterEmail(CharSequence... charSequences) {
        enterValue(email, charSequences);
        return this;
    }

    public RegistrationPage enterPassword(CharSequence... charSequences) {
        enterValue(password, charSequences);
        return this;
    }

    public RegistrationPage confirmPassword(CharSequence... charSequences) {
        enterValue(confirmPassword, charSequences);
        return this;
    }

    public RegistrationPage checkConsent() {
        clickElement(consentCheckbox);
        return this;
    }

    public RegistrationPage submitRegistration() {
        clickElement(submit);
        return this;
    }

    public RegistrationPage verifySuccessRegistration() {
        Assert.assertEquals(getCurrentUrl(), "https://profile.onliner.by/", "Registration failed");
        return this;
    }
}
