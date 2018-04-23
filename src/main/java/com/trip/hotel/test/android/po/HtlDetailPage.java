package com.trip.hotel.test.android.po;

import io.appium.java_client.android.AndroidDriver;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HtlDetailPage extends PoBase {
	public static By hotel_description = By.id("view_hotel_detail_description_content"); // 详情页酒店描述
	public static By days = By.id("rooms_top_days"); // 详情页酒店描述
	public static By back = By.id("ivBack"); //返回
	public static By adult_number = By.id("tv_adult_number"); //成人数
	public static By child_number = By.id("tv_child_number"); //儿童数
	public static By book = By.id("hotel_rooms_list_sub_room_btn_book");
	public final static By Favorite = By.id("ivFavorite");
	private final static By.ById Share = new By.ById("ivShare");
	private final static By reviewcount = By.id("view_hotel_detail_review_reviews_count");
	private final static By filter_quick = By.id("hrv_filter_quick");
	private final static By filter_quickcontent = By.className("android.widget.CheckedTextView");
	private final static By filtercontent = By.id("fbl_filter_content");
	
	public static WebElement findFavorite(AndroidDriver<WebElement> driver)
	{
		return PoBase.waitFind(driver, HtlDetailPage.Favorite);
	}
	
	public static WebElement findShare(AndroidDriver<WebElement> driver)
	{
		return PoBase.waitFind(driver, HtlDetailPage.Share);
	}
	
	public static ArrayList<WebElement> findfilter_quick(AndroidDriver<WebElement> driver){
		return (ArrayList<WebElement>) PoBase.waitFind(driver, filter_quick).findElements(filter_quickcontent);
	}
	
	public static ArrayList<WebElement> findfiltercontent(AndroidDriver<WebElement> driver){
		return (ArrayList<WebElement>) PoBase.waitFind(driver, filtercontent).findElements(filter_quickcontent);
	}
	
	public static void toReviews(AndroidDriver<WebElement> driver)
	{
		try {
			Thread.sleep(3000);
			PoBase.waitFind(driver, HtlDetailPage.reviewcount).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static class Reviews{
		private final static By filter = By.id("left_filter_tv");
		private final static By baseRoom = By.id("right_filter_tv");
		private final static By filtertitle = By.id("tv_title");
		private final static By leftfilter = By.id("left_filter_tv");
		private final static By rightfilter = By.id("right_filter_tv");
		
		public static WebElement findFilter(AndroidDriver<WebElement> driver){
			return PoBase.waitFind(driver, filter);
		}
		public static WebElement findbaseRoom(AndroidDriver<WebElement> driver){
			return PoBase.waitFind(driver, baseRoom);
		}
		
		public static ArrayList<WebElement> findfiltertitle(AndroidDriver<WebElement> driver){
			PoBase.waitFind(driver, filtertitle);
			return PoBase.findElements(driver, filtertitle);
		}
		public static ArrayList<WebElement> findbaseRoomtitle(AndroidDriver<WebElement> driver){
			PoBase.waitFind(driver, filtertitle);
			return PoBase.findElements(driver, filtertitle);
		}
		public static String getleftfilter(AndroidDriver<WebElement> driver){
			return PoBase.findElement(driver, leftfilter).getText().toString();
		}
		public static String getrightfilter(AndroidDriver<WebElement> driver){
			return PoBase.findElement(driver, rightfilter).getText().toString();
		}
	}
	public static void BookRoom(AndroidDriver<WebElement> driver)
	{
		ArrayList<WebElement> BookButtons = (ArrayList<WebElement>) driver.findElements(book);
        for (WebElement BookButton : BookButtons) 
        {      	 
	       	 if(BookButton.isDisplayed())
	       	 {
					BookButton.click();
					break;
	       	 }
		}
	}
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
	
	public static class ReviewPage extends PoBase{
		public static By hotel_detail_comment_header_excellent = By.id("hotel_detail_comment_header_excellent");
		public static By hotel_detail_comment_grade = By.id("hotel_detail_comment_grade");
		public static By hotel_detail_comment_cleanliness_progress = By.id("hotel_detail_comment_cleanliness_progress");
		public static By hotel_detail_comment_service_progress = By.id("hotel_detail_comment_service_progress");
		public static By hotel_detail_comment_facility_progress = By.id("hotel_detail_comment_facility_progress");
		public static By hotel_detail_comment_location_progress = By.id("hotel_detail_comment_location_progress");
		public static By left_filter_tv = By.id("left_filter_tv");
		public static By right_filter_tv = By.id("right_filter_tv");
		public static By tag = By.id("tag");
		public static By tv_user_nickname = By.id("tv_user_nickname");
		public static By tv_user_desc = By.id("tv_user_desc");
		public static By tv_date = By.id("tv_date");
		public static By tv_user_score = By.id("tv_user_score");
		public static By tv_room_and_date = By.id("tv_room_and_date");
		public static By tv_content = By.id("tv_content");
		public static By translate = By.id("translate");
		public static By back = By.id("iv_back");
		
		public static void Back(AndroidDriver driver)
		{
			WebElement ele= new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(back));
			ele.click();
		}
		
		public static boolean IsExist(AndroidDriver driver,By by)
		{
			try {
				WebElement ele= new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(by));
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
	}

	
}
