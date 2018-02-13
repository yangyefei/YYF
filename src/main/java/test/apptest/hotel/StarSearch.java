package test.apptest.hotel;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;
import service.impl.HotelHomePageInitialImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.testng.annotations.DataProvider;
import common.frame.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

public class StarSearch extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private HotelHomePageInitialImpl hotelHomePageInitialImpl = new HotelHomePageInitialImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	private Logger logger = Logger.getLogger("StarSearch.class");
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}
	@Test(description = "by lnn: 星级为3星或者4星或者5星搜索C1309631", groups = { "Base" })
	public void starSearch() throws Exception {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("上海"); 
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     destinationlist.get(0).click(); 
	     
		 //清除酒店首页的成人儿童筛选
	     hotelHomePageInitialImpl.HotelPageAdultsChildFilter(driver);
	     
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_3"))).click();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click(); 
	     try {
	    	 WebElement filterreddot= driver.findElement(By.id("top_bar_filter_red_dot"));
	    	 Assert.assertTrue(filterreddot.isDisplayed());
	    	 logger.info("C1309631:验证筛选3星级成功");
	     } catch (Exception e) 
	    {
	    	logger.info("C1309631:筛选3星级失败");
	    }
	}
	
	@Test(description = "by sxm: C1309630星级分别为<=2星搜索", groups = { "Base" })
	public void twostarSearch() throws Exception {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("上海"); 
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     destinationlist.get(0).click(); 
	     
		 //清除酒店首页的成人儿童筛选
	     hotelHomePageInitialImpl.HotelPageAdultsChildFilter(driver);
	     
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_2"))).click();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click(); 
	     try {
	    	 WebElement filterreddot= driver.findElement(By.id("top_bar_filter_red_dot"));
	    	 Assert.assertTrue(filterreddot.isDisplayed());
	    	 logger.info("C1309630 星级分别为<=2星搜索成功");
	     } catch (Exception e) 
	    {
	    	logger.info("C1309630星级分别为<=2星搜索失败");
	    }
	}
	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}

}
