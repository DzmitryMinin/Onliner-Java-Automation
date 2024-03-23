package pageObject.baseObjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static driver.WebDriverSetUp.getDriver;
import static propertyUtils.PropertyReader.getProperties;

@Log4j
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
        log.info("Navigate to:: " + getProperties().getProperty("url"));
        driver.get(getProperties().getProperty("url"));
    }

    protected void open(String url) {
        log.info("Navigate to :: " + url);
        driver.get(url);
    }

    protected void moveOverElementAndClick(WebElement webElement) {
        log.info("Move cursor over the element :: " + webElement + " and click on it");
        actions.moveToElement(webElement).perform();
        sleep(500);
        webElement.click();
    }

    protected void moveOverElement(WebElement webElement) {
        log.info("Move cursor over the element :: " + webElement);
        actions.moveToElement(webElement).perform();
    }

    protected void submit(By by) {
        log.info("Submit by clicking :: " + driver.findElement(by));
        driver.findElement(by).submit();
    }

    protected void enterValue(By by, CharSequence... charSequences) {
        log.info("Enter value into :: " + driver.findElement(by));
        clear(by);
        driver.findElement(by).sendKeys(charSequences);
    }

    protected void clear(By by) {
        log.info("Clear field :: " + driver.findElement(by));
        driver.findElement(by).sendKeys(Keys.CONTROL + "a");
        driver.findElement(by).sendKeys(Keys.DELETE);
    }

    protected void clickElement(By by) {
        log.info("Click on :: " + driver.findElement(by));
        waitUntilElementToBeClickable(by);
        driver.findElement(by).click();
    }

    protected void clickElementFromList(By by, Integer index) {
        log.info("Click on the element :: " + driver.findElement(by) + " with index" + index + " from list" );
        waitUntilElementToBeClickable(by);
        driver.findElements(by).get(index).click();
    }

    protected void waitUntilElementToBeClickable(By by) {
        log.info("Wait until the element is clickable :: " + driver.findElement(by));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
    }

    protected void waitUntilVisibilityOf(By by) {
        log.info("Wait until the element is visible :: " + driver.findElement(by));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    protected void waitUntilInvisibilityOfElementLocated(By by) {
        log.info("Wait until the element disappears :: " + driver.findElement(by));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void switchToFrame(By by) {
        log.info("Switch to frame :: " + driver.findElement(by));
        waitUntilVisibilityOf(by);
        driver.switchTo().frame(driver.findElement(by));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    protected List<WebElement> getListOfWebElements(By by) {
        return driver.findElements(by);
    }

    protected void getBack() {
        log.info("Return to previous page");
        driver.navigate().back();
    }

    protected void switchToTab(String tabIndex) {
        log.info("Switch to tab with index :: " + tabIndex);
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
        log.info("Open new tab");
        ((JavascriptExecutor) driver).executeScript("window.open()");
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(tab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    protected void scrollTo(By by) {
        log.info("Scroll to the element :: " + driver.findElement(by));
        actions.scrollToElement(driver.findElement(by)).scrollByAmount(0, 50).perform();
    }



    protected void refreshPage() {
        driver.navigate().refresh();
    }
}
