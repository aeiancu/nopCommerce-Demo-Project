package nopcommerce.tests;

import nopcommerce.pages.FormSubmissionPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormSubmissionTestSuite extends FormSubmissionPage {

    public FormSubmissionTestSuite() {
        super(null);
    }
    String url = "https://demo.nopcommerce.com/contactus";

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);
    }

 /*   @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        PageFactory.initElements(driver, this);
     //   WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);
    }*/

    @Test(priority = 1)
    public void emptyFieldsTestCase(){
        submit.click();

        //Get the actual error messages
        String actualNameErrorMessage = nameErrorMessage.getText();
        String actualEmptyEmailErrorMessage = emptyEmailErrorMessage.getText();
        String actualEnquiryErrorMessage = enquiryErrorMessage.getText();

        //Compare the actual error messages with expected error messages
        Assert.assertEquals(actualNameErrorMessage, "Enter your name", "Actual name error message does not match expected name error message: " + actualNameErrorMessage);
        Assert.assertEquals(actualEmptyEmailErrorMessage, "Enter email", "Actual email error message does not match expected email error message: " + actualEmptyEmailErrorMessage);
        Assert.assertEquals(actualEnquiryErrorMessage, "Enter enquiry", "Actual enquiry error message does not match expected enquiry error message: " + actualEnquiryErrorMessage);
    }

    @Test(priority = 2)
    public void happyPathTestCase() {
        fullName.sendKeys("Automation Tester");
        email.sendKeys("automationtester@gmail.com");
        enquiry.sendKeys("This is a test enquiry");

        //Submit the form
        submit.click();

        //Get the "submit" response and create the expected response variable
        String expectedText = "Your enquiry has been successfully sent to the store owner.";
        String actualText = successfulSubmit.getText();

        //Verify that the 2 messages match
        Assert.assertEquals(actualText, expectedText, "Actual text does not match expected text");
    }

    @Test(priority = 3)
    public void invalidEmailFormatTestCase() {
        driver.navigate().back();

        //Fill in the contact form with an invalid email address
        fullName.sendKeys("Automation tester");
        email.sendKeys("automation.tester@");
        enquiry.sendKeys("This is a test enquiry");

        //Submit the form
        submit.click();

        //Identify the error message
        String actualWrongEmailErrorMessage = wrongEmailErrorMessage.getText();

        //Create the error message
        String expectedWrongEmailErrorMessage = "Wrong email";

        //Verify that the 2 error messages match
        Assert.assertEquals(actualWrongEmailErrorMessage, expectedWrongEmailErrorMessage, "The wrong email error messages do not match: " + actualWrongEmailErrorMessage);
    }

    @Test(priority = 4)
    public void specialCharactersTestCase() {
        //Clear the fields
        fullName.clear();
        email.clear();
        enquiry.clear();

        //Fill in the contact form with special characters for each field
        fullName.sendKeys("Alice & Bob");
        email.sendKeys("alice&bob@example.com");
        enquiry.sendKeys("I have a question about the product's pricing ($100)");
        submit.click();

        //Get the "submit" response and create the expected response variable
        String expectedText = "Your enquiry has been successfully sent to the store owner.";
        String actualText = successfulSubmit.getText();

        //Verify that the 2 messages match
        Assert.assertEquals(actualText, expectedText, "Actual text does not match expected text");
    }

    @Test(priority = 5)
    public void boundaryValuesTestCase() {
        //Navigate to the previous page
        driver.navigate().back();

        //Clear the fields
        fullName.clear();
        email.clear();
        enquiry.clear();

        //Fill in the contact form with minimum allowed characters
        fullName.sendKeys("a");
        email.sendKeys("a@example.com");
        enquiry.sendKeys("a");
        submit.click();

        //Verify whether the test case fails with the appropriate error message
        String expectedNameFieldText = "For the Name field, please enter at least 2 characters";
        String expectedEnquiryFieldText = "For the Enquiry field, please enter at least 2 characters";;
        String actualText = successfulSubmit.getText();

        Assert.assertEquals(actualText, expectedNameFieldText, "Actual text does not match expected text");
        Assert.assertEquals(actualText, expectedEnquiryFieldText, "Actual text does not match expected text");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
