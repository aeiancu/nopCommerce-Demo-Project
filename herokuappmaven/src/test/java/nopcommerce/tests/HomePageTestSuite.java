package nopcommerce.tests;

import nopcommerce.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePageTestSuite extends HomePage {

    @Test(groups = "SearchFunctionality")
    public void testEmptyStoreSearchField() {
        //Perform empty search
        storeSearchField.clear();
        searchButton.click();

        //Wait for the alert to appear
        String alertText = driver.switchTo().alert().getText();

        //Close the alert
        driver.switchTo().alert().accept();

        //Verify the alert message
        Assert.assertEquals("Please enter some search keyword",
                alertText);
    }

    @Test(groups = "SearchFunctionality")
    public void testStoreSearchWithValidData() {
        //Search for books
        storeSearchField = driver.findElement(By.id("small-searchterms"));
        searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));

        storeSearchField.sendKeys("Book");
        searchButton.click();

        //Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));

        // Refresh the page to ensure elements are updated
        driver.navigate().refresh();

        //Re-locate the search input field
        storeSearchField = driver.findElement(By.id("small-searchterms"));
        storeSearchField.clear();
        storeSearchField.sendKeys("Book");

        //Re-locate the search button
        searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
        searchButton.click();

        //Wait for search results to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));

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

            // Wait for the page to navigate to the search results page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-text")));

            // Re-locate the search results items after page navigation
            List<WebElement> updatedSearchResultsItems = driver.findElements(By.className("search-results"));

            // Verify that all search results contain the term 'book' case-insensitively
            for (WebElement searchResult : updatedSearchResultsItems) {
                String searchResultText = searchResult.getText().toLowerCase(); // Convert text to lowercase for case-insensitive comparison
                Assert.assertTrue(searchResultText.contains(term.toLowerCase()), "Search result is not a book: " + searchResultText);
            }

            // Navigate back to the contact us page
            driver.navigate().back();

            // Re-locate the search field and search button for the next iteration
            storeSearchField = driver.findElement(By.id("small-searchterms"));
            searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
        }

    }

    @Test(groups = "SearchFunctionality")
    public void testPartialSearchTerm() {
        //Search for books using different partial terms
        String[] partialSearchTerms = {"books", "pie", "pies", "Bradbury", "Prejudice"};
        for (String partialTerm : partialSearchTerms) {
            storeSearchField.clear();
            storeSearchField.sendKeys(partialTerm);
            searchButton.click();

            //Wait for the page to navigate to the search results page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-text")));

            //Verify that all search results contain the term book
            List<WebElement> updatedSearchResultsItems = driver.findElements(By.className("search-results"));
            for (WebElement searchResult : updatedSearchResultsItems) {
                String searchResultText = searchResult.getText().toLowerCase();
                Assert.assertTrue(searchResultText.contains(partialTerm.toLowerCase()), "Search result is not a book: " + searchResultText);
            }

            //Navigate back to the contactus page
            driver.navigate().back();

            //Re-locate the search field and the search button for the next iteration
            storeSearchField = driver.findElement(By.id("small-searchterms"));
            searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
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

            //Wait for the page to navigate to the search results page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-text")));

            //Verify that the search results perfectly match the search terms
            List<WebElement> updatedSearchResultsItems = driver.findElements(By.className("search-results"));
            for (WebElement searchResult : updatedSearchResultsItems) {
                String searchResultText = searchResult.getText();
                Assert.assertTrue(searchResultText.contains(exactTerm), "Search result does not perfectly match: " + searchResultText);
            }

            //Navigate back to the contactus page
            driver.navigate().back();

            // Re-locate the search field and search button for the next iteration
            storeSearchField = driver.findElement(By.id("small-searchterms"));
            searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
        }
    }

    @Test(groups = "SearchFunctionality")
    public void testSearchResultPagination() {
        //Search for a query with multiple results
        storeSearchField = driver.findElement(By.id("small-searchterms"));
        String searchQuery = "book";
        search(searchQuery);

        //Verify navigating through pages
        for (int i = 1; i<=paginationSize; i++) {
            String paginationSelector = "#pager>span>a:nth-child("+i+")";
            driver.findElement(By.cssSelector(paginationSelector)).click();


            // Wait for search results to load on the next page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));

            // Verify search results on each page
            verifySearchResults(searchQuery);

            // Additional assertions specific to each page
            // For example, you can verify the page number displayed or other relevant information
            WebElement currentPage = driver.findElement(By.className("current-page"));
            String currentPageNumber = currentPage.getText();
            Assert.assertEquals(Integer.toString(i), currentPageNumber, "Current page number is not as expected");
        }
    }


    @Test(groups = "SearchFunctionality")
    public void testSearchResultSorting() {
        //Search for a query with multiple results
        storeSearchField = driver.findElement(By.id("small-searchterms"));
        String searchQuery = "book";
        search(searchQuery);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("products-orderby")));

        //Refresh the page to ensure elements are updated
        driver.navigate().refresh();

        List<WebElement> productItems = driver.findElements(By.xpath("//*[contains(@class,'product-item')]"));
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));

        //Locate the dropdown buttons
        WebElement sortByDropdown = driver.findElement(By.id("products-orderby"));
        WebElement displayDropdown = driver.findElement(By.id("products-pagesize"));

        Select sortBy = new Select(sortByDropdown);
        Select display = new Select(displayDropdown);

        //Creating the original non-sorted product list
        display.selectByValue("9");

        List<String> originalList = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        for(WebElement product:productItems) {
            originalList.add(product.getText());
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("originalList - unsorted");
        System.out.println(originalList);
  /*  System.out.println("-------------------------------------------------------");
    System.out.println("Original List of Search Results:");
    for (String result : originalList) {
        System.out.println(result);
    }*/

        //Creating the sorted product list
        //  List<String> tempList = new ArrayList<>();
        //  for (WebElement product:productItems) {
//tempList.add(product.getText());
        //   }
        //Sort the list alphabetically
        Collections.sort(originalList);
        System.out.println("-------------------------------------------------------");
        System.out.println("originalList: sorted");
        System.out.println(originalList);

        //Sort the items by Name: A to Z

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("products-orderby")));
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));

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
        //Search for an invalid term
        storeSearchField = driver.findElement(By.id("small-searchterms"));
        searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));

        storeSearchField.sendKeys("ab");
        searchButton.click();

        // Refresh the page to ensure elements are updated
        driver.navigate().refresh();

        //Locate the error message
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='warning']"));

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
        }
    }
}
