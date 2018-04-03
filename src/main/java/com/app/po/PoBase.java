package com.app.po;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PoBase {
	public static WebElement findElement(AndroidDriver driver, By by) {
		return new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}
    //查找多个元素
	public static ArrayList<WebElement> findElements(AndroidDriver driver, By by) {
		return (ArrayList<WebElement>) driver.findElements(by);
}
}