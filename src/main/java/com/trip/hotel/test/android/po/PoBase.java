package com.trip.hotel.test.android.po;

import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class PoBase {
	public static WebElement findElement(AndroidDriver driver, By by) {
		return new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}
    //查找多个元素
	public static ArrayList<WebElement> findElements(AndroidDriver driver, By by) {
		return (ArrayList<WebElement>) driver.findElements(by);
}
	
}