package test.apptest.hotel;

import static org.testng.AssertJUnit.assertTrue; 
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import jxl.write.DateTime;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

public class BookGuaranteeHotel extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService  appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver ;
    
    @BeforeClass
    public void beforeclass() throws MalformedURLException{
    	driver = initial.appiumAndroidCtripSetUp(driver);
    }
    
    @Test(description = "hotelBySxm", groups = { "Base" })
    public void bookGuaranteeHotel() throws Exception{
    	logger.info("--进入酒店首页--");
    	new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("myctrip_hotel_icon"))).click();
    	//driver.findElement(By.id("myctrip_hotel_icon")).click(); // 进入酒店首页
    	logger.info("--点击搜索框--");
    	driver.findElement(By.id("tv_stay_in")).click();
    	logger.info("--定位目的地搜索框--");
    	WebElement search = new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")));
    	logger.info("--清除目的地搜索框--");
    	search.clear();
    	String keywords = "上海宝安大酒店";
    	logger.info("--对目的地搜索框输入内容--");
    	search.sendKeys(keywords);
    	new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("tvTitle"))).click();
    	
    }
}
