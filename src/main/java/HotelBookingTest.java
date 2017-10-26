import com.sun.javafx.PlatformUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelBookingTest extends DriverPath  {

    protected WebDriver driver;
    static String url;
    static String localitytextbox;
    static String travellersection;
    static{
    try {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\TestVagrant\\Hotels\\Data.properties");
		Properties data = new Properties();
		data.load(fis);
		url=data.getProperty("URL");
		localitytextbox=data.getProperty("localitytextbox");
		travellersection=data.getProperty("travellersection");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
   }

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
   
        
  @BeforeClass
  public void initialize(){
	  DriverPath.setDriverPath();
	  driver = new ChromeDriver();
	  PageFactory.initElements(driver,this);
  }
  
    @Test
    public void shouldBeAbleToSearchForHotels() {
    	
    	driver.get(url);
        waitFor(5000);
        hotelLink.click();
        waitFor(5000);
        
        localityTextBox.sendKeys(localitytextbox);

        new Select(travellerSelection).selectByVisibleText(travellersection);
        searchButton.click();

        driver.quit();

    }
       
}
