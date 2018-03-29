package com.app.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

 public class HtlHomePage {
	public static By by=By.id("");
	
	public static WebElement  findElemnet(AndroidDriver driver, By by){
		return  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public class SearchEnginePage{
		
	}
}
