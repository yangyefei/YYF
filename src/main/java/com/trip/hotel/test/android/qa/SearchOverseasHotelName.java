package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.AppCommonService;
import service.InitialService;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class SearchOverseasHotelName extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	Logger logger = Logger.getLogger("SearchOverseasHotelName.class");
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}
	@Test(description = "by chr: 根据海外酒店名称搜索酒店C1309616", groups = { "Base" })
	public void SearchOverseasHotelName() throws Exception {
		String attractionText = null;
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("新加坡希尔顿酒店");   
	     logger.info("点击第一条搜出的酒店 ");
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     try {
	    	 attractionText=destinationlist.get(0).getText();
		     logger.info(attractionText);
		     Thread.sleep(3000);
	    	 Assert.assertEquals("新加坡希爾頓酒店(Hilton Singapore)",attractionText);
	         logger.info("C1309616:成功找到新加坡希爾頓酒店(Hilton Singapore)");
	     } catch (Exception e) 
	    {
	    	logger.info("C1309616:没有找到新加坡希爾頓酒店(Hilton Singapore)");
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
	
	@Test(description = "by chr: 中文输入英文酒店名称搜索C1309624", groups = { "Base" })
	public void HkSearchEn() throws Exception{
		String attractionText1 = null;
		logger.info("----开始测试C1309624中文输入英文酒店名称搜索----");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_home_bottom_account_icon"))).click();
		logger.info("----点击设定----");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("ll_settings"))).click();
		List<WebElement> vaule = driver.findElements(By.id("value"));
		if(vaule.get(0).getText().equals("繁體中文")){
			logger.info("----已经是繁體中文站点----");
			logger.info("----点击返回到公共首页----");
			driver.findElement(By.className("android.widget.ImageButton")).click();
			logger.info("----点击返回到主页----");
			driver.findElement(By.id("rl_home")).click();
		}
		else{
			List<WebElement> sites = driver.findElements(By.id("text"));
		    logger.info("----点击语言----");
		    sites.get(0).click();
		    List<WebElement> languages = driver.findElements(By.id("ibu_baseview_language_item_name"));
		    for (WebElement language : languages) {
		    	if(language.getText().equals("繁體中文")){
		    		logger.info("----是繁體中文站点就点击----");
		    		language.click();
		    		break;
		    	}
			}
            //中文繁体选择 Hong kong
		    ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("ibu_baseview_item_unsupport_language_country_subname"));
		    attractionText1=destinationlist.get(1).getText();
		    logger.info("----已经选择了繁體中文----");
		    logger.info(attractionText1);
		    destinationlist.get(1).click();
		    if(driver.findElement(By.className("android.widget.ImageButton")).isDisplayed()){
				logger.info("----点击返回到公共首页----");
				driver.findElement(By.className("android.widget.ImageButton")).click();
				logger.info("----点击返回到主页----");
				new AppCommonServiceImpl();
				driver = new InitialServiceImpl().appiumAndroidCtripSetUp(driver,"ctrip.english");
				new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_home"))).click();
				driver.findElement(By.id("rl_home")).click();
			}
		}
		logger.info("----点击酒店----");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_stay_in"))).click();
        WebElement e = new WebDriverWait(driver, timeOutInSeconds)
		.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")));
        e.clear();
        String searchKeyWord = "V Hotel Lavender Singapore";
        logger.info("----输入英文酒店名称----"+searchKeyWord);
        e.sendKeys(searchKeyWord);
		WebElement tvTitle = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")));
		try{
			String hotelname = tvTitle.getText();
			assertTrue(hotelname.contains(searchKeyWord));
			logger.info("----C1309624中文输入英文酒店名称搜索成功----");
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}
