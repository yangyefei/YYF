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

public class SearchHotelInOverseasTrain extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	Logger logger = Logger.getLogger("SearchHotelInOverseasTrain.class");
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}
	@Test(description = "by lnn: 根据海外火车站搜索酒店C1309613", groups = { "Base" })
	public void searchHotelInOverseasTrain() throws Exception {
		String trainText = null;
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("首尔火车站");   
	     logger.info("点击第二条搜出首尔火车站 ");
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     try {
	    	 trainText=destinationlist.get(1).getText();
		     logger.info(trainText);
		     Thread.sleep(3000);
	    	 Assert.assertEquals("首爾火車站",trainText);
	         logger.info("C1309613:成功找到海外火车站首尔火车站");
	     } catch (Exception e) 
	    {
	    	logger.info("C1309613:没有找到海外火车站首尔火车站");
	    }
	     destinationlist.get(1).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("etKeyword"))).clear();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("etKeyword")))
         .sendKeys("首爾站韓流民宿"); 
	     logger.info("搜索酒店首尔站韩流民宿 ");
	     ArrayList<WebElement> keywordslist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     try {
	    	 trainText=keywordslist.get(1).getText();
		     logger.info(trainText);
		     Thread.sleep(3000);
	    	 Assert.assertEquals("首爾站韓流民宿(K-Pop Guesthouse Seoul Station)",trainText);
	    	 logger.info("C1309613:成功找到海外酒店首爾站韓流民宿");
	    	 keywordslist.get(1).click();
	    	 logger.info("C1309613:成功跳转到酒店详情页");
	     } catch (Exception e) 
	    {
	    	logger.info("C1309613:没有找到海外酒店首爾站韓流民宿");
	    }
	     
		
	}
	
	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}
