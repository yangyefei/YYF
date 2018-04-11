package com.trip.hotel.test.service;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public interface InitialService {
    /**
     * 启动ctrip应用
     *
     * @param driver
     * @return
     * @throws MalformedURLException
     */
    public AndroidDriver<WebElement> appiumAndroidCtripSetUp(AndroidDriver driver, String AppPackage) throws MalformedURLException;


    public WebDriver browserOfInternetSetUp(WebDriver driver) throws Exception;

    /**
     * 启动chrome浏览器
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public WebDriver browserOfChromeSetUp(WebDriver driver) throws Exception;

    /**
     * 启动Firefox浏览器
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public WebDriver browserOfFirefoxSetUp(WebDriver driver) throws Exception;


}
