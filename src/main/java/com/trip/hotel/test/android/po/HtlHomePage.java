package com.trip.hotel.test.android.po;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
	public static By star_price = By.id("rl_star_price");
	public static By adult = By.id("tv_adult");
	public static By children = By.id("children");

	public static void DoSearch(AndroidDriver driver)
	{
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(search_button))
		.click();
	}
	
	public static void ShowDateAdultChildPage(AndroidDriver driver)
	{
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(adult))
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
	
	
	/**
	 * @param driver
	 * @return AppiumDriver
	 * @description 默认星级无限制
	 */
	public static AppiumDriver Stars_no_limit(AppiumDriver driver) {
		try{
		    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
		    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
		    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return driver;	
		}
		
	}
	
	/**
	 * 
	 * @param driver
	 * @return AppiumDriver
	 * @description 成人1，儿童0
	 */
	public static AppiumDriver Adults_Child_Defult(AppiumDriver driver) {
		try{
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_adult"))).click();
		     //获取刚进入首页时，显示的成人数
	    	 WebElement adult=driver.findElement(By.id("view_plus_guests_adult"));
	    	 WebElement adultplus=adult.findElement(By.xpath("//android.widget.TextView"));
	    	 int adultnums=Integer.parseInt(adultplus.getText());
	     	//获取刚进入首页时，显示的儿童数
	    	 WebElement child=driver.findElement(By.id("view_plus_guests_child"));
	    	 WebElement childplus=child.findElement(By.xpath("//android.widget.TextView"));
	    	 int childnums=Integer.parseInt(childplus.getText());
    	     for(int i=0;i<adultnums-1;i++)
    	     {
    	    	 WebElement adults=driver.findElement(By.id("view_plus_guests_adult"));
	    	     adults.findElement(By.xpath("//android.widget.ImageView")).click();	
    	     }
    	     for(int j=0;j<childnums;j++)
    	     {
    	    	 WebElement adults=driver.findElement(By.id("view_plus_guests_child"));
    	    	 adults.findElement(By.xpath("//android.widget.ImageView")).click();
    	     }  
    	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_apply"))).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			return driver;
			}
		
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
