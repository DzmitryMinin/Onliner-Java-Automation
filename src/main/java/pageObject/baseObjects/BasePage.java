package pageObject.baseObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.WebDriverSetUp.getDriver;
import static propertyUtils.PropertyReader.getProperties;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    {
        driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
    }

    protected void open() {
        driver.get(getProperties().getProperty("url"));
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected void moveOverElementAndClick(WebElement webElement) {
        actions.moveToElement(webElement).click().perform();
        sleep(500);
        webElement.click();
    }

    protected void submit(By by) {
        driver.findElement(by).submit();
    }

    protected void enterValue(By by, CharSequence... charSequences) {
        clear(by);
        driver.findElement(by).sendKeys(charSequences);
    }

    protected void clear(By by) {
        driver.findElement(by).sendKeys(Keys.CONTROL + "a");
        driver.findElement(by).sendKeys(Keys.DELETE);
    }

    protected void clickElement(By by) {
        waitUntilElementToBeClickable(by);
        driver.findElement(by).click();
    }

    protected void clickElementFromList(By by, Integer index) {
        waitUntilElementToBeClickable(by);
        driver.findElements(by).get(index).click();
    }

    protected void waitUntilElementToBeClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
    }

    protected void waitUntilVisibilityOf(By by) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    protected void waitUntilInvisibilityOfElementLocated(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void switchToFrame(By by) {
        waitUntilVisibilityOf(by);
        driver.switchTo().frame(driver.findElement(by));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void clickElement(WebElement webElement) {
        webElement.click();
    }

    protected WebElement getWebElement(By by) {
        WebElement webElement = driver.findElement(by);
        return webElement;
    }

    protected void getBack() {
        driver.navigate().back();
    }

    protected void switchToTab(String tabIndex) {
        driver.switchTo().window(tabIndex);
    }

    protected void sleep(Integer ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void openNewTab(String tab) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(tab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    protected void scrollTo(By by) {
        actions.scrollToElement(driver.findElement(by)).scrollByAmount(0, 50).perform();
    }

}
