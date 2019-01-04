import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {

    protected WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {

        //browser = "chrome";

        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "/home/tina/IdeaProjects/testframework-awfulvalentine/src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;

            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/home/tina/IdeaProjects/testframework-awfulvalentine/src/main/resources/chromedriver");
                driver = new ChromeDriver();
                break;

            default:
                throw new Exception("Browser not found");

        }

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

    }

    public String[][] getExcelData(String fileName, String sheetName){
        //String[] arrayExcelData;
        //= new String[5];
        List<String> dataList = new ArrayList<String>();

        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);
            XSSFRow row = sheet.getRow(1);


            int cols = 5;

            for(int i=0; i < cols; i++){

               switch (row.getCell(i).getCellType()) {
                   case Cell.CELL_TYPE_STRING:
                       String cellData = row.getCell(i).getStringCellValue();
                       dataList.add(cellData);
                       //arrayExcelData[i] = row.getCell(i).getStringCellValue();
                       break;
                   case Cell.CELL_TYPE_NUMERIC:
                       double d = row.getCell(i).getNumericCellValue();
                       dataList.add(Double.toString(d));
                       //arrayExcelData[i] = Double.toString(d);
                       break;
                   case Cell.CELL_TYPE_BOOLEAN:
                       boolean b = row.getCell(i).getBooleanCellValue();
                       dataList.add(Boolean.toString(b));
                       //arrayExcelData[i] = Boolean.toString(b);
                       break;
                }
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception ioe) {
            ioe.printStackTrace();
        }

        String[] arrayExcelData = (String[]) dataList.toArray();

        return new String[][] {arrayExcelData};
    }


}
