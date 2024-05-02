package demoqa;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class AlertsFrameWindowsTestSuite extends AlertsFrameWindowsPage {
    public AlertsFrameWindowsTestSuite() {
        super(null);
    }

    String url = "https://demoqa.com/alertsWindows";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        int xOffset = 1900;
        int yOffset = 0;
        Point position = new Point(xOffset, yOffset);
        options.addArguments("window-position=" + position.getX() + "," + position.getY());
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        driver.get(url);
    }

    @Test(groups = "Browser Windows")
    public void openLinkInNewTab() {
        browserWindows.click();
        newTabBtn.click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> newWindowHandles = driver.getWindowHandles();
        for (String windowHandle : newWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://demoqa.com/sample";
        Assert.assertEquals(actualURL, expectedURL, "The URLs do not match.");

        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test(groups = "Browser Windows")
    public void openLinkInNewWindow() {
        browserWindows.click();
        newWindowBtn.click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> newWindowHandles = driver.getWindowHandles();
        for (String windowHandle : newWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://demoqa.com/sample";
        Assert.assertEquals(actualURL, expectedURL, "The URLs do not match.");

        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test(groups = "Browser Windows")
    public void verifyMessageInNewWindow() {
        browserWindows.click();
        newWindowMessageBtn.click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> newWindowHandles = driver.getWindowHandles();
        for (String windowHandle : newWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement newWindowMessage = driver.findElement(By.tagName("body"));
        String actualNewWindowMessage = newWindowMessage.getText();
        String expectedNewWindowMessage = "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";
        Assert.assertEquals(actualNewWindowMessage, expectedNewWindowMessage, "The message from the new window is not correct.");

        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test(groups = "Alerts")
    public void verifyStandardAlert() {
        alerts.click();
        standardAlert.click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            //Assert that no alert it present after acceptance
            //This line will throw NoAlertPresentException if the alert is still present
            driver.switchTo().alert();
            System.out.println("Alert accepted successfully.");
        } catch (NoAlertPresentException e) {
            System.out.println("Alert was accepted and is no longer present.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
