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
import junit.framework.TestCase;

public class BrowserStackTest extends TestCase {
	
	public static AndroidDriver<AndroidElement> driver = null;
	static String username = "caroline201";
	static String accessKey = "8op9B2aALi1qT85NPMBu";
    private static final String APP = "bs://4036699cabbea52e1baf23af60dff0c84bf631db";
    
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "Xiaomi Redmi Note 7");
        caps.setCapability("os_version","9.0");
    	
        caps.setCapability("appActivity", "io.cloudgrey.the_app.MainActivity");
		
        caps.setCapability("app", APP);
        driver = new AndroidDriver<AndroidElement>(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
    }
    
    
    @Test 
    public void testEchoBox() {

    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	AndroidElement echoBox = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Echo Box")));
    	echoBox.click();
    	
    	AndroidElement saySomething = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("messageInput")));
    	saySomething.sendKeys("Hello world");

    	AndroidElement save = driver.findElement(MobileBy.AccessibilityId("messageSaveBtn"));
    	save.click();
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Hello world")));
    }
    
    @Test
    public void testLoginScreen() {
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
    }
   
    @After
    public void tearDown() {
        if (driver != null) {
        	System.out.println("Fin BrowserStack!");
            driver.quit();
        }
    }
}
