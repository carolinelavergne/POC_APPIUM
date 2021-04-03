package appiumtests;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class JenkinsTest {
	
	public static AndroidDriver<AndroidElement> driver = null;
	
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
    String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
    String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
    String app = System.getenv("BROWSERSTACK_APP_ID");
    
    @Test 
    public void testEchoBox() {
    	System.out.println("testEchoBox - Début");
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	AndroidElement echoBox = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Echo Box")));
    	echoBox.click();
    	
    	AndroidElement saySomething = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("messageInput")));
    	saySomething.sendKeys("Hello world");

    	AndroidElement save = driver.findElement(MobileBy.AccessibilityId("messageSaveBtn"));
    	save.click();
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Hello world")));
    	System.out.println("testEchoBox - Fin");
    }
    @Test
    public void testLoginScreen() {
    	System.out.println("testLoginScreen - Début");
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login Screen")));
    	screen.click();
    
    	WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("username")));

    	username.sendKeys("alice");
    	WebElement password = driver.findElement(MobileBy.AccessibilityId("password"));
    	password.sendKeys("mypassword");
    	WebElement login = driver.findElement(MobileBy.AccessibilityId("loginBtn"));
    	login.click();
    	
    	WebElement loginText = wait.until(ExpectedConditions.presenceOfElementLocated(
    			MobileBy.xpath("//android.widget.TextView[contains(@text, 'You are logged in')]")));
    	
    	assert(loginText.getText().contains("alice"));
    	System.out.println("testLoginScreen - Fin");
    }
    
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
        	System.out.println("Fin JenkinsTest!");
            driver.quit();
        }
    }
}
