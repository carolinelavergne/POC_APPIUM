package appiumtests;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class JenkinsTest2 {
	public static AndroidDriver<AndroidElement> driver = null;

    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
    String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
    String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
    String app = System.getenv("BROWSERSTACK_APP_ID");
    
    @Before
    public void setUp() throws Exception {
    	System.out.println("setUp - Début");
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "Xiaomi Redmi Note 7");
        caps.setCapability("os_version","9.0");
    	
        caps.setCapability("appActivity", "io.cloudgrey.the_app.MainActivity");
		
		// JENKINS
        caps.setCapability("name", "BStack-[Java] Sample Test"); // test buildName
        caps.setCapability("build", buildName); // CI/CD job name using BROWSERSTACK_BUILD_NAME env variable
        caps.setCapability("browserstack.local", true);
        caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
        
        caps.setCapability("app", app);
        driver = new AndroidDriver<AndroidElement>(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
    	System.out.println("setUp - Fin");
        
    }
    @After
    public void tearDown() {
        if (driver != null) {
        	System.out.println("Fin!");
            driver.quit();
        }
    }
    
    @Test
    public void testEditionList() {
    	System.out.println("testEditionList - Début");
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	driver.get("https://appiumpro.com");

    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleMenu"))).click();
    	driver.findElement(By.linkText("All Editions")).click();

		try { 
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/h1")));
    	assert(element.getText().contains("All Editions"));
    	System.out.println("testEditionList - Fin");
    }
    
    @Test
    public void testChallenge() {
    	System.out.println("testChallenge - Début");
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	driver.get("https://appiumpro.com");

    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleMenu"))).click();
    	driver.findElement(By.linkText("Contact")).click();
		WebElement contactEmail = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contactEmail")));
		contactEmail.sendKeys("test@test.com");
		WebElement contactText = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contactText")));
		contactText.sendKeys("contact Text");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Send']"))).click();
		try { 
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/form/div[1]")));
		assert(element.getText().contains("Captcha"));
    	System.out.println("testChallenge - Fin");
    }
    
}