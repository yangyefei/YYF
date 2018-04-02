package com.app.po;

import java.util.ArrayList;

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
   
	public static By hotel_main_search = By.id("rl_stay_in"); // 酒店首页搜索框
	public static By search_button = By.id("tv_search");// 搜索按钮
    
	//查找单个元素
	public static WebElement findElemnet(AndroidDriver driver, By by) {
	
		return new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}
    //查找多个元素
	public static ArrayList<WebElement> findElemnets(AndroidDriver driver, By by) {
		return (ArrayList<WebElement>) driver.findElements(by);
	}
	
	//首页关键字页面SearchEnginePage
	public static class SearchEnginePage {
		public static By hotel_destination_search_keyword = By.id("hotel_destination_search_keyword_import");// 关键字也搜索框
		public static By tvTitle = By.id("tvTitle");// 关键字搜索框联想列表
	}
}
