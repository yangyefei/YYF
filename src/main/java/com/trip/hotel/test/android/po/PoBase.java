package com.trip.hotel.test.android.po;

import io.appium.java_client.android.AndroidDriver;
import okio.Timeout;

import org.apache.log4j.PropertyConfigurator;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class PoBase {
	private static final long TIMEOUT = 30;
	
	public static WebElement findElement(AndroidDriver driver, By by) {
		return new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}
	
    //查找多个元素
	public static ArrayList<WebElement> findElements(AndroidDriver driver, By by) {
		return (ArrayList<WebElement>) driver.findElements(by);
}
	

	public static WebElement waitFind(AndroidDriver driver, By by) {
		return new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by));
	}

}