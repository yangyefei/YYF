package com.web.test;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class WebTestDemo {
	public WebDriver webdriver;
	@BeforeClass
	public void beforeClass() {

	}

	@Test
	public void OpenUrl() throws InterruptedException, MalformedURLException {

		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		// webdriver = new FirefoxDriver();

		// System.setProperty("webdriver.chrome.driver",
		// "D:\\github\\com.ctrip.hotel.test\\src\\main\\resources\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		
		System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
//		webdriver = new RemoteWebDriver(new URL("http://"+"127.0.0.1"+":4444/wd/hub"), DesiredCapabilities.internetExplorer());
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer(); 
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
		webdriver = new InternetExplorerDriver();
		webdriver.get("trip.com");
//		webdriver.manage().window().maximize();
		webdriver.findElement(By.id("hotelsCity")).click();
		webdriver.findElement(By.linkText("Hong Kong")).click();
		// homesearch-btn
		webdriver.findElement(By.id("homesearch-btn")).click();
		// driver.findElement(By.linkText("请登录")).click();
		Thread.sleep(3000);
		webdriver.quit();

	}

	@AfterClass
	public void afterClass() {

	}

}
