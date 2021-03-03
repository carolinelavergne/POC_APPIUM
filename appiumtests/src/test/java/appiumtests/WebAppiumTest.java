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


public class WebAppiumTest {

    private static final String APPIUM = "http://127.0.0.1:4723/wd/hub";

    private static RemoteWebDriver driver;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
		caps.setCapability("deviceName", "caro");
		caps.setCapability("udid", "4efb89e5");
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2"); // UiAutomator2 for android XCUITest for ios
		caps.setCapability("platformVersion", "10 QKQ1.190910.002");
		// download chromedriver : https://chromedriver.storage.googleapis.com/index.html?path=88.0.4324.96/ (le laisser dans download)
		// lancer appium : appium --chromedriver-executable ~/downloads/chromedriver
        driver = new RemoteWebDriver(new URL(APPIUM), caps);
        
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
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	driver.get("https://appiumpro.com");

    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleMenu"))).click();
    	driver.findElement(By.linkText("All Editions")).click();

		try { 
			Thread.sleep(1000);
		} catch (Exception e) {
		}/*
		System.out.println(driver.getPageSource());*/
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/h1")));
System.out.println(element.getText());
    	assert(element.getText().contains("All Editions"));
    }
    
    @Test
    public void testChallenge() {
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
    }
    
}