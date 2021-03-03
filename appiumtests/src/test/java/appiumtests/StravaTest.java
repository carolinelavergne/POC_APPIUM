package appiumtests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class StravaTest {
	
	static AppiumDriver<MobileElement> driver;
		
		
	public static void main(String[] args) {
		try {
			openStrava();
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	public static void openStrava() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "caro");
		cap.setCapability("udid", "4efb89e5");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10 QKQ1.190910.002");
		cap.setCapability("appPackage", "com.strava");
		cap.setCapability("appActivity", "com.strava.SplashActivity");
		cap.setCapability("skipServerInstallation", true);

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url, cap);
		driver.quit();
		System.out.println("fin");
	}

}
