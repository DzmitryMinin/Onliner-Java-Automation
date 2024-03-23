package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import propertyUtils.PropertyReader;

import java.time.Duration;

public class WebDriverSetUp {
    private static WebDriver webDriver;

    public static void createDriver (DriverTypes type) {
        if (webDriver == null) {
            switch (type) {
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(PropertyReader.getProperties().getProperty("browser.option").split(";"));
                    webDriver = new ChromeDriver(options);
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    webDriver = new FirefoxDriver(firefoxOptions);
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    webDriver = new EdgeDriver(edgeOptions);
                    break;
            }
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            webDriver.manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
