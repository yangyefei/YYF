package com.trip.hotel.test.android.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class HtlBookPage extends PoBase {
	public static By surname = By.id("ctrip.english:id/view_edit_text");
	public static By givename = By.id("ctrip.english:id/view_edit_text");
	private final static By btnBook = By.id("tv_bottom_select");
	
	public static void InputSurName(AndroidDriver<WebElement> driver, String surname)
	{
		PoBase.waitFind(driver, HtlBookPage.surname).clear();;
		PoBase.waitFind(driver, HtlBookPage.surname).sendKeys(surname);
	}
	
	public static void InputGiveName(AndroidDriver<WebElement> driver, String givename)
	{
		PoBase.waitFind(driver, HtlBookPage.givename).clear();
		PoBase.waitFind(driver, HtlBookPage.givename).sendKeys(givename);
	}
	
	public static void Book(AndroidDriver<WebElement> driver)
	{
		PoBase.waitFind(driver, HtlBookPage.btnBook).click();
	}
}
