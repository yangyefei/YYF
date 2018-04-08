package com.app.po;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.android.AndroidDriver;

/**
 * 
 * @author yefeiyang 酒店搜索首页包含：
 * 1.页面的元素
 * 2.查找的方法
 * 3.内部类-子页面
 */
//酒店搜索首页
public class HtlHomePage extends PoBase {
    //页面元素
	public static By hotel_main_search = By.id("rl_stay_in"); // 酒店首页搜索框
	public static By search_button = By.id("tv_search");// 搜索按钮
	public static By checkin = By.id("tvDepartMonth");
	public static By checkout = By.id("tvReturnMonth");

	public static void DoSearch(AndroidDriver driver)
	{
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(search_button))
		.click();
	}
	
	public static void SetCheckin(AndroidDriver driver, String Month, String Day)
	{
		driver.findElement(checkin).click();		
		CalendarPage.SetCheckin(driver, Month, Day);
	}
	
	public static void SetCheckout(AndroidDriver driver, String Month, String Day)
	{
		//driver.findElement(checkout).click();		
		CalendarPage.SetCheckout(driver, Month, Day);
	}
	
	//首页关键字页面SearchEnginePage
	public static class SearchEnginePage {
		public static By hotel_destination_search_keyword = By.id("hotel_destination_search_keyword_import");// 关键字也搜索框
		public static By tvTitle = By.id("tvTitle");// 关键字搜索框联想列表
	}
	
	public static class CalendarPage
	{
		public static By checkin = By.id("hotel_calendar_selected_date_summery_check_in");
		public static By checkout = By.id("hotel_calendar_selected_date_summery_check_out");
		public static By month_title = By.id("tv_title");
		public static By day = By.id("tv_day");
		
		public static void SetCheckin(AndroidDriver driver, String Month, String Day)
		{
			driver.findElement(checkin).click();
			
			int y=0;
			List<WebElement> monthes = driver.findElements(month_title);
			for (WebElement month : monthes) 
			{
				if (month.getText().equals(Month)) 
				{
					y = month.getLocation().getY();
					break;
				}
			}
			
			List<WebElement> days = driver.findElements(day);
			for (WebElement day : days) 
			{
				if (day.getText().equals(Day) && day.getLocation().y>y) 
				{
					day.click();
					break;
				}
			}
		}
		
		public static void SetCheckout(AndroidDriver driver, String Month, String Day)
		{
			driver.findElement(checkout).click();
			
			int y=0;
			List<WebElement> monthes = driver.findElements(month_title);
			for (WebElement month : monthes) 
			{
				if (month.getText().equals(Month)) 
				{
					y = month.getLocation().getY();
					break;
				}
			}
			
			List<WebElement> days = driver.findElements(day);
			for (WebElement day : days) 
			{
				if (day.getText().equals(Day) && day.getLocation().y>y) 
				{
					day.click();
					break;
				}
			}
		}
	}
}
