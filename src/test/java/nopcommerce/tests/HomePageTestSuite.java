package nopcommerce.tests;

import nopcommerce.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageTestSuite extends HomePage {

    public HomePageTestSuite() {
        super(null);
    }

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @Test(groups = "SearchFunctionality")
    public void testEmptyStoreSearchField() {
        //Perform empty search
        searchButton.click();

        //Get the alert text
        String alertText = driver.switchTo().alert().getText();

        //Close the alert
        driver.switchTo().alert().accept();

        //Verify the alert message
        Assert.assertEquals("Please enter some search keyword",
                alertText);
    }

    @Test(groups = "SearchFunctionality")
    public void testStoreSearchWithValidData() {
        //Search for term 'book'
        storeSearchField.sendKeys("Book");
        searchButton.click();

        //Verify that all search results are books
        List<WebElement> updatedSearchResultsItems = driver.findElements(By.className("search-results"));
        for (WebElement searchResult : updatedSearchResultsItems) {
            String searchResultText = searchResult.getText();
            Assert.assertTrue(searchResultText.contains("Book"), "Search result is not a book: " + searchResultText);
        }
    }

    @Test(groups = "SearchFunctionality")
    public void testCaseInsensitivitySearchField() {
        // Search for books with different cases
        String[] searchTerms = {"book", "Book", "BOOK"};
        for (String term : searchTerms) {
            // Clear the search field and enter the search term
            storeSearchField.clear();
            storeSearchField.sendKeys(term);

            // Click the search button
            searchButton.click();

            // Re-locate the search results items after page navigation
            List<WebElement> updatedSearchResultsItems = driver.findElements(By.className("search-results"));

            // Verify that all search results contain the term 'book' case-insensitively
            for (WebElement searchResult : updatedSearchResultsItems) {
                String searchResultText = searchResult.getText().toLowerCase(); // Convert text to lowercase for case-insensitive comparison
                Assert.assertTrue(searchResultText.contains(term.toLowerCase()), "Search result is not a book: " + searchResultText);
            }

            // Navigate back to the contact us page
            driver.navigate().back();
        }
    }

    @Test(groups = "SearchFunctionality")
    public void testPartialSearchTerm() {
        //Search for books using different partial terms
        String[] partialSearchTerms = {"book", "pie", "pies", "Bradbury", "Prejudice"};
        for (String partialTerm : partialSearchTerms) {
//            storeSearchField.clear();
            storeSearchField.sendKeys(partialTerm);
            searchButton.click();

            //Verify that all search results contain the term book
            List<WebElement> updatedSearchResultsItems = driver.findElements(By.className("search-results"));
            for (WebElement searchResult : updatedSearchResultsItems) {
                String searchResultText = searchResult.getText().toLowerCase();
                Assert.assertTrue(searchResultText.contains(partialTerm.toLowerCase()), "Search result is not a book: " + searchResultText);
            }

            //Navigate back to the contactus page
            driver.navigate().back();
        }
    }

    @Test(groups = "SearchFunctionality")
    public void testExactSearchTerms() {
        //Search for exact match terms
        String[] exactSearchTerms = {"Nikon D5500 DSLR", "Apple iCam", "Leica T Mirrorless Digital Camera"};
        for (String exactTerm : exactSearchTerms) {
            storeSearchField.clear();
            storeSearchField.sendKeys(exactTerm);
            searchButton.click();

            //Verify that the search results perfectly match the search terms
            List<WebElement> updatedSearchResultsItems = driver.findElements(By.className("search-results"));
            for (WebElement searchResult : updatedSearchResultsItems) {
                String searchResultText = searchResult.getText();
                Assert.assertTrue(searchResultText.contains(exactTerm), "Search result does not perfectly match: " + searchResultText);
            }

            //Navigate back to the contactus page
            driver.navigate().back();
        }
    }

    @Test(groups = "SearchFunctionality")
    public void testSearchResultPagination() {
        String searchQuery = "book";
        search(searchQuery);

        //Verify navigating through pages
        for (int i = 1; i<=paginationSize; i++) {
            String paginationSelector = "pager>span>a:nth-child("+i+")";
            driver.findElement(By.cssSelector(paginationSelector)).click();

            // Verify search results on each page
            verifySearchResults(searchQuery);

            //Verify the page number displayed
            WebElement currentPage = driver.findElement(By.className("current-page"));
            String currentPageNumber = currentPage.getText();
            Assert.assertEquals(Integer.toString(i), currentPageNumber, "Current page number is not as expected");
        }
    }

    @Test(groups = "SearchFunctionality")
    public void testSearchResultSorting() {
        String searchQuery = "book";
        search(searchQuery);

        //Refresh the page to ensure elements are updated
        driver.navigate().refresh();

        List<WebElement> productItems = driver.findElements(By.xpath("//*[contains(@class,'product-item')]"));
        Select sortBy = new Select(sortByDropdown);
        Select display = new Select(displayDropdown);

        //Creating the original non-sorted product list
        display.selectByValue("9");

        List<String> originalList = new ArrayList<>();
        for(WebElement product:productItems) {
            originalList.add(product.getText());
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("originalList - unsorted");
        System.out.println(originalList);

        //Sort the list alphabetically
        Collections.sort(originalList);
        System.out.println("-------------------------------------------------------");
        System.out.println("originalList: sorted");
        System.out.println(originalList);

        //Sort the items by Name: A to Z
        sortBy.selectByValue("5");

        List<String> sortedlList = new ArrayList<>();
        List<WebElement> sortedProductItems = driver.findElements(By.xpath("//*[contains(@class,'product-item')]"));
        for(WebElement sortedProduct:sortedProductItems) {
            sortedlList.add(sortedProduct.getText());
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("sortedlList - Sorted List of Search Results:");
        System.out.println(sortedlList);

        //Verify that the list is alphabetically ordered
        Assert.assertEquals(sortedlList, originalList, "The lists do not match: " + originalList);
    }

    @Test(groups = "SearchFunctionality")
    public void testStoreSearchWithInvalidData() {
        storeSearchField.sendKeys("ab");
        searchButton.click();

        // Refresh the page to ensure elements are updated
        driver.navigate().refresh();

        //Get the text of the error message
        String errorText = errorMessage.getText();
        System.out.println("Error message: " + errorText);

        //Expected error message
        String expectedErrorMessage = "Search term minimum length is 3 characters";

        //Assert that the error message matches the expected error message
        Assert.assertEquals(errorText, expectedErrorMessage, "Error message doesn't match");
    }

    @Test
    public void tabsNavigationTestCase() {
        String[] tabLinks = { "/electronics", "/computers", "/apparel", "/digital-downloads", "/books", "/jewelry", "/gift-cards" };
        for (String link : tabLinks) {
            WebElement tabFrame = driver.findElement(By.xpath("//a[@href='" + link + "']"));
            tabFrame.click();

            String updatedURL = driver.getCurrentUrl();
            Assert.assertTrue(updatedURL.contains(link), "URL doesn't contain the tab link");
        }
    }

    @Test
    public void checkTitlePageTestCase() {
        String homePageTitleText = homePageTitle.getText();
        System.out.println("--------------");
        System.out.println(homePageTitleText);
        Assert.assertEquals(homePageTitleText, "nopCommerce", "Home page title doesn't contain 'nopCommerce'");
    }

    @Test
    public void newCustomerRegistrationTestCase() {
        //Go to the Register page
        registerPage.click();

        //Fill in the register form
        femaleGender.click();
        firstNameRegisterPage.sendKeys("Automation");
        lastNameRegisterPage.sendKeys("Tester");
        Select day = new Select(dateOfBirthDay);
        Select month = new Select(dateOfBirthMonth);
        Select year = new Select(dateOfBirthYear);
        day.selectByValue("3");
        month.selectByValue("6");
        year.selectByValue("1992");

        //Generate a random email
        String randomEmail = generateRandomEmail();
        emailRegisterPage.sendKeys(randomEmail);
        passwordRegisterPage.sendKeys("Password123");
        confirmPasswordRegisterPage.sendKeys("Password123");
        registerButton.click();
      //  registrationCompleted = driver.findElement(By.xpath("//div[@class='result']"));

        //Store the registered email for later use
        registeredEmail = randomEmail;

        //Check whether the registration is complete
        String registrationCompletedText = registrationCompleted.getText();
        Assert.assertEquals(registrationCompletedText, "Your registration completed", "Actual text doesn't match expected text: 'Your registration completed'");
    }

    @Test(dependsOnMethods = {"newCustomerRegistrationTestCase"})
    public void logInHappyFlowTestCase() {
        logInPage.click();
        emailLogInForm.sendKeys(registeredEmail);
        passwordLogInForm.sendKeys("Password123");
        logInButton.click();
    }

    @Test
    public void actionsClassTestCase() {
        Actions actions = new Actions(driver);
        actions.moveToElement(compareProducts);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
