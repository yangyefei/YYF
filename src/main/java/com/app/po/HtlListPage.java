package com.app.po;

import org.openqa.selenium.By;

import com.mysql.jdbc.Driver;

import io.appium.java_client.android.AndroidDriver;

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
	//子页面 Location页面
	public static class locationPage {
	
		public static By show_result = By.id("tv_show_result"); //显示结果 按钮
		public static String  subway="//android.widget.CheckedTextView[@text='地鐵站']";//地铁站
		public static String line5="//android.widget.CheckedTextView[contains(@text,'5號線')]";//5号线
		public static String  xinzhuang="//android.widget.CheckedTextView[contains(@text,'莘莊')]";//地铁莘莊
	}

	
	
	public static class filterPage {
		
		//品牌主菜单
		public static String  brand="(//android.widget.CheckedTextView[@content-desc='HotelFilterViewTitle'])[3]";//品牌
		public static String  sevenDay="//android.widget.CheckedTextView[contains(@text,'7天')]";//7天
		public static By show_result = By.id("tv_show_result"); //显示结果 按钮
	}
}
