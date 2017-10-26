import com.sun.javafx.PlatformUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest extends DriverPath{
	
    WebDriver driver ;
    static String url;
	static String signinerror;
    static{
    try {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\TestVagrant\\Hotels\\Data.properties");
		Properties data = new Properties();
		data.load(fis);
		url=data.getProperty("URL");
		signinerror=data.getProperty("signinerror");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
    
    @BeforeClass
    public void initialize() throws IOException{
    	DriverPath.setDriverPath();
        driver = new ChromeDriver();
    }
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	
        driver.get(url);
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        waitFor(10000);
        try{
        	driver.switchTo().frame("modal_window");
        	driver.findElement(By.id("signInButton")).click();
        }
        catch(Exception e){
         	System.out.println("Unable to click on Sign in button : "+e);
        }
        
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains(signinerror));
        driver.quit();
    }
   
}
