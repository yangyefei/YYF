package com.trip.hotel.test.android.qa.self;//package <set your test package>;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;


public class IOSUI {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    protected AppiumDriver<MobileElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    @HowToUseLocators
    @iOSXCUITFindBy(xpath = "//*[@text='Trip.com']")
//    @WithTimeout(time = 500, chronoUnit = ChronoUnit.SECONDS)
    MobileElement trip;
    @iOSXCUITFindBy(accessibility = "home icon 0")
    MobileElement hotel;
    /////*[@class='UIAImage']
    @iOSXCUITFindBy(xpath = "//*[@text='北京']")
    MobileElement nearbyhotel;

    @iOSXCUITFindBy(accessibility = "城市、機場、區域、地標或酒店名稱")
    MobileElement search;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability("newCommandTimeout", 30);

        dc.setCapability("autoAcceptAlerts", true);
        dc.setCapability(" autoDismissAlerts", true);
        dc.setCapability(MobileCapabilityType.UDID, "ef4e867909ce8bd2fb460ccba04baf550fd97c82");
        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Test
    public void testUntitled() throws InterruptedException {
        trip.click();
        hotel.click();
        nearbyhotel.click();
        search.sendKeys("猪");
        Thread.sleep(60000);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}