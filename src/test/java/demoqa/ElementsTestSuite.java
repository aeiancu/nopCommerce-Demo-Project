package demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;
import java.util.Set;

public class ElementsTestSuite extends ElementsPage {

    public ElementsTestSuite() {
        super(null);
    }

    String url = "https://demoqa.com/elements";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        driver.get(url);
    }

    @Test(groups = "Text Box")
    public void fillTheFormWithValidData() {
        driver.switchTo().frame(adClose).findElement(By.id("cbb")).click();
        driver.switchTo().defaultContent();
        textBox.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400)");
        userName.sendKeys("Automation Tester");
        userEmail.sendKeys("automation@example.com");
        currentAddress.sendKeys("49 Featherstone Street");
        permanentAddress.sendKeys("49 Featherstone Street");
        submit.click();
    }

    @Test(groups = "Check Box")
    public void expandCheckBoxes() {
        driver.switchTo().frame(adClose).findElement(By.id("cbb")).click();
        driver.switchTo().defaultContent();
        checkBox.click();
        homeCollapseButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400)");
        desktopCollapseButton.click();
        documentsCollapseButton.click();
        workspaceCollapseButton.click();
        officeCollapseButton.click();
        js.executeScript("window.scrollBy(0, 200)");
        downloadsCollapseButton.click();
        Assert.assertTrue(notesCheckbox.isDisplayed(), "Notes checkbox is not displayed after expanding Desktop checkbox");
        Assert.assertTrue(workspaceCheckbox.isDisplayed(), "WorkSpace checkbox is not displayed after expanding Documents checkbox");
    }

    @Test(groups = "Radio Button")
    public void clickOnRadioButtons() {
        driver.switchTo().frame(adClose).findElement(By.id("cbb")).click();
        driver.switchTo().defaultContent();
        radioButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");

        yesRadio.click();
        Assert.assertTrue(yesRadio.isSelected(), "Yes option is not selected after clicking on it.");
    }

    @Test(groups = "Web Tables")
    public void extractMaxAgeFromTable() {
        webTables.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        int maxAge = Collections.max(getTheAgeList());
        System.out.println("Max age is: " + maxAge);

        Assert.assertTrue(getTheAgeList().contains(maxAge), "The age column does not contain the max number.");
    }

    @Test(groups = "Web Tables")
    public void extractSalaryOfOldestEmployee() {
        webTables.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        int maxAge = Collections.max(getTheAgeList());
        int maxAgeIndex = getTheAgeList().indexOf(maxAge);

        int salaryOfOldestEmployee = getTheSalaryList().get(maxAgeIndex);
        System.out.println("Salary of the oldest employee: " + salaryOfOldestEmployee);
        int expectedSalaryOfOldestEmployee = 12000;
        Assert.assertEquals(expectedSalaryOfOldestEmployee, salaryOfOldestEmployee, "Salary of the oldest employee does not match the expected salary.");
    }

    @Test(groups = "Web Tables")
    public void extractEmailAndDepartmentOfYoungestEmployee() {
        webTables.click();
        int minAge = Collections.min(getTheAgeList());
        int minAgeIndex = getTheAgeList().indexOf(minAge);
        String emailOfYoungestEmployee = getTheEmailList().get(minAgeIndex);
        String departmentOfYoungestEmployee = getTheDepartmentList().get(minAgeIndex);
        System.out.println("The email of the youngest employee is: " + emailOfYoungestEmployee);
        System.out.println("The department of the youngest employee is: " + departmentOfYoungestEmployee);
        Assert.assertEquals(emailOfYoungestEmployee, "kierra@example.com", "Email of the youngest employee does not match the expected email.");
        Assert.assertEquals(departmentOfYoungestEmployee, "Legal", "Department of the youngest employee does not match the expected department.");
    }

    @Test(groups = "Buttons")
    public void performMouseActions() {
        buttons.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickBtn).perform();
        String doubleClickMessageText = doubleClickMessage.getText();
        Assert.assertEquals(doubleClickMessageText, "You have done a double click", "The double click message is not displayed.");
        actions.contextClick(rightClickBtn).perform();
        String rightClickMessageText = rightClickMessage.getText();
        Assert.assertEquals(rightClickMessageText, "You have done a right click", "The right click message is not displayed.");
        js.executeScript("window.scrollBy(0, 250)");
        dynamicClickBtn.click();
        String dynamicClickMessageText = dynamicClickMessage.getText();
        Assert.assertEquals(dynamicClickMessageText, "You have done a dynamic click", "The dynamic click message is not displayed.");
    }

    @Test(groups = "Links")
    public void followLinkInNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        links.click();

        //Get the handles of all open windows before clicking the link
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> oldWindowHandles = driver.getWindowHandles();

        //Perform click on the link
        homeLink.click();
        Set<String> newWindowHandles = driver.getWindowHandles();

        //Check if a new window/tab was opened
        newWindowHandles.removeAll(oldWindowHandles);
        Assert.assertEquals(newWindowHandles.size(), 1, "A new window/tab should have been opened.");

        //Switch to the new tab
        for (String windowHandle : newWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        js.executeScript("window.scrollBy(0, 200)");

        //Check whether navigating to the elements page works fine
        elements.click();
        String actualNewURL = driver.getCurrentUrl();
        String expectedNewURL = "https://demoqa.com/elements";
        Assert.assertEquals(actualNewURL, expectedNewURL, "The new URLs do not match.");

        //Close the new tab
        driver.close();

        //Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        //Check whether the new URL is the correct one
        String actualMainURL = driver.getCurrentUrl();
        String expectedMainURL = "https://demoqa.com/links";
        Assert.assertEquals(actualMainURL, expectedMainURL, "The main URLs do not match.");
    }

    @Test(groups = "Broken Links - Images")
    public void verifyValidImageInNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        brokenLinksImages.click();
        js.executeScript("window.scrollBy(0, 200)");

        String mainWindowHandle = driver.getWindowHandle();

        Actions actions = new Actions(driver);
        actions.contextClick(validImage).perform();
        js.executeScript("window.open(arguments[0].src,'_blank')", validImage);

        Set<String> newWindowHandles = driver.getWindowHandles();
        for (String windowHandle : newWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String actualURL = driver.getCurrentUrl();
        String expectedImageSource = "https://demoqa.com/images/Toolsqa.jpg";
        Assert.assertEquals(actualURL, expectedImageSource, "The URLs do not match.");

        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test(groups = "Broken Links - Images")
    public void verifyBrokenImage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        brokenLinksImages.click();

        //Verify that the broken image is displayed
        Assert.assertFalse(brokenImage.isDisplayed(), "Broken image is displayed.");
    }

    @Test(groups = "Broken Links - Images")
    public void verifyValidLink() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        brokenLinksImages.click();
        js.executeScript("window.scrollBy(0, 200)");
        validLink.click();
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://demoqa.com/";
        Assert.assertEquals(actualURL, expectedURL, "The URLs do not match.");
    }

    @Test(groups = "Broken Links - Images")
    public void verifyBrokenLink() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        brokenLinksImages.click();
        js.executeScript("window.scrollBy(0, 300)");
        brokenLink.click();
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://the-internet.herokuapp.com/status_codes/500";
        Assert.assertEquals(actualURL, expectedURL, "The URLs do not match.");
    }

    @Test(groups = "Upload and Download")
    public void downloadFile() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        uploadAndDownload.click();
        downloadButton.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            Robot robot = new Robot();
            // Simulate pressing Enter key to save the file
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //Check that the file was successfully downloaded
        String downloadDir = "C:/Users/Alina/Downloads";
        String fileName = "sampleFile.jpeg";
        File downloadedFile = Paths.get(downloadDir, fileName).toFile();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(downloadedFile.exists(), "Downloaded file does not exist.");
    }

    @Test(groups = "Upload and Download")
    public void uploadFile() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        uploadAndDownload.click();
        uploadFile.sendKeys("C:/Users/Alina/Downloads/sampleFile.jpeg");
        //Check that the file was successfully uploaded
        String actualUploadedFilePath = uploadedFilePath.getText();
        String expectedUploadedFilePath = "C:\\fakepath\\sampleFile.jpeg";
        Assert.assertEquals(actualUploadedFilePath, expectedUploadedFilePath, "The uploaded file paths do not match.");
    }

    @Test(groups = "Dynamic Properties")
    public void testButtonEnabledAfterDelay() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        dynamicProperties.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(enabledAfter5SecondsBtn.isEnabled(), "The button is not enabled after the 5-second timeframe.");
    }

    @Test(groups = "Dynamic Properties")
    public void testButtonRemainsDisabledWithin4Seconds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        dynamicProperties.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Verify that the button is still disabled
        Assert.assertFalse(enabledAfter5SecondsBtn.isEnabled(), "The button was enabled faster than the 5-second timeframe.");
    }

    @Test(groups = "Dynamic Properties")
    public void testButtonColorChangeAfter5Seconds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        dynamicProperties.click();
        //Get the initial color of the button
        String initialColor = colorChangeBtn.getCssValue("color");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String finalColor = colorChangeBtn.getCssValue("color");
        Assert.assertNotEquals(initialColor, finalColor, "The color of the button did not change after 5 seconds.");
    }

    @Test(groups = "Dynamic Properties")
    public void testVisibilityOfButtonAfter5Seconds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        dynamicProperties.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(visibleAfter5SecondsBtn.isDisplayed(), "The button was not displayed after 5 seconds.");
    }

    @Test(groups = "Dynamic Properties")
    public void testButtonRemainsNotDisplayedWithin4Seconds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        dynamicProperties.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("visibleAfter")));
        Assert.assertFalse(visibleAfter5SecondsBtn.isDisplayed(), "The button was displayed earlier than 5-second timeframe.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}







