import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class PurchasePerfectWorld extends MainTest {

    @DataProvider(name = "credentials")
    public Object[][] getData(){

        String[][] purchaseCreds = getExcelData("/home/tina/IdeaProjects/testframework-awfulvalentine/src/main/resources/credentials.xlsx", "Sheet1");
        return purchaseCreds;
    }




    @Test(dataProvider = "credentials")
    public void madePurchase(String testcaseNumber, String dataName, String dataCreditCard, String dataMonth, String dataYear){

        //open main site
        //navigate to purchase forms
        //open perfect world link
        //insert full name, credit card, month, year
        //click on purchase
        //assert "purchase complete

        String url = "http://awful-valentine.com/";
        driver.get(url);

        //check title
        String expectedTitleMainPage = "Valentine's Day Cards | Please your loved ones with a card this year!";
        Assert.assertEquals(expectedTitleMainPage, driver.getTitle());


        Actions action = new Actions(driver);
        WebElement purchaseLink = driver.findElement(By.linkText("Purchase Forms"));
        action.moveToElement(purchaseLink).perform();

        WebElement perfectWorldLink = driver.findElement(By.linkText("Perfect World"));

        action.moveToElement(perfectWorldLink).perform();
        perfectWorldLink.click();

        //check title
        String expectedTitlePurchasePage = "Perfect World | Valentine's Day Cards";
        Assert.assertEquals(expectedTitlePurchasePage, driver.getTitle());


        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys(dataName);

        WebElement creditCard = driver.findElement(By.id("cc"));
        creditCard.sendKeys(dataCreditCard);

        WebElement month = driver.findElement(By.id("month"));
        month.sendKeys(dataMonth);

        WebElement year = driver.findElement(By.id("year"));
        year.sendKeys(dataYear);

        WebElement buttonPurchase = driver.findElement(By.id("go"));
        buttonPurchase.click();

        //check completion
        WebElement complete = driver.findElement(By.id("success"));

        complete.isDisplayed();
        Assert.assertTrue(complete.isDisplayed(), "Purchase is not complete. Success message is not displayed");

        driver.quit();



    }
}
