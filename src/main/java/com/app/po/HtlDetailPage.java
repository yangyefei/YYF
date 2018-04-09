package com.app.po;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.android.AndroidDriver;
public class HtlDetailPage  extends PoBase {
	public static By hotel_description = By.id("view_hotel_detail_description_content"); // 详情页酒店描述
	public static By days = By.id("rooms_top_days"); // 详情页酒店描述
	public static By back = By.id("ivBack"); //返回
	public static By adult_number = By.id("tv_adult_number"); //成人数
	public static By child_number = By.id("tv_child_number"); //儿童数
	public static String getHotelDescription(AndroidDriver driver)
	{
		WebElement descript= new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(hotel_description));
		return descript.getText();
	}
	
	public static String getDays(AndroidDriver driver)
	{
		WebElement d = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(days));
		return d.getText();
	}
	
	public static String getAdultNumber(AndroidDriver driver)
	{
		WebElement d = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(adult_number));
		return d.getText();
	}
	
	public static String getChildNumber(AndroidDriver driver)
	{
		WebElement d = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(child_number));
		return d.getText();
	}
	
	public static void backToList(AndroidDriver driver)
	{
		WebElement d = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(back));
		d.click();
	}
	
	
}
