package demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormsTestSuite extends FormsPage {
    public FormsTestSuite() {
        super(null);
    }

    String url = "https://demoqa.com/forms";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        int xOffset = 1920; // Width of the primary screen
        int yOffset = 0;    // Set the 2nd screen to the right of the primary screen
        Point position = new Point(xOffset, yOffset);
        options.addArguments("window-position=" + position.getX() + "," + position.getY());
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        driver.get(url);
    }

    @Test
    public void fillInFormWithValidData() {
        practiceForm.click();
        firstName.sendKeys("Automation");
        lastName.sendKeys("Tester");
        userEmail.sendKeys("automationtester@example.com");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 350)");
        femaleGender.click();
        userNumber.sendKeys("1234567890");
        js.executeScript("window.scrollBy(0, 300)");
        dateOfBirthInput.click();
        Select monthSelect = new Select(dateOfBirthMonth);
        monthSelect.selectByValue("2");
        Select yearSelect = new Select(dateOfBirthYear);
        yearSelect.selectByValue("1992");
        dateOfBirthDay.click();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subjectsInput")));
        actions.moveToElement(subjects).sendKeys("Test").build().perform();
        readingCheckBox.click();
        uploadPicture.sendKeys("C:/Users/Alina/Downloads/sampleFile.jpeg");
        currentAddress.sendKeys("49 Featherstone Street");
        stateDropdown.click();
        actions.moveToElement(uttarPradeshState).click().build().perform();
        cityDropdown.click();
        actions.moveToElement(agraCity).click().build().perform();
        submitButton.click();
        String actualSubmissionFormText = successfulSubmit.getText();
        String expectedSubmissionFormText = "Thanks for submitting the form";
        Assert.assertEquals(actualSubmissionFormText, expectedSubmissionFormText, "The text for successfully submitting the form is not correct.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
