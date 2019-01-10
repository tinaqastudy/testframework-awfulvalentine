import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.MainTest;

public class SearchNegativeTest extends MainTest {


    @Test
    @Parameters("searchWord")
    public void searchNegativeResult(String searchWord){

        //open page
        String url = "http://awful-valentine.com/";
        driver.get(url);

        //check title
        String expectedTitle = "Valentine's Day Cards | Please your loved ones with a card this year!";
        Assert.assertEquals(expectedTitle, driver.getTitle());

        //perform search. Insert "cheese" into search input field
        WebElement searchInput = driver.findElement(By.id("searchinput"));
        searchInput.sendKeys(searchWord);
        driver.findElement(By.id("searchsubmit")).click();

        //get result page. Check "No results found"

        WebElement searchResult = driver.findElement(By.className("entry"));
        String searchResultText = searchResult.getText();

        Assert.assertTrue(searchResultText.contains("No Results Found"), "Expected 'No Results Found', actual:" + searchResultText);

        driver.quit();


    }
}
