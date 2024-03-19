package pageObject.tempEmail;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import pageObject.baseObjects.BasePage;

import java.util.Random;

public class TempEmailPage extends BasePage {
    private final By getEmail = By.xpath("(//button[@type='button'])[3]");
    private final By username = By.cssSelector("input[id='username']");
    private final By select = By.tagName("select");
    private final By addEmail = By.xpath("//button[contains(text(), 'Add Inbox')]");
    private final By emailMessage = By.xpath("//tbody/tr/td[2]");
    private final By submit = By.xpath("//strong[contains(text(), 'Подтвердить')]");
    private String emailAddress;
    private String firstTabHandle = driver.getWindowHandle();
    private String emailUsername = generateRandomName() + generateRandomNumber();
    private static final Random random = new Random();

    public String generateRandomName() {
        String[] names = {"john", "alice", "michael", "emily", "david", "sophia", "daniel", "emma"};
        return names[random.nextInt(names.length)];
    }

    public String generateRandomNumber() {
        return String.valueOf(random.nextInt(1000));
    }

    public TempEmailPage navigateTo(String url) {
        open(url);
        return this;
    }

    public String getTextOfSelectedValue() {
        Select select = new Select(getWebElement(this.select));
        select.selectByIndex(random.nextInt(20));
        return select.getFirstSelectedOption().getText();
    }
    public TempEmailPage copyEmail() {
        clickElement(getEmail);
        clickElement(username);
        enterValue(username, emailUsername);
        emailAddress = emailUsername + "@" + getTextOfSelectedValue();
        clickElement(addEmail);
        return this;
    }

    public TempEmailPage openNewTab() {
        openNewTab(firstTabHandle);
        return this;
    }

    public TempEmailPage confirmNewAccount() {
        switchToTab(firstTabHandle);
        sleep(5000);
        clickElement(emailMessage);
        clickElement(submit);
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
