package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.android.po.HtlDetailPage;
import com.trip.hotel.test.android.po.HtlHomePage;
import com.trip.hotel.test.android.po.HtlListPage;
import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
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
import java.util.Calendar;

public class HtlHomeSearch extends BaseTest {

	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;


	//C1309620	搜索海外城市在28天搜索酒店
	@Test
	public void C1309620() {
		String searchKey = "首尔";
		logger.info("--C1309620	搜索海外城市在28天搜索酒店---");
		
		logger.info("--点击输入框 转到输入页面---");
		HtlHomePage.findElement(driver,HtlHomePage.hotel_main_search).click();
		
		logger.info("--在输入框输入 首尔---");
		WebElement input = HtlHomePage.findElement(driver,HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
		input.clear();
		input.sendKeys(searchKey);
		
		logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
		ArrayList<WebElement>  elm= HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);	
		elm.get(0).click();
		
		logger.info("--设置入店日期---");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		String Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckin(driver, Month, (cal.get(Calendar.DATE)+""));
		
		logger.info("--设置离店日期---");
		cal.add(Calendar.DATE, 28);
		Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckout(driver, Month, (cal.get(Calendar.DATE)+""));
		
		logger.info("进入酒店列表");
		HtlHomePage.DoSearch(driver);

		logger.info("进入酒店详情页");
		HtlListPage.ToFirstHotelDetailPage(driver);
		
		logger.info("验证天数是28");
		String Days = HtlDetailPage.getDays(driver);		
		Assert.assertTrue(Days.equals("28"));
		
		HtlDetailPage.backToList(driver);
	}
	
	//C1309620	搜索国内城市在28天搜索酒店
	@Test
	public void C1309619() {
		String searchKey = "上海";
		logger.info("--C1309619	搜索国内城市在28天搜索酒店---");
		
		logger.info("--点击输入框 转到输入页面---");
		HtlHomePage.findElement(driver,HtlHomePage.hotel_main_search).click();
		
		logger.info("--在输入框输入上海---");
		WebElement input = HtlHomePage.findElement(driver,HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
		input.clear();
		input.sendKeys(searchKey);
		
		logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
		ArrayList<WebElement>  elm= HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);	
		elm.get(0).click();
		
		logger.info("--设置入店日期---");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		String Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckin(driver, Month, (cal.get(Calendar.DATE)+""));
		
		logger.info("--设置离店日期---");
		cal.add(Calendar.DATE, 28);
		Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckout(driver, Month, (cal.get(Calendar.DATE)+""));
		
		logger.info("进入酒店列表");
		HtlHomePage.DoSearch(driver);

		logger.info("进入酒店详情页");
		HtlListPage.ToFirstHotelDetailPage(driver);
		
		logger.info("验证天数是28");
		String Days = HtlDetailPage.getDays(driver);		
		Assert.assertTrue(Days.equals("28"));
		
		HtlDetailPage.backToList(driver);
	}
	
	@AfterMethod
	public void afterMethod() {
//		logger.info("---返回搜索首页---");
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
		// 返回Trip首页
		//logger.info("---返回Trip首页---");
		//driver.findElementByClassName("android.widget.ImageButton").click();
		driver.quit();
	}


	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		logger.info("---启动APP---");
		driver = initial.createAndroidReleaseDriver();

		logger.info("进入酒店首页");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
	}

	@AfterClass
	public void afterClass() {
		logger.info("Close Trip App");
		driver.quit();
	}

}
