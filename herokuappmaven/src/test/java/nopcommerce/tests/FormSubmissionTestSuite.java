package nopcommerce.tests;

import nopcommerce.pages.FormSubmissionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormSubmissionTestSuite extends FormSubmissionPage {

    @Test(priority = 0)
    public void happyPathTestCase() {
        //Fill in the contact form
        fullName.sendKeys("Automation Tester");
        email.sendKeys("automationtester@gmail.com");
        enquiry.sendKeys("This is a test enquiry");

        //Submit the form
        submit.click();

        //Wait for the new page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement successfulSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result']")));

        //Get the "submit" response and create the expected response variable
        String expectedText = "Your enquiry has been successfully sent to the store owner.";
        String actualText = successfulSubmit.getText();

        //Verify that the 2 messages match
        Assert.assertEquals(actualText, expectedText, "Actual text does not match expected text");
    }

    @Test(priority = 1)
    public void emptyFieldsTestCase() {
        //Navigate back to the previous page
        driver.navigate().back();

        //Re-locate the elements after returning back to the previous page
        fullName = driver.findElement(By.id("FullName"));
        email = driver.findElement(By.id("Email"));
        enquiry = driver.findElement(By.id("Enquiry"));
        submit = driver.findElement(By.name("send-email"));

        //Submit the empty form
        submit.click();

        //Identify the error messages
        nameErrorMessage = driver.findElement(By.id("FullName-error"));
        emptyEmailErrorMessage = driver.findElement(By.id("Email-error"));
        enquiryErrorMessage = driver.findElement(By.id("Enquiry-error"));

        //Get the actual error messages
        String actualNameErrorMessage = nameErrorMessage.getText();
        String actualEmailErrorMessage = emptyEmailErrorMessage.getText();
        String actualEnquiryErrorMessage = enquiryErrorMessage.getText();

        //Create the expected error messages
        String expectedNameErrorMessage = "Enter your name";
        String expectedEmailErrorMessage = "Enter email";
        String expectedEnquiryErrorMessage = "Enter enquiry";

        //Verify that the actual and expected error messages match
        Assert.assertEquals(actualNameErrorMessage, expectedNameErrorMessage, "The name error messages do not match: " + actualNameErrorMessage);
        Assert.assertEquals(actualEmailErrorMessage, expectedEmailErrorMessage, "The email error messages do not match: " + actualEmailErrorMessage);
        Assert.assertEquals(actualEnquiryErrorMessage, expectedEnquiryErrorMessage, "The enquiry error messages do not match: " + actualEnquiryErrorMessage);
    }

    @Test(priority = 2)
    public void invalidEmailFormatTestCase() {
        //Re-locate the elements
        fullName = driver.findElement(By.id("FullName"));
        email = driver.findElement(By.id("Email"));
        enquiry = driver.findElement(By.id("Enquiry"));
        submit = driver.findElement(By.name("send-email"));

        //Fill in the contact form with an invalid email address
        fullName.sendKeys("Automation tester");
        email.sendKeys("automation.tester@");
        enquiry.sendKeys("This is a test enquiry");

        //Submit the form
        submit.click();

        //Wait until the wrong email error message becomes visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email-error")));

        //Identify the error message
        wrongEmailErrorMessage = driver.findElement(By.id("Email-error"));
        String actualWrongEmailErrorMessage = wrongEmailErrorMessage.getText();

        //Create the error message
        String expectedWrongEmailErrorMessage = "Wrong email";

        //Verify that the 2 error messages match
        Assert.assertEquals(actualWrongEmailErrorMessage, expectedWrongEmailErrorMessage, "The wrong email error messages do not match: " + actualWrongEmailErrorMessage);
    }

    @Test(priority = 3)
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

        //Wait for the new page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement successfulSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result']")));

        //Get the "submit" response and create the expected response variable
        String expectedText = "Your enquiry has been successfully sent to the store owner.";
        String actualText = successfulSubmit.getText();

        //Verify that the 2 messages match
        Assert.assertEquals(actualText, expectedText, "Actual text does not match expected text");
    }

    @Test(priority = 4)
    public void boundaryValuesTestCase() {
        //Navigate to the previous page
        driver.navigate().back();

        //Re-locate the elements
        fullName = driver.findElement(By.id("FullName"));
        enquiry = driver.findElement(By.id("Enquiry"));
        email = driver.findElement(By.id("Email"));
        submit = driver.findElement(By.name("send-email"));

        //Fill in the contact form with minimum allowed characters
        fullName.sendKeys("a");
        enquiry.sendKeys("a");
        email.sendKeys("a@example.com");
        submit.click();

        //Wait for the new page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement successfulSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result']")));

        //Verify whether the test case fails with the appropriate error message
        String expectedNameFieldText = "For the Name field, please enter at least 2 characters";
        String expectedEnquiryFieldText = "For the Enquiry field, please enter at least 2 characters";;
        String actualText = successfulSubmit.getText();

        Assert.assertEquals(actualText, expectedNameFieldText, "Actual text does not match expected text");
        Assert.assertEquals(actualText, expectedEnquiryFieldText, "Actual text does not match expected text");
    }

}
