package pageObject.onliner;

import org.openqa.selenium.By;
import pageObject.baseObjects.BasePage;

public class ProfilePage extends BasePage {
    private final By personalDataTab = By.cssSelector("a[href='/personal']");
    private final By changePhoneBtn = By.xpath("(//a[contains(text(), 'Изменить')])[2]");
    private final By changeBtn = By.xpath("");
    public ProfilePage navigateTo(String url) {
        open(url);
        return this;
    }

    public ProfilePage clickPersonalDataTab() {
        clickElement(personalDataTab);
        return this;
    }

    public ProfilePage clickChangePhoneNumber() {
        moveOverElementAndClick(driver.findElement(changePhoneBtn));
        return this;
    }


}
