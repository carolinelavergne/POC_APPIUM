package appiumtests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LambdaTest {
	
	public static RemoteWebDriver driver = null;
	static String username = "caroline.lavergne";
	static String accessKey = "xELM8lkrjYtD0yNFXWGABUH4pYGAK5YRLt8yAeOlzxPFg1WK2G";
		
		
	public static void main(String[] args) {
		try {
			climaginaireTest();
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	

	public static void climaginaireTest() throws MalformedURLException {
		System.out.println("debut");
		DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("build", "First Test");
        capabilities.setCapability("name", "Sample Test");
        
    	capabilities.setCapability("platformName", "Android");
    	capabilities.setCapability("deviceName", "Galaxy S8 Plus");
    	capabilities.setCapability("platformVersion","11");
    	capabilities.setCapability("geoLocation","FR");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
    System.out.println("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub");
		driver = new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities);
		
		
		driver.get("https://www.google.com/");
		/*driver.findElement(By.name("li1")).click();
		driver.findElement(By.name("li2")).click();
		driver.findElement(By.id("sampletodotext")).clear();
		driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
		driver.findElement(By.id("addbutton")).click();*/
		driver.quit();	
		System.out.println("fin LambdaTests");	
	}

}
