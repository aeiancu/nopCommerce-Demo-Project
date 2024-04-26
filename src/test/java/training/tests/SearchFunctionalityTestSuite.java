package training.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import training.pages.SearchFunctionalityPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchFunctionalityTestSuite extends SearchFunctionalityPage {

    public SearchFunctionalityTestSuite() {
        super(null);
    }

    @BeforeClass
    public void setUp(@Optional("chrome") String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }

        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://makeup.ro");
        driver.manage().window().maximize();
        acceptCookies.click();
    }


    @Test(priority = 1)
    public void emptySearchField() {
        //Click the search button
        searchButton.click();

        //Perform an empty search
        searchInput.clear();
        searchInput.sendKeys(Keys.ENTER);

        //Check that there are no search results
        String searchResultText = searchResults.getText();
        Assert.assertEquals(searchResultText, "A fost găsit 0 produse.", "Search result is not 0: " + searchResultText);
    }

    @Test(priority = 2)
    public void validDataSearchField() {
        searchButton.click();

        //Search for 'sampon'
        searchInput.sendKeys("Sampon");
        searchInput.sendKeys(Keys.ENTER);

        //Check whether the results contain the key word 'sampon'
        for (WebElement product : catalogProducts) {
            String productText = product.getText();
            Assert.assertTrue(productText.contains("sampon"), "Product list does not contain the key word 'sampon'");
        }
    }

    @Test(priority = 3)
    public void sortSearchResultsByPrice() {
        sortByDropdown.click();
        sortByPrice.click();

        List<WebElement> productPrices = driver.findElements(By.cssSelector("span[class='product-price']"));

        // Convert the product prices to a list of doubles for comparison
        List <Double> sortedPrices = new ArrayList<>();
              for (WebElement priceElement : productPrices) {
                  String priceText = priceElement.getText();
                  double price = Double.parseDouble(priceText);
                  sortedPrices.add(price);
              }

        // Verify that the prices are sorted in ascending order
       for (int i = 0; i < sortedPrices.size() - 1; i++) {
           Assert.assertTrue(sortedPrices.get(i) <= sortedPrices.get(i + 1), "Prices are not sorted in ascending order: " + sortedPrices);
       }
    }

    @Test(dependsOnMethods = {"validDataSearchField"})
    public void sortSearchResultsByName() {
        //Get the list of products after searching for 'sampon'
        List <WebElement> productNames = driver.findElements(By.xpath("//a[@class='simple-slider-list__name']"));

        List <String> itemsName = new ArrayList<>();
        for (WebElement itemName : productNames) {
            itemsName.add(itemName.getText());
        }

        //Sort the results
        Collections.sort(itemsName);
        System.out.println("-------------");
        System.out.println(itemsName);

        //Sort the results from the dropdown menu
        sortByDropdown.click();
        sortByName.click();

        //Get the list of products after sorting them from the dropdown menu
        List <String> sortedItems = new ArrayList<>();
        for (WebElement sortedItem : productNames) {
            sortedItems.add(sortedItem.getText());
        }
        System.out.println("-------------");
        System.out.println(sortedItems);

        //Check whether the 2 lists are the same
        Assert.assertEquals(sortedItems, itemsName, "Items are not sorted ascending by name: " + sortedItems);
    }

    @Test(dependsOnMethods = {"validDataSearchField"})
    public void searchResultsPagination() {
        for (WebElement page : pages) {
            page.click();
            //Assert that each page contains the search term
            catalogProducts = driver.findElements(By.xpath("//div[@class='catalog-products']"));
            for (WebElement product : catalogProducts) {
                String productText = product.getText();
                Assert.assertTrue(productText.contains("sampon"), "The list of products does not contain the search term 'sampon': " + productText);
            }

            // Sleep for a short duration to allow the page to load
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }

    @Test(priority = 5)
    public void caseInsensitivityTestCase() {
        String[] searchQuery = {"sampon", "Sampon", "SAMPON", "Șampon", "șampon"};
        for (String searchKeyWord : searchQuery) {
            searchButton.click();
            searchInput.sendKeys(searchKeyWord);
            searchInput.sendKeys(Keys.ENTER);

            catalogProducts = driver.findElements(By.cssSelector("div[class='info-product-wrapper']"));
            for (WebElement product : catalogProducts) {
                String productText = product.getText().toLowerCase();
                Assert.assertTrue(productText.contains(searchKeyWord), "The list of products does not contain 'sampon': " +productText);
            }
        }

        //Navigate back to start the new iteration
        driver.navigate().back();
    }



  /*  @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

   */
}