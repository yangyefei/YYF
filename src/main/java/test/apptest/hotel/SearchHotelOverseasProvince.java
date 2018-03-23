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


public class SearchHotelOverseasProvince extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	Logger logger = Logger.getLogger("SearchOverseasHotelName.class");
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}
	@Test(description = "by chr: 根据海外省或城市搜索酒店C1309610", groups = { "Base" })
	public void SearchHotelOverseasProvince() throws Exception {
		String attractionText = null;
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("紐約州");   
	     logger.info("点击第一条搜出的酒店 ");
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     try {
	    	 attractionText=destinationlist.get(0).getText();
		     logger.info(attractionText);
		     Thread.sleep(3000);
	    	 Assert.assertEquals("紐約州",attractionText);
	         logger.info("C1309610:成功找到紐約州");
	     } catch (Exception e) 
	    {
	    	logger.info("C1309610:没有找到紐約州");
	    }
	     
	     try {
	    	 attractionText=destinationlist.get(3).getText();
		     logger.info(attractionText);
		     Thread.sleep(3000);
		     Assert.assertTrue(attractionText.contains("酒店"));
		     Assert.assertTrue(attractionText.contains("紐約州"));
	         logger.info("C1309610:成功找到紐約州酒店- "+attractionText);
	     } catch (Exception e) 
	    {
	    	logger.info("C1309610:没有找到紐約州酒店 - "+attractionText);
	    }
	     
	     try {
		     destinationlist.get(0).click();
		     Thread.sleep(3000);
		     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		        logger.info("成功跳转到列表页");
		    } catch (Exception e) 
		    {
		    	logger.info("跳转到列表页失败");
		    }
	        
	}
	
	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}
