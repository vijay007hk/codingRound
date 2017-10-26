import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends DriverPath{

    WebDriver driver ;
    @BeforeClass
    public void initialize(){
  	  DriverPath.setDriverPath();
      driver = new ChromeDriver();
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        
        driver.get("https://www.cleartrip.com/");
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
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }
   
}
