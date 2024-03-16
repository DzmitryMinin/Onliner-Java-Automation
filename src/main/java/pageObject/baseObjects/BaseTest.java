package pageObject.baseObjects;

import static driver.DriverTypes.*;
import static driver.WebDriverSetUp.createDriver;
import static driver.WebDriverSetUp.quitDriver;
import static propertyUtils.PropertyReader.getProperties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import testngUtils.Listener;

@Listeners(Listener.class)
public class BaseTest {
    @BeforeTest
    protected void setUp() {
        createDriver(System.getProperties().contains("config")
                ? valueOf(getProperties().getProperty("browser").toUpperCase())
                : CHROME
        );
    }

    /*@AfterTest(alwaysRun = true)
    protected void tearDown() {
        quitDriver();
    }*/
}
