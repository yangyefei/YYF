package com.trip.hotel.test.android.po;

import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author yefeiyang 酒店列表页面
 *
 *         子页面 Location页面
 */
//酒店列表页面
public class HtlListPage extends PoBase {
    //页面元素
	
	public static By filter = By.id("tv_filter"); // 筛选元素
	public static By location = By.id("tv_location"); // 位置 筛选元素
	public static By hotel_address = By.id("view_hotels_item_bottom_address");//酒店底部距离，位置区
	public static By hotel_name = By.id("tv_hotel_name");//酒店名字
	
	public static By hotel_item = By.id("view_hotels_item_container");//酒店项
	
	public static void ToFirstHotelDetailPage(AndroidDriver driver)
	{
		WebElement hotel= new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(hotel_item));
		hotel.click();		
	}
	
	//子页面 Location页面
	public static class locationPage {
	
		public static By show_result = By.id("tv_show_result"); //显示结果 按钮
		public static String  subway="//android.widget.CheckedTextView[contains(@text,'地鐵站')]";//地铁站
		public static String line5="//android.widget.CheckedTextView[contains(@text,'5號線')]";//5号线
		public static String  xinzhuang="//android.widget.CheckedTextView[contains(@text,'莘莊')]";//地铁莘莊
	}

	public static class filterPage {
		
		//品牌主菜单
		public static String  brand="(//android.widget.CheckedTextView[@content-desc='HotelFilterViewTitle'])[3]";//品牌
		public static String  sevenDay="//android.widget.CheckedTextView[contains(@text,'7天')]";//7天
		public static By show_result = By.id("tv_show_result"); //显示结果 按钮
	}
	
	public static void ShowDateAdultChildPage(AndroidDriver driver)
	{
		WebElement d = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("ll_check_date")));
		d.click();
	}
	public static class DateAdultChildPage {
		
		public static void ShowAdultChildPage(AndroidDriver driver)
		{
			WebElement d = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("ll_choose_adult_children")));
			d.click();
		}
		
		public static void DoSearch(AndroidDriver driver)
		{
			WebElement d = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_search")));
			d.click();
		}
	}
	
	public static class AdultChildPage {
		public static By numbers = By.id("plus_minus_number_view_number");
		
		public static void SetAdultChildNumber(AndroidDriver driver, int AdultNumber, int ChildNumber)
		{
	    	 List<WebElement> number=driver.findElements(By.id("plus_minus_number_view_number"));
	    	 int adult = Integer.parseInt(number.get(0).getText());
	    	 int child = Integer.parseInt(number.get(1).getText());
	    	 
	    	 List<WebElement> numberPlus = driver.findElements(By.id("plus_minus_number_view_plus"));
	    	 List<WebElement> numberMinus = driver.findElements(By.id("plus_minus_number_view_minus"));
	    	 
	    	 if (adult < AdultNumber) 
	    	 {
				for( ; adult < AdultNumber; adult++)
				{
					numberPlus.get(0).click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
	    	 else if (adult > AdultNumber) 
	    	 {
	 				for( ; adult>AdultNumber ;adult--)
	 				{
	 					numberMinus.get(0).click();
	 					try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	 				}
			 }
	    	 
	    	 if (child < ChildNumber) 
	    	 {
	    		 for( ; child<ChildNumber ; child++)
	    		 {
	    			 numberPlus.get(1).click();
	    			 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		 }
	    	 }
	    	 else 
	    	 {
	    		 if(child > ChildNumber)
	    		 {
	    			 for( ; child>ChildNumber ; child--)
	    			 {
	    				 numberMinus.get(1).click();
	    				 try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			 }
	    		 }
	    	 }
		}	
		
		public static void SetChildAge(AndroidDriver driver, int i, int index )
		{
			List<WebElement> children = driver.findElements(By.id("view_child_item"));
			children.get(i-1).click();
			
			List<WebElement> childAges = null;
			

			if(index>7)
			{
		   	     AppCommonServiceImpl acs = new AppCommonServiceImpl();
		         acs.swipeToDown(driver);//滚屏
		         
		         if(index>14)
		        	 acs.swipeToDown(driver);//再滚次屏
		         
		         childAges = driver.findElements(By.id("tv_age_index"));
		 		 for (WebElement childAge : childAges) 
		 		 {
					if (childAge.getText().contains(index+"")) 
					{
						childAge.click();
						break;
					}
				 }
		         //System.out.println("当前最小年龄" + childAges.get(0).getText());
		         //System.out.println("当前最大年龄" + childAges.get(childAges.size()-1).getText());
			}
			else
			{
				childAges = driver.findElements(By.id("tv_age_index"));
				WebElement ChildAge = childAges.get(index);
				ChildAge.click();
		         //System.out.println("当前最小年龄" + childAges.get(0).getText());
		         //System.out.println("当前最大年龄" + childAges.get(childAges.size()-1).getText());
			}

		}
		
		public static void Confirm(AndroidDriver driver)
		{
			WebElement d = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_apply")));
			d.click();
		}
		
	}
}
