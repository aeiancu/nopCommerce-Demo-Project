package demoqa.tests;

import demoqa.pages.AlertsFrameWindowsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
            //Assert that no alert is present after acceptance
            //This line will throw NoAlertPresentException if the alert is still present
            driver.switchTo().alert();
            System.out.println("Alert accepted successfully.");
        } catch (NoAlertPresentException e) {
            System.out.println("Alert was accepted and is no longer present.");
        }
    }

    @Test(groups = "Alerts")
    public void verifyTimerAlert() {
        alerts.click();
        timerAlert.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().alert();
            System.out.println("Alert accepted successfully.");
        } catch (NoAlertPresentException e) {
            System.out.println("Alert was accepted and is no longer present.");
        }
    }

    @Test(groups = "Alerts")
    public void verifyAcceptPopupBox() {
        alerts.click();
        confirmButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String actualMessage = confirmResult.getText();
        String expectedMessage = "You selected Ok";
        Assert.assertEquals(actualMessage, expectedMessage, "The messages do not match.");
    }

    @Test(groups = "Alerts")
    public void verifyDismissPopupBox() {
        alerts.click();
        confirmButton.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String actualMessage = confirmResult.getText();
        String expectedMessage = "You selected Cancel";
        Assert.assertEquals(actualMessage, expectedMessage, "The messages do not match.");
    }

    @Test(groups = "Alerts")
    public void verifyAcceptPromptBoxWithTextField() {
        alerts.click();
        promptButton.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Automation Tester");
        alert.accept();
        String actualMessage = promptResult.getText();
        String expectedMessage = "You entered Automation Tester";
        Assert.assertEquals(actualMessage, expectedMessage, "The messages do not match.");
    }

    @Test(groups = "Frames")
    public void verifyParentFrameText() {
        frames.click();
        String actualMessage = parentFrameText.getText();
        String expectedMessage = "Sample Iframe page There are 2 Iframes in this page. Use browser inspecter or firebug to check out the HTML source. " +
                "In total you can switch between the parent frame, which is this window, and the two frames below";
        Assert.assertEquals(actualMessage, expectedMessage, "The messages do not match.");
    }

    @Test(groups = "Frames")
    public void verifyChildFrame1Text() {
        frames.click();
        driver.switchTo().frame(childFrame1);
        WebElement childFrame1Text = driver.findElement(By.id("sampleHeading"));
        String actualMessage = childFrame1Text.getText();
        String expectedMessage = "This is a sample page";
        Assert.assertEquals(actualMessage, expectedMessage, "The messages do not match.");
        driver.switchTo().defaultContent();
    }

    @Test(groups = "Frames")
    public void verifyChildFrame2Text() {
        frames.click();
        driver.switchTo().frame(childFrame2);
        WebElement childFrame2Text = driver.findElement(By.id("sampleHeading"));
        String actualMessage = childFrame2Text.getText();
        String expectedMessage = "This is a sample page";
        Assert.assertEquals(actualMessage, expectedMessage, "The messages do not match.");
        driver.switchTo().defaultContent();
    }

    @Test(groups = "Nested Frames")
    public void verifyNestedFrames() {
        frames.click();
        nestedFrames.click();
        driver.switchTo().frame(parentNestedFrame);
        WebElement parentNestedFrameText = driver.findElement(By.tagName("body"));
        String actualParentNestedFrameMessage = parentNestedFrameText.getText();
        String expectedParentNestedFrameMessage = "Parent frame";
        Assert.assertEquals(actualParentNestedFrameMessage, expectedParentNestedFrameMessage, "The messages do not match.");
        driver.switchTo().defaultContent();

        driver.switchTo().frame(childNestedFrame);
        String actualChildNestedFrameMessage = childNestedFrameText.getText();
        String expectedChildNestedFrameMessage = "Child frame";
        Assert.assertEquals(actualChildNestedFrameMessage, expectedChildNestedFrameMessage, "The messages do not match.");
        driver.switchTo().defaultContent();
    }

    @Test(groups = "Modal Dialogs")
    public void verifySmallModalDialog() {
        modalDialogs.click();
        smallModalBtn.click();
        String actualMessage = smallModalBody.getText();
        String expectedMessage = "This is a small modal. It has very less content";
        Assert.assertEquals(actualMessage, expectedMessage, "The messages do not match.");
        closeSmallModalBtn.click();
    }

    @Test(groups = "Modal Dialogs")
    public void verifyLargeModalDialog() {
        modalDialogs.click();
        largeModalBtn.click();
        String actualMessage = largeModalBody.getText();
        String expectedMessage = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type " +
                "specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised " +
                "in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker " +
                "including versions of Lorem Ipsum.";
        closeLargeModalBtn.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
