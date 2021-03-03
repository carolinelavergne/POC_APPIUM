package appiumtests;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BrowserStackTest {
	
	public static AndroidDriver<AndroidElement> driver = null;
	static String USERNAME = "caroline200";
	static String ACCESSKEY = "PgRQjroW26yWGyyxrNan";
    private static final String APP = "bs://4036699cabbea52e1baf23af60dff0c84bf631db";

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

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "Xiaomi Redmi Note 7");
        caps.setCapability("os_version","9.0");
    	
        caps.setCapability("appActivity", "io.cloudgrey.the_app.MainActivity");
		caps.setCapability("fullReset", true); 
		
        caps.setCapability("app", APP);
        driver = new AndroidDriver<AndroidElement>(new URL("https://"+USERNAME+":"+ACCESSKEY+"@hub-cloud.browserstack.com/wd/hub"), caps);
		
    }
    @After
    public void tearDown() {
        if (driver != null) {
        	System.out.println("Fin!");
            driver.quit();
        }
    }
}
