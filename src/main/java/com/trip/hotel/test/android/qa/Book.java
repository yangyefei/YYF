package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.android.developer.DriverUtils;
import com.trip.hotel.test.android.developer.TouchUtils;
import com.trip.hotel.test.android.po.HtlBookPage;
import com.trip.hotel.test.android.po.HtlDetailPage;
import com.trip.hotel.test.android.po.HtlHomePage;
import com.trip.hotel.test.android.po.HtlListPage;
import com.trip.hotel.test.android.po.PoBase;
import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Book extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonServiceImpl appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	
	@Test(description = "by ylf: C1309754 非中国酒店填写中文姓名", groups = { "Base" })
	public void InputChineseForOverseaHotel() 
	{
		String giveName = "三";
		String surName = "张";
		
		String keyword = "新加坡";
		logger.info("开始搜索"+keyword);
	    HtlHomePage.DoSearch(driver, keyword);
	    
		logger.info("进入酒店详情页");
		HtlListPage.ToFirstHotelDetailPage(driver);
		
		logger.info("滚屏");//for locate book button
		AppCommonServiceImpl acs = new AppCommonServiceImpl();
        acs.swipeToDown(driver);
		
		logger.info("进入订单填写页");
		HtlDetailPage.BookRoom(driver);
		
		logger.info("输入中文名");
		HtlBookPage.InputSurName(driver, surName);
		
		logger.info("验证仅限英文");
		HtlBookPage.Book(driver);
		DriverUtils.assertToast(driver, "僅限英文");
		
	}
		
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		driver = initial.createAndroidReleaseDriver();
		logger.info("初始化成功");
		logger.info("进入酒店首页");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	
	}

}
