package com.web.test;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import common.frame.test.BaseTest;

import service.InitialService;

public class WebTestDemo {
	public WebDriver webdriver;
	@BeforeClass
	public void beforeClass() {

	}

	@Test
	public void OpenUrl() throws InterruptedException {

		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		// webdriver = new FirefoxDriver();

		// System.setProperty("webdriver.chrome.driver",
		// "D:\\github\\com.ctrip.hotel.test\\src\\main\\resources\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();

		System.setProperty("webdriver.ie.driver", "D:\\browsedriver\\IEDriverServer_X64\\IEDriverServer.exe");
		webdriver = new InternetExplorerDriver();
		webdriver.navigate().to("trip.com");
		webdriver.manage().window().maximize();
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
