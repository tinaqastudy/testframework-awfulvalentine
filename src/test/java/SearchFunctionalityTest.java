import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTest extends MainTest{



    @Test
    public void searchTest(){

        String searchKeyWord = "closeness";
        String formattedSearchKeyWord = searchKeyWord.toLowerCase();

        //open page
        String url = "http://awful-valentine.com/";
        driver.get(url);


        //check title
        String expectedTitle = "Valentine's Day Cards | Please your loved ones with a card this year!";
        Assert.assertEquals(expectedTitle, driver.getTitle());

        //search "closeness"
        WebElement searchField = driver.findElement(By.id("searchinput"));
        searchField.sendKeys(formattedSearchKeyWord);
        driver.findElement(By.id("searchsubmit")).click();

        //check result
        //expect 2 elements with classname main-product
        List<WebElement> foundCards = driver.findElements(By.className("main-product"));

        System.out.println("Found cards:" + foundCards.size());
        Assert.assertEquals(foundCards.size(), 2, "Expected amount: 2. Found: " + foundCards.size() + "cards.");

        //check that search word is highlighted. "closeness" is wrapped with span with highlight classname
        String highlightedWord = driver.findElement(By.className("search-everything-highlight-color")).getText().toLowerCase();

        System.out.println(highlightedWord + "   " + formattedSearchKeyWord);
        Assert.assertTrue(highlightedWord.equals(formattedSearchKeyWord), "Search word 'closeness' should be highlighted. But highlighted another word: " + highlightedWord);


        driver.quit();
    }


}
