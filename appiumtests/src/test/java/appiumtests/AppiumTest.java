package appiumtests;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;



public class AppiumTest {

    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://127.0.0.1:4723/wd/hub";

    private static AndroidDriver driver;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "caro");
		caps.setCapability("udid", "4efb89e5");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10 QKQ1.190910.002");
        caps.setCapability("appActivity", "io.cloudgrey.the_app.MainActivity");
		caps.setCapability("fullReset", true); 
		System.out.println(APP);
        caps.setCapability("app", APP);
        driver = new AndroidDriver(new URL(APPIUM), caps);
    }
    @After
    public void tearDown() {
        if (driver != null) {
        	System.out.println("Fin!");
            driver.quit();
        }
    }
    
    @Test 
    public void testEchoBox() {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	WebElement echoBox = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Echo Box")));
    	echoBox.click();
    	
    	WebElement saySomething = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("messageInput")));
    	saySomething.sendKeys("Hello world");

    	WebElement save = driver.findElement(MobileBy.AccessibilityId("messageSaveBtn"));
    	save.click();
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Hello world")));

    	/*	try { 
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}*/
    	//System.out.println(driver.getPageSource());
    	
    }
    
    @Test
    public void testListDemo() {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("List Demo")));
    	screen.click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(
    			MobileBy.AccessibilityId("Altostratus")));
    	
    	
    	PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    	Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 520, 1530);
    	Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
    	Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 520, 490);
    	Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
    	
    	Sequence scroll = new Sequence(finger, 0);
    	scroll.addAction(moveToStart); 
    	scroll.addAction(pressDown);
    	scroll.addAction(moveToEnd);
    	scroll.addAction(pressUp);
    	
    	driver.perform(Arrays.asList(scroll));
    	driver.findElement(MobileBy.AccessibilityId("Stratus"));	
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
    
}