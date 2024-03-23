package pageObject.onliner;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObjects.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static propertyUtils.PropertyReader.getProperties;

public class LoginPage extends BasePage {
    private final By footer = By.cssSelector("div[class='auth-form__footer']");
    private final By login = By.cssSelector("input[type='text']");
    private final By password = By.cssSelector("input[type='password']");
    private final By enterBtn = By.cssSelector("button[type='submit']");
    private final By registrationLink = By.cssSelector("a[href='/registration']");
    private final By recoverPasswordLink = By.cssSelector("a[href='/recover-password']");
    private final By captchaCheckbox = By.xpath("//div[@class='recaptcha-checkbox-border']");
    private final By captchaFrame = By.cssSelector("iframe[title='reCAPTCHA']");
    private final By invalidCredsText = By.xpath("//div[contains(@class, 'auth-form__description_extended-other')]");

    public LoginPage navigateTo(String url) {
        open(url);
        return this;
    }

    public LoginPage enterLogin(CharSequence... charSequences) {
        enterValue(this.login, charSequences);
        return this;
    }

    public LoginPage enterPassword(CharSequence... charSequences) {
        enterValue(this.password, charSequences);
        return this;
    }

    public LoginPage clickEnterBtn() {
        clickElement(enterBtn);
        return this;
    }

    public LoginPage checkCaptcha() {
        clickElement(captchaCheckbox);
        return this;
    }

    public LoginPage switchToFrame() {
        switchToFrame(captchaFrame);
        return this;
    }

    public LoginPage switchToContent() {
        switchToDefaultContent();
        return this;
    }

    public LoginPage verifyRegistrationLink() {
        clickElement(registrationLink);
        Assert.assertTrue(getCurrentUrl().equals("https://profile.onliner.by/registration"));
        getBack();
        return this;
    }

    public LoginPage verifyRecoverPasswordLink() {
        clickElement(recoverPasswordLink);
        Assert.assertTrue(getCurrentUrl().equals("https://profile.onliner.by/recover-password"));
        getBack();
        return this;
    }

    public LoginPage verifyFooter() {
        Assert.assertEquals(getWebElement(footer).getText(), "© 2001–2024 Onlíner", "Incorrect footer text");
        return this;
    }

    public LoginPage verifyInvalidCredsText() {
        Assert.assertEquals(getWebElement(invalidCredsText).getText(), "Неверный логин или пароль", "Incorrect error message");
        return this;
    }
}
