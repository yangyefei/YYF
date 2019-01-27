package com.trip.hotel.test.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

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

		System.setProperty("webdriver.chrome.driver", "./driver/chromedrivermac");
		WebDriver driver = new ChromeDriver();
//        WebDriver driver = new SafariDriver();
        // System.setProperty("webdriver.ie.driver",
        // "./driver/IEDriverServer.exe");
        // webdriver = new RemoteWebDriver(new
        // URL("http://"+"127.0.0.1"+":4444/wd/hub"),
        // DesiredCapabilities.internetExplorer());
        // DesiredCapabilities ieCapabilities =
        // DesiredCapabilities.internetExplorer();
        // ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
        // true);
        // ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,
        // true);
        // webdriver = new InternetExplorerDriver();

        driver.get("http://www.trip.com");

        driver.findElement(By.id("hotelsCity")).click();
        driver.findElement(By.linkText("Hong Kong")).click();
        // homesearch-btn
        driver.findElement(By.id("homesearch-btn")).click();
        // driver.findElement(By.linkText("请登录")).click();
        Thread.sleep(3000);
        driver.quit();

    }

    @AfterClass
    public void afterClass() {

    }

}
