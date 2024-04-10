package nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;

public class HomePage {
    protected WebDriver driver;
    protected WebElement computersFrame;
    protected WebElement electronicsFrame;
    protected WebElement apparelFrame;
    protected WebElement digitalDownloadsFrame;
    protected WebElement booksFrame;
    protected WebElement jewelryFrame;
    protected WebElement giftCardsFrame;
    protected WebElement storeSearchField;
    protected WebElement searchButton;
    protected List<WebElement> searchResultsItems;
    protected int paginationSize;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");

        computersFrame = driver.findElement(By.xpath("//a[@href='/electronics']"));
        electronicsFrame = driver.findElement(By.xpath("//a[@href='/electronics']"));
        apparelFrame = driver.findElement(By.xpath("//a[@href='/apparel']"));
        digitalDownloadsFrame = driver.findElement(By.xpath("//a[@href='/digital-downloads']"));
        booksFrame = driver.findElement(By.xpath("//a[@href='/books']"));
        jewelryFrame = driver.findElement(By.xpath("//a[@href='/jewelry']"));
        giftCardsFrame = driver.findElement(By.xpath("//a[@href='/gift-cards']"));
        storeSearchField = driver.findElement(By.id("small-searchterms"));
        searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
        searchResultsItems = driver.findElements(By.className("search-results"));
        paginationSize = driver.findElements(By.cssSelector("#pager>span>a")).size();
    }
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


}
