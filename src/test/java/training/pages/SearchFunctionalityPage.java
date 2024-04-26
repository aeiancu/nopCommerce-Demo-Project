package training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchFunctionalityPage {

   public SearchFunctionalityPage(WebDriver driver) {
       this.driver = driver;
       PageFactory.initElements(driver, this);
   }

    protected WebDriver driver;
    protected WebElement rejectCookies;
    @FindBy(xpath = "//button[@class='button']")
    protected WebElement acceptCookies;
    @FindBy(id = "search-input")
    protected WebElement searchInput;
    @FindBy(css = "div[data-popup-handler='search']")
    protected WebElement searchButton;
    @FindBy(css = "div[class='search-results info-text']")
    protected WebElement searchResults;
    @FindBy(css = "div[class='catalog-products']")
    protected List <WebElement> catalogProducts;
    @FindBy(css = "div[class='catalog-sort__list-title']")
    protected WebElement sortByDropdown;
    @FindBy(css = "label[for='input-sort-1']")
    protected WebElement sortByPrice;
    @FindBy(css = "label[for='input-sort-0']")
    protected WebElement sortByName;
    @FindBy(css = ".page")
    protected WebElement pagination;
    @FindBy(css = "page__item")
    protected List<WebElement> pages;
    @FindBy(xpath = "(//a[@class='menu-list__link menu-list__link_category'])[1]")
    protected WebElement perfumeMenu;
    @FindBy(xpath = "//a[@href='/categorys/324237/']")
    protected WebElement womanPerfume;



}
