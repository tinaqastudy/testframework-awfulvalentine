import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;

    String baseURL = "http://awful-valentine.com/";

    //WebElements

    By searchField = By.id("searchinput");
    By cardLink;



    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Page Methods

    //set card name
    public void SetCardName(String cardName){
        this.cardLink = By.linkText(cardName);
    }

    public void OpenCardLink(){
        WebElement selectedCardLink = driver.findElement(cardLink);
        selectedCardLink.click();
    }

    //perform search
    //submit search
    //navigate to purchase forms

}
