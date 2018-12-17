import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchFunctionalityTest {



    @Test
    public void searchTest(){

        System.setProperty("webdriver.gecko.driver", "/home/tina/IdeaProjects/testframework-awfulvalentine/src/main/resources/geckodriver");
        WebDriver driver = new FirefoxDriver();

        //open page
        String url = "http://awful-valentine.com/";
        driver.get(url);

        mywait(3000);

        //check title
        String expectedTitle = "Valentine's Day Cards | Please your loved ones with a card this year!";
        Assert.assertEquals(expectedTitle, driver.getTitle());

        //search "cheese"
        WebElement searchField = driver.findElement(By.id("searchinput"));
        searchField.sendKeys("cheese");
        driver.findElement(By.id("searchsubmit")).click();

        mywait(3000);

        //check result
        WebElement searchResult = driver.findElement(By.className("entry"));
        String searchResultText = searchResult.getText();

        Assert.assertTrue(searchResultText.contains("No Results Found"), "Expected 'No Results Found', actual:" + searchResultText);

        driver.quit();
    }

    protected void mywait(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
