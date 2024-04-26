package training.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import training.pages.TablePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableTestSuite extends TablePage {

    public TableTestSuite() {
        super(null);
    }

    String url = "https://website.multiformis.com/sort-and-tables/";

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

    @Test
    public void extractTheMaximumAndMinimumAgeValues() {
        List<Integer> ageList = new ArrayList<>();
        for (WebElement age : ageColumn) {
            String ageText = age.getText();
         //   System.out.println(ageText);
            int ageInt = Integer.parseInt(ageText);
            ageList.add(ageInt);
          //  System.out.println(ageInt);
        }

        int maxAge = Collections.max(ageList);
        System.out.println("Maximum age number is: " + maxAge);

        int minAge = Collections.min(ageList);
        System.out.println("Minimum age number is: " + minAge);

        //Assert the maximum and minimum age values
        Assert.assertTrue(ageList.contains(maxAge), "Max age is not found in the age list");
        Assert.assertTrue(ageList.contains(minAge), "Min age is not found in the age list");

    }

    @Test
    public void sortByFirstName() {
        Select selectByEntries = new Select(selectEntries);
        selectByEntries.selectByValue("100");
        List<String> firstNameItems = new ArrayList<>();
        for (WebElement firstNameItem : firstNameColumn) {
            String firstNameItemText = firstNameItem.getText();
            firstNameItems.add(firstNameItemText);
        }

        System.out.println("-----Items before sorting-----");
        System.out.println(firstNameItems);

        List<String> firstNameItemsSorted = new ArrayList<>();
        for (WebElement firstNameItemSorted : firstNameColumn) {
            String firstNameItemSortedText = firstNameItemSorted.getText();
            firstNameItemsSorted.add(firstNameItemSortedText);
        }

        Collections.sort(firstNameItemsSorted);
        System.out.println("-----Items after sorting with Collection class-----");
        System.out.println(firstNameItemsSorted);

        sortByFirstName.click();
        List<String> firstNamesSortedByButton = new ArrayList<>();
        for (WebElement firstNameSortedByButton : firstNameColumn) {
            String firstNameSortedByButtonText = firstNameSortedByButton.getText();
            firstNamesSortedByButton.add(firstNameSortedByButtonText);
        }
        System.out.println("-----Items after sorting by first_name button-----");
        System.out.println(firstNamesSortedByButton);
        Assert.assertEquals(firstNamesSortedByButton, firstNameItemsSorted, "The list of first names is not equal: " + firstNameItems);
    }











}
