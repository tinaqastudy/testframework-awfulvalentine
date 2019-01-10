import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.MainTest;

public class PurchaseSlowAnimation extends MainTest {

    @Test(dataProvider = "credentials")
    public void makePurchase(String testcaseNumber, String dataName, String dataCreditCard, String dataMonth, String dataYear){

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

        WebElement slowAnimationLink = driver.findElement(By.linkText("Slow Animation"));

        action.moveToElement(slowAnimationLink).perform();
        slowAnimationLink.click();

        //check title
        String expectedTitlePurchasePage = "Slow Animation | Valentine's Day Cards";
        Assert.assertEquals(driver.getTitle(), expectedTitlePurchasePage, "Wrong title. Expected " + expectedTitlePurchasePage + ". Actual is: " + driver.getTitle());


        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys(dataName);

        WebElement creditCard = driver.findElement(By.id("cc"));
        creditCard.sendKeys(dataCreditCard);

        WebElement month = driver.findElement(By.id("month"));
        month.sendKeys(dataMonth);

        WebElement year = driver.findElement(By.id("year"));
        year.sendKeys(dataYear);

        WebElement buttonPurchase = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.id("go")));
        buttonPurchase.click();

        //check completion
        //driver.findElement(By.id("success"));
        //WebElement complete = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(By.id("success")));

        //WebElement successMessage = (new WebDriverWait(driver, 5)).until(ExpectedConditions.attributeToBe(By.id("success"), "width", "105"));
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

        WebElement complete = driver.findElement(By.id("success"));
        Assert.assertEquals(complete.getText(), "Purchase complete!", "Purchase message is" + complete.getText() + ". Expected result: Purchase complete!");

        //take screenshot
        takeScreenshot(testcaseNumber);

        driver.quit();




    }
}
