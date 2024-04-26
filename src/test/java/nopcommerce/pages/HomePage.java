package nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class HomePage {

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver driver;
    @FindBy(id = "small-searchterms")
    protected WebElement storeSearchField;
    @FindBy(xpath = "//a[@href='/register?returnUrl=%2F']")
    protected WebElement registerPage;
    @FindBy(xpath = "//button[@class='button-1 search-box-button']")
    protected WebElement searchButton;
    protected List<WebElement> searchResultsItems;
    protected int paginationSize;
    @FindBy(id = "products-orderby")
    protected WebElement sortByDropdown;
    @FindBy(id = "products-pagesize")
    protected WebElement displayDropdown;
    @FindBy(xpath = "//div[@class='warning']")
    protected WebElement errorMessage;
    @FindBy(xpath = "//img[@alt='nopCommerce demo store']")
    protected WebElement homePageTitle;
    @FindBy(id = "gender-male")
    protected WebElement maleGender;
    @FindBy(id = "gender-female")
    protected WebElement femaleGender;
    @FindBy(id = "FirstName")
    protected WebElement firstNameRegisterPage;
    @FindBy(id = "LastName")
    protected WebElement lastNameRegisterPage;
    @FindBy(xpath = "//select[@name='DateOfBirthDay']")
    protected WebElement dateOfBirthDay;
    @FindBy(xpath = "//select[@name='DateOfBirthMonth']")
    protected WebElement dateOfBirthMonth;
    @FindBy(xpath = "//select[@name='DateOfBirthYear']")
    protected WebElement dateOfBirthYear;
    @FindBy(id = "Email")
    protected WebElement emailRegisterPage;
    @FindBy(id = "Company")
    protected WebElement companyName;
    @FindBy(id = "Newsletter")
    protected WebElement newsletterCheckbox;
    @FindBy(id = "Password")
    protected WebElement passwordRegisterPage;
    @FindBy(id = "ConfirmPassword")
    protected WebElement confirmPasswordRegisterPage;
    @FindBy(id = "register-button")
    protected WebElement registerButton;
    @FindBy(xpath = "//div[@class='result']")
    protected WebElement registrationCompleted;
    @FindBy(xpath = "//a[@class='ico-login']")
    protected WebElement logInPage;
    @FindBy(id = "Email")
    protected WebElement emailLogInForm;
    @FindBy(id = "Password")
    protected WebElement passwordLogInForm;
    @FindBy(xpath = "//button[@class='button-1 login-button']")
    protected WebElement logInButton;
    protected String registeredEmail;
    @FindBy(xpath = "//a[@href='/compareproducts']")
    protected WebElement compareProducts;

    public void search(String query) {
        storeSearchField.sendKeys(query);
        driver.findElement(By.xpath("//button[@class='button-1 search-box-button']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));
    }

    public void verifySearchResults(String searchQuery) {
        List<WebElement> searchResults = driver.findElements(By.className("search-results"));
        for (WebElement result : searchResults) {
            String resultText = result.getText().toLowerCase();
            Assert.assertTrue(resultText.contains(searchQuery), "Search result does not contain the search query: " + resultText);
        }
    }
    public static String generateRandomEmail() {
        String randomUUID = UUID.randomUUID().toString();
        randomUUID = randomUUID.replace("-", "");
        String emailAddress = randomUUID + "@example.com";
        return emailAddress;
    }
}
