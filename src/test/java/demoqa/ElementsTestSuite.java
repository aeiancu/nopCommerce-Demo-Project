package demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;

public class ElementsTestSuite extends ElementsPage {

    public ElementsTestSuite() {
        super(null);
    }

    String url = "https://demoqa.com/elements";

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

    @Test(groups = "Text Box")
    public void fillingTheFormWithValidData() {
        driver.switchTo().frame(adClose).findElement(By.id("cbb")).click();
        driver.switchTo().defaultContent();
        textBox.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400)");
        userName.sendKeys("Automation Tester");
        userEmail.sendKeys("automation@example.com");
        currentAddress.sendKeys("49 Featherstone Street");
        permanentAddress.sendKeys("49 Featherstone Street");
        submit.click();
    }

    @Test(groups = "Check Box")
    public void expandingCheckBoxes() {
        driver.switchTo().frame(adClose).findElement(By.id("cbb")).click();
        driver.switchTo().defaultContent();
        checkBox.click();
        homeCollapseButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400)");
        desktopCollapseButton.click();
        documentsCollapseButton.click();
        workspaceCollapseButton.click();
        officeCollapseButton.click();
        js.executeScript("window.scrollBy(0, 200)");
        downloadsCollapseButton.click();
        Assert.assertTrue(notesCheckbox.isDisplayed(), "Notes checkbox is not displayed after expanding Desktop checkbox");
        Assert.assertTrue(workspaceCheckbox.isDisplayed(), "WorkSpace checkbox is not displayed after expanding Documents checkbox");
    }

    @Test(groups = "Radio Button")
    public void clickingOnRadioButtons() {
            driver.switchTo().frame(adClose).findElement(By.id("cbb")).click();
            driver.switchTo().defaultContent();
            radioButton.click();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 300)");

            yesRadio.click();
            Assert.assertTrue(yesRadio.isSelected(), "Yes option is not selected after clicking on it.");
    }

    @Test(groups = "Web Tables")
    public void extractingMaxAgeFromTable() {
        webTables.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        int maxAge = Collections.max(getTheAgeList());
        System.out.println("Max age is: " + maxAge);

        Assert.assertTrue(getTheAgeList().contains(maxAge), "The age column does not contain the max number.");
    }

    @Test(groups = "Web Tables")
    public void extractingSalaryOfOldestEmployee() {
        webTables.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        int maxAge = Collections.max(getTheAgeList());
        int maxAgeIndex = getTheAgeList().indexOf(maxAge);

        int salaryOfOldestEmployee = getTheSalaryList().get(maxAgeIndex);
        System.out.println("Salary of the oldest employee: " + salaryOfOldestEmployee);
        int expectedSalaryOfOldestEmployee = 12000;
        Assert.assertEquals(expectedSalaryOfOldestEmployee, salaryOfOldestEmployee, "Salary of the oldest employee does not match the expected salary.");
    }

    @Test(groups = "Web Tables")
    public void extractingEmailAndDepartmentOfYoungestEmployee() {
        webTables.click();
        int minAge = Collections.min(getTheAgeList());
        int minAgeIndex = getTheAgeList().indexOf(minAge);
        String emailOfYoungestEmployee = getTheEmailList().get(minAgeIndex);
        String departmentOfYoungestEmployee = getTheDepartmentList().get(minAgeIndex);
        System.out.println("The email of the youngest employee is: " + emailOfYoungestEmployee);
        System.out.println("The department of the youngest employee is: " + departmentOfYoungestEmployee);
        Assert.assertEquals(emailOfYoungestEmployee, "kierra@example.com", "Email of the youngest employee does not match the expected email.");
        Assert.assertEquals(departmentOfYoungestEmployee, "Legal", "Department of the youngest employee does not match the expected department.");
    }

    @Test(groups = "Buttons")
    public void performingMouseActions() {
        buttons.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickBtn).perform();
        actions.contextClick(rightClickBtn).perform();
        dynamicClickBtn.click();
        String doubleClickMessageText = doubleClickMessage.getText();
        String rightClickMessageText = rightClickMessage.getText();
        String dynamicClickMessageText = dynamicClickMessage.getText();
        Assert.assertEquals(doubleClickMessageText, "You have done a double click", "The double click message is not displayed.");
        Assert.assertEquals(rightClickMessageText, "You have done a right click", "The right click message is not displayed.");
        Assert.assertEquals(dynamicClickMessageText, "You have done a dynamic click", "The dynamic click message is not displayed.");
    }





}

