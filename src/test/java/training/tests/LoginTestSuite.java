package training.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import training.pages.LoginPage;

import java.time.Duration;


public class LoginTestSuite extends LoginPage {

    public LoginTestSuite() {
        super(null); // Call superclass constructor
    }

    String url = "https://makeup.ro";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
       // acceptCookies.click();
    }


//Disabling this test as registering twice with the same credentials is not possible and thus causing the test to fail on subsequent runs.
    @Test(enabled = false)
    public void registerAsNewClient() {
        driver.get(url);
        acceptCookies.click();
        loginButton.click();
        registerButton.click();
        lastName.sendKeys("Automation");
        firstName.sendKeys("Tester");
        dateOfBirth.click();
        dateOfBirthYear.click();
        yearOption.click();
        dateOfBirthMonth.click();
        monthOption.click();
        dayOption.click();
        phoneNumber.sendKeys("773657899");
        emailAddressField.sendKeys("automationtester1@example.com");
        passwordField.sendKeys("ABC123!");
        confirmPassword.sendKeys("ABC123!");
        submitButton.click();
        String registrationDoneText = registrationDone.getText();
        Assert.assertEquals(registrationDoneText, "Înregistrarea a fost încheiată cu succes!\nMesajul cu datele contului tău a fost trimis la adresa ta de e-mail.",
                "Registration was not completed" + registrationDoneText);
    }


    @Test
    public void registerAsNewClientUsingRandomEmailsGeneration() {
        driver.get(url);
        acceptCookies.click();
        loginButton.click();
        registerButton.click();
        lastName.sendKeys("Automation");
        firstName.sendKeys("Tester");
        dateOfBirth.click();
        dateOfBirthYear.click();
        yearOption.click();
        dateOfBirthMonth.click();
        monthOption.click();
        dayOption.click();
        phoneNumber.sendKeys("773657899");

        //Generate random email
        String randomEmail = generateRandomEmailAddress();
        emailAddressField.sendKeys(randomEmail);

        //Store the registered email for later use
        registeredEmail = randomEmail;

        passwordField.sendKeys("ABC123!");
        confirmPassword.sendKeys("ABC123!");
        submitButton.click();
        String registrationDoneText = registrationDone.getText();
        Assert.assertEquals(registrationDoneText, "Înregistrarea a fost încheiată cu succes!\nMesajul cu datele contului tău a fost trimis la adresa ta de e-mail.",
                "Registration was not completed" + registrationDoneText);
    }

    @Test
    public void logOutFlow() {
        driver.get(url);
        acceptCookies.click();
        loginButton.click();
        loginEmail.sendKeys("automationtester1@example.com");
        loginPassword.sendKeys("ABC123!");
        authenticationButton.click();
        accountDetails.click();
        logOutButton.click();
        loginButton.click();
        Assert.assertTrue(authenticationButton.isDisplayed(), "Authentication button is not displayed after logging out.");
    }

    @Test(dataProvider = "loginData")
    public void loginWithNonexistentAccounts(String username, String password) {
        driver.get(url);
        acceptCookies.click();
        loginButton.click();
        loginEmail.clear();
        loginEmail.sendKeys(username);
        loginPassword.clear();
        loginPassword.sendKeys(password);
        authenticationButton.click();

        //Assert that login failed with nonexistent accounts
        Assert.assertTrue(authenticationButton.isDisplayed(), "Authentication button is not displayed after attempting to log in with nonexistent accounts.");
    }

    @Test(priority=3)
    public void happyFlowLogin() {
        driver.get(url);
        acceptCookies.click();
        loginButton.click();
        loginEmail.sendKeys("automationtester1@example.com");
        loginPassword.sendKeys("ABC123!");
        authenticationButton.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
