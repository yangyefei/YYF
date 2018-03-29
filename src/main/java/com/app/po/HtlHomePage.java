package com.app.po;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class HtlHomePage {
	public static AndroidDriver driver;
	public static By by=By.id("");
	
	public static void  findElemnet(By by){
		 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}
}
